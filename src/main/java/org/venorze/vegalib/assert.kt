package org.venorze.vegalib

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

/* Creates on 2023/4/29. */

import org.venorze.vegalib.Objects.*
import org.venorze.vegalib.annotations.Favorite
import org.venorze.vegalib.exception.ValidationException
import org.venorze.vegalib.io.IOUtils
import org.venorze.vegalib.logging.LoggerFactory
import java.util.*

/**
 * 无返回值，无参默认匿名函数声明接口。适用于Lambda函数或
 * 闭包函数。
 *
 * @see Assert.throwIfError
 */
fun interface NormalFunction {
    @Throws(Throwable::class)
    fun apply()
}

/**
 * 执行泛型类型作为返回值，无参默认匿名函数声明接口。适用于Lambda函数或
 * 闭包函数。
 *
 * @see Assert.throwIfError
 */
fun interface GenericFunction<T> {
    @Throws(Throwable::class)
    fun apply(): T
}

/**
 * 断言工具类，这个工具类支持静态函数引用。有以下几类断言规则可使用：
 *
 *  - is*
 *    is 开头的断言基本上表示匹配只有两种内容，那就是：`true` or `false`，像类似的
 *    `throwIfNull`、`throwIfTrue`、`throwIfEmpty`等断言函数
 *
 *  - throwIf*
 *    throwIf 开头的断言函数，通常是基于 `Lambda` 函数然后再断言函数内部去捕获异常
 *    信息并处理。
 *
 *  - ignoreIf*
 *    ignoreIfError 忽略异常信息
 *
 * @author venorze
 */
@Favorite
@Suppress("MemberVisibilityCanBePrivate")
object Assert {
    /**
     * 断言一个对象，要求这个对象实例不能为空，如果它是空的，则会抛出 [NullPointerException] 这种经典
     * 的空指针异常。反之对象不为空，则会返回对象实例的引用。
     *
     * 代码示例：
     *
     *     var obj = null;
     *     /* throw NullPointerException */
     *     throwIfNull(obj);
     *
     * @param obj
     *        被断言的对象实例
     *
     * @return 被断言的对象实例，也就是参数 [obj]。
     */
    @JvmStatic
    fun <T> throwIfNull(obj: T?): T? = throwIfNull(obj, "Error validation orError cause parameter obj is null.")

    /**
     * 断言一个对象，要求这个对象实例不能为空，如果它是空的，则会抛出 [NullPointerException] 这种经典
     * 的空指针异常。反之对象不为空，则会返回对象实例的引用。
     *
     * 代码示例：
     *
     *     var obj = null;
     *     /* throw NullPointerException("obj is null.") */
     *     throwIfNull(obj, "%s is null", "obj");
     *
     * @param obj
     *        被断言的对象实例
     *
     * @param vfmt
     *        如果出现异常后自定义抛出异常信息
     *
     * @param args
     *        格式化参数
     *
     * @return 被断言的对象实例，也就是参数 [obj]。
     */
    @JvmStatic
    fun <T> throwIfNull(obj: T?, vfmt: String, vararg args: Any): T? =
            java.util.Objects.requireNonNull<T>(obj, strxfmt(vfmt, *args))

    /**
     * 断言一个对象，要求这个对象实例不能为空，如果它是空的，则会抛出 [NullPointerException] 这种经典
     * 的空指针异常。反之对象不为空，则会返回对象实例的引用。
     *
     * 代码示例：
     *
     *     var obj = null;
     *     /* throw NullPointerException */
     *     throwIfNull(obj);
     *
     * @param obj
     * 被断言的对象实例
     */
    @JvmStatic
    fun throwIfNotNull(obj: Any?) =
            throwIfNotNull(obj, "Error validation orError cause parameter obj not null.")

    /**
     * 断言一个对象，要求这个对象实例不能为空，如果它是空的，则会抛出 [NullPointerException] 这种经典
     * 的空指针异常。反之对象不为空，则会返回对象实例的引用。
     *
     * 代码示例：
     *
     *     var obj = null;
     *     /* throw NullPointerException("obj is not null.") */
     *     throwIfNull(obj, "%s is not null.", "obj");
     *
     * @param obj
     *        被断言的对象实例
     *
     * @param vfmt
     *        如果出现异常后自定义抛出异常信息
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun throwIfNotNull(obj: Any?, vfmt: String?, vararg args: Any) {
        if (obj != null)
            throw ValidationException(vfmt, *args)
    }

    /**
     * 断言一个布尔类型的值，如果 `input` 参数为 `true` 则抛出参数错误异常。
     * 反之，程序正常运行，不抛出任何错误。
     *
     * @param value
     *        被断言的 boolean 类型的参数
     */
    @JvmStatic
    fun throwIfTrue(value: Boolean) =
        throwIfTrue(value, "Error validation orError cause parameter value is false!")

    /**
     * 断言一个布尔类型的值，如果 `input` 参数为 `true` 则抛出参数错误异常。
     * 反之，程序正常运行，不抛出任何错误。
     *
     * @param value
     *        被断言的 boolean 类型的参数
     *
     * @param message
     *        如果断言出现异常，自定义抛出信息。支持格式化。详情请
     *        参考 [StringUtils#strxfmt] 函数。
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun throwIfTrue(value: Boolean, message: String, vararg args: Any) {
        if (value)
            throw ValidationException(strxfmt(message, *args))
    }

    /**
     * 断言一个布尔类型的值，如果 `input` 参数为 `false` 则抛出参数错误异常。
     * 反之，程序正常运行，不抛出任何错误。
     *
     * @param value
     *        被断言的 boolean 类型的参数
     */
    @JvmStatic
    fun throwIfFalse(value: Boolean) =
            throwIfFalse(value, "Error validation orError cause parameter value is false!")

    /**
     * 断言一个布尔类型的值，如果 `input` 参数为 `false` 则抛出参数错误异常。
     * 反之，程序正常运行，不抛出任何错误。
     *
     * @param value
     *        被断言的 boolean 类型的参数
     *
     * @param message
     *        如果断言出现异常，自定义抛出信息。支持格式化。详情请
     *        参考 [StringUtils#strxfmt] 函数。
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun throwIfFalse(value: Boolean, message: String, vararg args: Any) {
        if (!value)
            throw ValidationException(strxfmt(message, *args))
    }

    /**
     * 断言集合对象以及所有实现了 [Collection] 接口的对象实例内部是否存在
     * 数据。如果内部是空的，或 `collection` 是 `null` 则会抛出断言
     * 异常。
     *
     * @param collection
     *        实现了 [Collection] 接口的对象实例
     */
    @JvmStatic
    fun throwIfEmpty(collection: Collection<*>?) =
            throwIfEmpty(collection, "Error validation orError cause collection is null or empty.")

    /**
     * 断言集合对象以及所有实现了 [Collection] 接口的对象实例内部是否存在
     * 数据。如果内部是空的，或 `collection` 是 `null` 则会抛出断言
     * 异常。
     *
     * @param collection
     *        实现了 [Collection] 接口的对象实例
     *
     * @param message
     *        如果断言出现异常，自定义抛出信息。支持格式化。详情请
     *        参考 [StringUtils.strxfmt] 函数。
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun throwIfEmpty(collection: Collection<*>?, message: String, vararg args: Any) =
            throwIfTrue(collection == null || collection.isEmpty(), strxfmt(message, *args))

    /**
     * 传入一个无返回值、无参的闭包函数对象，Lambda使用样例：
     *
     * 代码示例：
     *
     *     InputStream stream;
     *     Assert.ifError(stream::close);
     *
     * 或者也可以匿名函数直接调用：
     *
     *     Assert.ifError(() -> System.out.println("Hello World"))
     *
     * @see IOUtils.closeQuietly
     */
    @JvmStatic
    fun throwIfError(runnable: NormalFunction) {
        try {
            runnable.apply()
        } catch (e: Throwable) {
            throw ValidationException(
                "Error validation orError cause the function run error!",
                e
            )
        }
    }

    /**
     * 传入一个无返回值、无参的闭包函数对象，执行后捕获异常，并忽略。Lambda使用样例：
     *
     * 代码示例：
     *
     *      InputStream stream;
     *      Assert.ignore(stream::close); // ignore NullPointException.
     *
     * 或者也可以匿名函数直接调用：
     *
     *      Assert.ignore(() -> stream.read()) // ignore NullPointException.
     *
     * @see IOUtils.closeQuietly
     */
    @JvmStatic
    fun ignoreIfError(function: NormalFunction) {
        try {
            function.apply()
        } catch (ignore: Throwable) {
            // ignore exception.
        }
    }

    /**
     * 执行泛型类型作为返回值、无参的闭包函数对象，Lambda使用样例：
     *
     * 代码示例：
     *
     *     InputStream stream = x;
     *     byte[] b = new byte[1024];
     *     int eof = Assert.ifError(() -> stream.read(b));
     */
    @JvmStatic
    fun <T> throwIfError(function: GenericFunction<T>): T =
            throwIfError(function, "Error validation orError cause the function run error!")

    /**
     * 执行泛型类型作为返回值、无参的闭包函数对象，Lambda使用样例：
     *
     * 代码示例：
     *
     *     InputStream stream = x;
     *     byte[] b = new byte[1024];
     *     int eof = Assert.ifError(() -> stream.read(b));
     *
     * @param message
     *        自定义异常信息
     */
    @JvmStatic
    fun <T> throwIfError(function: GenericFunction<T>, message: String): T {
        return try {
            function.apply()
        } catch (e: Throwable) {
            throw ValidationException(message, e)
        }
    }
}

/**
 * Optional 函数，通常用于避免空指针、异常等情况下使用，以下是常见的 Optional 操作：
 *
 *  - optionalIfNull*
 *    `optionalIfNull`函数用于判断一个带返回值的闭包函数返回结果是否为`null`，如果返回
 *    结果为`null`的话，那么就 Optional 使用的 `orElse` 返回。
 *
 *  - optionalIfError*
 *    `optionalIfError`函数用于校验一个函数（有返回值或无返回值）是否出现异常，如果出现
 *    异常，可选返回 `orError` 或 `orSuccess` & `orError`
 *
 * @author venorze
 */
object Optional {
    private val log = LoggerFactory.getLogger(Optional::class.java)

    /**
     * #brief：如果[function]执行结果为`null`则返回[orElse]
     *
     * 如果[function]执行结果为`null`则返回[orElse]，[function]是一个闭包函数，这个
     * 函数必须带有返回值。如果[function]执行结果为`null`则会返回[orElse]
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orElse
     *        如果闭包函数执行结果为`null`，那么则返回`orElse`
     *
     */
    @JvmStatic
    fun <T> optionalIfNull(function: GenericFunction<T>, orElse: T): T =
        optionalIfNull(function, orElse, "")

    /**
     * #brief：如果[function]执行结果为`null`则返回[orElse]
     *
     * 如果[function]执行结果为`null`则返回[orElse]，[function]是一个闭包函数，这个
     * 函数必须带有返回值。如果[function]执行结果为`null`则会返回[orElse]
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orElse
     *        如果闭包函数执行结果为`null`，那么则返回`orElse`
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun <T> optionalIfNull(function: GenericFunction<T>, orElse: T, message: String, vararg args: Any): T {
        val ret = function.apply()
        if (ret == null) {
            if (strnemp(message))
                log.error("%s - 异常信息：%s", message, *args)
            return orElse
        }
        return ret
    }

    /**
     * #brief：如果闭包函数没有出现异常，返回闭包函数值。否则返回 [orError] 值
     *
     * 封装一个 Optional 函数，如果闭包函数没有出现异常，返回闭包函数值。如果出现异常
     * 返回 [orError] 值。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0d"), 0.0)
     *      println(ret) // ret = 1.0
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orError
     *        执行结果出现异常返回
     */
    @JvmStatic
    fun <T> optionalIfError(function: GenericFunction<T>, orError: T): T =
        optionalIfError(function, orError, "")

    /**
     * #brief：如果闭包函数没有出现异常，返回闭包函数值。否则返回 [orError] 值
     *
     * 封装一个 Optional 函数，如果闭包函数没有出现异常，返回闭包函数值。如果出现异常
     * 返回 [orError] 值。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0d"), 0.0)
     *      println(ret) // ret = 1.0
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orError
     *        执行结果出现异常返回
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     */
    @JvmStatic
    fun <T> optionalIfError(function: GenericFunction<T>, orError: T, message: String, vararg args: Any): T {
        return try {
            function.apply()
        } catch (e: Throwable) {
            if (strnemp(message)) {
                val tmp = strxfmt(message, *args)
                log.error("%s - 异常信息：%s", tmp, e.message)
            }
            return orError
        }
    }

    /**
     * #brief：optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]
     *
     * optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]，用于平判断一个
     * 函数执行是否出错，避免空指针可使用。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0a"), true, false)
     *      println(ret) // ret = false
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param orSuccess
     *        执行结果未出现异常返回 [true]
     *
     * @param orError
     *        执行结果出现异常返回 [false]
     *
     */
    @JvmStatic
    fun <T> optionalIfError(function: NormalFunction, orSuccess: T, orError: T): T =
        optionalIfError(function, orSuccess, orError, "")

    /**
     * #brief：optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]
     *
     * optional 选项封装，如果出现异常返回 [orError] 否则返回 [orSuccess]，用于平判断一个
     * 函数执行是否出错，避免空指针可使用。
     *
     * 代码示例：
     *
     *      var ret = optionalIfError(() -> Double.parse("1.0a"), true, false)
     *      println(ret) // ret = false
     *
     * @param function
     *        闭包函数用于执行方法体，捕获异常
     *
     * @param message
     *        自定义异常信息
     *
     * @param args
     *        格式化参数
     *
     */
    @JvmStatic
    @Suppress("UNREACHABLE_CODE")
    fun <T> optionalIfError(function: NormalFunction, orSuccess: T, orError: T, message: String, vararg args: Any): T {
        return try {
            function.apply()
            return orSuccess
        } catch (e: Throwable) {
            if (strnemp(message)) {
                val tmp = strxfmt(message, *args)
                log.error("%s - 异常信息：%s", tmp, e.message)
            }
            return orError
        }
    }
}
