package com.guilhermereisdev.jettrivia.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.guilhermereisdev.jettrivia.model.QuestionItem
import com.guilhermereisdev.jettrivia.screens.QuestionViewModel
import com.guilhermereisdev.jettrivia.util.AppColors

@Composable
fun Questions(viewModel: QuestionViewModel) {
    val questions = viewModel.data.value.data?.toMutableList()
    if (viewModel.data.value.loading == true) {
        CircularProgressIndicator()
    } else {
        if (questions != null) {
            QuestionDisplay(questionItem = questions.first())
        }
    }
}

//@Preview
@Composable
fun QuestionDisplay(
    questionItem: QuestionItem,
    //questionIndex: MutableState<Int>,
    //viewModel: QuestionViewModel,
    onNextClicked: (Int) -> Unit = {}
) {
    val choicesState = remember(questionItem) { questionItem.choices.toMutableList() }
    val answerState = remember(questionItem) { mutableStateOf<Int?>(null) }
    val correctAnswerState = remember(questionItem) { mutableStateOf<Boolean?>(null) }
    val updateAnswer: (Int) -> Unit = remember {
        {
            answerState.value = it
            correctAnswerState.value = choicesState[it] == questionItem.answer
        }
    }
    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        color = AppColors.mDarkPurple,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
        ) {
            QuestionTracker()
            DottedLine(pathEffect)
            Column {
                Text(
                    text = questionItem.question,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .fillMaxHeight(0.3f),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = AppColors.mOffWhite,
                    lineHeight = 22.sp,
                )
                choicesState.forEachIndexed { index, answerText ->
                    Row(
                        modifier = Modifier
                            .padding(3.dp)
                            .fillMaxWidth()
                            .height(45.dp)
                            .border(
                                width = 4.dp,
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        AppColors.mOffDarkPurple,
                                        AppColors.mOffDarkPurple,
                                    )
                                ),
                                shape = RoundedCornerShape(15.dp)
                            )
                            .clip(
                                RoundedCornerShape(
                                    topStartPercent = 50,
                                    topEndPercent = 50,
                                    bottomEndPercent = 50,
                                    bottomStartPercent = 50,
                                )
                            )
                            .background(Color.Transparent),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (answerState.value == index),
                            onClick = {
                                updateAnswer(index)
                            },
                            modifier = Modifier.padding(start = 16.dp),
                            colors = RadioButtonDefaults
                                .colors(
                                    selectedColor =
                                    if (correctAnswerState.value == true && index == answerState.value) {
                                        Color.Green.copy(alpha = 0.2f)
                                    } else {
                                        Color.Red.copy(alpha = 0.2f)
                                    }
                                )
                        )
                        Text(text = answerText)
                    }
                }
            }
        }
    }
}

@Composable
fun QuestionTracker(counter: Int = 10, outOf: Int = 100) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = ParagraphStyle(textIndent = TextIndent.None)) {
                withStyle(
                    style = SpanStyle(
                        color = AppColors.mLightGray,
                        fontWeight = FontWeight.Bold,
                        fontSize = 27.sp
                    )
                ) {
                    append("Question $counter/")
                    withStyle(
                        style = SpanStyle(
                            color = AppColors.mLightGray,
                            fontWeight = FontWeight.Light,
                            fontSize = 14.sp
                        )
                    ) {
                        append("$outOf")
                    }
                }
            }
        },
//        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun DottedLine(pathEffect: PathEffect) {
    Column(
        modifier = Modifier
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        ) {
            drawLine(
                color = AppColors.mLightGray,
                start = Offset(x = 0f, y = 0f),
                end = Offset(x = size.width, y = 0f),
                pathEffect = pathEffect
            )
        }
    }
}
