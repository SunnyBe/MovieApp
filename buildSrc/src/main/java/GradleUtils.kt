import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.util.*

object GradleUtils {
    fun gradleProperties(projectRootDir: File, fileName: String): Properties {
        val properties = Properties()
        val localProperties = File(projectRootDir, fileName)

        if (localProperties.isFile) {
            InputStreamReader(FileInputStream(localProperties), Charsets.UTF_8).use { reader ->
                properties.load(reader)
            }
        } else {
//            infoln("Gradle local properties file not found at $localProperties")
            println("Gradle local properties file not found at $localProperties")
        }
        return properties
    }
}