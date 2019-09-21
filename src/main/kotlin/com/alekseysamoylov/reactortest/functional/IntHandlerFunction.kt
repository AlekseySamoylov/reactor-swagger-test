package com.alekseysamoylov.reactortest.functional

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@Component
class IntHandlerFunction {

  fun mono(serverRequest: org.springframework.web.reactive.function.server.ServerRequest): Mono<ServerResponse> {
    return ServerResponse.ok()
        .contentType(MediaType.APPLICATION_JSON)
        .body(
            Mono.just(5),
            Int::class.java
        )
  }
}
