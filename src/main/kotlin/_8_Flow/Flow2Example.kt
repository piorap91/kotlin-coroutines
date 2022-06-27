package _8_Flow

import commons.logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    Flow2Example().run()
}

class Flow2Example {

    fun run() {
        runBlocking(Dispatchers.Default) {
            getFlow()
                .buffer(4)
                .onEach {
                    Thread.sleep(3000)
                    logger().info("Consuming $it")
                }.collect()
        }
    }

    private fun getFlow(): Flow<String> =
        flow {
            repeat(5) {
                delay(1000)
                logger().info("Sending $it")
                emit("$it")
            }
        }
}