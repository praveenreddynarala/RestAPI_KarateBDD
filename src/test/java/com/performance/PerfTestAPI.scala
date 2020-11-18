package com.performance

import com.intuit.karate.gatling.PreDef._
import io.gatling.core.Predef._
import scala.concurrent.duration._

class PerfTestAPI extends Simulation {

  val protocol = karateProtocol(
    "/api/users/{id}" -> Nil,
    "/api/users" -> pauseFor("get" -> 15, "post" -> 25, "put" -> 25, "patch" -> 25, "delete" -> 15)
  )

  protocol.nameResolver = (req, ctx) => req.getHeader("karate-name")

  val all_users = scenario("Get all users").exec(karateFeature("classpath:com/performance/PerfTest.feature"))
  val update_user = scenario("update a user").exec(karateFeature("classpath:com/performance/PerfPostRequests.feature"))

  setUp(
    all_users.inject(rampUsers(50).during(5 seconds)).protocols(protocol),
    update_user.inject(rampUsers(50).during(5 seconds)).protocols(protocol)
  )

  after {
    Thread.sleep(5000)
    println("Simulation is finished!")
  }
}
