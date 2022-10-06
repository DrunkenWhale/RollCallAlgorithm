package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output


fun extractSample(input: Input, output: Output) {
    val (data, credit) = input.read()

    //TODO (implement your algorithm)

    output.write(data)
}