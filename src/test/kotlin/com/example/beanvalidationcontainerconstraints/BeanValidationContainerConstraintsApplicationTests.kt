package com.example.beanvalidationcontainerconstraints

import org.hamcrest.Matchers.hasSize
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class BeanValidationContainerConstraintsApplicationTests {

	@Autowired lateinit var mockMvc: MockMvc

	@Test
	fun `should fail with two validation errors`() {
		mockMvc.perform(post("/samples")
			.contentType(MediaType.APPLICATION_JSON)
			.content("""
    				{
    					"name": "",
    					"someMap": { "some": "" }
    				}""".trimIndent()))
			.andDo(MockMvcResultHandlers.print())
			.andExpect(status().isBadRequest)
			.andExpect(jsonPath("errors", hasSize<String>(2)))
	}

}
