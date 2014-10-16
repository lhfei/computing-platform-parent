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

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Oct 8, 2014
 */
public class NewsItem {
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getpId() {
		return pId;
	}
	public void setpId(String pId) {
		this.pId = pId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSimScore() {
		return simScore;
	}
	public void setSimScore(String simScore) {
		this.simScore = simScore;
	}
	public String getHotLevel() {
		return hotLevel;
	}
	public void setHotLevel(String hotLevel) {
		this.hotLevel = hotLevel;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
	private int id;
	private String pId;			// referenced id
	
	private String itemId;
	private String simScore;
	private String hotLevel;
	private String category;
	
	
}
