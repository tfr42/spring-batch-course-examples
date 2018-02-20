package net.gfu.seminar.spring.batch;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:batch-config.xml",
		"classpath:helloWorldJob.xml", "classpath:batch-test-config.xml"})
@ActiveProfiles("inmemory")
public class PrintTaskletTest {

	@Autowired
	private JobLauncherTestUtils jobLauncherTestUtils;

	@Test
	public void testHelloWorldJob() throws Exception {
		ExitStatus jobExecution = jobLauncherTestUtils.launchJob().getExitStatus();
		assertEquals( "COMPLETED", jobExecution.getExitCode() );
	}

}
