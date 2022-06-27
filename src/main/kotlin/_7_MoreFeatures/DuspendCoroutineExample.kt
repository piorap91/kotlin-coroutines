package _7_MoreFeatures

import commons.logger
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

fun main() {
    DuspendCoroutineExample(Api()).run()
}

class DuspendCoroutineExample(
    private val api: Api
) {

    fun run() {
        runBlocking {
            val user = callFromOtherAsyncFramework()
            logger().info("Returned $user")
        }
    }

    private suspend fun callFromOtherAsyncFramework(): String =
        suspendCoroutine { continuation ->
            api.getUserById("1") { user ->
                continuation.resume(user)
            }
        }

}

class Api {
    fun getUserById(id: String, callback: (String) -> Unit) {
        callback.invoke("User$id")
    }
}
