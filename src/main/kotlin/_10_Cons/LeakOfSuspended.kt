package _10_Cons

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

fun main() {
    LeakOfSuspended().run()
}

class LeakOfSuspended {
    fun run() {
        runBlocking {
            println("Time:" +
                    measureTimeMillis {
                        getData()
                    }
            )

            println(timeMeasured { getData() })
        }
    }

    private inline fun timeMeasured(jobToMeasure: () -> Any): String =
        "Time:" + measureTimeMillis {
            jobToMeasure()
        }

    private suspend fun getData(): String {
        delay(1000)
        return "data"
    }
}