package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output


fun extractSample(input: Input, output: Output) {
    val (data, credit) = input.read()

    val list = (1..credit.size)
        .toList()
        .zip(credit)
        .sortedBy {
            it.second
        }
        .subList(0, 10)


    //TODO (implement your algorithm)

    output.write(data)
}