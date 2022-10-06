package org.rollcall.output

internal typealias Label = Int
internal typealias Lesson = List<Label>

interface Output {
    /**
     * put data into Output subclass instance
     * */
    fun write(data: List<Lesson>)

    /**
     * get the written data
     * */
    fun read(): List<List<Lesson>>

    /**
     * output data to ?
     * console / database / file
     * who cares?
     * */
    fun output(e: Double)

}