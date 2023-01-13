package com.ozcan.alasalvar.database.fake

import java.io.InputStream

fun interface AssetManager {
    fun open(fileName: String): InputStream
}