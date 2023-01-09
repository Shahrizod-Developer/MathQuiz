package uz.smartmuslim.matematiktestlar.data.model

import java.util.UUID

data class Test(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val desc: String,
    val time: String,
    val level: String,
    var state: Int
)