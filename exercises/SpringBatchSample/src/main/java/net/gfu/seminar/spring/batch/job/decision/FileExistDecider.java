package net.gfu.seminar.spring.batch.job.decision;

import net.gfu.seminar.spring.batch.job.decision.status.FileExistStatus;
import net.gfu.seminar.spring.batch.job.decision.status.FileNotExistStatus;
import org.apache.log4j.Logger;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.job.flow.FlowExecutionStatus;
import org.springframework.batch.core.job.flow.JobExecutionDecider;

public class FileExistDecider implements JobExecutionDecider {

	protected Logger log = Logger.getLogger(FileExistDecider.class);

	private boolean isFileExist = true;

	public boolean isFileExist() {
		return isFileExist;
	}

	public void setFileExist(boolean isFileExist) {
		this.isFileExist = isFileExist;
	}

	@Override
	public FlowExecutionStatus decide(JobExecution jobExecution,
			StepExecution stepExecution) {

		log.debug("FlowExecutionStatus called");

		if (isFileExist)
			return new FileExistStatus();
		else
			return new FileNotExistStatus();

	}

}
