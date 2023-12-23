package com.example.graphqlcontriesapp.data

import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphqlcontriesapp.domain.DetailedCountry
import com.example.graphqlcontriesapp.domain.SimpleCountry


fun CountryQuery.Country.toDetailedCountry(): DetailedCountry {
    return DetailedCountry(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No Capital",
        currency = currency ?: "No currency",
        languages = languages.mapNotNull { name },
        continent = continent.name
    )
}

fun CountriesQuery.Country.toSimpleCountry(): SimpleCountry {
    return SimpleCountry(
        code = code, name = name, emoji = emoji, capital = capital ?: "No Capital"
    )
}