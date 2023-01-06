package uz.smartmuslim.matematiktestlar.data.model

data class Question(
    val question: String,
    val ansOne: String,
    val ansTwo: String,
    val ansThree: String,
    val ansFour: String,
    val ansTrue: String,
    val imageOrText: String,
    val degree: String,
)