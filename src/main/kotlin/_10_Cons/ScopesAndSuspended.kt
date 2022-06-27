package _10_Cons

import commons.logger
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
   ScopesAndSuspended().run()
}

class ScopesAndSuspended {

    fun run() {
        runBlocking {
            helloCoroutinesWorld()
        }
        exclamationMarks()
    }

    private fun CoroutineScope.helloCoroutinesWorld() {
        hello()
        launch { world() }
        coroutines()
    }

    private fun hello() = logger().info("Hello")

    private suspend fun world() {
        delay(1000)
        logger().info("World")
    }

    private fun coroutines() = logger().info("Coroutines")

    private fun exclamationMarks() = logger().info("!!!")
}
