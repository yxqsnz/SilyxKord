package dev.yxqsnz.classes.command

abstract class TextCommand(val options: Options) {
    abstract suspend fun exec(context: CommandContext)

    abstract class Options(val name: String) {
        open var description: String? = "Comando sem descrição"
        open var aliases: List<String> = listOf()
        open var guildOnly: Boolean = false
        open var onlyDev: Boolean = false
    }

}
