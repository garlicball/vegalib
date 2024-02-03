package org.forironflower.vegalib.test.ipv4;

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
|* File:           IP2RegionTest.java                                                *|
|* Create Time:    2024/1/29 19:41                                                   *|
|* Author:         forironflower                                                    *|
|* EMail:          forironflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.forironflower.vegalib.ipv4.IP2Region;
import org.forironflower.vegalib.Objects;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.forironflower.vegalib.Objects.fprintlnf;

/**
 * @author forironflower
 */
public class IP2RegionTest {

    @Test
    public void ip2Region() throws UnknownHostException {
        Objects.fprintlnf("location: %s", IP2Region.search(InetAddress.getByName("www.baidu.com").getHostAddress()));
    }

}
