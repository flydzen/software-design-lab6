package tokens

import visitors.TokenVisitor

abstract class Brace(private val string: String) : Token {
    override fun accept(visitor: TokenVisitor) {
        visitor.visit(this)
    }

    override fun toString(): String = string
}

class Left : Brace("(")

class Right : Brace(")")
