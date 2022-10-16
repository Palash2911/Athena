package com.gdsc.athena

import android.R.attr.contentDescription
import android.content.Intent
import android.util.Log
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.IconSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun SelectionScreen(
    onNextButtonClicked: () -> Unit,
){
    val interFontB = FontFamily(Font(R.font.inter_bold))
    val interFontM = FontFamily(Font(R.font.inter_med))
    val interFontR = FontFamily(Font(R.font.inter_reg))
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(Modifier.fillMaxWidth().height(100.dp).background(Color(0xFF1D1D1D)), verticalAlignment = Alignment.CenterVertically) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.5f)
                    .background(color = Color(0xFF1D1D1D)),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "Categories",
                    style = TextStyle(fontFamily = interFontB,fontSize = 50.sp, color = Color(0xFFFFFFFF)),
                    modifier = Modifier.padding(start = 30.dp, bottom = 15.dp),
                    textAlign = TextAlign.Start
                )
            }
        }
        LazyColumn(
            Modifier
                .fillMaxSize()
                .weight(0.8f)
                .background(color = Color(0xFF1D1D1D)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            val intent= Intent(context, PromtScreen::)
            item {
                CustomButton(
                    name = "Sci-Fi",
                    painter = painterResource(id = R.drawable.sci_fi__2_b),
                        { onNextButtonClicked() }
                )
            }
            item {
                CustomButton(
                    name = "Action",
                    painter = painterResource(id = R.drawable.actionbb),
                        { onNextButtonClicked() }
                )
            }
            item {
                CustomButton(
                    name = "Romantic",
                    painter = painterResource(id = R.drawable.romanticsb),
                        { onNextButtonClicked() }
                )
            }
            item {
                CustomButton(
                    name = "Comedy",
                    painter = painterResource(id = R.drawable.comedyb),
                        { onNextButtonClicked() }
                )
            }
            item {
                CustomButton(
                    name = "Mystery",
                    painter = painterResource(id = R.drawable.myterib),
                        { onNextButtonClicked() }
                )
            }
            item {
                CustomButton(
                    name = "Horror",
                    painter = painterResource(id = R.drawable.horrorb),
                        { onNextButtonClicked() }
                )
            }
        }
    }
}

@Composable
fun CustomButton(name: String, painter: Painter, onNextButtonClicked: () -> Unit, modifier: Modifier = Modifier){
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    Card(
        modifier
            .padding(12.dp)
                .clickable(onClick = {
                    db.collection("Users").document(auth.currentUser?.uid.toString())
                            .update("Category", name).addOnSuccessListener {
                                Log.d("Success","asdf")
                                onNextButtonClicked()
                            }.addOnFailureListener {
                                Log.d("Selection1", it.toString())
                            }
                })
            .fillMaxWidth(0.9f)
            .background(color = Color(0xFF1D1D1D))
            .border(width = 3.dp, color = Color(0xFF1D1D1D), shape = RoundedCornerShape(22.dp))
            .clip(shape = RoundedCornerShape(16.dp)),
        shape = RoundedCornerShape(22.dp)
    ) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .clip(RoundedCornerShape(22.dp))
                .background(color = Color(0xFF1D1D1D)),
        ) {
            Image(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(22.dp))
                    .background(color = Color(0xFF1D1D1D)),
                painter = painter,
                contentDescription = name,
                contentScale = ContentScale.Crop
            )
            Box(modifier = Modifier
                .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = name, style = TextStyle(fontFamily = interFontM,color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Medium))
            }
        }
    }
}
