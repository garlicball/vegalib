package com.bitfashion.vortextools.test.compre;

/* ************************************************************************
 *
 * Copyright (C) 2020 bit-bitfashion All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not useEnv this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * ************************************************************************/

/* Creates on 2023/6/19. */

import com.bitfashion.libraries.fashiontools.io.ByteBuf;
import com.bitfashion.libraries.fashiontools.io.MutableFile;
import com.bitfashion.libraries.fashiontools.io.IOUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author bit-bitfashion
 */
@SuppressWarnings("all")
public class DataCompressTest {

    @Test
    public void encode() throws IOException {
        var freader = new MutableFile("C:\\Users\\Lenovo\\Desktop\\cad绘制demo.rar")
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

        ImageIO.write(image, "PNG", new MutableFile("C:\\Users\\Lenovo\\Desktop\\text.txt.data.png"));
    }

    @Test
    public void decode() throws IOException {
        var fimg = new MutableFile("C:\\Users\\Lenovo\\Desktop\\text.txt.data.png");
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

        var ftxt = new MutableFile("C:\\Users\\Lenovo\\Desktop\\text.txt.data.png.txt");
        IOUtils.write(buffer.toByteArray(), ftxt.openWriter());
    }

}
