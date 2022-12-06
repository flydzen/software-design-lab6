package visitors

import tokens.*
import java.util.*

class ParserVisitor(private val tokens: List<Token>) : TokenVisitor {
    private val stack = Stack<Token>()
    private val opsTokens = mutableListOf<Token>()

    fun parse(): List<Token> {
        tokens.forEach { it.accept(this) }
        while (stack.isNotEmpty()) {
            val prev = stack.pop()
            if (prev !is Operation) throw IllegalStateException("Incorrect expression")
            opsTokens.add(prev)
        }
        if (opsTokens.count { it is NumberToken } - opsTokens.count { it is Operation } != 1)
            throw IllegalStateException("Incorrect expression")
        return opsTokens
    }

    override fun visit(token: NumberToken) {
        opsTokens.add(token)
    }

    override fun visit(token: Brace) {
        when (token) {
            is Left -> stack.add(token)
            is Right -> {
                while (stack.isNotEmpty() and (stack.peek() !is Left)) {
                    opsTokens.add(stack.pop())
                }
                if (stack.isEmpty()) throw IllegalStateException("Brackets are not consistent")
                stack.pop()
            }
        }
    }

    override fun visit(token: Operation) {
        while (stack.isNotEmpty()) {
            val prev = stack.peek()
            if (prev is Operation && prev.priority >= token.priority) opsTokens.add(stack.pop())
            else break
        }
        stack.add(token)
    }
}
