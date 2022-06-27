package _4_StructuredConcurrency

import commons.logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.RuntimeException

fun main() {
    StructuredConcurrency().run()
}

class StructuredConcurrency {
    fun run() {
        runBlocking {
            launch { logEverySecForTenSec() }
            launch { throwAfter5Sec() }
        }
    }

    private suspend fun logEverySecForTenSec() {
        repeat(10) {
            logger().info("Logging: $it")
            delay(1000)
        }
    }

    private suspend fun throwAfter5Sec() {
        delay(5000)
        throw RuntimeException()
    }
}