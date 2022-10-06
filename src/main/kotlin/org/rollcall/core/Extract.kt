package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output


fun extractSample(input: Input, output: Output, rollCallNumber: Int) {
    val (data, credit) = input.read()

    val currentList = (0..credit.size)
        .toList()
        .zip(credit)
        .sortedBy { it.second }
        .subList(0, rollCallNumber)
        .map { it.first }

    val rollCallSchema = data.map { _ ->
        credit.indices.map { if (it in currentList) 1 else 0 }
    }

    output.write(rollCallSchema)
}