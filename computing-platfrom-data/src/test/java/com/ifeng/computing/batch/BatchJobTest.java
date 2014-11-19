/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ifeng.computing.batch;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 29, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class BatchJobTest {

	private static final Logger log = LoggerFactory.getLogger(BatchJobTest.class);
	
	@Test
	public void create() {
		
		try {
			JobExecution execution = jobLauncher.run(reportJob, new JobParameters());
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	@Test
	public void createNewsJob() {
		
		try {
			JobExecution execution = jobLauncher.run(createNewsJob, new JobParameters());
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	
	@Test
	public void logDataFormatJob() {
		
		try {
			JobExecution execution = jobLauncher.run(logDataFormatJob, new JobParameters());
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	@Test
	public void logDataFormatJobWithParameters() {
		
		try {
			Map<String,JobParameter> jobParameters = new HashMap<String,JobParameter>();
			jobParameters.put("inputFile", new JobParameter("data/log-data.json"));
			
			JobExecution execution = jobLauncher.run(logDataFormatJob, new JobParameters(jobParameters));
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	@Test
	public void partitionReadNewsItemJob() {
		
		try {
			
			JobExecution execution = jobLauncher.run(partitionReadNewsItemJob, new JobParameters());
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	

	@Test
	public void logDataProcessor() {
		
		try {
			
			JobExecution execution = jobLauncher.run(logDataProcessor, new JobParameters());
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	@Test
	public void parallelWriteJob() {
		
		try {
			
			JobExecution execution = jobLauncher.run(parallelWriteJob, new JobParameters());
			
			log.info(">>>>>>>>>>>>>>> result: " +execution.getStatus());
			
			Assert.assertEquals(BatchStatus.COMPLETED, execution.getStatus());
			
		} catch (JobExecutionAlreadyRunningException e) {
			log.error(e.getMessage(), e);
		} catch (JobRestartException e) {
			log.error(e.getMessage(), e);
		} catch (JobInstanceAlreadyCompleteException e) {
			log.error(e.getMessage(), e);
		} catch (JobParametersInvalidException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	
	@Autowired
	private JobLauncher jobLauncher;
	@Autowired
	private Job reportJob;
	
	@Autowired
	private Job createNewsJob;
	
	@Autowired
	private Job logDataFormatJob;
	
	@Autowired
	private Job partitionReadNewsItemJob;
	
	@Autowired
	private Job logDataProcessor;
	
	@Autowired
	private Job parallelWriteJob;
	
	
	
}
