package org.rollcall.core

import org.rollcall.input.Input
import org.rollcall.output.Output


fun extractSample(input: Input, output: Output, rollCallNumber: Int) {
    val (data, credit) = input.read()

    val orderList = (0..credit.size)
        .toList()
        .zip(credit)
        .sortedBy { it.second }

    val currentList = orderList
        .subList(0, rollCallNumber)
        .map { it.first }

    val rollCallSchema = data.map { _ ->
//        val randomAbsenceList = (1..3).map { _ ->
//            orderList[Random.nextInt(rollCallNumber, rollCallNumber + 7)]
//        }.map { it.first }
//        credit.indices.map { if (it in currentList || it in randomAbsenceList) 1 else 0 }
        credit.indices.map { if (it in currentList) 1 else 0 }
    }

    output.write(rollCallSchema)
}