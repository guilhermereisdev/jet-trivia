package com.guilhermereisdev.jettrivia.repository

import com.guilhermereisdev.jettrivia.data.DataOrException
import com.guilhermereisdev.jettrivia.model.QuestionItem
import com.guilhermereisdev.jettrivia.network.QuestionApi
import javax.inject.Inject

class QuestionRepository @Inject constructor(
    private val api: QuestionApi
) {
    private val listOfQuestions = DataOrException<ArrayList<QuestionItem>, Boolean, Exception>()
}
