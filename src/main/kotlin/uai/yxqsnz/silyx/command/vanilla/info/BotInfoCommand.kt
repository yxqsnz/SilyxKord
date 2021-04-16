package uai.yxqsnz.silyx.command.vanilla.info

import dev.kord.common.Color
import dev.kord.core.behavior.reply
import uai.yxqsnz.silyx.command.handler.CommandContext
import uai.yxqsnz.silyx.command.handler.TextCommand
import java.text.CharacterIterator
import java.text.StringCharacterIterator


fun b(bytes: Long): String? {
    val absB = if (bytes == Long.MIN_VALUE) Long.MAX_VALUE else Math.abs(bytes)
    if (absB < 1024) {
        return "$bytes B"
    }
    var value = absB
    val ci: CharacterIterator = StringCharacterIterator("KMGTPE")
    var i = 40
    while (i >= 0 && absB > 0xfffccccccccccccL shr i) {
        value = value shr 10
        ci.next()
        i -= 10
    }
    value *= java.lang.Long.signum(bytes).toLong()
    return String.format("%.1f %ciB", value / 1024.0, ci.current())
}
class BotInfoCommand: TextCommand(Options) {
    companion object Options:TextCommand.Options("botinfo") {
        override val description: String = "BotInfo"
        override val aliases: List<String> = listOf("stats","status","info")
    }

    override suspend fun exec(context: CommandContext) {

        with(context) {
            message.channel.type()

            val runtime = Runtime.getRuntime()
            val mb = 1024 * 1024
            val usedMemory = (runtime.totalMemory() - runtime.freeMemory())

            val totalMemory = runtime.totalMemory()
            val maxMemory = runtime.maxMemory()
            val usedPercentage = (100 * usedMemory) / maxMemory
            val freeMemory = maxMemory - usedMemory
            message.reply {
                
                embed {
                    title = "Minhas informações"
                    description = """
                    Olá, Me chamo Silyx um simples bot. fui feito pelo yxqsnz em <:kotlin_logo:832277232721461339> [Kotlin](https://kotlinlang.org/)
                    usando a biblioteca <:kord_logo:832277232394567692> [Kord](https://github.com/kordlib/kord) 
                    
                    :computer: Ram: `Usados ${b(usedMemory)}/${b(maxMemory)} ($usedPercentage% usados,${b(freeMemory)} Livres.) `
                    <:kotlin_logo:832277232721461339> Versão do Kotlin: ${KotlinVersion.CURRENT}
                    <:kord_logo:832277232394567692> Versão do Kord: 0.7.x-SNAPSHOT
    
                """.trimIndent()
                color = Color(255,35,45)
                }
            }
        }

    }
}