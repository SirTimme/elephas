package providers

import org.gradle.api.Project
import org.gradle.api.provider.Provider

fun Project.currentBranch(): String {
    return execute("git", "rev-parse", "--abbrev-ref", "HEAD").get()
}

fun Project.getLastChangeDate(): String {
    return execute("git", "log", "-n", "1", "--date=short", "--pretty=%cd").get().replace("-", "")
}

fun Project.getCommitShort(): String {
    return execute("git", "rev-parse", "HEAD").get().substring(0, 7)
}

fun Project.execute(vararg commands: String): Provider<String> {
    return providers.of(GitValueProvider::class.java) {
        parameters.commands.set(commands.toList())
    }
}