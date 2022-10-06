package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output

// 不好意思，🧔不知道取什么名字好 你要是看到了就取一下
// 下面那个函数也是

fun temp(input: Input, output: Output) {
    val (data, gpa) = input.read()

    //TODO (implement your algorithm)

    output.write(data, 1.0)
}