package org.rollcall.alg

import org.rollcall.output.Lesson


internal val rollCallSchema: (List<Lesson>, List<Double>, Int) -> List<Lesson> = { data, gpa, rollCallNumber ->
    val orderList = (0..gpa.size)
        .toList()
        .zip(gpa)
        .sortedBy { it.second }

    val currentList = orderList
        .subList(0, rollCallNumber)
        .map { it.first }

    val rollCallSchema = data.map { _ ->
        gpa.indices.map { if (it in currentList) 1 else 0 }
    }
    rollCallSchema
}