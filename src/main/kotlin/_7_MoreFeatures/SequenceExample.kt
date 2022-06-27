package _7_MoreFeatures

import commons.logger

fun main() {
    SequenceExample(DB()).run()
}

class SequenceExample(
    private val db: DB
) {

    fun run() {
        db.getAll().forEach {
            logger().info("Name: $it")
        }
    }
}

class DB {
    fun getAll() = sequence {
        connection().use { connection ->
            connection.query("SELECT * FROM foo").use { resultSet ->
                while (resultSet.next()) {
                    yield(resultSet.getName())
                }
            }
        }
    }

    private fun connection() = Connection()

}

class Connection : AutoCloseable {
    fun query(query: String) = ResultSet()

    override fun close() {
        logger().info("Closing Connection")
    }
}

class ResultSet : AutoCloseable {
    private var index = -1
    private val data = (1..10).toList().map { "$it" }

    fun next() = (++index) < data.size
    fun getName() = data[index]

    override fun close() {
        logger().info("Closing ResultSet")
    }
}
