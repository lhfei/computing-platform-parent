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

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;

import com.ifeng.computing.orm.domain.LogData;
import com.ifeng.computing.orm.domain.NewsItem;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 4, 2014
 */
public class TextFlatFileItemWriter extends FlatFileItemWriter<LogData> {

	@Override
	public void write(List<? extends LogData> items) throws Exception {
		super.write(items);
		
		/*PrintWriter writer = new PrintWriter(new File("sub-log.txt"));
		StringBuilder sb = null;
		
		List<NewsItem> news = null;
		for(LogData data : items){
			sb = new StringBuilder("");
			news = data.getNewsItem();
			
			sb.append(data.getpId());
			sb.append(",");
			
			for(NewsItem item : news){
				sb.append(item.getCategory());
				sb.append(",");
				
				sb.append(item.getHotLevel());
				sb.append(",");
				
				sb.append(item.getId());
				sb.append(",");
				
				sb.append(item.getpId());
				sb.append(",");
				
				sb.append(item.getSimScore());
			}
			
			writer.append(sb.toString());
			writer.println();
		}
		
		writer.flush();
		writer.close();*/
		
		List<NewsItem> list = new ArrayList<NewsItem>();
		
		for(LogData data : items){
			list.addAll(data.getNewsItem());
		}
		
		subWriter.write(list);
	}

	
	
	public void setSubWriter(ItemWriter<NewsItem> subWriter) {
		this.subWriter = subWriter;
	}

	private ItemWriter<NewsItem> subWriter;
}
