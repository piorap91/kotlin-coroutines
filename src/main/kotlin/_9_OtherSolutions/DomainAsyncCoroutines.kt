package _9_OtherSolutions

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

class DomainAsyncCoroutines {

    data class PhotosToDiff(
        val photo1: PhotoData,
        val photo2: PhotoData,
        val userId: Any
    )

    data class PhotoData(
        val link: String,
    )

    class PhotoStore {
        suspend fun getMetadata(photoLink: Any): Deferred<Any> = coroutineScope {
            return@coroutineScope async(IO) {
                TODO("Not yet implemented")
            }
        }

        suspend fun getPhoto(photoLink: Any): Deferred<Any> = coroutineScope {
            return@coroutineScope async(IO) {
                TODO("Not yet implemented")
            }
        }

        suspend fun save(diff: Any): Deferred<PhotoData> = coroutineScope {
            return@coroutineScope async(IO) {
                TODO("Not yet implemented")
            }
        }
    }

    class UserStore {
        suspend fun getOperationPreferences(userId: Any): Deferred<String?> = coroutineScope {
            return@coroutineScope async(IO) {
                TODO("Not yet implemented")
            }
        }
    }


    class DiffService {
        fun getDiff(photo1: Any, photo2: Any, preferences: String): Any {
            TODO("Not yet implemented")
        }
    }


    class ValidationService {
        fun theSameParameters(it: Any, metadata2: Any): Boolean {
            TODO("Not yet implemented")
        }
    }
}