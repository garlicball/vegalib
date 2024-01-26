package com.thesmartkbd.vegalib.time;

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

/* Creates on 2022/3/30. */

import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author thesmartkbd
 */
public enum VegaTimeUnit {

    /** 毫秒 */
    MILLISECONDS,
    /** 秒 */
    SECONDS,
    /** 分 */
    MINUTES,
    /** 小时 */
    HOURS,
    /** 天 */
    DAYS,
    /** 周 */
    WEEKS,
    /** 月 */
    MONTHS,
    /** 年 */
    YEARS,
    ;

    public Date plus(int unit) {
        return plus(DateFormatter.now(), unit);
    }

    /**
     * #brief：将指定日期对象加上 unit + 当前枚举<p>
     *
     * 将指定日期对象加上 unit + 当前枚举。假设需要让一个日期对象加一天，可以
     * 这样使用：{@code TimeUnits.DAYS.plus(date, 1L)}。
     *
     * @param date
     *        日期对象实例
     *
     * @param unit
     *        添加的单位数
     *
     * @return 加上对应 TimeUnits + unit 后的日期
     */
    public Date plus(Date date, int unit) {
        DateTime calc = new DateTime(date);
        var retdate = switch (this) {
            case MILLISECONDS -> calc.plusMillis(unit);
            case SECONDS -> calc.plusSeconds(unit);
            case MINUTES -> calc.plusMinutes(unit);
            case HOURS -> calc.plusHours(unit);
            case DAYS -> calc.plusDays(unit);
            case WEEKS -> calc.plusWeeks(unit);
            case MONTHS -> calc.plusMonths(unit);
            case YEARS -> calc.plusYears(unit);
        };
        return retdate.toDate();
    }

    public Date minus(int unit) {
        return minus(DateFormatter.now(), unit);
    }

    /**
     * #brief：将指定日期对象减去 unit - 当前枚举<p>
     *
     * 将指定日期对象减去 unit - 当前枚举。假设需要让一个日期对象加一天，可以
     * 这样使用：{@code TimeUnits.DAYS.plus(date, 1L)}。
     *
     * @param date
     *        日期对象实例
     *
     * @param unit
     *        添加的单位数
     *
     * @return 减去对应 TimeUnits - unit 后的日期
     */
    public Date minus(Date date, int unit) {
        DateTime calc = new DateTime(date);
        var retdate = switch (this) {
            case MILLISECONDS -> calc.minusMillis(unit);
            case SECONDS -> calc.minusSeconds(unit);
            case MINUTES -> calc.minusMinutes(unit);
            case HOURS -> calc.minusHours(unit);
            case DAYS -> calc.minusDays(unit);
            case WEEKS -> calc.minusWeeks(unit);
            case MONTHS -> calc.minusMonths(unit);
            case YEARS -> calc.minusYears(unit);
        };
        return retdate.toDate();
    }

}
