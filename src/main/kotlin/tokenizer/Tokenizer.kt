package tokenizer

import tokens.Token
import java.io.InputStream
import kotlin.system.exitProcess

class Tokenizer(private val inputStream: InputStream) {
    private var state: TokenizerState = StartState(this)
    private val tokens = mutableListOf<Token>()

    fun tokenize() : List<Token> {
        while (state is StartState || state is NumberState) {
            state.next()
        }
        if (state is ErrorState) {
            println((state as ErrorState).error)
            exitProcess(-1)
        }
        return tokens
    }

    fun nextSymbol(): Char {
        return inputStream.read().toChar()
    }

    fun changeState(newState: TokenizerState) {
        state = newState
    }

    fun addToken(token: Token) {
        tokens.add(token)
    }

}