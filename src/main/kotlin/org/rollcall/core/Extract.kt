package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output

fun indexesToMultiShot(list: List<Int>, len: Int): MutableList<Int> {
    val res = mutableListOf<Int>()
    for (i in 0 until len) {
        if (i in list) {
            res.add(1)
        } else {
            res.add(0)
        }
    }
    return res
}

fun extractSample(input: Input, output: Output, rollCallNumber: Int) {
    val (data, credit) = input.read()

    val rollList = mutableListOf<MutableList<Int>>()

    var currentIndexList = credit.indices
        .toList()
        .zip(credit)
        .sortedBy { it.second }
        .subList(0, rollCallNumber)
        .map {
            it.first
        }



    for (lesson in data) {

        val absenceIndexList= mutableListOf<Int>()

        for (i in lesson.indices){
            if (lesson[i]==1)
                absenceIndexList.add(i)
        }

        rollList.add(indexesToMultiShot(currentIndexList, credit.size))
        val capturedList = absenceIndexList.indices.intersect(currentIndexList.toSet()).toList()
        val restNumber = rollCallNumber - capturedList.size

        val restRollList = (1..credit.size)
            .toList()
            .subtract(currentIndexList.toSet())
            .zip(credit)
            .sortedBy {
                it.second
            }
            .subList(0, restNumber)
            .map {
                it.first
            }
        currentIndexList = (capturedList + restRollList).toList()

    }


    //TODO (implement your algorithm)

    output.write(rollList)
}