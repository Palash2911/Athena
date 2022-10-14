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
import androidx.compose.material.icons.filled.ArrowForward
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
fun ProfileScreen(onNextButtonClicked:()->Unit){
    val auth = FirebaseAuth.getInstance()
    val db = Firebase.firestore
    var name by remember { mutableStateOf("")}
    var email by remember {mutableStateOf("")}
    val Cat = mutableListOf<String>("")
    val Pro by remember { mutableStateOf(mutableListOf(""))}
    val Stor by remember { mutableStateOf(mutableListOf(""))}
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .background(color = Color(0xFF1D1D1D)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        db.collection("Users").document(auth.currentUser?.uid.toString())
                .collection("Saved").get()
                .addOnSuccessListener {
                    for(ss in it){
                        Cat.add(ss.get("Category").toString())
                        Pro.add(ss.get("Prompt").toString())
                        Stor.add(ss.get("Story").toString())
                    }
                }
        Log.d("Carte", Cat.toString())
        db.collection("Users").document(auth.currentUser?.uid.toString())
                .get().addOnSuccessListener {
                    name = it["FName"].toString() + " " + it["LName"].toString()
                    email = it["Email"].toString()
                }.addOnFailureListener {
                    Log.d("FAILED!", it.toString())
                }
        Log.d("asdf", name+email)
        Icon(Icons.Filled.ExitToApp, "Logout",Modifier.size(65.dp).align(Start).padding(all =15.dp).clickable(onClick = {

            //


        }),
            Color.Red
        )
        Spacer(modifier = Modifier.size(60.dp))
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
        Row(Modifier.fillMaxWidth().height(35.dp), verticalAlignment = Alignment.CenterVertically) { Text(text = "deez@gmail.com" , style = TextStyle(fontSize = 20.sp ,color = Color.Gray , textAlign = TextAlign.Center) , modifier = Modifier.fillMaxWidth()) }

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
                    modifier = Modifier.padding(start = 25.dp),
                    textAlign = TextAlign.Start
                )
                Button(onClick = { onNextButtonClicked() },
                    Modifier
                        .padding(all = 12.dp)
                        .height(35.dp), colors = ButtonDefaults.buttonColors(backgroundColor = Color(0XFFFF772A)),) {
                    Text(
                        text = "Create",
                        style = TextStyle(
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp,
                            color = Color.White
                        ),
                    )
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
            item {
                CustomPrompt(
                    type = Cat[0],
                    prompt = "Mama mia its mario ",
                    story = "I'm so angry with you right now! I can't believe you would agree to play Mario in the upcoming movie. Mario is one of my all-time favorite video game characters and you are just ruining him!\n" +
                            "\n" +
                            "You are nothing like Mario. You are not Italian, you are not a plumber, and you are not short. In fact, you are the complete opposite of Mario! You are tall, white, and an actor. You have no business playing Mario in the movie.\n" +
                            "\n" +
                            "I don't care if you are the star of the movie. I will not be watching it. I will not support it. I am so mad at you right now. You have ruined my childhood hero and I will never forgive you for it." )
            }
            item {
                CustomPrompt(
                    type = "Sci-Fi",
                    prompt = "The Tale of Johnny Town-mouse",
                    story = "Do you ever feel that the grass is always greener on the other side of the fence? Well, so did Johnny Town-Mouse and Timmy Willie. One was a town mouse and one was a country mouse, and when they end up in each other's worlds they soon discover that they were much happier where they started! The Tale of Johnny Town-Mouse is number 13 in Beatrix Potter's series of 23 little books, the titles of which are as follows: 1 The Tale of Peter Rabbit 2 The Tale of Squirrel Nutkin 3 The Tailor of Gloucester 4 The Tale of Benjamin Bunny 5 The Tale of Two Bad Mice 6 The Tale of Mrs. Tiggy-Winkle 7 The Tale of Mr. Jeremy Fisher 8 The Tale of Tom Kitten 9 The Tale of Jemima Puddle-Duck 10 The Tale of the Flopsy Bunnies 11 The Tale of Mrs. Tittlemouse 12 The Tale of Timmy Tiptoes 13 The Tale of Johnny Town-Mouse 14 The Tale of Mr. Tod 15 The Tale of Pigling Bland 16 The Tale of Samuel Whiskers 17 The Tale of The Pie and the Patty-Pan 18 The Tale of Ginger and Pickles 19 The Tale of Little Pig Robinson 20 The Story of a Fierce Bad Rabbit 21 The Story of Miss Moppet 22 Appley Dapply's Nursery Rhymes 23 Cecily Parsley's Nursery Rhymes"
                )
            }
            item {
                CustomPrompt(
                    type = "Sci-Fi",
                    prompt = "The Tale of Johnny Town-mouse",
                    story = "Do you ever feel that the grass is always greener on the other side of the fence? Well, so did Johnny Town-Mouse and Timmy Willie. One was a town mouse and one was a country mouse, and when they end up in each other's worlds they soon discover that they were much happier where they started! The Tale of Johnny Town-Mouse is number 13 in Beatrix Potter's series of 23 little books, the titles of which are as follows: 1 The Tale of Peter Rabbit 2 The Tale of Squirrel Nutkin 3 The Tailor of Gloucester 4 The Tale of Benjamin Bunny 5 The Tale of Two Bad Mice 6 The Tale of Mrs. Tiggy-Winkle 7 The Tale of Mr. Jeremy Fisher 8 The Tale of Tom Kitten 9 The Tale of Jemima Puddle-Duck 10 The Tale of the Flopsy Bunnies 11 The Tale of Mrs. Tittlemouse 12 The Tale of Timmy Tiptoes 13 The Tale of Johnny Town-Mouse 14 The Tale of Mr. Tod 15 The Tale of Pigling Bland 16 The Tale of Samuel Whiskers 17 The Tale of The Pie and the Patty-Pan 18 The Tale of Ginger and Pickles 19 The Tale of Little Pig Robinson 20 The Story of a Fierce Bad Rabbit 21 The Story of Miss Moppet 22 Appley Dapply's Nursery Rhymes 23 Cecily Parsley's Nursery Rhymes"
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