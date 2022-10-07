import org.rollcall.alg.calculateE
import org.rollcall.alg.onlyRollCallFrequentlyAbsenceStudents
import org.rollcall.core.extractSample
import org.rollcall.input.DsvInput
import org.rollcall.output.ConsoleOutput

fun main() {

    val rollCallNumber = 7
    val output = ConsoleOutput()
    val fileList = listOf(
        "data/lesson1.csv",
        "data/lesson2.csv",
        "data/lesson3.csv",
        "data/lesson4.csv",
        "data/lesson5.csv"
    )
    val dsvInputList = fileList.map { path ->
        DsvInput(path)
    }.toList()


    dsvInputList.forEach { extractSample(onlyRollCallFrequentlyAbsenceStudents, it, output, rollCallNumber) }

    val rollCallScheme = output.read()

    val inputDataList = dsvInputList.map { it.read().first }

    val e = calculateE(inputDataList, rollCallScheme)

    output.output(e)
}

