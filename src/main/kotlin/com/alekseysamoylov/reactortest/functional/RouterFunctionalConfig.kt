package com.alekseysamoylov.reactortest.functional

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.HandlerFunction
import org.springframework.web.reactive.function.server.RequestPredicates.GET
import org.springframework.web.reactive.function.server.RequestPredicates.accept
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse
import springfox.documentation.builders.OperationBuilder
import springfox.documentation.builders.ResponseMessageBuilder
import springfox.documentation.schema.ModelRef
import springfox.documentation.service.ApiDescription
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spi.service.ApiListingScannerPlugin
import springfox.documentation.spi.service.contexts.DocumentationContext
import springfox.documentation.spring.web.readers.operation.CachingOperationNameGenerator


@Configuration
class RouterFunctionalConfig : ApiListingScannerPlugin {
  override fun supports(delimiter: DocumentationType?): Boolean {
    return true
  }

  override fun apply(context: DocumentationContext?): MutableList<ApiDescription> {
    val built = ResponseMessageBuilder().code(200).message("Integer response")
        .build()
    return mutableListOf(ApiDescription(
        "reactive-test",
        MONO_URL,
        "some description",
        listOf(OperationBuilder(CachingOperationNameGenerator())
            .method(HttpMethod.GET)
            .responseModel(ModelRef("Integer"))
            .responseMessages(setOf(built))
            .build()),
        false
    ))
  }

  @Bean
  fun route(intHandlerFunction: IntHandlerFunction): RouterFunction<ServerResponse> = RouterFunctions
      .route(GET(MONO_URL).and(accept(MediaType.APPLICATION_JSON)),
          HandlerFunction { intHandlerFunction.mono(it) }
      )
}

private const val MONO_URL = "/functional/mono";
