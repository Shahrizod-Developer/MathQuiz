package uz.smartmuslim.matematiktestlar.domain.repository.first

import kotlinx.coroutines.flow.Flow
import uz.smartmuslim.matematiktestlar.data.model.Query
import uz.smartmuslim.matematiktestlar.data.model.Question
import uz.smartmuslim.matematiktestlar.data.model.Test
import uz.smartmuslim.matematiktestlar.data.utils.ResultData

interface FirstRepository {


    fun addQuestion(question: Question): Flow<ResultData<String>>

    fun generateTest(questionCount: Int): Flow<ResultData<String>>

    fun getAllEasyTests(): Flow<List<Test>>

    fun getTestByQuery(query: Query): Flow<List<Question>>
}