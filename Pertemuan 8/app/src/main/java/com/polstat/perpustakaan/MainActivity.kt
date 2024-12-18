package com.polstat.perpustakaan

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.polstat.perpustakaan.ui.theme.PerpustakaanTheme

class MainActivity : ComponentActivity() {
    val MyActivityTag: String = "lifecycle"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PerpustakaanTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(MyActivityTag, "onStart State")
    }

    override fun onResume() {
        super.onResume()
        Log.i(MyActivityTag, "onResume State")
    }

    override fun onPause() {
        super.onPause()
        Log.i(MyActivityTag, "onPause State")
    }

    override fun onStop() {
        super.onStop()
        Log.i(MyActivityTag, "onStop State")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(MyActivityTag, "onDestroy State")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(MyActivityTag, "onRestart State")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Selamat Datang Di Perpustakaan $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PerpustakaanTheme {
        Greeting("Politeknik Statistika STIS")
    }
}