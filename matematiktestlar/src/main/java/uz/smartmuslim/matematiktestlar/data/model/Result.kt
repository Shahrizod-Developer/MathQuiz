package uz.smartmuslim.matematiktestlar.data.model

import java.util.UUID

data class Result(
    val id:String = UUID.randomUUID().toString(),
    val testId:String,

)