package dev.yxqsnz.silyx.command.handler
class CommandManager {
    val commands = hashSetOf<TextCommand>()

    fun register(command: TextCommand) =
        commands.add(command)

    operator fun get(command: String): TextCommand? {
        var vanillaCommand = commands.firstOrNull { it.options.aliases?.contains(command.toLowerCase()) == true }
        if (vanillaCommand == null) vanillaCommand = commands.firstOrNull { it.options.name == command.toLowerCase()}
        return vanillaCommand
    }
}