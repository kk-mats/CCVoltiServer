package io.github.kk_mats.ccvoltiserver.application

import io.github.kk_mats.ccvoltiserver.domain.service.ValidationService
import io.github.kk_mats.ccvoltiserver.domain.type.Failable
import io.github.kk_mats.ccvoltiserver.domain.type.query.DetectionQuery
import io.github.kk_mats.ccvoltiserver.domain.type.response.DetectionResponse
import io.github.kk_mats.ccvoltiserver.domain.type.succeed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("run")
class ExecutionController {
	@Autowired
	lateinit var validationService: ValidationService

	@RequestMapping(path = ["/"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
	@ResponseBody
	fun root(@RequestBody query: DetectionQuery): Failable<DetectionResponse> {
		val r = this.validationService.validate(query)
		return when (r.error) {
			null -> succeed(DetectionResponse("success!"))
			else -> r.delegate()
		}
	}
}