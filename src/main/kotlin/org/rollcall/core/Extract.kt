package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output


fun extractSamples(input: Input, output: Output) {
    val (data, credit) = input.read()
    data.


    // TODO (implement your algorithm)

    output.write(data, 1.0)
}