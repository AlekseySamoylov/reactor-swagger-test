package com.alekseysamoylov.reactortest

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2WebFlux


@EnableSwagger2WebFlux
@SpringBootApplication
class App {
  @Bean
  fun api() = Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .enable(true)
      .select()
      .paths(PathSelectors.ant("/**"))
      .build()

  private fun apiInfo() = ApiInfoBuilder()
      .title("Reactor test API")
      .version("1.0")
      .build()
}

fun main() {
  runApplication<App>()
}
