package com.example.estoquemercado

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ItemListScreen(
    items: List<Item>,
    onAddItem: () -> Unit,
    onRemoveItem: (Item) -> Unit,
    onEditItem: (Item) -> Unit
) {
    Column {
        // Lista de itens
        LazyColumn {
            items(items) { item ->
                ItemRow(item, onRemoveItem, onEditItem)
            }
        }
        Spacer(modifier = Modifier.width(8.dp))
        Button(
            onClick = onAddItem,
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
        ) {
            Text("Adicionar Item", color = Color.Black)
        }
    }
}

@Composable
fun ItemRow(item: Item, onRemoveItem: (Item) -> Unit, onEditItem: (Item) -> Unit) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(item.imageUrl),
                contentDescription = item.name,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    item.name,
                    style = TextStyle(fontSize = 20.sp)
                )
                Text("Quantidade: ${item.quantity}")
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Row {
            Button(
                onClick = { onEditItem(item) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
            ) {
                Text("Editar", color = Color.Black)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Button(
                onClick = { onRemoveItem(item) },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text("Remover", color = Color.White)
            }
        }
    }
}
