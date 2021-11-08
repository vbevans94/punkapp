package com.punkapp.beers

import com.punkapp.api.ApiBeer

data class Beer(val id: String, val name: String, val description: String, val imageUrl: String)

fun ApiBeer.toBeer() = Beer(id = id, name = name, description = description, imageUrl = imageUrl)