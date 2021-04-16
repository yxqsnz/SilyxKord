package dev.yxqsnz.silyx.command.vanilla.misc
import io.ktor.client.*
import io.ktor.client.request.*
import com.beust.klaxon.Klaxon
import dev.kord.common.Color
import dev.kord.core.behavior.reply
import dev.yxqsnz.silyx.command.handler.CommandContext
import dev.yxqsnz.silyx.command.handler.TextCommand

class RandomWaifuCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("randomwaifuimage") {
        override val aliases: List<String> = listOf("rw")
        override val description: String = "random waifu image"
    }

    private class Api(val url: String)
    override suspend fun exec(context: CommandContext) {

        with(context) {
            message.channel.type()
            var waifuType = "waifu"
            if (args.size == 1)
                waifuType = args[0]

            val url = "https://waifu.pics/api/sfw/$waifuType"
            val client = HttpClient()
            val requestBody: String = client.get(url)
            val response = Klaxon().parse<Api>(requestBody)


            if (response?.url == null) {
                message.reply { content = "Ops, Ocorreu um erro ao buscar a sua waifu." }
                return
            }
            message.reply {
                embed {
                    title = waifuType
                    description = "**[Clique aqui](${response.url})** caso a imagem não apareça."
                    image = response.url
                    author {
                        icon = message.author?.avatar?.url
                        name = message.author?.username
                    }
                    color = Color(255,66,77)
                }
            }

        }

    }

}