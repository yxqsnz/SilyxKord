import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.kord.core.event.message.MessageCreateEvent
import dev.kord.core.on
import uai.yxqsnz.silyx.Config
import uai.yxqsnz.silyx.command.handler.CommandExecutor
import uai.yxqsnz.silyx.command.handler.CommandManager
import uai.yxqsnz.silyx.command.handler.CommandRegister
import uai.yxqsnz.silyx.events.*
class Silyx(internal var config: Config) {
    lateinit var kord: Kord
    val commandManager: CommandManager = CommandManager()
    private val commandExecutor: CommandExecutor = CommandExecutor(this)
    suspend fun start() {
        kord = Kord(this.config.discordToken)

        kord.on<ReadyEvent> {  onReady() }

        CommandRegister(commandManager).registerCommands()
        commandExecutor.startExecutor()


        kord.login();
    }

}
suspend fun main() {
    val silyx = Silyx(Config())
    silyx.start()

}
