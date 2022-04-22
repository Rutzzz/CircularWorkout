package cz.muni.fi.circularworkout.data

enum class MuscleGroup(val text: String, val exercises: List<String>) {
    CHEST("Chest", listOf("Chest exercise 1", "Chest exercise 2", "Chest exercise 3")),
    BACK("Back", listOf("Back exercise 1", "Back exercise 2", "Back exercise 3")),
    LEGS("Legs", listOf("Leg exercise 1", "Leg exercise 2"))
}