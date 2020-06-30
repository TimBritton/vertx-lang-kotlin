package io.vertx.kotlin.dsl.core

import io.vertx.core.*
import io.vertx.kotlin.coroutines.CoroutineVerticle

typealias PromisedStartFun = (startPromise: Promise<Void>?) -> Unit
typealias InitFun = (vertx: Vertx?, context: Context?) -> Unit
typealias SuspendStartFun = suspend () -> Unit

class VerticleBuilder {
  var start: PromisedStartFun? = null
  var init: InitFun? = null

  fun start(fn: PromisedStartFun) {
    start = fn;
  }

  fun init(initFun: InitFun) {
    init = initFun
  }

  fun getVerticle() = FunctionalVerticle(startFun = start, initFun = init)
}


class SuspendVerticleBuilder {
  var start: SuspendStartFun? = null
  var init: InitFun? = null

  fun start(fn: SuspendStartFun) {
    start = fn;
  }

  fun init(initFun: InitFun) {
    init = initFun
  }

  fun getVerticle() = FunctionalCoroutineVerticle(startFun = start, initFun = init)
}
class FunctionalVerticle(val startFun: PromisedStartFun?, val initFun: InitFun?) : AbstractVerticle() {

  override fun start(startPromise: Promise<Void>?) {
    super.start(startPromise)
    if (startFun != null) {
      (this.startFun)(startPromise)
    }
  }


  override fun init(vertx: Vertx?, context: Context?) {
    super.init(vertx, context)
    if (initFun != null) {
      (this.initFun)(vertx, context)
    }
  }
}

class FunctionalCoroutineVerticle(val startFun: SuspendStartFun?, val initFun: InitFun?) : CoroutineVerticle() {

  override suspend fun start() {
    super.start()
    if (startFun != null) {
      (this.startFun)()
    }
  }

  override fun init(vertx: Vertx, context: Context) {
    super.init(vertx, context)
    if(initFun != null){
      (this.initFun)(vertx, context)
    }
  }
}

fun verticle(fn: VerticleBuilder.() -> Unit): Verticle {
  val builder = VerticleBuilder()
  fn(builder)
  return builder.getVerticle()
}

suspend fun verticleAwait(fn: suspend SuspendVerticleBuilder.() -> Unit): Verticle {
  val builder = SuspendVerticleBuilder()
  fn(builder)
  return builder.getVerticle()
}
