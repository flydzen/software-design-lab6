package tokenizer

import tokens.NumberToken

class NumberState(private val tokenizer: Tokenizer, private var number: StringBuffer) : TokenizerState {
    override fun next() {
        when (val symbol : Char = tokenizer.nextSymbol()) {
            in '0'..'9' -> {
                tokenizer.changeState(NumberState(tokenizer, number.append(symbol)))
            }
            else -> {
                tokenizer.addToken(NumberToken(number.toString().toInt()))
                tokenizer.changeState(StartState(tokenizer, symbol))
            }
        }

    }
}
