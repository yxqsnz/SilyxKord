package dev.yxqsnz.silyx.workers

import kotlinx.coroutines.delay
import java.time.Instant

class UpdateStatusWorker: Worker("update Status Thread") {
     private val started: Instant = Instant.now()
     override suspend fun exec() {

         with(this.context) {
            while (true) {
                silyx.kord.editPresence {
                    since = started
                    playing("Sx cat")
                }
                delay(5000)
                silyx.kord.editPresence {
                    since = started
                    playing("Sx rw")
                }
                delay(5000)
                silyx.kord.editPresence {
                    since = started
                    playing("Criado pelo yxqsnz!")
                }
                delay(5000)
            }

         }
    }
}