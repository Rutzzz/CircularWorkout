package cz.muni.fi.circularworkout.data

enum class OptionGroup (val text: String, val number: List<Int>){
    PAUSE("Pause", listOf(15, 20, 25, 30, 40, 50, 60)),
    ROUNDS("Rounds", listOf(2, 3, 4, 5, 6, 8, 10)),
}