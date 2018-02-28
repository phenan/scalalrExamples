package com.phenan.scheme

case class Env private (map: Map[SValue, SValue]) {
  def resolve (v: SValue): SValue = map.getOrElse(v, v)

  def register (name: SValue, value: SValue): Env = {
    Env(map + (name -> value))
  }

  def assignParams (parameters: List[SValue], args: List[SValue]): Env = {
    Env(map ++ parameters.zip(args))
  }
}

object Env {
  lazy val default: Env = Env(
    Map(SLiteral('define) -> DefineStatement)
  )
}
