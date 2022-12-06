package visitors

import tokens.Brace
import tokens.NumberToken
import tokens.Operation
import tokens.Token
import java.util.*

class CalcVisitor(private val tokens: List<Token>) : TokenVisitor {
    private val stack = Stack<Int>()

    fun calculate() : Int {
        tokens.forEach { it.accept(this) }
        if (stack.size != 1) throw IllegalStateException("Expression cannot be counted")
        return stack.pop()
    }

    override fun visit(token: NumberToken) {
        stack.add(token.value)
    }

    override fun visit(token: Brace) =
        throw IllegalArgumentException("No brackets in the opz")

    override fun visit(token: Operation) {
        if (stack.size < 2) throw IllegalStateException("Incorrect expression")
        val a = stack.pop()
        val b = stack.pop()
        stack.add(token.apply(b, a))
    }
}
