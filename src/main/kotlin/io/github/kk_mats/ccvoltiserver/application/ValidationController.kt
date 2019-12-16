package io.github.kk_mats.ccvoltiserver.application

import io.github.kk_mats.ccvoltiserver.domain.service.ValidationService
import io.github.kk_mats.ccvoltiserver.domain.type.Failable
import io.github.kk_mats.ccvoltiserver.domain.type.query.*
import io.github.kk_mats.ccvoltiserver.domain.type.query.Target
import io.github.kk_mats.ccvoltiserver.domain.type.succeed
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("validate")
class ValidationController {
	@Autowired
	lateinit var validationService: ValidationService

	@RequestMapping(
			path = ["/ccvolti", "/ccvolti/{version}"],
			method = [RequestMethod.POST],
			consumes = [MediaType.APPLICATION_JSON_VALUE]
	)
	@ResponseBody
	fun root(@PathVariable("version") version: String?, @RequestBody parameters: HashMap<String, String>): Failable<Boolean> {
		val r = this.validationService.validate(version, RawDetectionQuery(Target(Directory("", ""), Revision("", "")), "", parameters))
		if (r.value == null) {
			return r.delegate()
		}
		return succeed(true)
	}
}