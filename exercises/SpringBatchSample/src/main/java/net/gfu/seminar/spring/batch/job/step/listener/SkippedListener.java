package net.gfu.seminar.spring.batch.job.step.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

public class SkippedListener implements StepExecutionListener {

	private boolean skipped = false;

	public boolean isSkipped() {
		return skipped;
	}

	public void setSkipped(boolean skipped) {
		this.skipped = skipped;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		if (skipped)
			return new SkippedExistStatus();
		else
			return stepExecution.getExitStatus();
	}

}
