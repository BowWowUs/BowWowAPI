package com.pets.bowwow.domain.batch.company;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.pets.bowwow.domain.batch.company.tasklet.CompanyTasklet;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
@RequiredArgsConstructor
public class CompanyJobConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final CompanyTasklet companyTasklet;

    @Bean
    public Job companyJob(){
        return new JobBuilder("COMPANY_JOB", jobRepository)
            .start(companyStep())
            .build();

    }

    @Bean
    public Step companyStep(){
        return new StepBuilder("COMPANY_STEP", jobRepository)
            .tasklet(companyTasklet, platformTransactionManager)
            .build();
    }

}
