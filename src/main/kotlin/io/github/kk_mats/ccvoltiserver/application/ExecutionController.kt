package io.github.kk_mats.ccvoltiserver.application

import io.github.kk_mats.ccvoltiserver.domain.service.ExecutionService
import io.github.kk_mats.ccvoltiserver.domain.service.ValidationService
import io.github.kk_mats.ccvoltiserver.domain.type.Failable
import io.github.kk_mats.ccvoltiserver.domain.type.query.RawDetectionQuery
import io.github.kk_mats.ccvoltiserver.domain.type.response.DetectionResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("run")
class ExecutionController {
	@Autowired
	lateinit var executionService: ExecutionService

	@Autowired
	lateinit var validationService: ValidationService

	@RequestMapping(
			path = ["/ccvolti", "/ccvolti/{version}"],
			method = [RequestMethod.POST],
			consumes = [MediaType.APPLICATION_JSON_VALUE]
	)
	@ResponseBody
	fun root(@PathVariable("version") version: String?, @RequestBody query: RawDetectionQuery): Failable<DetectionResponse> {
		val r = this.validationService.validate(version, query)
		r.value ?: return r.delegate()

		return this.executionService.run(r.value)
	}
}