package com.phenan.scheme

sealed trait SValue

case object SUnit extends SValue

case class SLiteral [T] (value: T) extends SValue

case class SFunction (function: List[SValue] => SValue) extends SValue

case class SMacro (macroFunction: List[SExpr] => SExpr) extends SValue

sealed trait SpecialForm extends SValue {
  def apply (env: Env, args: List[SExpr]): (Env, SValue)
}

case object DefineStatement extends SpecialForm {
  def apply (env: Env, args: List[SExpr]): (Env, SValue) = args match {

    // (define (<name> <param-list>) <body>)
    case Combination(name, params) :: body =>
      (env.register(name.exec(env), SFunction(args => Program(body).exec(env.assignParams(params.map(_.exec(env)), args)))), SUnit)

    // (define <name> <expr>)
    case name :: expr :: Nil =>
      (env.register(name.exec(env), expr.exec(env)), SUnit)

    // syntax error
    case _ =>
      throw InvalidSchemeSyntaxException("special form 'define' should follow (define <name> <expr>) or (define (<name> <param-list>) <body>)")
  }
}
