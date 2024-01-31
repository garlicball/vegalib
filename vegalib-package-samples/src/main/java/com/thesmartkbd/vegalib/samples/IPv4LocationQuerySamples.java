package com.thesmartkbd.vegalib.samples;

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

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|* File:           IPv4LocationQuerySamples.java                                    *|
|* Create Time:    2024/1/29 19:17                                                  *|
|* Author:         thesmartkbd                                                      *|
|* EMail:          thesmartkbd@hotmail.com                                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.thesmartkbd.vegalib.ipv4.IP2Region;
import org.junit.Test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import static com.thesmartkbd.vegalib.Objects.fprintlnf;

/**
 * IP地址查询
 *
 * @author thesmartkbd
 */
public class IPv4LocationQuerySamples {

    /**
     * 通过 IP2Region#search 函数查询 IP 所属区域
     */
    @Test
    public void api_sample_ip2region_search() throws UnknownHostException {
        InetAddress ip_google_com = InetAddress.getByName("www.google.com"); /* www.google.com */
        fprintlnf("host %s, location: %s", ip_google_com, IP2Region.search(ip_google_com.getHostAddress()));
        InetAddress ip_baidu_com = InetAddress.getByName("www.baidu.com"); /* www.baidu.com */
        fprintlnf("host %s, location: %s", ip_baidu_com, IP2Region.search(ip_baidu_com.getHostAddress()));
        InetAddress ip_bilibili_com = InetAddress.getByName("www.bilibili.com"); /* www.bilibili.com */
        fprintlnf("host %s, location: %s", ip_bilibili_com, IP2Region.search(ip_bilibili_com.getHostAddress()));
        InetAddress ip_steampower_com = InetAddress.getByName("www.steampower.com"); /* www.steampower.com */
        fprintlnf("host %s, location: %s", ip_steampower_com, IP2Region.search(ip_steampower_com.getHostAddress()));
    }

}
