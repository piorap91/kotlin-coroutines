package _9_OtherSolutions

import _9_OtherSolutions.DomainSync.*
import io.reactivex.Maybe
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers.computation
import io.reactivex.schedulers.Schedulers.io
import java.util.*

class Rx2SyncExample(
    private val photoStore: PhotoStore,
    private val userStore: UserStore,
    private val diffService: DiffService,
    private val validationService: ValidationService,
) {
    fun photoDifference(photosToDiff: PhotosToDiff): String? {
        return Single.zip(
            Single.fromCallable { photoStore.getMetadata(photosToDiff.photo1.link) }.subscribeOn(io()),
            Single.fromCallable { photoStore.getMetadata(photosToDiff.photo2.link) }.subscribeOn(io()),
        ) { metadata1, metadata2 ->
            validationService.theSameParameters(metadata1, metadata2)
        }
            .filter { canBeDiffed -> canBeDiffed == true }
            .flatMapSingleElement {
                val preferences = Maybe.fromCallable { userStore.getOperationPreferences(photosToDiff.userId) }
                    .subscribeOn(io())
                    .toSingle("CPU")
                val photo1 = Single.fromCallable { photoStore.getPhoto(photosToDiff.photo1.link) }.subscribeOn(io())
                val photo2 = Single.fromCallable { photoStore.getPhoto(photosToDiff.photo1.link) }.subscribeOn(io())

                Single.zip(photo1, photo2, preferences) { p1, p2, pref ->
                    diffService.getDiff(p1, p2, pref)
                }
                    .subscribeOn(computation())
                    .flatMap { diff -> Single.fromCallable { photoStore.save(diff) }.subscribeOn(io()) }
                    .map { Optional.of(it.link) }
            }
            .blockingGet(Optional.empty()).orElse(null)
    }
}
