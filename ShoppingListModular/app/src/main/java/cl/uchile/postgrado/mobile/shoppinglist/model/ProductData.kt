package cl.uchile.postgrado.mobile.shoppinglist.model

data class ProductData(
    val id: Int?,
    val productQuantity: Int,
    val productName: String,
    val productBrand: String,
    val productDescription: String,
    val productCategory: String,
    val productPrice: Float?
)