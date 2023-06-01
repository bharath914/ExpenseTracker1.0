package com.bharath.expensetracker.ui.theme.Screen

import androidx.compose.animation.core.StartOffset
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.ExperimentalTextApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.viewModelFactory
import com.bharath.expensetracker.R
import com.bharath.expensetracker.ui.theme.Inter_SemiBold
import com.bharath.expensetracker.ui.theme.Lato_Regular
import com.bharath.expensetracker.ui.theme.viewmodel.NameViewModel
import kotlinx.coroutines.launch
import okhttp3.internal.checkOffsetAndCount

@OptIn(ExperimentalTextApi::class)
@Composable
fun NameScreen(){
    val viewModel:NameViewModel = hiltViewModel()
    var text by remember { mutableStateOf("") }
    var brush = remember {
        Brush.linearGradient(
            colors = listOf(
                Color.Red,
                Color.Green,
                Color.Blue

            )
        )
    }
    var namebrush = remember {
        Brush.linearGradient(
            colors = listOf(

            )
        )
    }
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Column(horizontalAlignment = Alignment.CenterHorizontally,

            ) {


            Image(
                painter = painterResource(id = R.drawable.usernamescreen),
                contentDescription = "",
                modifier = Modifier.padding(20.dp)
            )
            Spacer(modifier = Modifier.height(35.dp))


            Text(
                text = "Enter Your Name To Continue " + "\n",
                fontFamily = Lato_Regular,
                fontSize = 25.sp, style = TextStyle(
                    brush
                )
            )
        }
       OutlinedTextField(
            value = text,
            onValueChange = {
                if(it.length<=8){
                    text=it
                }
                            },

            label ={ Text(text = text)},
           placeholder = {

                Text(text = "Enter Your Name")
            },

            shape = RoundedCornerShape(10.dp)

        )
        

    }
    Button(
        content = {
            Text(text = "Click To Save Name")
        },
        onClick = {
            viewModel.viewModelScope.launch {
                viewModel.insertName(name = text)
            }
        },
        shape = MaterialTheme.shapes.small,


        ) 
   
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun Preview() {
    NameScreen()
}