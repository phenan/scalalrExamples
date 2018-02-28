package com.phenan.scheme

sealed trait SExpr {
  def eval (env: Env): (Env, SValue) = {
    val (e, v) = evaluate(env)
    (e, e.resolve(v))
  }
  def exec (env: Env): SValue = eval(env)._2

  protected def evaluate (env: Env): (Env, SValue)
}

case class LiteralExpr [T] (value: T) extends SExpr {
  protected def evaluate (env: Env): (Env, SValue) = (env, SLiteral(value))
}

case class Combination (head: SExpr, args: List[SExpr]) extends SExpr {
  protected def evaluate (env: Env): (Env, SValue) = env.resolve(head.exec(env)) match {

    // 関数呼び出し
    case SFunction(f) =>
      (env, f(args.map(_.exec(env))))

    // マクロ呼び出し
    case SMacro(m) =>
      m(args).eval(env)

    // 特殊形式
    case special: SpecialForm =>
      special(env, args)

    // その他
    case other =>
      throw SchemeRuntimeException(s"$other is not a name of function / macro / special form")

  }
}

case class Program (expressions: List[SExpr]) {
  def eval (env: Env): (Env, SValue) = evalExprs(env, expressions)
  def exec (env: Env): SValue = eval(env)._2

  private def evalExprs (env: Env, expressions: List[SExpr]): (Env, SValue) = expressions match {
    case expr :: Nil  => expr.eval(env)
    case expr :: rest => evalExprs(expr.eval(env)._1, rest)
    case Nil          => (env, SUnit)
  }
}
