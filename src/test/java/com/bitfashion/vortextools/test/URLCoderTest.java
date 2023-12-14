package com.bitfashion.vortextools.test;

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

/* Creates on 2023/9/6. */

import com.bitfashion.libraries.fashiontools.security.Crypts;
import org.junit.Test;

import static com.bitfashion.libraries.fashiontools.io.IOUtils.stdout;

/**
 * @author bit-bitfashion
 */
@SuppressWarnings("all")
public class URLCoderTest {

    static final String WWW_BAIDU_COM = "https://www.baidu.com?search=地铁判官";

    @Test
    public void urlEncode() {
        stdout.println("url encode: %s", Crypts.Encoder.url(WWW_BAIDU_COM));
    }

    @Test
    public void urlDecode() {
        stdout.println("url decode: %s", Crypts.Decoder.url(Crypts.Encoder.url(WWW_BAIDU_COM)));
    }

}
