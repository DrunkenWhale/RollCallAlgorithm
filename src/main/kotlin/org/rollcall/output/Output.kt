package org.rollcall.output

internal typealias Label = Int
internal typealias Lesson = List<Label>

interface Output {
    /**
     * @param data roll call method
     * */
    fun write(data: List<Lesson>, E: Double)
}