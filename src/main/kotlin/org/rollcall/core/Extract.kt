package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output

fun indexesToMultiShot(list:List<Int>,len:Int): MutableList<Int> {
    val res = mutableListOf<Int>()
    for (i in 0..len){
        if (i in list){
            res.add(1)
        }else{
            res.add(0)
        }
    }
    return res
}

fun extractSample(input: Input, output: Output, rollCallNumber:Int) {
    val (data, credit) = input.read()

    val rollList= mutableListOf<MutableList<Int>>()

    var currentList = (1..credit.size)
        .toList()
        .zip(credit)
        .sortedBy {
            it.second
        }
        .subList(0, rollCallNumber)
        .map {
            it.first
        }


    for (lesson in data){
        rollList.add(indexesToMultiShot(currentList,credit.size))
        val captureList=(1..credit.size).intersect(currentList.toSet()).toList()
        val restNumber=rollCallNumber-captureList.size

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

    }



    //TODO (implement your algorithm)

    output.write(rollList)
}