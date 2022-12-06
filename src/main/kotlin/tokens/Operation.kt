package tokens

import visitors.TokenVisitor

abstract class Operation(private val string: String, private val function: (Int, Int) -> Int, val priority: Int) : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    open fun apply(a: Int, b: Int): Int = function(a, b)

    override fun toString(): String = string
}

class Plus : Operation("+", { a, b -> a + b }, 0)

class Minus : Operation("-", { a, b -> a - b }, 0)

class Div : Operation("/", { a, b -> a / b }, 1)

class Mul : Operation("*", { a, b -> a * b }, 1)
