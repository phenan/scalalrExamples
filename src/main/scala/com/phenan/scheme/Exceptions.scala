package com.phenan.scheme

case class InvalidSchemeSyntaxException (msg: String) extends Exception(msg)

case class SchemeRuntimeException (msg: String) extends Exception(msg)
