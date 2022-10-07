import org.rollcall.core.extractSample
import org.rollcall.input.DsvInput
import org.rollcall.output.JsonOutput
import java.nio.file.Files
import java.nio.file.Path

const val rollCallNumber = 7

fun main(args: Array<String>) {

    val pathString = "data"
    val output = JsonOutput()
    val dsvInputList = Files.list(Path.of(pathString)).map { path ->
        DsvInput(path)
    }.toList()!!

    dsvInputList.forEach { extractSample(it, output, rollCallNumber) }

    val rollCallScheme = output.read()

    val inputDataList = dsvInputList.map { it.read().first }

    assert(rollCallScheme.size == inputDataList.size)
    assert(rollCallScheme[0].size == inputDataList[0].size)
    assert(rollCallScheme[0][0].size == inputDataList[0][0].size)

    val validRollCallNumber = rollCallScheme.flatten().flatten()
        .zip(inputDataList.flatten().flatten())
        .filter { it.first != 0 && it.first == it.second }
        .size


    val rollCallNumber = rollCallScheme.flatten().flatten().sum()

    val e = validRollCallNumber.toDouble() / rollCallNumber

    output.output(e)
}