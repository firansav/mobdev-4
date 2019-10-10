package com.example.tutorial4

import java.io.Serializable

data class Artist(val imageResId: Int,
                  val name: String,
                  val description: String,
                  val url: String,
                  var text: String = "") : Serializable
