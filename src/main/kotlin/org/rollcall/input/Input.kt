package org.rollcall.input

internal typealias Label = Int
internal typealias Lesson = List<Label>

interface Input {
    fun read(): Pair<List<Lesson>, List<Double>>
}