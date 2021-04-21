package dev.yxqsnz.silyx.command.handler
class CommandManager {
    private val commands = hashSetOf<TextCommand>()

    fun register(command: TextCommand) =
        commands.add(command)

    operator fun get(command: String): TextCommand? {
        var vanillaCommand = commands.firstOrNull { it.options.aliases.contains(command.toLowerCase()) }
        if (vanillaCommand == null) vanillaCommand = commands.firstOrNull { it.options.name == command.toLowerCase()}
        return vanillaCommand
    }
}