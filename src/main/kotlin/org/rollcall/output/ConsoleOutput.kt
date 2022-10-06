package org.rollcall.output

class ConsoleOutput : Output {

    private val data = mutableListOf<List<Lesson>>()

    override fun write(data: List<Lesson>) {
        this.data.add(data)
    }

    override fun read(): List<List<Lesson>> {
        return this.data
    }


    override fun output(e: Double) {
        println("data\n--------------------------------------------------------------")
        data.forEach { println(it) }
        println("--------------------------------------------------------------\n\n\n")
        println("E")
        println("--------------------------------------------------------------")
        println(e)
        println("--------------------------------------------------------------")
    }


}