import org.rollcall.core.extractSample
import org.rollcall.input.DsvInput
import org.rollcall.output.ConsoleOutput
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {
    val pathString = "data"
    val output = ConsoleOutput()
    val dsvInputList = Files.list(Path.of(pathString)).map { path ->
        DsvInput(path)
    }.toList()!!

    dsvInputList.forEach { extractSample(it, output) }

    val rollCallScheme = output.read()

    val inputDataList = dsvInputList.map { it.read().first }

    assert(rollCallScheme.size == inputDataList.size)
    assert(rollCallScheme[0].size == inputDataList[0].size)
    assert(rollCallScheme[0][0].size == inputDataList[0][0].size)

    val validRollCallNumber = rollCallScheme.flatten().flatten()
        .zip(inputDataList.flatten().flatten())
        .map { it.first == it.second }
        .count { it }

    val rollCallNumber = rollCallScheme.flatten().flatten().count()

    val e = validRollCallNumber.toDouble() / rollCallNumber

}