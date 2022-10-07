import org.rollcall.alg.calculateE
import org.rollcall.alg.rollCallSchema
import org.rollcall.core.extractSample
import org.rollcall.input.DsvInput
import org.rollcall.output.JsonOutput
import java.nio.file.Files
import java.nio.file.Path

fun main(args: Array<String>) {

    val rollCallNumber = 7
    val pathString = "data"
    val output = JsonOutput()
    val dsvInputList = Files.list(Path.of(pathString)).map { path ->
        DsvInput(path)
    }.toList()!!

    dsvInputList.forEach { extractSample(rollCallSchema, it, output, rollCallNumber) }

    val rollCallScheme = output.read()

    val inputDataList = dsvInputList.map { it.read().first }

    val e = calculateE(inputDataList, rollCallScheme)

    output.output(e)
}
