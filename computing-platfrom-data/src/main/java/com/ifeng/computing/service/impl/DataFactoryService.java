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

package com.ifeng.computing.service.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ifeng.computing.orm.domain.EventLogData;
import com.ifeng.computing.orm.domain.LogData;
import com.ifeng.computing.orm.domain.NewsItem;
import com.ifeng.computing.orm.mybatis.mapper.IDataFactoryMapper;
import com.ifeng.computing.service.IDataFactoryService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 29, 2014
 */
@Service("dataFactoryService")
public class DataFactoryService implements IDataFactoryService {
	private static final Logger log = LoggerFactory.getLogger(DataFactoryService.class);

	public int createEventLog(EventLogData data) {
		int result = -1;
		try {
			
			dataFactoryMapper.createEventLog(data);
			
			result = 0;
			
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		} 
		
		return result;
	}

	@Override
	public List<LogData> readLogData(Map<String, Object> map) {
		return dataFactoryMapper.readLogData(map);
	}

	@Override
	public List<NewsItem> readNewsByUserId(Map<String, Object> map) {
		return dataFactoryMapper.readNewsByUserId(map);
	}
	
	@Autowired
	private IDataFactoryMapper dataFactoryMapper;


}
