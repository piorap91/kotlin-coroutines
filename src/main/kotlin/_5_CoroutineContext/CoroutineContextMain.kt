package _5_CoroutineContext

import commons.logger
import kotlinx.coroutines.*

fun main() {
    CoroutineContextMain().run()
}

class CoroutineContextMain {

    fun run() {
        logger().info("SL4J warmup")
        runBlocking {
            repeat(10) {
                launch { ioTask(it) }
            }
        }
    }

    private fun ioTask(i: Int) {
        Thread.sleep(1000)
        logger().info("Done $i")
    }
}