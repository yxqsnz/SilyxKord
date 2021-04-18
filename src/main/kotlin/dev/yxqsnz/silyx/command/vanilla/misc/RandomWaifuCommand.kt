package dev.yxqsnz.silyx.command.vanilla.misc
import com.beust.klaxon.Klaxon
import dev.kord.common.Color
import dev.kord.core.behavior.channel.createEmbed
import dev.kord.core.behavior.reply
import dev.yxqsnz.silyx.command.handler.CommandContext
import dev.yxqsnz.silyx.command.handler.TextCommand
import dev.yxqsnz.utils.network.requests
import java.time.Instant

class RandomWaifuCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("randomwaifuimage") {
        override var aliases: List<String> = listOf("rw")
        override var description: String? = "random waifu image"
    }

    private class Api(val url: String)
    override suspend fun exec(context: CommandContext) {

        with(context) {

            var waifuType = "waifu"
            var sw = "sfw"
            if (args.isNotEmpty()) {
                waifuType = args[0]
                if (args.size > 1 && args[1] == "--nsfw")
                    sw = "nsfw"
                else
                    message.channel.type()

            }
            val url = "https://waifu.pics/api/$sw/$waifuType"
            val res = requests.get(url)
            @Suppress("BlockingMethodInNonBlockingContext")
            val requestBody = res.body!!.string()

            val error = when (res.code) {
                404 -> "waifu não encontrada."
                429 -> "Estou buscando muitas waifu agora.Tente Novamente mais tarde."
                403 -> "Não tenho permissão para buscar uma waifu agora."
                else -> "Erro desconhecido."
            }
            if (!res.isSuccessful || res.code != 200) {
                message.reply { content = "Ops, Ocorreu um erro ao buscar a sua waifu. Erro: $error" }
                return
            }
            val result = requestBody.let { Klaxon().parse<Api>(it) }



            if (sw == "nsfw")
                try {
                    message.author?.getDmChannel()?.createEmbed {
                        title = waifuType
                        description = "**[Clique aqui](${result!!.url})** para fazer o download da imagem."
                        image = result.url
                        author {
                            icon = message.author!!.avatar.url
                            name = message.author!!.username
                        }

                        color = Color(255, 66, 77)
                    }
                   
                }catch (e: Exception) {}
            else
                message.reply {
                    embed {
                        title = waifuType
                        description = "**[Clique aqui](${result!!.url})** para fazer o download da imagem."
                        image = result.url
                        author {
                            icon = message.author?.avatar?.url
                            name = message.author?.username
                        }
                        color = Color(255,66,77)

                    }
                    res.body!!.close()
                }

        }

    }

}