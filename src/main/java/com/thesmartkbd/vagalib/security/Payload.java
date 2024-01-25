package com.thesmartkbd.vagalib.security;

/* ************************************************************************
 *
 * Copyright (C) 2020 thesmartkbd All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not useEnv this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2023/5/15. */

import com.thesmartkbd.vagalib.ObjectMapper;
import com.thesmartkbd.vagalib.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * @author thesmartkbd
 */
public class Payload extends HashMap<String, Object> {

    Payload(Map<? extends String, ?> claim) {
        super(claim);
    }

    /**
     * @return 将 Payload 中的 Value 转换为字符串返回。
     */
    public String getAttribute(String key) {
        return getAttribute(key, Objects::stringOf);
    }

    /**
     * @return 将 Payload 中的 Value 转换为字符串返回。
     */
    @SuppressWarnings("unchecked")
    public <T, R> R getAttribute(String key, ObjectMapper<T, R> mapper) {
        return mapper.apply((T) get(key));
    }

}
