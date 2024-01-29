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
|* File:           VegaFileSamples.java                                                *|
|* Create Time:    2024/1/29 20:44                                                   *|
|* Author:         thesmartkbd                                                      *|
|* EMail:          thesmartkbd@hotmail.com                                          *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import com.thesmartkbd.vegalib.io.IOUtils;
import com.thesmartkbd.vegalib.io.VegaFile;
import org.junit.Test;

/**
 * VegaFile对象示例
 *
 * @author thesmartkbd
 */
public class VegaFileSamples {

    @Test
    public void api_sample_file_copy() {
        VegaFile source = new VegaFile("%user.dir%/copy/source.vegalib");
        source.write("Hello World");
        source.copy("%user.dir%/copy/target.vegalib");
    }

}
