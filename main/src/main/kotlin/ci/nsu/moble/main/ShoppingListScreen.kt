package ci.nsu.moble.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = uiState.newItemText,
                onValueChange = { viewModel.onNewItemTextChanged(it) },
                label = { Text("Товар") },
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { viewModel.addItem() },
                modifier = Modifier.padding(start = 8.dp)
            ) {
                Text("Добавить")
            }
        }

        LazyColumn(modifier = Modifier.padding(top = 16.dp)) {
            items(uiState.items) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = item.isBought,
                        onCheckedChange = { viewModel.toggleItemBought(item.id) }
                    )
                    Text(
                        text = item.name,
                        style = if (item.isBought) {
                            MaterialTheme.typography.bodyLarge.copy(
                                textDecoration = androidx.compose.ui.text.style.TextDecoration.LineThrough
                            )
                        } else {
                            MaterialTheme.typography.bodyLarge
                        },
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = { viewModel.deleteItem(item.id) }) {
                        Text("Удалить")
                    }
                }
            }
        }
    }
}