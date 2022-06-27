package _8_Flow

import commons.logger
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce

fun main() {
    ChannelExample().run()
}

class ChannelExample {

    fun run() {
        runBlocking {
            val channel = getChannel()
            delay(5000)
            channel.consumeEach {
                logger().info("Consuming $it")
            }
        }
    }

    private fun CoroutineScope.getChannel(): ReceiveChannel<String> =
        produce {
            repeat(5) {
                delay(100)
                logger().info("Sending $it")
                send("$it")
            }
        }
}