package _5_CoroutineContext

import commons.logger
import kotlinx.coroutines.*

fun main() {
    CoroutineContextOOTBDispatchers().run()
}

class CoroutineContextOOTBDispatchers {

//    private val dispatcher = Dispatchers.IO
//    private val dispatcher = Dispatchers.Default
//    private val dispatcher = Dispatchers.Main
//    private val dispatcher = Dispatchers.Unconfined

    fun run() {
        logger().info("SL4J warmup")
        runBlocking {
            repeat(10) {
                launch(Dispatchers.IO) { ioTask(it) }
            }
        }
    }

    private fun ioTask(i: Int) {
        Thread.sleep(1000)
        logger().info("Done $i")
    }
}