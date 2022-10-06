package org.rollcall.output

class ConsoleOutput : Output {
    override fun write(data: List<List<Int>>) {
        println("data\n--------------------------------------------------------------")
        data.forEach { println(it) }
        println("--------------------------------------------------------------\n\n\n")
    }
}