package com.pets.bowwow.domain.batch.code;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.pets.bowwow.domain.batch.code.tasklet.CodeTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CodeJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final CodeTasklet codeTasklet;

    @Bean
    public Job codeJob(){
        return new JobBuilder("CODE_JOB", jobRepository)
            .start(codeStep())
            .build();
    }

    @Bean
    public Step codeStep(){
        return new StepBuilder("CODE_STEP", jobRepository)
            .tasklet(codeTasklet, platformTransactionManager)
            .build();
    }


}
