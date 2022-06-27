package _8_Flow

import commons.logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking

fun main() {
    FlowExample().run()
}

class FlowExample {

    fun run() {
        runBlocking {
            val flow = getFlow()
            delay(5000)
            flow.onEach {
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