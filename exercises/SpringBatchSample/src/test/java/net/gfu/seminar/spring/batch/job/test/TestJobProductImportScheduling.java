package net.gfu.seminar.spring.batch.job.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/context/batch-context.xml")
public class TestJobProductImportScheduling {
	
	@Test
	public void testJobProductImportScheduler() {		
		
	}
}
