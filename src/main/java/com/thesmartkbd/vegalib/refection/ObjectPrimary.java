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

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.thesmartkbd.vegalib.Assert.throwIfError;
import static com.thesmartkbd.vegalib.Assert.throwIfNull;
import static com.thesmartkbd.vegalib.collection.Collections.*;

/**
 * 类属性`Object`对象封装
 *
 * @author thesmartkbd
 */
public class ObjectPrimary {
    /**
     * 对象类属性
     */
    @Getter
    private transient final Class<?> primary;
    /**
     * 属性列表
     */
    private final Map<String, ObjectProperty> properties = mapOf();

    public ObjectPrimary(Object instance) {
        this(instance.getClass());
    }

    public ObjectPrimary(Class<?> primary) {
        this.primary = primary;
        /* init */
        Map<String, ObjectProperty> map = getPrimaryProperties(primary, listOf()).stream()
                .collect(Collectors.toMap(ObjectProperty::getName, v -> v));
        properties.putAll(map);
    }

    public static ObjectPrimary forName(String className) {
        Class<?> clazz = (Class<?>) throwIfError(() -> Class.forName(className));
        return new ObjectPrimary(clazz);
    }

    /**
     * #brief：实例化当前类对象，可选构造函数参数<p>
     * <p>
     * 实例化当前类对象，可选构造函数参数。通过 {@code args} 参数列表自动选择
     * 实例化对象的构造器。如果没有参数默认使用无参构造器初始化。
     *
     * @param args 构造器参数（可变
     * @return 实例化类过后的对象实例
     */
    @SuppressWarnings("unchecked")
    public <T> T newInstance(Object... args) {
        return (T) JvmRefs.newInstance(primary, args);
    }

    /**
     * #brief：获取当前类下的所有属性列表<p>
     * <p>
     * 获取当前类下的所有属性列表，属性列表已经是封装好了的`ObjectProperty`
     * 对象实例，可以直接使用。
     *
     * @return 封装好的`ObjectProperty`实例列表
     */
    public List<ObjectProperty> getProperties() {
        return getProperties(true);
    }

    /**
     * #brief：获取当前类下的所有属性列表<p>
     * <p>
     * 获取当前类下的所有属性列表，的属性列表已经是封装好了的`ObjectProperty`
     * 对象实例，可以直接使用。
     *
     * @return 封装好的`ObjectProperty`实例列表
     */
    public List<ObjectProperty> getProperties(boolean isFilter) {
        if (!isFilter)
            return listOf(properties.values());
        /* 筛选出修饰符非 static & final 的属性列表 */
        return listFilter(properties.values(), value -> (!value.isStatic() && !value.isFinal()));
    }

    /**
     * 递归查找属性列表
     */
    static List<ObjectProperty> getPrimaryProperties(Class<?> primary,
                                                     List<ObjectProperty> primaryProperties) {
        // 获取所有成员
        Field[] properties = primary.getDeclaredFields();
        primaryProperties.addAll(listMap(properties, ObjectProperty::new));

        Class<?> superclass = primary.getSuperclass();
        if (superclass != Object.class)
            getPrimaryProperties(superclass, primaryProperties);

        return primaryProperties;
    }

    @SuppressWarnings({"DataFlowIssue", "unchecked"})
    public <R> R readProperty(String name, Object instance) {
        return (R) throwIfNull(properties.get(name), "`%s`属性成员不存在", name)
                .read(instance);
    }

    public Object staticInvoke(String name, Object... args) {
        return throwIfError(() -> {
            Method method = args == null ? primary.getDeclaredMethod(name) :
                    primary.getDeclaredMethod(name, JvmRefs.toClassArray(args));
            return method.invoke(null, args);
        }, "invoke failed.");
    }

    /**
     * Returns the class loader for the class.  Some implementations may use
     * null to represent the bootstrap class loader. This method will return
     * null in such implementations if this class was loaded by the bootstrap
     * class loader.
     *
     * <p>If this {@code Class} object
     * represents a primitive type or void, null is returned.
     *
     * @return the class loader that loaded the class or interface
     * represented by this {@code Class} object.
     * @throws SecurityException if a security manager is present, and the caller's class loader
     *                           is not {@code null} and is not the same as or an ancestor of the
     *                           class loader for the class whose class loader is requested,
     *                           and the caller does not have the
     *                           {@link RuntimePermission}{@code ("getClassLoader")}
     * @see java.lang.ClassLoader
     * @see SecurityManager#checkPermission
     * @see java.lang.RuntimePermission
     */
    public ClassLoader getClassLoader() {
        return primary.getClassLoader();
    }

}
