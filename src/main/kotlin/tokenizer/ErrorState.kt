package tokenizer

class ErrorState(val error: String) : TokenizerState {
    override fun next() { }
}
