package _3_Decompile

import commons.logger
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

//Tools -> Kotlin -> Show Kotlin Bytecode -> Decompile
fun main() {
    Decompile().run()
}

class Decompile {
    fun run() {
        runBlocking {
            launch { foo() }
        }
    }

    private suspend fun foo() {
        logger().info("1")
        delay(1000)
        logger().info("2")
    }
}