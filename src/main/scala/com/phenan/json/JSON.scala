package com.phenan.json

import com.phenan.scalalr._

import org.json4s._

@dsl[JValue]
object JSON {
  @syntax(s"[ $values ]")
  def jArray (values: JValue@sep(",")*): JArray = JArray(values.toList)

  @syntax(s"{ $fields }")
  def jObject (fields: JField@sep(",")*): JObject = JObject(fields.toList)

  @syntax(s"$name : $value")
  def jField (name: String, value: JValue): JField = JField(name, value)

  @syntax(s"$value")
  def jDouble (value: Double): JDouble = JDouble(value)

  @syntax(s"$value")
  def jLong (value: Long): JLong = JLong(value)

  @syntax(s"$value")
  def jBool (value: Boolean): JBool = JBool(value)

  @syntax(s"$value")
  def jString (value: String): JString = JString(value)
}
