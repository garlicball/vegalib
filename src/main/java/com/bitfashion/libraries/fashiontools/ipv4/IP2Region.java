package com.bitfashion.libraries.fashiontools.ipv4;

/* ************************************************************************
 *
 * Copyright (C) 2020 bit-bitfashion All rights reserved.
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

/* Creates on 2023/5/18. */

import com.bitfashion.libraries.fashiontools.io.IOUtils;
import com.bitfashion.libraries.fashiontools.refection.JvmRefs;
import org.lionsoul.ip2region.xdb.Searcher;

import java.io.InputStream;

import static com.bitfashion.libraries.fashiontools.Objects.strtok;
import static com.bitfashion.libraries.fashiontools.io.IOUtils.stdout;

/**
 * 获取IP所在区域
 *
 * @author bit-bitfashion
 */
public class IP2Region {

    private static final InputStream IP_REGION_DB_RESOURCE_STREAM =
            JvmRefs.getStream("ip2region.xdb");

    public static Searcher searcher = null;

    /**
     * 检索 ip 地址
     */
    public static Region search(String ip) {
        try {
            if (searcher == null) {
                byte[] buf = IOUtils.read(IP_REGION_DB_RESOURCE_STREAM);
                stdout.println("ip table buffer size: %s", buf.length);
                searcher = Searcher.newWithBuffer(buf);
            }
            String search = searcher.searchByStr(ip);
            String[] split = strtok(search, "\\|");
            return new Region(split[0], split[2], split[3]);
        } catch (Exception e) {
            return Region.UNKNOWN_REGION;
        }
    }

}
