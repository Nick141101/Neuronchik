class NeuralNetwork {

    private val listOfNeurons = mutableListOf<Neuron>()
    var listOfSignals = mutableListOf<Int>()
    var nSignals = 1

    fun addNeuron(threshold: Int,
                  nExcitatory: Int,
                  nInhibitory: Int,
                  connectTo: Int, // root = -1
                  connectType: Boolean = true) { // true - excitatory, false - inhibitory

        if (connectTo == -1) {
            listOfNeurons.add(Neuron(threshold, nExcitatory, nInhibitory))
            nSignals += (nExcitatory + nInhibitory - 1)
            return
        }

        val index = if (connectType) listOfNeurons[connectTo].listOfExcitatory.indexOf(-1)
                                else listOfNeurons[connectTo].listOfInhibitory.indexOf(-1)
        if (index > -1) {
            listOfNeurons.add(Neuron(threshold, nExcitatory, nInhibitory))
            nSignals += (nExcitatory + nInhibitory - 1)
            if (connectType)
                listOfNeurons[connectTo].listOfExcitatory[index] = listOfNeurons.lastIndex
            else
                listOfNeurons[connectTo].listOfInhibitory[index] = listOfNeurons.lastIndex
        } else {
            println("Not enough ${if (connectType) "excitatory" else "inhibitory"} inputs")
        }
    }

    fun newState(index: Int) {

        val curNeuron = listOfNeurons[index]

        var nActive = 0
        var isInhibitory = false

        for (i in curNeuron.listOfExcitatory) {
            val state = if (i == -1) listOfSignals[0] else listOfNeurons[i].state
            if (state == 1) nActive++
            if (i == -1) listOfSignals.removeAt(0)
        }

        for (i in curNeuron.listOfInhibitory) {
            val state = if (i == -1) listOfSignals[0] else listOfNeurons[i].state
            if (state == 1) isInhibitory = true
            if (i == -1) listOfSignals.removeAt(0)
        }

        curNeuron.state = if (nActive >= curNeuron.threshold && !isInhibitory) 1 else 0

    }

    fun nextTick(): Int {
        while (true) {
            listOfSignals = readln().map { it.digitToInt() }.toMutableList()
            if (listOfSignals.isEmpty())
                listOfSignals = MutableList(nSignals) {0}

            if (listOfSignals.size != nSignals)
                println("Incorrect input length")
            else
                break
        }

        for (index in listOfNeurons.indices)
            newState(index)

        return listOfNeurons[0].state
    }

}