package com.example.graphqlcontriesapp.domain

import com.example.CountryQuery

data class SimpleCountry(
    val code: String,
    val name: String,
    val capital: String,
    val emoji: String
)
