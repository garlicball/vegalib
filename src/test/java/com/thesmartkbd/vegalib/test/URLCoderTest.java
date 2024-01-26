package com.thesmartkbd.vegalib.test;

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

/* Creates on 2023/9/6. */

import com.thesmartkbd.vegalib.security.Crypts;
import org.junit.Test;

import static com.thesmartkbd.vegalib.io.IOUtils.stdout;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class URLCoderTest {

    static final String WWW_BAIDU_COM = "https://www.baidu.com?search=地铁判官";

    @Test
    public void urlEncode() {
        stdout.println("url encode: %s", Crypts.Encoder.url(WWW_BAIDU_COM));
    }

    @Test
    public void urlDecode() {
        stdout.println("url decode: %s", Crypts.Decoder.url(Crypts.Encoder.url(WWW_BAIDU_COM)));
    }

}
