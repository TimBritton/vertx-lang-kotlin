package io.vertx.kotlin.dsl.core

import io.vertx.core.Promise
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.jupiter.api.assertDoesNotThrow
import java.util.concurrent.Future
import kotlin.test.assertNotNull

class VerticleTests {
  @Test
  fun `It should create a verticle with the dsl`() {
    val verticle: FunctionalVerticle = verticle {
      start {
        println("The Verticle has started")
      }

    } as FunctionalVerticle
      assertNotNull(verticle)
      assertNotNull(verticle.startFun)

    assertDoesNotThrow {
      verticle.start(Promise.promise())
    }


  }


}
