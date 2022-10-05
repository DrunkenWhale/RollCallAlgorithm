package org.rollcall.input

private typealias Label = Int
private typealias Lesson = List<Label>

interface Input {
    fun read(): Pair<List<Lesson>, List<Double>>
}