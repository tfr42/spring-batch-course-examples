package net.gfu.seminar.spring.batch.job.step.listener;

import org.springframework.batch.core.ExitStatus;

public class SkippedExistStatus extends ExitStatus {

	public SkippedExistStatus() {
		super("SKIPPED");
	}

}
