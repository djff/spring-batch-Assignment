package com.djff.springbatchrestful.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Service
public class StudentBatchService {
    final JobLauncher jobLauncher;
    final Job job;

    public StudentBatchService(JobLauncher jobLauncher, Job job) {
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    public void run() throws Exception{
        JobParameters jobParameters = new JobParameters();
        jobParameters.getParameters().put("startTime", new JobParameter(System.currentTimeMillis()));
        System.out.println(jobParameters.getParameters());
        System.out.println("************ job ************: " + job.getName());
        jobLauncher.run(job, jobParameters);
    }
}
