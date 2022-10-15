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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.Start
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
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
    val Cat = remember { mutableStateListOf("") }
    val Pro = remember { mutableStateListOf("Action") }
    val Stor by remember { mutableStateOf(mutableListOf(""))}
    getlist(Cat)
    Log.d("Carte", Cat.toString())
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
                Modifier.size(65.dp).align(Start).padding(all = 15.dp).clickable(onClick = {
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
        Row(Modifier.fillMaxWidth().height(35.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(text = email , style = TextStyle(fontSize = 20.sp ,color = Color.Gray , textAlign = TextAlign.Center) , modifier = Modifier.fillMaxWidth()) }

        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Saved prompts",
                    style = TextStyle(
                        fontSize = 30.sp,
                        color = Color.LightGray
                    ),
                    modifier = Modifier.padding(start = 40.dp),
                    textAlign = TextAlign.Start
                )
                Button(onClick = { onNextButtonClicked() },
                    Modifier
                        .padding(all = 9.dp)
                        .height(30.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFF772A)),) {
                    Icon(Icons.Filled.Add, "Add Story",  modifier = Modifier.size(ButtonDefaults.IconSize))
                }
            }
        }
        LazyColumn(
            Modifier
                .fillMaxHeight()
                .padding(bottom = 20.dp)
                .background(color = Color(0xFF1D1D1D)),
            horizontalAlignment = CenterHorizontally
        ) {
            items(Cat.size){
                CustomPrompt(
                    type = Cat[it],
                    prompt = "Mama mia its mario ",
                    story = "I'm so angry with you right now! I can't believe you would agree to play Mario in the upcoming movie. Mario is one of my all-time favorite video game characters and you are just ruining him!\n" +
                            "\n" +
                            "You are nothing like Mario. You are not Italian, you are not a plumber, and you are not short. In fact, you are the complete opposite of Mario! You are tall, white, and an actor. You have no business playing Mario in the movie.\n" +
                            "\n" +
                            "I don't care if you are the star of the movie. I will not be watching it. I will not support it. I am so mad at you right now. You have ruined my childhood hero and I will never forgive you for it." )
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
            .clip(shape = RoundedCornerShape(16.dp)),

        shape = RoundedCornerShape(22.dp)
    ) {
        Column(modifier = Modifier.background(color=Color(0xFF1A1A1A))) {
            Text(text = type ,style = TextStyle(fontSize = 28.sp, color = Color(0xFFFFFFFF )), modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 2.dp, vertical = 3.dp), textAlign = TextAlign.Center,)
            Text(text = prompt , style = TextStyle(fontSize = 18.sp ,color = Color.White , textAlign = TextAlign.Center) , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp), maxLines = 1, overflow = TextOverflow.Ellipsis)
            Text(text = story , style = TextStyle(fontSize = 14.sp ,color = Color.Gray , textAlign = TextAlign.Center) , modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 9.dp), maxLines = 3, overflow = TextOverflow.Ellipsis)
            Spacer(Modifier.height(5.dp))
        }
    }
}

@Composable
fun getlist(Cateo: MutableList<String>): MutableList<String> {
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    val Pro = mutableListOf<String>()
    val Stor = mutableListOf<String>()
    db.collection("Users").document(auth.currentUser?.uid.toString())
        .collection("Saved").get()
        .addOnSuccessListener {
            for(ss in it){
                Cateo.add(ss.get("Category").toString())
                Pro.add(ss.get("Prompt").toString())
                Stor.add(ss.get("Story").toString())
            }
        }
    Log.d("Carte22", Cateo.toString())
    return Cateo
}