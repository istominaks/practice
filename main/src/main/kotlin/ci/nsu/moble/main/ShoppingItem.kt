package ci.nsu.moble.main

data class ShoppingItem(
    val id: Int,
    val name: String,
    val isBought: Boolean = false
)