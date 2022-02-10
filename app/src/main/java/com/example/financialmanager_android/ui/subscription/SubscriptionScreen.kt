package com.example.financialmanager_android.ui.subscription

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.financialmanager_android.ui.theme.MainPurple

@Composable
fun SubscriptionScreen() {
    var switchedOn = remember {mutableStateOf(false)};
    Surface(color = Color.White, modifier = Modifier.fillMaxSize()) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Spacer(modifier = Modifier.height(20.dp))
            TotalCard()
            Spacer(modifier = Modifier.height(20.dp))
            Row() {
                Text(text = "Include annual", fontSize = 20.sp, color = MaterialTheme.colors.MainPurple, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(20.dp))
                Switch(checked = switchedOn.value, onCheckedChange = {switchedOn.value = !switchedOn.value})
            }
            Spacer(modifier = Modifier.height(50.dp))
            SubscriptionBox()
        }
    }
}

@Composable
fun SubscriptionBox() {
    Text(text = "Subscriptions", fontSize = 20.sp, color = MaterialTheme.colors.MainPurple, fontWeight = FontWeight.Bold)
    LazyColumn(contentPadding = PaddingValues(16.dp)) {
        items(20) {
            SubscriptionCard()
        }
    }
}

@Composable
fun SubscriptionCard() {
    Card(
        shape = RoundedCornerShape(15.dp),
        elevation = 10.dp,
        modifier = Modifier.padding(top = 20.dp)
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row() {
                Text(text = "Netflix", fontSize = 25.sp, color = MaterialTheme.colors.MainPurple, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.width(150.dp))
                Text(text = "€200", fontSize = 25.sp, color = MaterialTheme.colors.MainPurple, fontWeight = FontWeight.Bold)
            }
            Text(text = "Entertainment", fontSize = 15.sp, color = MaterialTheme.colors.MainPurple)
        }
    }
}

@Composable
fun TotalCard() {
    Card(
        backgroundColor = MaterialTheme.colors.MainPurple,
        shape = RoundedCornerShape(15.dp)
    ) {
      Column(modifier = Modifier.padding(20.dp)) {
          Row() {
              Text(text = "Total", fontSize = 35.sp, color = Color.White, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.width(100.dp))
              Text(text = "€200", fontSize = 35.sp, color = Color.White, fontWeight = FontWeight.Bold)
          }
          Text(text = "Per month", fontSize = 15.sp, color = Color.White)
      }
    }
}

@Preview(showBackground = false)
@Composable
fun SubscriptionPreview() {
    SubscriptionScreen()
}