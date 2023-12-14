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

/* Creates on 2023/6/19. */

import com.bitfashion.libraries.fashiontools.io.ByteBuf;
import org.junit.Test;

import static com.bitfashion.libraries.fashiontools.Objects.intOf;
import static com.bitfashion.libraries.fashiontools.Objects.longOf;

/**
 * @author bit-bitfashion
 */
public class NumberValTest {

    @Test
    public void readInt() {
        ByteBuf buffer = ByteBuf.allocate();
        buffer.write(10086);
        System.out.println(intOf("10085"));
        System.out.println(intOf(buffer.toByteArray()));
    }


    @Test
    public void readLong() {
        ByteBuf buffer = ByteBuf.allocate();
        buffer.write(10086L);
        System.out.println(longOf("10085"));
        System.out.println(longOf(buffer.toByteArray()));
    }

}
