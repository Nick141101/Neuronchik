class Neuron(val threshold: Int,
             val nExcitatory: Int,
             val nInhibitory: Int) {

    var state = 0
    val listOfExcitatory = Array(nExcitatory) {-1}
    val listOfInhibitory = Array(nInhibitory) {-1}

}