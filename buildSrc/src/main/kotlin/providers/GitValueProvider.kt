package providers

import org.gradle.api.provider.ListProperty
import org.gradle.api.provider.ValueSource
import org.gradle.api.provider.ValueSourceParameters
import org.gradle.process.ExecOperations
import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import javax.inject.Inject

abstract class GitValueProvider : ValueSource<String, GitValueProvider.Parameters> {
    interface Parameters : ValueSourceParameters {
        val commands: ListProperty<String>
    }

    @get:Inject
    abstract val execOperations: ExecOperations

    override fun obtain(): String {
        val output = ByteArrayOutputStream()
        execOperations.exec {
            commandLine(parameters.commands.get())
            standardOutput = output
        }
        return String(output.toByteArray(), Charset.defaultCharset())
    }
}