package org.rollcall.output

class ConsoleOutput : Output {
    override fun write(data: List<List<Int>>, E: Double) {
        println("data\n--------------------------------------------------------------")
        data.forEach { println(it) }
        println("--------------------------------------------------------------\n\n\n")
        println("E\n--------------------------------------------------------------")
        println(E)
        println("--------------------------------------------------------------")
    }
}