package com.thesmartkbd.vegalib.test.io;

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
|* File:           VegaFileTest.java                                                *|
|* Create Time:    2023/6/7                                                         *|
|* Author:         fsilverflower                                                    *|
|* EMail:          fsilverflower@hotmail.com                                        *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.thesmartkbd.vegalib.io.VegaFile;

/**
 * 拷贝大（2GB）文件测试
 *
 * @author thesmartkbd
 */
public class VegaFileTest {

    public static void main(String[] args) {
        VegaFile file = new VegaFile("C:\\Users\\Lenovo\\Desktop\\ccc.rar");
        file.copy("C:\\Users\\Lenovo\\Desktop\\ddd.rar");
    }

}
