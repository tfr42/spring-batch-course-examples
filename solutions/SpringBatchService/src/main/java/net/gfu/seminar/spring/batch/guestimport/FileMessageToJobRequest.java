package net.gfu.seminar.spring.batch.guestimport;

import java.io.File;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.integration.launch.JobLaunchRequest;
import org.springframework.integration.annotation.Transformer;

/**
 * Launches a Job and passes a file name as parameter.
 * Uses Spring Integration.
 */
public class FileMessageToJobRequest {
    private Job job;
    private String fileParameterName;

    public void setFileParameterName(String fileParameterName) {
        this.fileParameterName = fileParameterName;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    @Transformer
    public JobLaunchRequest toRequest(File file) {
        JobParametersBuilder jobParametersBuilder =
              new JobParametersBuilder();

        jobParametersBuilder.addString(fileParameterName,
                                      file.getAbsolutePath());

        return new JobLaunchRequest(job, jobParametersBuilder.toJobParameters());
    }
}
