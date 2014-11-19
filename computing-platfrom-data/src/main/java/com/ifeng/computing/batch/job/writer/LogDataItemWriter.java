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

package com.ifeng.computing.batch.job.writer;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import com.ifeng.computing.orm.domain.LogData;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 3, 2014
 */
public class LogDataItemWriter implements ItemWriter<LogData> {

	private static final Logger log = LoggerFactory.getLogger(LogDataItemWriter.class);
	
	@Override
	public void write(List<? extends LogData> items) throws Exception {
		logDatas.addAll(items);
		
		
		log.debug("Data size: {}", items.size());
	}
	
	

	public List<LogData> getLogDatas() {
		return logDatas;
	}


	private List<LogData> logDatas = new ArrayList<LogData>();
	
	
	
}
