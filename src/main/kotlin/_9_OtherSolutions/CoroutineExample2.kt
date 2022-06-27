package _9_OtherSolutions

import _9_OtherSolutions.DomainAsyncCoroutines.*
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.runBlocking

class CoroutineExample2(
    private val photoStore: PhotoStore,
    private val userStore: UserStore,
    private val diffService: DiffService,
    private val validationService: ValidationService,
) {

    fun photoDifference(photosToDiff: PhotosToDiff): String? = runBlocking(Default) {
        val metadata1 = photoStore.getMetadata(photosToDiff.photo1.link)
        val metadata2 = photoStore.getMetadata(photosToDiff.photo2.link)
        val canBeDiffed = validationService.theSameParameters(metadata1.await(), metadata2.await())
        return@runBlocking if (canBeDiffed) {
            val preferences = userStore.getOperationPreferences(photosToDiff.userId)
            val photo1 = photoStore.getPhoto(photosToDiff.photo1.link)
            val photo2 = photoStore.getPhoto(photosToDiff.photo1.link)
            val diff = diffService.getDiff(photo1.await(), photo2.await(), preferences.await() ?: "CPU")
            photoStore.save(diff).await().link
        } else null
    }
}
