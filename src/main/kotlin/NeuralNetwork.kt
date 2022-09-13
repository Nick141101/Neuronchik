class NeuralNetwork {

    val listOfNeurons = mutableListOf<Neuron>()
    var listOfSignals = mutableListOf<Int>()

    fun addNeuron(threshold: Int,
                  nExcitatory: Int,
                  nInhibitory: Int,
                  connectTo: Int, // root = -1
                  connectType: Boolean = true) { // true - excitatory, false - inhibitory

        listOfNeurons.add(Neuron(threshold, nExcitatory, nInhibitory))

        if (connectTo == -1) return

        if (connectType) {
            val index = listOfNeurons[connectTo].listOfExcitatory.indexOf(-1)
            if (index > -1) {
                listOfNeurons[connectTo].listOfExcitatory[index] = listOfNeurons.lastIndex
            }
        } else {
            val index = listOfNeurons[connectTo].listOfInhibitory.indexOf(-1)
            if (index > -1) {
                listOfNeurons[connectTo].listOfInhibitory[index] = listOfNeurons.lastIndex
            }
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
        listOfSignals = readln().toMutableList().map { it.toInt() }
        for (index in listOfNeurons.indices) {
            newState(index)
        }
        return listOfNeurons[0].state
    }

}