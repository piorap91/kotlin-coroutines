package _9_OtherSolutions

import _9_OtherSolutions.DomainSync.*

class SyncExample(
    private val photoStore: PhotoStore,
    private val userStore: UserStore,
    private val diffService: DiffService,
    private val validationService: ValidationService,
) {
    fun photoDifference(photosToDiff: PhotosToDiff): String? {
        val metadata1 = photoStore.getMetadata(photosToDiff.photo1.link)
        val metadata2 = photoStore.getMetadata(photosToDiff.photo2.link)
        val canBeDiffed = validationService.theSameParameters(metadata1, metadata2)
        return if (canBeDiffed) {
            val preferences = userStore.getOperationPreferences(photosToDiff.userId) ?: "CPU"
            val photo1 = photoStore.getPhoto(photosToDiff.photo1.link)
            val photo2 = photoStore.getPhoto(photosToDiff.photo1.link)
            val diff = diffService.getDiff(photo1, photo2, preferences)
            photoStore.save(diff).link
        } else null
    }
}
