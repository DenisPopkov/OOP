import java.io.ByteArrayOutputStream
import java.io.PrintStream

object GameTestUtil {

    val outputBuffer = ByteArrayOutputStream()
    val output = PrintStream(outputBuffer)

    fun String.getBufferLastLine() = substring(this.lastIndexOf("\n"))
}