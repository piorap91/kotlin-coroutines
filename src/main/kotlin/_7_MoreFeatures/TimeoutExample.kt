package _7_MoreFeatures

import commons.logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import java.lang.RuntimeException

fun main() {
    TimeoutExample().run()
}

class TimeoutExample {

    fun run() {
        runBlocking {
            logger().info("Starting")
            try {
                withTimeout(1000) {
                    shortTasks()
                }
            } catch (e: RuntimeException){
                logger().error("End", e)
            }
        }
    }

    private suspend fun shortTasks() {
        repeat(10){
            delay(1000)
        }
    }
}