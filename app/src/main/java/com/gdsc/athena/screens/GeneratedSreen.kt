package com.gdsc.athena
import android.util.Log
import android.widget.Toast
import androidx.compose.animation.AnimatedContentScope.SlideDirection.Companion.End
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.End
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextAlign.Companion.End
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import getResponse
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import story


@Composable
fun genretedtScreen(
//    usersprompt : String,
    onNextButtonClicked: () -> Unit,
) {
    var text by remember { mutableStateOf(story) }
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val Story: HashMap<String, Any> = HashMap()
    val cnt = LocalContext.current
    Column(
        Modifier
            .fillMaxHeight()
            .background(color = Color(0xFF1D1D1D))
            .fillMaxWidth()) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(100.dp), verticalAlignment = Alignment.CenterVertically) {
            Spacer(modifier = Modifier.fillMaxWidth(0.5f))
            Button(onClick = {
                    db.collection("Users").document(auth.currentUser?.uid.toString())
                            .get().addOnSuccessListener {
                                Story["Category"] = it["Category"].toString()
                                Story["Prompt"] = it["Prompt"].toString()
                                Story["Story"] = text
                                db.collection("Users").document(auth.currentUser?.uid.toString())
                                        .collection("Saved").document()
                                        .set(Story).addOnSuccessListener {
                                            Toast.makeText(cnt, "Saved", Toast.LENGTH_SHORT).show()
                                        }
                                        .addOnFailureListener {
                                            Log.d("Saved", it.toString())
                                        }
                            }
                            .addOnFailureListener {
                                Log.d("Failed Generated", it.toString())
                            }
                 }, Modifier.padding(all = 12.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFF772A)),
                ) {
//                Icon(Icons.Filled.ArrowForward)
                Icon(
                    painterResource(id = R.drawable.ic_baseline_bookmark_24),
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
            Button(onClick = { onNextButtonClicked() }, Modifier.padding(all = 12.dp), colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0XFFFF772A)
            ),) {
//                Icon(Icons.Filled.ArrowForward)
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
                )
            }
        }
        Card(
            Modifier
                .fillMaxSize(),
        ) {
            Card(
                Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, bottom = 60.dp, start = 30.dp, end = 30.dp),
                shape = RoundedCornerShape(16.dp),
                border = BorderStroke(4.dp, color = Color(0xFFEF5B0C))
            ) {
                TextField(
//                    modifier = Modifier.padding(start = 4.dp, top = 10.dp, bottom = 18.dp),
                    label = {
                        Text(
                            text = "Generated Story",
                            style = TextStyle(color = Color.White, fontSize = 22.sp),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(all = 16.dp)
                        )
                    },
                    value = text,
                        onValueChange = { newText ->
                            text = newText
                        },
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent,
                        backgroundColor = Color(0xFF1D1D1D),
                        textColor = Color.Gray,
                    ),
                    maxLines = 50,
                    singleLine = false,
                )
            }
        }
    }
}