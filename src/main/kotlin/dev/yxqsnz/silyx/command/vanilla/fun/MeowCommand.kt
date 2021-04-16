package dev.yxqsnz.silyx.command.vanilla.`fun`
import com.beust.klaxon.Klaxon
import dev.kord.common.Color
import dev.kord.core.behavior.reply
import dev.yxqsnz.silyx.command.handler.CommandContext
import dev.yxqsnz.silyx.command.handler.TextCommand
import uai.yxqsnz.silyx.command.handler.*
import io.ktor.client.*
import io.ktor.client.request.*
class CatApi(val file: String)
class MeowCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("cat"){
        override val description: String = ""
        override val aliases = listOf("gato","meow")
    }
    override suspend fun exec(context: CommandContext) {
        with(context) {
            message.channel.type()
            val requestData: String = HttpClient().get("https://aws.random.cat/meow")
            val json = Klaxon().parse<CatApi>(requestData)
            message.reply {
                embed {
                    title = "Meow!"
                    description = "**[clique aqui](${json?.file})** caso a imagem não apareça."
                    image = json?.file
                    author {
                        icon = message.author?.avatar?.url
                        name = message.author?.username
                    }
                    color = Color(255,125,22)
                }
            }
        }


    }
}