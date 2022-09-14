fun main() {

    val nn = NeuralNetwork()
    nn.addNeuron(2, 3, 0, -1)
    nn.addNeuron(1, 1, 0, 0)
    nn.addNeuron(1, 2, 0, 0)
    nn.addNeuron(1, 0, 1, 0)
    nn.addNeuron(1, 1, 0, 1)
    nn.addNeuron(3, 3, 0, 2)
    nn.addNeuron(1, 0, 1, 2)

    while (true) {
        val ans = nn.nextTick()
        println(ans)
    }
}