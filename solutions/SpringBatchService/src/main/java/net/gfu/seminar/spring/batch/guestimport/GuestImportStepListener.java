package net.gfu.seminar.spring.batch.guestimport;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class GuestImportStepListener implements StepExecutionListener {

	@Override
	public ExitStatus afterStep(StepExecution arg0) {
		System.out.println("Items read: " + arg0.getReadCount());
		System.out.println("Items wrote: " + arg0.getWriteCount());
		System.out.println("Items skipped: " + arg0.getSkipCount());
		System.out.println("Commits : " + arg0.getCommitCount());
		System.out.println("Summary: " + arg0.getSummary());
		// TODO Auto-generated method stub
		return arg0.getExitStatus();
	}

	@Override
	public void beforeStep(StepExecution arg0) {
		System.out.println("Step '" +arg0.getStepName() + "' finished with status:"+ arg0.getStatus());
		// TODO Auto-generated method stub
		
	}

}
