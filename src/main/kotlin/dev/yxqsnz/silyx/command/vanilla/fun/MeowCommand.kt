package dev.yxqsnz.silyx.command.vanilla.`fun`
import com.beust.klaxon.Klaxon
import dev.kord.common.Color
import dev.kord.core.behavior.reply
import dev.yxqsnz.classes.command.*
import dev.yxqsnz.utils.network.requests

class MeowCommand: TextCommand(Options) {
    companion object Options: TextCommand.Options("cat"){
        override var description: String? = ""
        override var aliases = listOf("gato","meow")
    }
    private class CatApi(val file: String)
    override suspend fun exec(context: CommandContext) {
        with(context) {
            message.channel.type()
            val requestData: String = requests.get("https://aws.random.cat/meow").body!!.string()
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