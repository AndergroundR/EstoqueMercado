package com.example.estoquemercado

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddEditItemScreen(
    item: Item?,
    onSave: (Item) -> Unit,
    onCancel: () -> Unit
) {
    var name by remember { mutableStateOf(item?.name ?: "") }
    var quantity by remember { mutableStateOf(item?.quantity ?: 0) }
    var imageUrl by remember { mutableStateOf(item?.imageUrl ?: "") }

    Column {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nome") }
        )
        TextField(
            value = quantity.toString(),
            onValueChange = { quantity = it.toIntOrNull() ?: 0 },
            label = { Text("Quantidade") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        ImagePicker(onImageSelected = { uri ->
            imageUrl = uri.toString()
        })
        Row {
            Button(
                onClick = { onSave(Item(item?.id ?: 0, name, quantity, imageUrl)) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
            ) {
                Text("Salvar")
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = onCancel,
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text("Cancelar", color = Color.White)
            }
        }
    }
}
