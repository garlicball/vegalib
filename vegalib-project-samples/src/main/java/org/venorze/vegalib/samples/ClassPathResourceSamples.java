package org.venorze.vegalib.samples;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2023 venorze                                                    *|
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
|* File:           ClassPathResourceSamples.java                                    *|
|* Create Time:    2024/1/30 18:12                                                  *|
|* Author:         venorze                                                          *|
|* EMail:          venorze@hotmail.com                                              *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

import org.venorze.vegalib.refection.ClassPathResource;
import org.junit.Test;

import static org.venorze.vegalib.Arrays.array_to_string;
import static org.venorze.vegalib.Objects.fprintlnf;

/**
 * ClassPathResourceSamples 演示
 *
 * @author venorze
 */
public class ClassPathResourceSamples {

    @Test
    public void api_sample_classpath_resource() {
        fprintlnf("ClassPathResource Text:  %s", new ClassPathResource("classpath.resource.vegalib").strread());
        fprintlnf("ClassPathResource Bytes: %s", array_to_string(new ClassPathResource("classpath.resource.vegalib").read()));
    }

}
