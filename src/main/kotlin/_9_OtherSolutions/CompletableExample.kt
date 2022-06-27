package _9_OtherSolutions

import _9_OtherSolutions.DomainSync.*
import java.util.concurrent.CompletableFuture.supplyAsync

class CompletableExample(
    private val photoStore: PhotoStore,
    private val userStore: UserStore,
    private val diffService: DiffService,
    private val validationService: ValidationService,
) {
    fun photoDifference(photosToDiff: PhotosToDiff): String? {
        val metadata1 = supplyAsync { photoStore.getMetadata(photosToDiff.photo1.link) }
        val metadata2 = supplyAsync { photoStore.getMetadata(photosToDiff.photo2.link) }
        val canBeDiffed = validationService.theSameParameters(metadata1.get(), metadata2.get())
        return if (canBeDiffed) {
            val preferences = supplyAsync { userStore.getOperationPreferences(photosToDiff.userId) }
            val photo1 = supplyAsync { photoStore.getPhoto(photosToDiff.photo1.link) }
            val photo2 = supplyAsync { photoStore.getPhoto(photosToDiff.photo1.link) }
            val diff = supplyAsync { diffService.getDiff(photo1.get(), photo2.get(), preferences.get() ?: "CPU") }
            photoStore.save(diff).link
        } else null
    }
}
