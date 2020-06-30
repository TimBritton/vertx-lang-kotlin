package io.vertx.kotlin.dsl.core

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

fun main() {
  runBlocking {
    vertxAwait {
      val helloWorldVerticle = verticleAwait {

          start{
              println("Delaying for 1000 ms")
              delay(1000)
              println("Hello World")

            this@vertxAwait.close()
          }


      }

      deployVerticle(helloWorldVerticle)
    }


  }
}
