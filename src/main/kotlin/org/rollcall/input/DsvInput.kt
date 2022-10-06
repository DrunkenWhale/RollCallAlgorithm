package org.rollcall.input

import java.nio.file.Files
import java.nio.file.Path


class DsvInput(
    val path: Path,
    val sep: String = ","
) : Input {

    override fun read(): Pair<List<Lesson>, List<Double>> {
        val lines = Files.readAllLines(path)

        val gpaList = lines[0]
            .split(sep)
            .map { it.toDouble() }

        val lessonList = lines.subList(1, lines.size)
            .map { line ->
                line.split(sep)
                    .map { str -> str.toInt() }
            }

        return Pair(lessonList, gpaList)
    }

}