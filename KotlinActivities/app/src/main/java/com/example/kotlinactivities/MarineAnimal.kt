package com.example.kotlinactivities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

enum class MarineAnimal(val specie: String, val image: Int, val info: String) {
    DOLPHIN ("Dolphin", R.drawable.dolphin, "They're the most intelligent animals."),
    FISH ("Fish", R.drawable.fish,"Most animals feed on them." ),
    OCTOPUS ("Octopus", R.drawable.octopus, "They have three hearts."),
    SEAL ("Seal", R.drawable.seal, "Orcas play with Seals before eating them."),
    SHARK ("Shark", R.drawable.shark, "Sharks don't have any bones."),
    TURTLE ("Sea Turtles", R.drawable.turtle, "Sea Turtles can live up to 500 years."),
    WALRUS ("Walrus", R.drawable.walrus, "Underwater walruses can swimm up to 35km/h."),
    WHALE ("Whale", R.drawable.whale, "Fact: They're the best animal."),
    WHALESARK ("Whale Shark", R.drawable.whaleshark, "They only eat plants and krill.");

    companion object {
        fun list(): MutableList<MarineAnimal> {
            var listAnimals: MutableList<MarineAnimal> = mutableListOf()

            var marineAnimal = MarineAnimal.DOLPHIN
            for(marineAnimal in MarineAnimal.values()) {
                listAnimals.add(marineAnimal)
            }

            return listAnimals
        }
    }
}