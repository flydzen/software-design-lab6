package tokenizer

import tokens.*

class StartState(private val tokenizer: Tokenizer, private var symbol: Char? = null) : TokenizerState {
    override fun next() {
        if (symbol == null) symbol = tokenizer.nextSymbol()
        when (symbol) {
            (-1).toChar() -> tokenizer.changeState(EndState())
            in '0'..'9' -> tokenizer.changeState(NumberState(tokenizer, StringBuffer().append(symbol)))
            '(' -> tokenizer.addToken(Left())
            ')' -> tokenizer.addToken(Right())
            '+' -> tokenizer.addToken(Plus())
            '-' -> tokenizer.addToken(Minus())
            '*' -> tokenizer.addToken(Mul())
            '/' -> tokenizer.addToken(Div())
            ' ' -> {}
            '\t' -> {}
            else -> tokenizer.changeState(ErrorState("Symbol $symbol is not parsed"))
        }
        symbol = null
    }
}
