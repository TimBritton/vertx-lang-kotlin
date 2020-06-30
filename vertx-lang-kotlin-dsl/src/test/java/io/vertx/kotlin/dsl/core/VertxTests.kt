package io.vertx.kotlin.dsl.core


import io.vertx.core.VertxOptions
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.*

class VertxTests {
  @Test
  fun `It should create vertx instance`(){
    val vertx = vertx {
      assertNotNull(this)

    }

    assertNotNull(vertx)

    vertx.close()
  }

  @Test
  fun `It should create vertx instance with options`(){
    val vertx = vertx(vertxOptions = VertxOptions()) {
      assertNotNull(this)
    }

    assertNotNull(vertx)
    vertx.close()
  }

  @Test
  fun `It should create vertx instance with coroutines`(){
   runBlocking {
     val vertx = vertxAwait {
       assertNotNull(this)
      delay(5)
     }

     assertNotNull(vertx)

     vertx.close()
   }
  }

  @Test
  fun `It should create vertx instance with options with coroutines`(){
    runBlocking {
      val vertx = vertxAwait(vertxOptions = VertxOptions()) {
        assertNotNull(this)
delay(5)
      }

      assertNotNull(vertx)
      vertx.close()
    }
  }

}
