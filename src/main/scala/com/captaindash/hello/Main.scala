package com.captaindash.hello

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._

object Main extends App {
 implicit val system = ActorSystem("DemoHttp")
  implicit val materializer = ActorMaterializer()
  implicit val ec = system.dispatcher

    val route =
    pathSingleSlash {
      get {
        complete {
          "HTTP ready"
        }
      }
    }

  val bindingFuture = Http().bindAndHandle(route, "localhost", 8080)

  println("Application test started...")
  println("Web server up and running, listening on http://localhost:8080/")
  println("Press Ctrl-C to stop")
}