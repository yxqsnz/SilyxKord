package dev.yxqsnz.silyx.command.vanilla.dev

import dev.kord.common.Color
import dev.kord.core.behavior.reply
import dev.yxqsnz.silyx.command.handler.CommandContext
import dev.yxqsnz.silyx.command.handler.TextCommand
import dev.yxqsnz.services.BlackListService
import java.sql.Timestamp


class XbanCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("xban") {
        override var onlyDev: Boolean = true
        override var aliases: List<String> = listOf("blacklist", "silyxban", "bl")
    }

    override suspend fun exec(context: CommandContext) {
        with(context) {
            if (args.isEmpty()) {
                message.reply {
                    content = "Você precisa botar um argumento!"
                }
                return
            }

            if (listOf("add", "banir", "b", "a").contains(args[0].toLowerCase())) {
                if (args.size < 3) {
                    message.reply {
                        content = "Você precisa botar um id e um motivo."
                    }
                    return
                }
                val userId = args[1]
                val reason = args.drop(0).drop(1).drop(1).joinToString(" ")
                if(BlackListService.isUserBlackListed(userId)) {
                    message.reply {
                        content = "Esse usuário já está banido do Silyx."
                    }
                    return
                }

                BlackListService.addUserToBlackList(
                    userId,
                    reason
                )
                this.messageReply("O Usuário $userId foi punido com êxito por $reason")

            }
            if (listOf("remover", "r", "unb", "desbanir", "ub", "unban").contains(args[0].toLowerCase())) {
                if (args.size < 2) {
                    message.reply {
                        content = "Você precisa botar um id."
                    }
                    return
                }
                val userId = args[1]
                if(!BlackListService.isUserBlackListed(userId)) {
                    message.reply {
                        content = "Esse usuário não está banido do Silyx."
                    }
                    return
                }
                BlackListService.removeUser(userId)
                messageReply("Usuário punição do usuário foi removida com êxito.")
            }

            if (listOf("ver", "info", "i", "v").contains(args[0].toLowerCase())) {
                if (args.size < 2) {
                    message.reply {
                        content = "Você precisa botar um id."
                    }
                    return
                }
                val userId = args[1]
                if(!BlackListService.isUserBlackListed(userId)) {
                    message.reply {
                            content = "Esse usuário não está banido do Silyx."
                    }
                        return
                }
                val user = BlackListService.getUser(userId)
                message.reply {
                    embed {
                        title = "Info do ban"
                        color = Color(255,66,77)
                        field {
                            name = "Banido por"
                            value = user.reason
                        }
                        field {
                            name = "ID"
                            value = user.userId
                        }
                        field {
                            name = "Banido em"
                            val timeStamp = Timestamp(user.bannedIn.toLong())

                            value = timeStamp.toLocalDateTime().toString()
                        }

                    }

                }
            }
        }

    }
}