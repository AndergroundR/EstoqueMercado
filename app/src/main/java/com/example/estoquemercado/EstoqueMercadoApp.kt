package com.example.estoquemercado

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
fun EstoqueMercadoApp() {
    var items by remember { mutableStateOf(itemList) }
    var currentItem by remember { mutableStateOf<Item?>(null) }
    var isEditing by remember { mutableStateOf(false) }

    fun addItem(item: Item) {
        items = (items + item).toMutableList()
    }

    fun removeItem(item: Item) {
        items = (items - item).toMutableList()
    }

    fun editItem(updatedItem: Item) {
        items = items.map { if (it.id == updatedItem.id) updatedItem else it }.toMutableList()
    }

    if (isEditing) {
        AddEditItemScreen(
            item = currentItem,
            onSave = { item ->
                if (currentItem == null) {
                    addItem(item)
                } else {
                    editItem(item)
                }
                isEditing = false
                currentItem = null
            },
            onCancel = {
                isEditing = false
                currentItem = null
            }
        )
    } else {
        ItemListScreen(
            items = items,
            onAddItem = {
                currentItem = null
                isEditing = true
            },
            onRemoveItem = { removeItem(it) },
            onEditItem = {
                currentItem = it
                isEditing = true
            }
        )
    }
}
