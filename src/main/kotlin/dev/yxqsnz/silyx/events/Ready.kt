package dev.yxqsnz.silyx.events
import Silyx
import dev.yxqsnz.services.BlackListService.loadBlackList
import dev.yxqsnz.silyx.workers.WorkerController

suspend fun onReady(silyx: Silyx) {
    silyx.logger.success("O Bot está pronto.")
    WorkerController.registerAllWorkers()
    WorkerController.start(silyx)
    silyx.logger.info("Carregando Xban (blacklist)...")
    try {
        val bannedUsersCount = loadBlackList(silyx.database.mongoClient)
        silyx.logger.success("$bannedUsersCount Membros estão banidos do bot.")

    } catch(e: Exception) {
        silyx.logger.error("Erro ao carregar Xban.")
        silyx.logger.panic(e)
    }



}
