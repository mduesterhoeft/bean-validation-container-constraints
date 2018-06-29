package com.example.beanvalidationcontainerconstraints

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid
import javax.validation.constraints.NotEmpty

@RestController
@RequestMapping("/samples")
class SampleController {

    @PostMapping
    fun create(@RequestBody @Valid request: SamplePayload): ResponseEntity<Void> {
        return ResponseEntity.ok().build()
    }

    @ExceptionHandler
    fun handle(e: MethodArgumentNotValidException): ResponseEntity<Errors> =
        ResponseEntity.badRequest().body(
            Errors(e.bindingResult.fieldErrors.map {
                Error(it.field, it.defaultMessage!!)
            }))
}

data class Errors(val errors: List<Error>)
data class Error(val field: String, val message: String)

data class SamplePayload(
    @field:NotEmpty val name: String,
    @field:NotEmpty val someMap: Map<String, @NotEmpty String>)
