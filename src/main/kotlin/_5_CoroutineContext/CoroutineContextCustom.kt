package _5_CoroutineContext

import commons.logger
import kotlinx.coroutines.launch
import kotlinx.coroutines.newFixedThreadPoolContext
import kotlinx.coroutines.runBlocking

fun main() {
    CoroutineContextCustom().run()
}

class CoroutineContextCustom {

    private val dispatcher = newFixedThreadPoolContext(4, "myThreads")

    fun run() {
        logger().info("SL4J warmup")
        runBlocking {
            repeat(10) {
                launch(dispatcher) { ioTask(it) }
            }
        }
    }

    private fun ioTask(i: Int) {
        Thread.sleep(1000)
        logger().info("Done $i")
    }
}