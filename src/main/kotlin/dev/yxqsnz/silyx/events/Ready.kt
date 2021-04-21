package dev.yxqsnz.silyx.events
import Silyx
import dev.yxqsnz.silyx.workers.WorkerController

suspend fun onReady(silyx: Silyx) {
    silyx.logger.success("O Bot está pronto.")
    WorkerController.registerAllWorkers()
    WorkerController.start(silyx)

}
