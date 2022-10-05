package org.rollcall.output

private typealias Label = Int
private typealias Lesson = List<Label>

interface Output {
    /**
     * @param data roll call method
     * */
    fun write(data: List<List<Int>>, E: Double)
}