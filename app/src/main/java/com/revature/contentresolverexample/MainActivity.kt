package com.revature.contentresolverexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.revature.contentresolverexample.ui.theme.ContentResolverExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ContentResolverExampleTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ResolverScreen()
                }
            }
        }
    }
}
@Composable
fun ResolverScreen(){
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopAppBar(title = {Text("Receiver Users")}) },
        content = {


            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Spacer(Modifier.size(5.dp))

                //Add customer button
                Button(
                    modifier = Modifier
                        .fillMaxWidth(.6f),
                    onClick = {


                        Toast.makeText(
                            context,
                            "Users Fetched",
                            Toast.LENGTH_SHORT
                        ).show()

                    }) {
                    Text(text = "Fetch Users")

                }

                Spacer(Modifier.size(5.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxHeight(.7f),
                    horizontalAlignment = Alignment.CenterHorizontally){
//                    items(userList.value){
//                        userCard(user = it, userViewModel)
//                    }
                }
            }
        }
    )
}
