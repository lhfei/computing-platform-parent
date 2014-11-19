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

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

import org.springframework.batch.item.file.FlatFileItemWriter;

import com.ifeng.computing.orm.domain.NewsItem;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 6, 2014
 */
public class NewsItemFlatFileItemWriter extends
		FlatFileItemWriter<List<NewsItem>> {

	@Override
	public void write(List<? extends List<NewsItem>> items) throws Exception {
		PrintWriter pw = new PrintWriter(new File("sub_data.csv"));
		StringBuilder sb = null;
		
		for(List<NewsItem> news : items){
			for(NewsItem item : news){
				sb = new StringBuilder("");
				
				sb.append(item.getId());
				sb.append(",");
				
				sb.append(item.getCategory());
				sb.append(",");
				
				sb.append(item.getHotLevel());
				sb.append(",");
				
				
				sb.append(item.getpId());
				sb.append(",");
				
				sb.append(item.getSimScore());
				
				pw.append(sb.toString());
				pw.println();
			}
		}
		
		pw.flush();
		pw.close();
	}
	
	
	/*public void setResource(Resource resource) {
		this.resource = resource;
	}


	private Resource resource;*/
}
