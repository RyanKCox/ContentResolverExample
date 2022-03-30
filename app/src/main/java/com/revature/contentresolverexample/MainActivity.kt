package com.revature.contentresolverexample

import android.Manifest
import android.annotation.SuppressLint
import android.content.ContentResolver
import android.net.Uri
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.revature.contentresolverexample.ui.theme.ContentResolverExampleTheme


class MainActivity : ComponentActivity() {
    companion object{

        const val PROVIDER_NAME="com.revature.user.provider"
        const val TABLE_NAME="users"

        val CONTENT_URI = Uri.Builder()
            .scheme(ContentResolver.SCHEME_CONTENT)
            .authority(PROVIDER_NAME)
            .appendPath(TABLE_NAME).build()
    }
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

@SuppressLint("Range")
@Composable
fun ResolverScreen(){
    val scaffoldState = rememberScaffoldState()
    val context = LocalContext.current
    var sResult by rememberSaveable { mutableStateOf("") }

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

                        val cursor =
                            context.contentResolver.query(
                                MainActivity.CONTENT_URI,
                            null,null,null,null
                        )
                        if ( cursor!!.moveToFirst()){



                            while (!cursor.isAfterLast){


                                sResult += "${cursor.getString(
                                    cursor.getColumnIndex("id"))}-${cursor.getString(cursor.getColumnIndex("name"))}"
                                cursor.moveToNext()
                                sResult += "\n"
                            }
                            Log.d("Data", sResult)
                        }
                        else {
                            Log.d("Data", "No Records Found")

                            Toast.makeText(
                                context,
                                "Users Fetched",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

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
