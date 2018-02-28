package com.phenan.json

import com.phenan.scalalr._
import JSON._

import org.scalatest._
import org.json4s._

import scala.language.postfixOps

class JSONTest extends FunSuite with DiagrammedAssertions {
  test ("double value") {
    val value: JValue = (10.0)

    assert (value == JDouble(10.0))
  }

  test ("string value") {
    val value: JValue = ("hello")

    assert (value == JString("hello"))
  }

  test ("boolean value") {
    val value: JValue = (true)

    assert (value == JBool(true))
  }

  test ("array") {
    val value: JValue = $$bracketleft (10.0)$$comma ("hello") $$bracketright

    assert (value == JArray(List(JDouble(10.0), JString("hello"))))
  }

  test ("object") {
    val value: JValue = ($$braceleft
                           ("foo") $$colon (false)$$comma
                           ("bar") $$colon $$bracketleft ("baz")$$comma (20.0) $$bracketright
                         $$braceright)

    assert (value == JObject(List(("foo", JBool(false)), ("bar", JArray(List(JString("baz"), JDouble(20.0)))))))
  }

}
