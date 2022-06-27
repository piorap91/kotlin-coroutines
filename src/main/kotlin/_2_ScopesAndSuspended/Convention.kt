package _2_ScopesAndSuspended

import commons.logger
import kotlinx.coroutines.*

fun main() {
    Convention().function()
}

class Convention {

    /**
     * Can provide scope to use CoroutineScope functions
     */
    fun function() {
        runBlocking {
            functionWithScope()
        }
    }

    /**
     * Can use any function, suspended or not,
     * Can create new scopes
     */
    private fun CoroutineScope.functionWithScope() {
        launch { functionSuspended() }
    }

    /**
     * Can be suspended,
     * Can change context
     * Can use `coroutineScope{}` use CoroutineScope functions
     * Can other suspended or not functions
     */
    private suspend fun functionSuspended() {
        delay(1000)
        anyFunction()
    }

    private fun anyFunction() {
        logger().info("any")
    }
}
