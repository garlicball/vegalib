package org.forironflower.vegalib.security;

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

/* Creates on 2023/5/15. */

import org.forironflower.vegalib.LambdaObjectMapper;
import org.forironflower.vegalib.Objects;

import java.util.HashMap;
import java.util.Map;

/**
 * @author forironflower
 */
public class Payload extends HashMap<String, Object> {

    Payload(Map<? extends String, ?> claim) {
        super(claim);
    }

    /**
     * @return 将 Payload 中的 Value 转换为字符串返回。
     */
    public String getAttribute(String key) {
        return getAttribute(key, Objects::atos);
    }

    /**
     * @return 将 Payload 中的 Value 转换为字符串返回。
     */
    @SuppressWarnings("unchecked")
    public <T, R> R getAttribute(String key, LambdaObjectMapper<T, R> mapper) {
        return mapper.apply((T) get(key));
    }

}
