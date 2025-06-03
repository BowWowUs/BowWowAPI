package com.pets.bowwow.domain.admin;

import java.time.LocalDateTime;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/batch/remote")
public class BatchRemoteController {

    private final JobLauncher jobLauncher;

    private final JobRegistry jobRegistry;

    @GetMapping("/excute/{job_name}")
    public ResponseEntity<Void> excuteJob(@PathVariable(name = "job_name", required = true) String jobName){

        log.warn("{} JOB START!!!", jobName);

        try {
            Job simpleJob = jobRegistry.getJob(jobName);
            JobParametersBuilder jobParam = new JobParametersBuilder().addLocalDateTime("TODAY_DATE", LocalDateTime.now());

            jobLauncher.run(simpleJob, jobParam.toJobParameters());
        } catch (Exception e) {
            log.error("", e);
        }

        log.warn("{} JOB END!!!", jobName);

        return ResponseEntity.ok().build();
    }

}
