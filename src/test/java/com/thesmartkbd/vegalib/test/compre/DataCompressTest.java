package com.thesmartkbd.vegalib.test.compre;

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

/* Creates on 2023/6/19. */

import com.thesmartkbd.vegalib.io.ByteBuf;
import com.thesmartkbd.vegalib.io.VegaFile;
import com.thesmartkbd.vegalib.io.IOUtils;
import com.thesmartkbd.vegalib.io.VegaFileReader;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author thesmartkbd
 */
@SuppressWarnings("all")
public class DataCompressTest {

    @Test
    public void encode() throws IOException {
        VegaFileReader freader = new VegaFile("C:\\Users\\Lenovo\\Desktop\\cad绘制demo.rar")
                .openReader();

        byte[] buf = IOUtils.read(freader);

        int size = buf.length / 4;

        int width = (int) Math.sqrt((double) size / 3);
        int height = size / 3 / width;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        for (int i = 0; i < width * height * 3; i += 3) {
            int x = (i / 3) % width;
            int y = (i / 3) / width;

            int r = ((buf[i + 0] & 0xff) << 24);
            int g = ((buf[i + 1] & 0xff) << 16);
            int b = ((buf[i + 2] & 0xff) << 8);
            int a = ((buf[i + 3] & 0xff) << 0);

            image.setRGB(x, y, (r | g | b | a));

            i += 4;
        }

        ImageIO.write(image, "PNG", new VegaFile("C:\\Users\\Lenovo\\Desktop\\text.txt.data.png"));
    }

    @Test
    public void decode() throws IOException {
        VegaFile fimg = new VegaFile("C:\\Users\\Lenovo\\Desktop\\text.txt.data.png");
        BufferedImage image = ImageIO.read(fimg);

        ByteBuf buffer = ByteBuf.allocate();

        int width = image.getWidth();
        int height = image.getHeight();

        for (int i = 0; i < width * height * 3; i += 3) {
            int x = (i / 3) % width;
            int y = (i / 3) / width;

            int rgba = image.getRGB(x, y);
            byte r = (byte) (((rgba >> 24) & 0xff) >> 24);
            byte g = (byte) (((rgba >> 16) & 0xff) >> 16);
            byte b = (byte) (((rgba >>  8) & 0xff) >>  8);
            byte a = (byte) (((rgba >>  0) & 0xff) >>  0);

            buffer.write(r);
            buffer.write(g);
            buffer.write(b);
            buffer.write(a);
        }

        VegaFile ftxt = new VegaFile("C:\\Users\\Lenovo\\Desktop\\text.txt.data.png.txt");
        IOUtils.write(buffer.toByteArray(), ftxt.openWriter());
    }

}
