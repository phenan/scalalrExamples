package com.phenan.scheme

import com.phenan.scalalr._
import Scheme._

import org.scalatest._

import scala.language.postfixOps

class SchemeTest extends FunSuite with DiagrammedAssertions {
  test("test1") {
    val program: Program = (
      $$parenleft define $$parenleft ('foo) ('a) $$parenright ('a) $$parenright
      $$parenleft ('foo) (10) $$parenright
    )

    println(program.exec(Env.default))
  }
}
