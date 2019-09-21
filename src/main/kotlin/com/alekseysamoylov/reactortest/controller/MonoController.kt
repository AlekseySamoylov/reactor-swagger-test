package com.alekseysamoylov.reactortest.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono


@RestController
class MonoController {

  @GetMapping("/mono")
  fun getMono() = Mono.just(1)
}
