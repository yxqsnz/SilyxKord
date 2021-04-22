package dev.yxqsnz.silyx.command.vanilla.misc

import dev.kord.core.behavior.reply
import dev.yxqsnz.classes.command.*
import dev.yxqsnz.silyx.command.vanilla.utils.Colors
import dev.yxqsnz.utils.humanizeBytes

class RamInfoCommand: TextCommand(Options) {
    private var lastRam: Long = 0
    companion object Options: TextCommand.Options("ramInfo") {
        override var aliases = listOf("ri", "ram", "ramStatus")
        override var description: String? = "mostra o uso de ram do bot com detalhes"
    }

    override suspend fun exec(context: CommandContext) {
        with(context) {
            message.channel.type()
            val runtime = Runtime.getRuntime()
            val usedMemory = (runtime.totalMemory() - runtime.freeMemory())
            val maxMemory = runtime.maxMemory()
            val usedPercentage = (100 * usedMemory) / maxMemory
            val freeMemory = maxMemory - usedMemory
            val op = if (lastRam > usedMemory) "" else "+"
            val diff = usedMemory - lastRam
            message.reply {
                embed {
                    title = "informações do meu uso de ram"
                    color = Colors.SILYX_RED
                    author {
                        name = message.author!!.username
                        icon = message.author?.avatar?.url
                    }
                    field {
                        name = "Usando"
                        value = "$usedPercentage% - ${humanizeBytes(usedMemory)}"
                    }
                    field {
                        name = "Máximo"
                        value = humanizeBytes(maxMemory)
                    }
                    field {
                        name = "Livre"
                        value = humanizeBytes(freeMemory)
                    }
                    footer {
                        text = "Usando ${humanizeBytes(usedMemory)} [$op${humanizeBytes(diff)}]"
                    }


                }
                lastRam = usedMemory
            }
        }
    }
}