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

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ifeng.computing.commons.util.TDateUtils;
import com.ifeng.computing.orm.domain.EventLogData;
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
	
	@Autowired
	private IDataFactoryService dataFactoryService;
	
	
	private static final String DATE_FORMATTER = "yyyyMMddHHmmss";
	
}
