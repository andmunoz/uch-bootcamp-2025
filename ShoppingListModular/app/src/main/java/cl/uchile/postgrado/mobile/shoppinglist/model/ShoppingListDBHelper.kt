package cl.uchile.postgrado.mobile.shoppinglist.model

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ShoppingListDBHelper(context: Context):
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "shopping_list.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = """
            CREATE TABLE products (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                productQuantity INTEGER,
                productName TEXT,
                productBrand TEXT,
                productDescription TEXT,
                productCategory TEXT,
                productPrice REAL
            )
        """.trimIndent()
        db?.execSQL(createTable)
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVer: Int,
        newVer: Int,
    ) {
        db?.execSQL("DROP TABLE IF EXISTS products")
        onCreate(db)
    }

    fun addOrUpdateProduct(product: ProductData) {
        val db = writableDatabase
        val values = ContentValues().apply {
            put("productQuantity", product.productQuantity)
            put("productName", product.productName)
            put("productBrand", product.productBrand)
            put("productDescription", product.productDescription)
            put("productCategory", product.productCategory)
        }
        if (product.id == null) {
            db.insert("products", null, values)
        } else {
            db.update("products", values, "id = ?", arrayOf(product.id.toString()))
        }
        db.close()
    }

    fun getProductById(id: Int): ProductData? {
        val db = readableDatabase
        val cursor = db.query(
            "products",
            arrayOf("id", "productQuantity", "productName", "productBrand", "productDescription", "productCategory", "productPrice"),
            "id = ?",
            arrayOf(id.toString()),
            null,
            null,
            null
        )
        var product: ProductData? = null
        if (cursor.moveToFirst()) {
            val productQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("productQuantity"))
            val productName = cursor.getString(cursor.getColumnIndexOrThrow("productName"))
            val productBrand = cursor.getString(cursor.getColumnIndexOrThrow("productBrand"))
            val productDescription =
                cursor.getString(cursor.getColumnIndexOrThrow("productDescription"))
            val productCategory = cursor.getString(cursor.getColumnIndexOrThrow("productCategory"))
            val productPrice = cursor.getFloat(cursor.getColumnIndexOrThrow("productPrice"))
            product = ProductData(
                id = id,
                productQuantity = productQuantity,
                productName = productName,
                productBrand = productBrand,
                productDescription = productDescription,
                productCategory = productCategory,
                productPrice = productPrice
            )
        }
        cursor.close()
        db.close()
        return product
    }

    fun getProducts(): List<ProductData> {
        val products = mutableListOf<ProductData>()
        val db = readableDatabase
        val cursor = db.query(
            "products",
            arrayOf("id", "productQuantity", "productName", "productBrand", "productDescription", "productCategory", "productPrice"),
            null,
            null,
            null,
            null,
            null
        )
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow("id"))
            val productQuantity = cursor.getInt(cursor.getColumnIndexOrThrow("productQuantity"))
            val productName = cursor.getString(cursor.getColumnIndexOrThrow("productName"))
            val productBrand = cursor.getString(cursor.getColumnIndexOrThrow("productBrand"))
            val productDescription = cursor.getString(cursor.getColumnIndexOrThrow("productDescription"))
            val productCategory = cursor.getString(cursor.getColumnIndexOrThrow("productCategory"))
            val productPrice = cursor.getFloat(cursor.getColumnIndexOrThrow("productPrice"))
            val product = ProductData(
                id = id,
                productQuantity = productQuantity,
                productName = productName,
                productBrand = productBrand,
                productDescription = productDescription,
                productCategory = productCategory,
                productPrice = productPrice
            )
            products.add(product)
        }
        cursor.close()
        db.close()
        return products
    }
}