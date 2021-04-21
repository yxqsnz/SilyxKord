package dev.yxqsnz.silyx.command.vanilla.utils

import com.github.doyaaaaaken.kotlincsv.dsl.csvReader
import dev.kord.common.Color
import dev.kord.core.behavior.edit
import dev.kord.core.behavior.reply
import dev.yxqsnz.silyx.command.handler.CommandContext
import dev.yxqsnz.silyx.command.handler.TextCommand
import dev.yxqsnz.utils.network.requests
import java.time.Instant

class ReadCsvCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("lercsv") {
        override var aliases: List<String>  = listOf("ler_csv","csv")
    }
    private fun parseCSV(csv: List<List<String>>): String {
        var result = ""
        for (row in csv) {
            result += "${row.joinToString("|").trim()}\n".trimStart()
        }
        if (result.length >= 1019) {
            result.removeRange(1019,result.length)
            result += "..."
        }
        result.trim()
        return result
    }

    override suspend fun exec(context: CommandContext) {
        with(context) {
            if (message.attachments.isEmpty()) return@with message.reply {
                content = "Por favor anexe um arquivo."
            }

            val msg = message.reply {
                embed {
                    title = "Carregando..."
                    description = "⏱️ | Processando arquivo `${message.attachments.first().filename}`..."
                    author {
                        icon = message.author?.avatar?.url
                        name = message.author?.username
                    }
                    timestamp = Instant.now()
                }
            }
            try {
                val csvFile = requests.get(message.attachments.first().url).body!!.string()
                val rows: List<List<String>> = csvReader().readAll(csvFile)
                msg.edit {
                    embed {
                        title = message.attachments.first().filename
                        field {
                            name = "Dados"
                            value = """
                            ```kt
                            ${parseCSV(rows)}
                            ```
                        """.trimIndent()
                        }
                        color = Color(255, 66, 77)
                        author {
                            icon = message.author?.avatar?.url
                            name = message.author?.username
                        }
                        timestamp = Instant.now()
                    }

                }

            } catch (e: Exception) {
                println(e.message)
                e.printStackTrace()
                msg.edit {
                    embed {
                        title = message.attachments.first().filename
                        description = "Ocorreu um erro ao ler esse arquivo me desculpe."
                        color = Color(255,65,77)
                    }

                }
            }
        }
    }
}