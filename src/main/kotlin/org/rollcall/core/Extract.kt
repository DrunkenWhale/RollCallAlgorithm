package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output


fun extractSample(input: Input, output: Output) {
    val (data, credit) = input.read()

    val rollList= mutableListOf<MutableList<Int>>()

    var currentList = (1..credit.size)
        .toList()
        .zip(credit)
        .sortedBy {
            it.second
        }
        .subList(0, 10)
        .map {
            it.first
        }

    rollList.add(currentList as MutableList<Int>)

    for (lesson in data){
        val captureList=(1..credit.size).toList().union(currentList)
        val restNumber=10-captureList.size

        val restRollList =  (1..credit.size)
            .toList()
            .subtract(currentList.toSet())
            .zip(credit)
            .sortedBy {
                it.second
            }
            .subList(0, restNumber)
            .map {
                it.first
            }
        currentList = (captureList+restRollList).toList()
        rollList.add(currentList as MutableList<Int>)
    }


    //TODO (implement your algorithm)

    output.write(rollList)
}