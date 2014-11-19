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

package com.ifeng.computing.batch.job.mapper;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.JsonLineMapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifeng.computing.orm.domain.LogData;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 3, 2014
 */
public class LogDataJsonLineMapper implements LineMapper<LogData> {
	
	private static final Logger log = LoggerFactory.getLogger(LogDataJsonLineMapper.class);

	@Override
	public LogData mapLine(String line, int lineNumber) throws Exception {
		Map<String, Object> productAsMap = delegate.mapLine(line, lineNumber);
		
		
		LogData data = new LogData();
		log.debug("LineNumber: {}, --> {}", lineNumber, line);
		
		data = objectMapper.readValue(line, LogData.class);
		
		
		return data;
	}

	public void setDelegate(JsonLineMapper delegate) {
		this.delegate = delegate;
	}

	private JsonLineMapper delegate;
	private static ObjectMapper objectMapper = new ObjectMapper();

}