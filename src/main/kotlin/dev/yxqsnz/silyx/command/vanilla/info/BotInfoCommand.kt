package dev.yxqsnz.silyx.command.vanilla.info

import dev.kord.common.Color
import dev.kord.core.behavior.reply
import dev.yxqsnz.classes.command.*
import dev.yxqsnz.utils.humanizeBytes as b


class BotInfoCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("botinfo") {
        override var description: String? = "BotInfo"
        override var aliases: List<String> = listOf("stats","status","info")
    }

    override suspend fun exec(context: CommandContext) {

        with(context) {
            message.channel.type()

            val runtime = Runtime.getRuntime()
            val usedMemory = (runtime.totalMemory() - runtime.freeMemory())
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