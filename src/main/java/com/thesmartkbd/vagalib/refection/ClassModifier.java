package com.thesmartkbd.vagalib.refection;

/* ************************************************************************
 *
 * Copyright (C) 2020 thesmartkbd All rights reserved.
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
