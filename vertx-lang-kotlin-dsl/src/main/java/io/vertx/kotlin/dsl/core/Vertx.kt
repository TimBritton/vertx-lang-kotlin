package io.vertx.kotlin.dsl.core

import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.kotlin.coroutines.dispatcher
import kotlinx.coroutines.withContext
import kotlin.coroutines.coroutineContext

fun vertx(vertxOptions: VertxOptions? = null, fn: Vertx.() -> Unit): Vertx{
  val vertx = if(vertxOptions != null) {
    Vertx.vertx(vertxOptions)
  } else {
    Vertx.vertx()
  }

  fn(vertx)
  return vertx
}

suspend fun vertxAwait(vertxOptions: VertxOptions? = null,  fn: suspend Vertx.() -> Unit ): Vertx {
  val vertx = if(vertxOptions != null) {
    Vertx.vertx(vertxOptions)
  } else {
    Vertx.vertx()
  }

  withContext(vertx.dispatcher()) {
    fn(vertx)
  }

  return vertx
}
