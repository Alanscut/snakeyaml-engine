/**
 * Copyright (c) 2018, http://www.snakeyaml.org
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.snakeyaml.engine.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.google.common.io.CharStreams;

public class TestUtils {

    public static String getResource(String theName) {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(theName);
        String string = null;
        try {
            string = CharStreams.toString(new InputStreamReader(inputStream, "UTF-8"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return string;
    }

}