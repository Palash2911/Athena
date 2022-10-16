package com.gdsc.athena

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


@Composable
fun ProfileScreen(onNextButtonClicked:()->Unit, onPrevButtonClicked:()->Unit){
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    var name by remember { mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    val Stor by remember {mutableStateOf(mutableListOf<String>())}
    val Cat by remember {mutableStateOf(mutableListOf<String>())}
    val Pro by remember {mutableStateOf(mutableListOf<String>())}
    getCat(Cat)
    getStory(Stor)
    getPrompt(Pro)
    val size = Cat.size
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color(0xFF1D1D1D)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        db.collection("Users").document(auth.currentUser?.uid.toString())
                .get().addOnSuccessListener {
                    name = it["FName"].toString() + " " + it["LName"].toString()
                    email = it["Email"].toString()
                }.addOnFailureListener {
                    Log.d("FAILED!", it.toString())
                }
        Icon(Icons.Filled.ExitToApp, "Logout",
            Modifier
                .size(65.dp)
                .align(Start)
                .padding(all = 15.dp)
                .clickable(onClick = {
                    auth.signOut()
                    onPrevButtonClicked()
                }),
            Color.Gray
        )
        Surface(
            shape = CircleShape,
            modifier = Modifier
                .size(150.dp)
                .background(Color.Transparent),
        ) {
            Image(
                painter = painterResource(R.drawable.logo2__1_),
                contentDescription = "avatar",
                contentScale = ContentScale.Crop,            // crop the image if it's not a square
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.Yellow,
                                Color.Transparent
                            )
                        )
                    )
            )
        }
        Spacer(modifier = Modifier.size(20.dp))
        Text(text = name ,style = TextStyle(fontSize = 30.sp , color = Color(0xFFFCFBF7)), modifier = Modifier
            .wrapContentSize(Alignment.Center), textAlign = TextAlign.Center,)
        Spacer(modifier = Modifier.size(4.dp))
        Row(
            Modifier
                .fillMaxWidth()
                .height(35.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = email , style = TextStyle(fontSize = 20.sp ,color = Color.Gray , textAlign = TextAlign.Center) , modifier = Modifier.fillMaxWidth()) }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Column(Modifier.fillMaxWidth()) {
                Button(onClick = { onNextButtonClicked() },
                    Modifier
                        .padding(horizontal = 40.dp, vertical = 12.dp)
                        .fillMaxWidth()
                        .height(100.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFF772A)),) {
                    Text(
                        text = "Create New Story",
                        style = TextStyle(
                            fontSize = 20.sp,
                            color = Color.White
                        ),
                    )
                }
                Text(
                    text = "Saved Stories",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.LightGray
                    ),
                    modifier = Modifier.fillMaxWidth(0.63f),
                    textAlign = TextAlign.Center
                )
            }
        }
        LazyColumn(
            Modifier
                .fillMaxHeight()
                .padding(bottom = 20.dp)
                .background(color = Color(0xFF1D1D1D)),
            horizontalAlignment = CenterHorizontally
        ) {
            items(size){
                CustomPrompt(
                    type = Cat[it],
                    prompt = Pro[it],
                    story = Stor[it],
                )
            }
        }
    }
}
@Composable
fun CustomPrompt(type: String, prompt : String, story : String , modifier: Modifier = Modifier, ){
    Card(
        modifier
            .padding(12.dp)
            .fillMaxWidth(0.9f)
            .height(180.dp)
            .background(color = Color.Transparent)
            .border(width = 3.dp, color = Color(0XFFFF772A), shape = RoundedCornerShape(22.dp))
            .clip(shape = RoundedCornerShape(10.dp)),

        shape = RoundedCornerShape(10.dp),
    ) {
        Column(
            modifier = Modifier.background(color=Color(0xFF1A1A1A)),
            verticalArrangement = Arrangement.Top,
        ) {
            Text(text = type ,style = TextStyle(fontSize = 28.sp, color = Color(0xFFFFFFFF )), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp), textAlign = TextAlign.Start,)
            Text(text = "Prompt: " + prompt , style = TextStyle(fontSize = 18.sp ,color = Color.White , textAlign = TextAlign.Start) , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = "Story: " + story.replace("\n", " ") , style = TextStyle(fontSize = 14.sp ,color = Color.Gray , textAlign = TextAlign.Start) , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp), maxLines = 3)
            Spacer(Modifier.height(5.dp))
        }
    }
}

fun getCat(Cat: MutableList<String>) : MutableList<String>{
    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()
    db.collection("Users").document(auth.currentUser?.uid.toString()).collection("Saved").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Cat.add(document["Category"].toString())
                }
            }
            .addOnFailureListener { exception ->
                Log.d("FAILED!", "Error getting documents: ", exception)
            }
    return Cat
}

fun getStory(Stor: MutableList<String>) : MutableList<String>{
    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()
    db.collection("Users").document(auth.currentUser?.uid.toString()).collection("Saved").get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Stor.add(document["Story"].toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.d("FAILED!", "Error getting documents: ", exception)
        }
    return Stor
}

fun getPrompt(Pro: MutableList<String>) : MutableList<String>{
    val db = Firebase.firestore
    val auth = FirebaseAuth.getInstance()
    db.collection("Users").document(auth.currentUser?.uid.toString()).collection("Saved").get()
        .addOnSuccessListener { result ->
            for (document in result) {
                Pro.add(document["Prompt"].toString())
            }
        }
        .addOnFailureListener { exception ->
            Log.d("FAILED!", "Error getting documents: ", exception)
        }
    return Pro
}