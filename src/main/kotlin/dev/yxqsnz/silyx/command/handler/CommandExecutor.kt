// TODO: delay nos comandos.
package dev.yxqsnz.silyx.command.handler

import Silyx
import dev.kord.common.Color
import dev.kord.core.behavior.reply
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import dev.yxqsnz.classes.command.*
import dev.yxqsnz.services.CommandService
import dev.yxqsnz.services.BlackListService

class CommandExecutor(private val silyx: Silyx) {
    fun startExecutor() = silyx.kord.on<MessageCreateEvent> {
        val prefix = silyx.config.prefix

        if (this.message.author?.isBot == true ||
            !this.message.content.toLowerCase().startsWith(prefix)
            ) return@on

        val args = this.message.content.substring(prefix.length, this.message.content.length).trim().split(" ")
        val context = CommandContext(
            silyx.kord,
            this.message,
            args.drop(1)
        )
        val command = CommandService[args[0]] ?: return@on
        if (command.options.guildOnly && this.message.getGuildOrNull() == null) {
            message.reply {
                content = "❌ | Esse comando só pode ser executado em uma guilda."
            }
            return@on
        }
        if (message.author?.id?.asString?.let { BlackListService.isUserBlackListed(it) } == true) return@on


        if (command.options.onlyDev && !silyx.config.devsID.contains(message.author?.id?.asString)) {
            message.reply {
                content = "❌ | Desculpe mas esse comando só pode ser executado por pessoas especiais."
            }
            return@on
        }
        try {
            command.exec(context)
        } catch(e: Exception) {
            silyx.logger.error("No comando ${command.options.name}: ${e.message}")
            this.message.reply {
                embed {
                    title = "❌ | Me desculpe... mas ocorreu um erro ao executar esse comando"
                    field {
                     name = "Erro"
                     value = """
                         ```kt
                         ${e.message}
                         ```
                     """.trimIndent()
                    }
                    if (silyx.config.devsID.contains(message.author?.id?.asString)) {
                        field {
                            name = "strace trace"
                            value = """
                                ```kt
                                ${e.stackTrace}
                                ```
                            """.trimIndent()
                        }
                    }
                     color = Color(200,66,77)
                }

            }

        }

    }

}