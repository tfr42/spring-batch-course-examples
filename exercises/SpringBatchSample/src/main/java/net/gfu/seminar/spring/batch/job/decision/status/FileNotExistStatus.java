package net.gfu.seminar.spring.batch.job.decision.status;

import org.springframework.batch.core.job.flow.FlowExecutionStatus;

public class FileNotExistStatus extends FlowExecutionStatus {

	public FileNotExistStatus() {
		super("FILE NOT EXIST");

	}

}
