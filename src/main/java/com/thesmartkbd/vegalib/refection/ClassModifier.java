package com.thesmartkbd.vegalib.refection;

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

/* Creates on 2019/5/16. */

import lombok.Getter;
import org.objectweb.asm.*;

import java.io.IOException;

/**
 * 动态类编辑技术
 *
 * @author luotiansheng
 */
public class ClassModifier {

    /**
     * 类路径
     */
    private final @Getter String classname;
    /**
     * 被修改的类
     */
    private final @Getter Class<?> modified;
    /**
     * 类写入器
     */
    private final ClassReader classReader;
    /**
     * 类读取器
     */
    private final ClassWriter classWriter;

    /**
     * 提交类对象，对该对象进行修改。
     *
     * @param modified
     *        被修改的类对象
     */
    public ClassModifier(Class<?> modified) {
        this.classname = modified.getName();
        this.modified = modified;
        /* 获取类的写入器和读取器 */
        try {
            this.classReader = new ClassReader(classname);
            this.classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        /* 初始化 visitor 访问器 */
        accept0(classWriter);
    }

    private void accept0(ClassVisitor visitor) {
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
    }

    public byte[] toBytecode() {
        return classWriter.toByteArray();
    }

}
