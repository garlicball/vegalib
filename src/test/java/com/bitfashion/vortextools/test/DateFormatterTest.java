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

/* Creates on 2023/7/17. */

import com.bitfashion.libraries.fashiontools.time.TimeUnits;
import com.bitfashion.libraries.fashiontools.time.DateFormatter;
import org.junit.Test;

/**
 * @author bit-bitfashion
 */
@SuppressWarnings("all")
public class DateFormatterTest {

    @Test
    public void formatTest() {
        System.out.println("formatTest：" + DateFormatter.fmt());
    }

    @Test
    public void parseTest() {
        System.out.println("parseTest：" + DateFormatter.fmt(DateFormatter.parse("2022-09-11 12:00:00")));
    }

    @Test
    public void dateCalc() {
        var date = DateFormatter.parse("2023-09-11 12:00:00");
        System.out.println(DateFormatter.fmt(TimeUnits.DAYS.minus(date, 1)));
        System.out.println(DateFormatter.fmt(TimeUnits.MONTHS.minus(date, 2)));
        System.out.println(DateFormatter.fmt(TimeUnits.YEARS.minus(date, 3)));
    }

}
