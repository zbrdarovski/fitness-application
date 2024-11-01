package si.um.feri.fitness

import java.util.*

class Fitness(private val name: String, private val address: String){
    val id = UUID.randomUUID().toString().replace("-", "")
    override fun toString(): String {
        return ("Fitness: $name located at $address.")
    }
}

var app = MyApplication();