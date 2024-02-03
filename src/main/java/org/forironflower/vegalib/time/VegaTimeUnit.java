package org.forironflower.vegalib.time;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 forironflower                                              *|
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

import org.forironflower.vegalib.annotations.Favorite;
import org.forironflower.vegalib.annotations.Upgradable;
import org.joda.time.DateTime;

import java.util.Date;

/**
 * @author forironflower
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
    @Favorite
    @Upgradable(version = "11", features = {"switch"})
    public Date plus(Date date, int unit) {
        DateTime calc = new DateTime(date);
        DateTime retdate = null;
        switch (this) {
            case MILLISECONDS: retdate = calc.plusMillis(unit); break;
            case SECONDS: retdate = calc.plusSeconds(unit); break;
            case MINUTES: retdate = calc.plusMinutes(unit); break;
            case HOURS: retdate = calc.plusHours(unit); break;
            case DAYS: retdate = calc.plusDays(unit); break;
            case WEEKS: retdate = calc.plusWeeks(unit); break;
            case MONTHS: retdate = calc.plusMonths(unit); break;
            case YEARS: retdate = calc.plusYears(unit); break;
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
    @Favorite
    @Upgradable(version = "11", features = {"switch"})
    public Date minus(Date date, int unit) {
        DateTime calc = new DateTime(date);
        DateTime retdate = null;
        switch (this) {
            case MILLISECONDS: retdate = calc.minusMillis(unit); break;
            case SECONDS: retdate = calc.minusSeconds(unit); break;
            case MINUTES: retdate = calc.minusMinutes(unit); break;
            case HOURS: retdate = calc.minusHours(unit); break;
            case DAYS: retdate = calc.minusDays(unit); break;
            case WEEKS: retdate = calc.minusWeeks(unit); break;
            case MONTHS: retdate = calc.minusMonths(unit); break;
            case YEARS: retdate = calc.minusYears(unit); break;
        };
        return retdate.toDate();
    }

}
