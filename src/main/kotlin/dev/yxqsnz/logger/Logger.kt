package dev.yxqsnz.logger
import kotlin.system.exitProcess
import dev.yxqsnz.utils.humanizeBytes
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Logger {
    private fun getRam(): String {
        val runtime = Runtime.getRuntime()
        val usedMemory = (runtime.totalMemory() - runtime.freeMemory())
        return humanizeBytes(usedMemory)
    }
    private fun getTime(): String {
        val currentDateTime = LocalDateTime.now()
        return currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
    }

    fun debug(content: Any) =
        println("DEBUG [${getTime()}--${getRam()}]: $content")

    fun info(content: Any) =
        println("\u001b[36mINFO\u001b[37m [${getTime()}--${getRam()}]: $content")
    fun error(content: Any) =
        println("\u001b[31mERROR\u001b[37m [${getTime()}--${getRam()}]: $content")
    fun success(content: Any) =
        println("\u001b[32mSUCCESS\u001b[37m [${getTime()}--${getRam()}]: $content")

    fun warn(content: Any) =
        println("\u001b[33mWARN\u001b[37m [${getTime()}--${getRam()}]: $content")
    fun panic(e: Exception) {
        println("\u001b[31mPANIC\u001b[37m  [${getTime()}--${getRam()}]:  ${e.message}")
        e.printStackTrace()
        exitProcess(1)
    }

}