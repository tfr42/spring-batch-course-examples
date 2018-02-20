package net.gfu.seminar.spring.batch.job.step.tasklet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class GenerateReportTasklet implements Tasklet {

	protected Log log = LogFactory.getLog(GenerateReportTasklet.class);

	@Override
	public RepeatStatus execute(StepContribution contribution,
			ChunkContext chunkContext) throws Exception {

		log.debug("GenerateReportTasklet called");

		return RepeatStatus.FINISHED;
	}

}
