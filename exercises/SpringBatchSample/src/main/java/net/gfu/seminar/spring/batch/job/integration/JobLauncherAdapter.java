package net.gfu.seminar.spring.batch.job.integration;

import java.util.Calendar;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;

public class JobLauncherAdapter {

	protected Log log = LogFactory.getLog(JobLauncherAdapter.class);

	private JobLauncher jobLauncher;
	private Job job;

	public void setJobLauncher(JobLauncher jobLauncher) {
		this.jobLauncher = jobLauncher;
	}

	public void launch() throws Exception {

		log.debug("JobLauncherAdapter launch job ... ");

	}

	public void setJob(Job job) {
		this.job = job;
	}

	protected JobParameters createJobParameters() {
		Calendar calendar = Calendar.getInstance();
		return new JobParametersBuilder().addDate("date", calendar.getTime())
				.toJobParameters();
	}

}
