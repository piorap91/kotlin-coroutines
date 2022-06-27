package _9_OtherSolutions

import _9_OtherSolutions.DomainSync.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class CoroutineExample(
    private val photoStore: PhotoStore,
    private val userStore: UserStore,
    private val diffService: DiffService,
    private val validationService: ValidationService,
) {

    fun photoDifference(photosToDiff: PhotosToDiff): String? = runBlocking(Default) {
        val metadata1 = async(IO) { photoStore.getMetadata(photosToDiff.photo1.link) }
        val metadata2 = async(IO) { photoStore.getMetadata(photosToDiff.photo2.link) }
        val canBeDiffed = validationService.theSameParameters(metadata1.await(), metadata2.await())
        return@runBlocking if (canBeDiffed) {
            val preferences = async(IO) { userStore.getOperationPreferences(photosToDiff.userId) ?: "CPU" }
            val photo1 = async(IO) { photoStore.getPhoto(photosToDiff.photo1.link) }
            val photo2 = async(IO) { photoStore.getPhoto(photosToDiff.photo1.link) }
            val diff = async { diffService.getDiff(photo1.await(), photo2.await(), preferences.await()) }
            async(IO) { photoStore.save(diff).link }.await()
        } else null
    }
}
