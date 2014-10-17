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

package com.ifeng.computing.orm.domain;

import java.util.List;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Oct 16, 2014
 */
public class LogData {

	public int getpId() {
		return pId;
	}
	public void setpId(int pId) {
		this.pId = pId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	public List<NewsItem> getNewsItem() {
		return newsItem;
	}
	public void setNewsItem(List<NewsItem> newsItem) {
		this.newsItem = newsItem;
	}


	private int pId;
	private String userId;
	private String time;
	
	private List<NewsItem> newsItem;
}
