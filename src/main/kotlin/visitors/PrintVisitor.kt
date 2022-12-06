package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation
import tokens.Token

class PrintVisitor(private val tokens: List<Token>) : TokenVisitor {

    fun print() {
        tokens.forEach { it.accept(this) }
        println()
    }

    override fun visit(token: NumberToken) {
        print("$token ")
    }

    override fun visit(token: Brace) {
        print("$token ")
    }

    override fun visit(token: Operation) {
        print("$token ")
    }
}
