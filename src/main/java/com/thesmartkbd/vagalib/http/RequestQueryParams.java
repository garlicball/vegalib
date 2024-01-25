package com.thesmartkbd.vagalib.http;

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

/* Creates on 2023/6/26. */

import java.util.HashMap;
import java.util.Map;

import static com.thesmartkbd.vagalib.Objects.sprintf;
import static com.thesmartkbd.vagalib.Objects.stringOf;

/**
 * @author thesmartkbd
 */
public class RequestQueryParams extends HashMap<String, String> {

    /** 参数拼接 */
    public String build(String url) {
        if (isEmpty())
            return url;

        StringBuilder builder = new StringBuilder();

        for (Map.Entry<String, String> entry : entrySet())
            builder.append(sprintf("%s=%s&", entry.getKey(), entry.getValue()));
        String finalArguments = stringOf(builder, 0, -1); /* 删掉最后一个字符 ‘&’ */

        return sprintf("%s?%s", url, finalArguments);
    }

}
