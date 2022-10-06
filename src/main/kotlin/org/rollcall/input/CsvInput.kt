package org.rollcall.input

import java.nio.file.Files
import java.nio.file.Path


class CsvInput(
    val path: Path
) : Input {

    override fun read(): Pair<List<Lesson>, List<Double>> {
        val lines = Files.readAllLines(path)

        val gpaList = lines[0]
            .split(",")
            .map { it.toDouble() }

        val lessonList = lines.subList(1, lines.size)
            .map { line ->
                line.split(",")
                    .map { str -> str.toInt() }
            }

        return Pair(lessonList, gpaList)
    }

}