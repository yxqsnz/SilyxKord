package dev.yxqsnz.silyx.workers
import Silyx
import kotlin.collections.hashSetOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

object WorkerController {
    private val workerManager = WorkerManager()
    fun registerAllWorkers() {
        workerManager.registerWorker(UpdateStatusWorker())
    }
    suspend fun start(bot: Silyx) {
        bot.logger.info("Iniciando ${workerManager.workers.size} workers...")

        for (worker in workerManager.workers) {
            bot.logger.debug("Iniciando worker: ${worker.workerName}")
            val context =  WorkerContext(bot)
            worker.context = context
            try {
                fun execWorker() = runBlocking {
                    launch {
                        worker.exec()
                    }
                }
                val thread = Thread(fun() {
                    execWorker()
                })
                thread.start()
                bot.logger.success("Worker ${worker.workerName} foi iniciado com sucesso.")
            }catch (e: Exception) {
                bot.logger.error("Ocorreu um erro ao Iniciar o worker ${worker.workerName}: ${e.message}")
            }
        }

    }


}
class WorkerManager {
    val workers = hashSetOf<Worker>()
    fun registerWorker(worker: Worker) = workers.add(worker)


}

open class Worker(val workerName: String) {
    lateinit var context: WorkerContext

    open suspend fun exec() {}


}

open class WorkerContext(
    val silyx: Silyx
)
