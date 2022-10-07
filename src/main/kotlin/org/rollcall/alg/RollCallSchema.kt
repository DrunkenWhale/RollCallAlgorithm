package org.rollcall.alg

import org.rollcall.output.Lesson

typealias RollCallSchema = (List<Lesson>, List<Double>, Int) -> List<Lesson>

internal val onlyRollCallFrequentlyAbsentStudents: RollCallSchema = { data, gpa, rollCallNumber ->

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

internal val rollCallStudentsPartlyBasedOnGpa: RollCallSchema = { data, gpa, rollCallNumber ->
    // rollCallNumber 2/3由绩点后50%学生中取 1/3由绩点前50%学生中取
    val orderList = gpa.indices
        .toList()
        .zip(gpa)
        .sortedBy { it.second }
        .map { it.first }

    val bottomList=orderList.subList(0,gpa.size/2)
    val topList=orderList.subList(gpa.size/2,gpa.size)

    val currentList = bottomList.shuffled().subList(0,rollCallNumber*2/3)+topList.shuffled().subList(0,rollCallNumber/3)

    val rollCallSchema = data.map { _ ->
        gpa.indices.map { if (it in currentList) 1 else 0 }
    }
    rollCallSchema
}