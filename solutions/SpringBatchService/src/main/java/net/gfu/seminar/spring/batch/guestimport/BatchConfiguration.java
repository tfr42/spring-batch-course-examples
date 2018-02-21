package net.gfu.seminar.spring.batch.guestimport;

import javax.sql.DataSource;

import net.gfu.seminar.spring.batch.PrintTasklet;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.SkipListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileParseException;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableBatchProcessing
@PropertySource("classpath:jdbc.properties")
public class BatchConfiguration {
	@Value("${jdbc.driverClassName}")
	private String driverClassName;
	@Value("${jdbc.url}")
	private String url;
	@Value("${jdbc.username}")
	private String username;
	@Value("${jdbc.password}")
	private String password;

	@Bean
	public ItemReader<Guest> reader() {
		FlatFileItemReader<Guest> reader = new FlatFileItemReader<Guest>();
		reader.setResource(new ClassPathResource("sample-data-with-error.csv"));
		reader.setLineMapper(new DefaultLineMapper<Guest>() {
			{
				setLineTokenizer(new DelimitedLineTokenizer() {
					{
						setNames(new String[] { "firstName", "lastName" });
					}
				});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Guest>() {
					{
						setTargetType(Guest.class);
					}
				});
			}
		});
		return reader;
	}

	@Bean
	public ItemProcessor<Guest, Guest> processor() {
		return new GuestItemProcessor();
	}

	@Bean
	public ItemWriter<Guest> writer(DataSource dataSource) {
		JdbcBatchItemWriter<Guest> writer = new JdbcBatchItemWriter<Guest>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Guest>());
		writer.setSql("INSERT INTO guests (first_name, last_name) VALUES (:firstName, :lastName)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job importGuestJob(JobBuilderFactory jobs, Step step1, Step step2) {
		return jobs.get("importGuestJob").incrementer(new RunIdIncrementer())
				.flow(step1).next(step2).end().build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory,
			ItemReader<Guest> reader, ItemWriter<Guest> writer,
			ItemProcessor<Guest, Guest> processor,
			StepExecutionListener stepListener,
			ItemReadListener<Guest> readListener,
			ItemWriteListener<Guest> writeListener,
			SkipListener<Guest, Guest> skipListener,
			ThreadPoolTaskExecutor executor) {
		return stepBuilderFactory.get("step1").<Guest, Guest> chunk(5)
				.reader(reader).listener(readListener).processor(processor)
				.writer(writer).listener(writeListener).faultTolerant()
				.retry(DataIntegrityViolationException.class)
				.skip(FlatFileParseException.class)
				.skip(DataIntegrityViolationException.class).skipLimit(3)
				.listener(skipListener).listener(stepListener)
				.build();
	}

	@Bean
	public Step step2(StepBuilderFactory stepBuilderFactory,
			Tasklet printTasklet) {
		return stepBuilderFactory.get("step2").tasklet(printTasklet).build();
	}

	@Bean
	public StepExecutionListener stepListener() {
		return new GuestImportStepListener();
	}

	@Bean
	public ItemReadListener<Guest> readListener() {
		return new GuestImportReadListener();
	}

	@Bean
	public ItemWriteListener<Guest> writeListener() {
		return new GuestImportWriteListener();
	}

	@Bean
	public SkipListener<Guest, Guest> skipListener() {
		return new GuestImportSkipListener();
	}

	@Bean
	public ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(5);
		executor.setMaxPoolSize(5);
		return executor;
	}

	@Bean
	public Tasklet printTasklet(
			@Value("#{systemProperties['user.name']}") String user) {
		PrintTasklet tasklet = new PrintTasklet();
		tasklet.setMessage("Chunk processed for user " + user);
		return tasklet;
	}

	@Bean
	public JobRepository jobRepository(DataSource dataSource,
			PlatformTransactionManager transactionManager) throws Exception {
		JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
		jobRepositoryFactoryBean.setDatabaseType("hsql");
		jobRepositoryFactoryBean.setDataSource(dataSource);
		jobRepositoryFactoryBean.setTransactionManager(transactionManager);
		return jobRepositoryFactoryBean.getObject();
	}

	@Bean
	public DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

}