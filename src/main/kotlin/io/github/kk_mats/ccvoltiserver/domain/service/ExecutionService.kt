package io.github.kk_mats.ccvoltiserver.domain.service

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.ObjectWriter
import io.github.kk_mats.ccvoltiserver.domain.repository.DetectorInfoRepository
import io.github.kk_mats.ccvoltiserver.domain.type.*
import io.github.kk_mats.ccvoltiserver.domain.type.query.DetectionQuery
import io.github.kk_mats.ccvoltiserver.domain.type.response.DetectionResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.nio.file.Files
import java.nio.file.Paths

@Service
class ExecutionService {
	@Autowired
	lateinit var detectorInfoRepository: DetectorInfoRepository

	fun run(query: DetectionQuery): Failable<DetectionResponse> {
		val output = Paths.get(query.output)

		for (l in listOf("-oc", "-ot", "-on", "-ocs")) {
			val ll = Label(l)
			val value = query.parameters[ll]
			if (value != null) {
				query.parameters[ll] = output.resolve(value).toString()
			}
		}

		query.parameters[Label("-jar", Int.MAX_VALUE)] = query.version.option
		query.parameters[Label("-d", Int.MAX_VALUE - 1)] = query.target.absolute

		val options = query.parameters.toList()
				.sortedWith(kotlin.Comparator { l1, l2 -> l2.first.priority.compareTo(l1.first.priority) })
				.flatMap { (label, value) -> listOf(label.option, value) }
				.toTypedArray()

		val pb = ProcessBuilder("java", *options).apply {
			redirectErrorStream(true)
		}

		val ins = Capture(pb)

		if (ins.exitCode != 0) {
			return fail(FailureCode.detectionFailed("java ${options.joinToString(" ")}", ins.input.toString()))
		}

		return succeed(DetectionResponse(output.fileName.toString(), output.toString(), listOf(), ins.input.toString()))
	}
}

class Capture(processBuilder: ProcessBuilder) {
	private var running = true
	val input = StringBuilder()
	val exitCode: Int

	init {
		val process = processBuilder.start()
		this.loadInputStream(process)
		this.exitCode = process.waitFor()
		this.running = false
	}

	private fun loadInputStream(p: Process) {
		val reader = BufferedReader(InputStreamReader(p.inputStream))
		Thread {
			reader.use { reader ->
				var line: String?
				while (this.running) {
					line = reader.readLine()
					if (line != null) {
						this.input.append(line + "\n")
					}
				}
			}
		}.start()
	}
}