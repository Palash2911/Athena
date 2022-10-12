package com.gdsc.athena

import android.R.attr.contentDescription
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SelectionScreen(
    onNextButtonClicked: () -> Unit,
){
    Column(modifier = Modifier.fillMaxHeight()) {
        Row(Modifier.fillMaxWidth().height(100.dp).background(Color(0xFF1D1D1D))) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(0.2f)
                    .background(color = Color(0xFF1D1D1D)),
                contentAlignment = Alignment.BottomStart
            ) {
                Text(
                    text = "Categories",
                    style = TextStyle(fontSize = 50.sp, color = Color(0xFFFFFFFF)),
                    modifier = Modifier.padding(start = 34.dp, bottom = 32.dp)
                )
            }
            Button(onClick = {onNextButtonClicked()},Modifier.padding(all =12.dp)) {
//                Icon(Icons.Filled.ArrowForward)
                Icon(
                    Icons.Filled.ArrowForward,
                    contentDescription = "Favorite",
                    modifier = Modifier.size(ButtonDefaults.IconSize)
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
            item {
                Modifier.clickable(onClick = {print("clicked")})
                CustomButton(
                    name = "Sci-Fi",
                    painter = painterResource(id = R.drawable.sci_fi__2_b),

                )
            }
            item {
                Modifier.clickable(onClick = {onNextButtonClicked()})
                CustomButton(
                    name = "Action",
                    painter = painterResource(id = R.drawable.actionbb)
                )
            }
            item {
                Modifier.clickable(onClick = {onNextButtonClicked()})
                CustomButton(
                    name = "Romantic",
                    painter = painterResource(id = R.drawable.romanticsb)
                )
            }
            item {
                Modifier.clickable(onClick = {onNextButtonClicked()})
                CustomButton(
                    name = "Comedy",
                    painter = painterResource(id = R.drawable.comedyb)
                )
            }
            item {
                Modifier.clickable(onClick = {onNextButtonClicked()})
                CustomButton(
                    name = "Mystery",
                    painter = painterResource(id = R.drawable.myterib)
                )
            }
            item {
                Modifier.clickable(onClick = {onNextButtonClicked()})
                CustomButton(
                    name = "Horror",
                    painter = painterResource(id = R.drawable.horrorb)
                )
            }
        }
    }
}

@Composable
fun CustomButton(name: String, painter: Painter, modifier: Modifier = Modifier){
    Card(
        modifier
            .padding(12.dp)
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
                Text(text = name, style = TextStyle(color = Color.White, fontSize = 40.sp, fontWeight = FontWeight.Medium))
            }
        }
    }
}
