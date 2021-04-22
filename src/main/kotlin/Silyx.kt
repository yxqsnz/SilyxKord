import dev.kord.core.Kord
import dev.kord.core.event.gateway.ReadyEvent
import dev.yxqsnz.logger.Logger
import dev.kord.core.on
import dev.yxqsnz.silyx.classes.Configuration
import dev.yxqsnz.utils.readConfigFile
import dev.yxqsnz.silyx.events.onReady
import dev.yxqsnz.silyx.command.handler.CommandExecutor
import dev.yxqsnz.silyx.command.handler.CommandRegister
import dev.yxqsnz.silyx.command.handler.CommandManager
import dev.yxqsnz.database.Client as database

class Silyx(internal var config: Configuration) {
    lateinit var kord: Kord
    var logger: Logger = Logger()
    val commandManager: CommandManager = CommandManager()
    val database = database()
    private val commandExecutor: CommandExecutor = CommandExecutor(this)
    suspend fun start() {
        kord = Kord(this.config.discordToken)
        kord.on<ReadyEvent> {  onReady(this@Silyx) }

        CommandRegister(commandManager).registerCommands()
        commandExecutor.startExecutor()
        try {
            database.connect(config.mongoUri)
            logger.success("Conectado ao mongodb com sucesso.")
        } catch (e: Exception) {
            logger.error("Ocorreu um erro ao se conectar ao mongodb!")
            logger.panic(e)

        }

        kord.login()
    }

}
suspend fun main() {
    val silyx = readConfigFile()?.let { Silyx(it) }
    silyx?.logger?.info("Eu sou o Silyx um Simples bot para o discord!")
    try {
        silyx!!.start()
    } catch(e: Exception) {
        silyx?.logger?.error("Sorry... Ocorreu um erro ao executar o bot!")
        silyx?.logger?.panic(e)

    }

}
