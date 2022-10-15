package com.gdsc.athena.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import getResponse
import io.ktor.util.date.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun PromtScreen(
    onNextButtonClicked: () -> Unit,
) {
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val cnt = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var text by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color(0xFF1D1D1D))
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProvideTextStyle(TextStyle(color = Color.White)) {
            OutlinedTextField(
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text, imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(
                    onDone = { keyboardController?.hide() }),
                maxLines = 5,
                shape = RoundedCornerShape(6),
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.50f),
//                    .border(
//                        color = Color(0xFFFF772A),
//                        width = 4.dp,
//                        shape = RoundedCornerShape(12),
//                    ),
                value = text,
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color(0xFFFF772A), unfocusedBorderColor = Color(0xFFFF772A)),
                label = {
                        Text(text = "Enter Your Prompt Here",
                        style = TextStyle(
                            fontSize = 18.sp, fontWeight = FontWeight.Bold,
                            color = Color.White,
                        )
                        )
                },
                onValueChange = {
                    text = it
                }
            )
        }
        Spacer(modifier = Modifier.size(30.dp))
        Button(onClick = {
            if(text.isNotEmpty())
            {
                var cat = ""
                db.collection("Users").document(auth.currentUser?.uid.toString())
                    .get().addOnSuccessListener {
                        cat = it["Category"].toString()
                    }
                db.collection("Users").document(auth.currentUser?.uid.toString())
                    .update("Prompt", text).addOnSuccessListener {
                        getResponse(cat, text)
                        onNextButtonClicked()
                    }.addOnFailureListener {
                        Log.d("Prompt", it.toString())
                    }
            }
            else
            {
                Toast.makeText(cnt, "Please Add A Prompt !!", Toast.LENGTH_SHORT).show()
            }
        },shape = RoundedCornerShape(16), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFF772A)), modifier = Modifier
            .fillMaxWidth(0.8f)
            .fillMaxHeight(0.18f)) {
                Text(text = "Generate" ,style = TextStyle(fontSize = 25.sp , color = Color(0xFFFCFBF7)), modifier = Modifier
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.CenterStart), textAlign = TextAlign.Left,)
            }
        }
    }
//    @Composable
//    @Preview(showBackground = true)
//    fun promtscreenpri() {
//        PromtScreen()
//    }
