import tokenizer.Tokenizer
import visitors.CalcVisitor
import visitors.ParserVisitor
import visitors.PrintVisitor

fun main(args: Array<String>) {
    val input: String = readLine() ?: ""
    val inputStream = input.byteInputStream()
    val tokens = Tokenizer(inputStream).tokenize()
    val opzTokens = ParserVisitor(tokens).parse()
    PrintVisitor(opzTokens).print()
    println("Answer = ${CalcVisitor(opzTokens).calculate()}")
}