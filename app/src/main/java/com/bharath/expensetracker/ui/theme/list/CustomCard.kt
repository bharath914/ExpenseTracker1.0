package com.bharath.expensetracker.ui.theme.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bharath.expensetracker.R
import com.bharath.expensetracker.ui.theme.Lato_LightItalic
import com.bharath.expensetracker.ui.theme.Lato_Regular


@Composable
fun CustomCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(15.dp),

        shape = RoundedCornerShape(8.dp),
    ) {

        MainRow()

    }

}

@Composable
private fun MainRow() {
    Row(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(2f)) {

        ImageRow()
        }
        Box(modifier = Modifier.weight(4f)) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .weight(4f)

                        .alpha(0.9f),
                    text = "Mode",
                    fontSize = 20.sp,
                    fontFamily = Lato_Regular,
                    color = Color.Black,

                    )
                Text(
                    modifier = Modifier
                        .weight(2f), text = "date and time",
                    textAlign = TextAlign.End,
                    fontFamily = Lato_LightItalic,
                    fontSize = 15.sp, color = Color.Black
                )
            }

        }
        Box(modifier = Modifier.weight(3f)) {

        }
    }
}

@Composable
private fun ImageRow() {
Image(painter = painterResource(id = R.drawable.expenselogo), contentDescription ="" )
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CustomCard()
}