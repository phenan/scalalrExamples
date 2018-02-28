package com.phenan.scheme

import com.phenan.scalalr._

@dsl[Program]
object Scheme {

  def program (expressions: SExpr*): Program @syntax(s"$expressions") = Program(expressions.toList)

  def intLiteral (value: Int): LiteralExpr[Int] @syntax(s"$value") = LiteralExpr(value)

  def symbolLiteral (value: Symbol): LiteralExpr[Symbol] @syntax(s"$value") = LiteralExpr(value)

  def combination (head: SExpr, args: SExpr*): Combination @syntax(s"( $head $args )") = Combination(head, args.toList)

  def defineStatement (name: Symbol, expr: SExpr): Combination @syntax(s"( define $name $expr )") = {
    Combination(symbolLiteral('define), symbolLiteral(name) :: expr :: Nil)
  }

  def defineFunction (nameAndParams: FuncNameAndParams, body: SExpr*): Combination @syntax(s"( define $nameAndParams $body)") = {
    Combination(symbolLiteral('define), Combination(symbolLiteral(nameAndParams.name), nameAndParams.params.map(symbolLiteral).toList) :: body.toList)
  }

  @syntax(s"( $name $params )")
  case class FuncNameAndParams (name: Symbol, params: Symbol*)
}
