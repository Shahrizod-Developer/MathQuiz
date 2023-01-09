package uz.smartmuslim.matematiktestlar.data.model

import java.util.UUID

data class Question(
    val id: String = UUID.randomUUID().toString(),
    val number: String,
    val question: String,
    val ansOne: String,
    val ansTwo: String,
    val ansThree: String,
    val ansFour: String,
    val ansTrue: String,
    val imageOrText: String,
    val degree: String,
    val testId: String,
    val one:Boolean,
    val two:Boolean,
    val three:Boolean
)