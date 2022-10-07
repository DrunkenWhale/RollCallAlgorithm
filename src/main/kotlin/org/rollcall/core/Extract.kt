package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Lesson
import org.rollcall.output.Output


fun extractSample(
    f: (List<Lesson>, List<Double>, Int) -> List<Lesson>,
    input: Input,
    output: Output,
    rollCallNumber: Int
) {
    val (data, gpa) = input.read()

    val rollCallSchema = f(data, gpa, rollCallNumber)

    output.write(rollCallSchema)
}