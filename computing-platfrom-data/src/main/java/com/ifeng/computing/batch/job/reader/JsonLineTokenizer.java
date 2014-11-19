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

package com.ifeng.computing.batch.job.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.batch.item.file.transform.DefaultFieldSet;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.batch.item.file.transform.LineTokenizer;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Nov 3, 2014
 */
public class JsonLineTokenizer implements LineTokenizer {

	@Override
	public FieldSet tokenize(String line) {
		List<String> tokens = new ArrayList<>();

        try {
            HashMap<String,Object> result =
                    new ObjectMapper().readValue(line, HashMap.class);

            tokens.add((String) result.get("field1"));
            tokens.add((String) result.get("field2"));

        } catch (IOException e) {
            throw new RuntimeException("Unable to parse json: " + line);
        }

        return new DefaultFieldSet(tokens.toArray(new String[0]), new String[]{"field1", "field2"});
	}

}
