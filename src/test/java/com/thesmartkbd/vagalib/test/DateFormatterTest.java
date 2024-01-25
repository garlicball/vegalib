package com.thesmartkbd.vagalib.test;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 thesmartkbd                                                *|
|*                                                                                  *|
|*    This program is free software: you can redistribute it and/or modify          *|
|*    it under the terms of the GNU General Public License as published by          *|
|*    the Free Software Foundation, either version 3 of the License, or             *|
|*    (at your option) any later version.                                           *|
|*                                                                                  *|
|*    This program is distributed in the hope that it will be useful,               *|
|*    but WITHOUT ANY WARRANTY; without even the implied warranty of                *|
|*    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the                 *|
|*    GNU General Public License for more details.                                  *|
|*                                                                                  *|
|*    You should have received a copy of the GNU General Public License             *|
|*    along with this program.  If not, see <https://www.gnu.org/licenses/>.        *|
|*                                                                                  *|
|*    This program comes with ABSOLUTELY NO WARRANTY; for details type `show w'.    *|
|*    This is free software, and you are welcome to redistribute it                 *|
|*    under certain conditions; type `show c' for details.                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2023/7/17. */

import com.thesmartkbd.vagalib.time.VagaTimeUnit;
import com.thesmartkbd.vagalib.time.DateFormatter;
import org.junit.Test;

/**
 * @author thesmartkbd
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
        System.out.println(DateFormatter.fmt(VagaTimeUnit.DAYS.minus(date, 1)));
        System.out.println(DateFormatter.fmt(VagaTimeUnit.MONTHS.minus(date, 2)));
        System.out.println(DateFormatter.fmt(VagaTimeUnit.YEARS.minus(date, 3)));
    }

}
