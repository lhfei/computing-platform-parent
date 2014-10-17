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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifeng.computing.commons.util.TDateUtils;
import com.ifeng.computing.orm.domain.EventLogData;
import com.ifeng.computing.orm.domain.LogData;
import com.ifeng.computing.orm.domain.NewsItem;
import com.ifeng.computing.service.IDataFactoryService;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 29, 2014
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/application-context.xml")
public class DataFactoryServiceTest {
	
	Logger log = LoggerFactory.getLogger(DataFactoryServiceTest.class);

	@Test
	public void create() {
		String version = "10";
		
		EventLogData data = new EventLogData();
		data.setItemId("001");
		data.setTime(TDateUtils.getTime(DATE_FORMATTER));
		data.setType("0");
		data.setUserId("u01");
		data.setVersion(version);
		
		int result = dataFactoryService.createEventLog(data);
		
		// assert the result: 0 is success, and the other wise is -1
		Assert.assertFalse(result < 0);
		
	}
	
	@Test
	public void readLogData() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 0);
		map.put("limit", 200);
		
		List<LogData> list = dataFactoryService.readLogData(map);
		
		Assert.assertNotNull(list);
		
		log.info("Result size {}", list.size());
	}
	
	@Test
	public void readNewsByUserId() {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("start", 0);
		map.put("limit", 200);
		map.put("pId", 3792);
		
		List<NewsItem> list = dataFactoryService.readNewsByUserId(map);
		
		Assert.assertNotNull(list);
		
		log.info("Result size {}", list.size());
	}
	
	@Test
	public void buildPushData() {
	
		File file = null;
		PrintWriter pw = null;
		OutputStream out = null;
		
		try {
			file = new File("F:BIG_EVENT_LOG2.txt");
			pw = new PrintWriter(new File("F:/HUGE_FILE.txt"));
			
			int start = 0;
			List<NewsItem> newsList = new ArrayList<NewsItem>();
			List<LogData> logList = new ArrayList<LogData>();
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("limit", LIMIT_SIZE);
			
			
			int steps = (MAX_USER_SIZE % LIMIT_SIZE == 0) ? (MAX_USER_SIZE / LIMIT_SIZE) : (MAX_USER_SIZE / LIMIT_SIZE + 1);
					
			Map<String,Object> newsMap = new HashMap<String,Object>();
			newsMap.put("start", 0);
			newsMap.put("limit", NEWS_ITEM_SIZE);
			
			pw.append("[");
			
			ObjectMapper mapper = new ObjectMapper();
			for (int i = 0; i < steps; i++) {
				start = i * LIMIT_SIZE;
				map.put("start", start);
				
				log.info("Step {} loading ... , Start index is [{}]", i, start);
				
				logList = dataFactoryService.readLogData(map);
				
				for(int j=0, length = logList.size(); j<length; j++) {
					LogData data = logList.get(j);
					
					newsMap.put("pId", data.getpId());
					newsList = dataFactoryService.readNewsByUserId(newsMap);
					
					data.setNewsItem(newsList);
					
					if(j == 0){
						pw.append(mapper.writeValueAsString(data));
					}else{
						pw.append(",");
						pw.append(mapper.writeValueAsString(data));
					}
					
					if(j % 50 == 0)
						pw.flush();
					
				}
				
				/*out = new BufferedOutputStream(new FileOutputStream(file, true), BUFFER_SIZE);
				mapper.writeValue(out, logList);*/
				
			}
			
			pw.append("]");
			
		} catch (JsonGenerationException e) {
			log.error(e.getMessage(), e);
		} catch (JsonMappingException e) {
			log.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		} finally {
			if(out != null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			if(pw != null){
				pw.flush();
				pw.close();
			}
			
		}
		
		
		
	}
	
	
	@Autowired
	private IDataFactoryService dataFactoryService;
	
	
	// PRIVATE FIELDS
	private static final String DATE_FORMATTER = "yyyyMMddHHmmss";
	private static final int LIMIT_SIZE = 10;
	private static final int MAX_USER_SIZE = 3 * 10;
	private static final int NEWS_ITEM_SIZE = 20;
	private static final int BUFFER_SIZE = 1024 * 1024;
	
}
