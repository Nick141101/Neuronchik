fun main() {

    val nn = NeuralNetwork()
    nn.addNeuron(2, 3, 0, -1)
    nn.addNeuron(1, 1, 0, 0)
    nn.addNeuron(1, 2, 0, 0)
    nn.addNeuron(1, 0, 1, 0)
    nn.addNeuron(1, 1, 0, 1)
    nn.addNeuron(3, 3, 0, 2)
    nn.addNeuron(1, 0, 1, 2)

    val ans = nn.nextTick(mutableListOf(0, 1, 1, 1, 1, 0))
    println(ans)

    repeat(10) {
        val ans = nn.nextTick(mutableListOf(false, false, false, false, false, false))
        println(ans)
    }
}