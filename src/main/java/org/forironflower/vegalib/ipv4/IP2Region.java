package org.forironflower.vegalib.ipv4;

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

/* Creates on 2023/5/18. */

import org.forironflower.vegalib.logging.Logger;
import org.forironflower.vegalib.logging.LoggerFactory;
import org.forironflower.vegalib.refection.ClassPathResourceReader;
import org.forironflower.vegalib.Objects;
import org.lionsoul.ip2region.xdb.Searcher;

/**
 * 获取IP所在区域
 *
 * @author forironflower
 */
public class IP2Region {

    private static final Logger logger = LoggerFactory.getLogger(IP2Region.class);

    public static Searcher searcher = null;

    /**
     * 检索 ip 地址
     */
    public static Region search(String ip) {
        try {
            if (searcher == null) {
                byte[] buf = ClassPathResourceReader.read("ip2region.xdb");
                logger.info("ip table buffer size: %s", buf.length);
                searcher = Searcher.newWithBuffer(buf);
            }
            String search = searcher.searchByStr(ip);
            String[] split = Objects.strtok(search, "\\|");
            return new Region(split[0], split[2], split[3]);
        } catch (Exception e) {
            return Region.UNKNOWN_REGION;
        }
    }

}
