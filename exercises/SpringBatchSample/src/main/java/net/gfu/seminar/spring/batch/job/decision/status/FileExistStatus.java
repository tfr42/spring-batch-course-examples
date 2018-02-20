package net.gfu.seminar.spring.batch.job.decision.status;

import org.springframework.batch.core.job.flow.FlowExecutionStatus;

public class FileExistStatus extends FlowExecutionStatus {

	public FileExistStatus() {
		super("FILE EXIST");

	}

}
