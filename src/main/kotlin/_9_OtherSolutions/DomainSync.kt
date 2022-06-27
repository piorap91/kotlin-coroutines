package _9_OtherSolutions

class DomainSync {

    data class PhotosToDiff(
        val photo1: PhotoData,
        val photo2: PhotoData,
        val userId: Any
    )

    data class PhotoData(
        val link: String,
    )

    class PhotoStore {
        fun getMetadata(photoLink: Any): Any {
            TODO("Not yet implemented")
        }

        fun getPhoto(photo1Link: Any): Any {
            TODO("Not yet implemented")
        }

        fun save(diff: Any): PhotoData {
            TODO("Not yet implemented")
        }
    }

    class UserStore {
        fun getOperationPreferences(userId: Any): String? {
            TODO("Not yet implemented")
        }
    }


    class DiffService {
        fun getDiff(photo1: Any, photo2: Any, preferences: String): Any {
            TODO("Not yet implemented")
        }
    }


    class ValidationService {
        fun theSameParameters(metadata1: Any, metadata2: Any): Boolean {
            TODO("Not yet implemented")
        }
    }
}