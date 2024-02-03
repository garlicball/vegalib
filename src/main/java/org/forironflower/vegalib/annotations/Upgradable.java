package org.forironflower.vegalib.annotations;

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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           Upgradable.java                                                     *|
|* Create Time:    2024/1/29 20:09                                                  *|
|* Author:         forironflower                                                    *|
|* EMail:          forironflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import java.lang.annotation.*;

/**
 * 被注解的代码表示可升级
 *
 * @author forironflower
 */
@Retention(RetentionPolicy.SOURCE)
@Documented
public @interface Upgradable {
    /**
     * #brief: 表示可升级到哪个版本的 JDK 代码
     */
    String version() default "";
    /**
     * #brief: 可用版本特性列表
     */
    String[] features() default "";
}
