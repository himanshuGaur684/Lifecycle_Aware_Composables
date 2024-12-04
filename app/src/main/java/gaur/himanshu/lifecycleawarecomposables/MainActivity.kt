package gaur.himanshu.lifecycleawarecomposables

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import gaur.himanshu.lifecycleawarecomposables.ui.theme.LifecycleAwareComposablesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LifecycleAwareComposablesTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

    val lifecyleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecyleOwner) {

        val lifeCycleEvents = LifecycleEventObserver { source, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> Log.d("TAGGGGG", "Greeting: ON_CREATE")
                Lifecycle.Event.ON_START -> Log.d("TAGGGGG", "Greeting: ON_START")
                Lifecycle.Event.ON_RESUME -> Log.d("TAGGGGG", "Greeting: ON_RESUME")
                Lifecycle.Event.ON_PAUSE -> Log.d("TAGGGGG", "Greeting: ON_PAUSE")
                Lifecycle.Event.ON_STOP -> Log.d("TAGGGGG", "Greeting: ON_STOP")
                Lifecycle.Event.ON_DESTROY -> Log.d("TAGGGGG", "Greeting: ON_DESTROY")
                Lifecycle.Event.ON_ANY -> Log.d("TAGGGGG", "Greeting: ON_ANY")
            }
        }

        lifecyleOwner.lifecycle.addObserver(lifeCycleEvents)

        onDispose {
            lifecyleOwner.lifecycle.removeObserver(lifeCycleEvents)
        }
    }
    Column(modifier = modifier.fillMaxSize()) {

        Text("lifecycle aware composable")
    }

}
