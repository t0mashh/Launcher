package pro.gravit.launcher.gui

import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import pro.gravit.launcher.runtime.LauncherEngine

// Этот класс будет вызываться из JavaRuntimeModule
class ComposeInterface(private val engine: LauncherEngine) {

    fun start() {
        // Запуск Compose приложения. 
        // Важно: application блокирует поток, поэтому запускать нужно аккуратно.
        application {
            val windowState = rememberWindowState(width = 800.dp, height = 600.dp)

            Window(
                onCloseRequest = ::exitApplication, 
                title = "Gravit Launcher (Compose)",
                state = windowState
            ) {
                MaterialTheme {
                    // Это наш временный экран для теста
                    MainScreen(engine)
                }
            }
        }
    }
}

@Composable
fun MainScreen(engine: LauncherEngine) {
    var message by remember { mutableStateOf("Runtime запущен на Compose!") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = message)
            Spacer(modifier = Modifier.height(20.dp))
            
            Button(onClick = { 
                message = "Профили: " + (engine.launcherConfig?.clientProfiles?.size ?: 0) 
            }) {
                Text("Проверить связь с ядром")
            }
        }
    }
}
