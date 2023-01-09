package uz.smartmuslim.matematiktestlar.domain.repository.first.impl

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.callbackFlow
import uz.smartmuslim.matematiktestlar.data.model.Query
import uz.smartmuslim.matematiktestlar.data.model.Question
import uz.smartmuslim.matematiktestlar.data.model.Test
import uz.smartmuslim.matematiktestlar.data.utils.ResultData
import uz.smartmuslim.matematiktestlar.domain.repository.first.FirstRepository
import javax.inject.Inject

class FirstRepositoryImpl @Inject constructor(
    private val db: FirebaseFirestore
) : FirstRepository {

    override fun addQuestion(question: Question): Flow<ResultData<String>> = callbackFlow {

        db.collection("questions").document(question.id).set(question).addOnSuccessListener {
            trySend(ResultData.Success("This question has been added successfully"))
        }.addOnFailureListener {
            trySend(ResultData.Success("This question has not been added"))
        }
    }

    override fun generateTest(questionCount: Int): Flow<ResultData<String>> = callbackFlow{

        val list = ArrayList<Test>()
        db.collection("tests").addSnapshotListener { value, error ->
            val data = value?.documents?.map {

            }
        }
    }


    override fun getAllEasyTests(): Flow<List<Test>> = callbackFlow {


    }

    override fun getTestByQuery(query: Query): Flow<List<Question>> = callbackFlow {

    }
}