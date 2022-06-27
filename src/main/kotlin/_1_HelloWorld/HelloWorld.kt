package _1_HelloWorld

import commons.logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    HelloWorld().run()
}

class HelloWorld {
    fun run() {
        runBlocking {
            logger().info("Hello")
            launch {
                delay(1000)
                logger().info("World")
            }
            logger().info("Coroutines")
        }
        logger().info("!!!")
    }
}