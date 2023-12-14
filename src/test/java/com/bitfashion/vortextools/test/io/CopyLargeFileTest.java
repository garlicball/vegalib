package com.bitfashion.vortextools.test.io;

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

/* Creates on 2023/6/7. */

import com.bitfashion.libraries.fashiontools.io.MutableFile;

/**
 * 拷贝大（2GB）文件测试
 *
 * @author bit-bitfashion
 */
public class CopyLargeFileTest {

    public static void main(String[] args) {
        var file = new MutableFile("C:\\Users\\Lenovo\\Desktop\\ccc.rar");
        file.copyTo("C:\\Users\\Lenovo\\Desktop\\ddd.rar");
    }

}
