package com.example.graphqlcontriesapp.data

import com.apollographql.apollo3.ApolloClient
import com.example.CountriesQuery
import com.example.CountryQuery
import com.example.graphqlcontriesapp.domain.CountryClient
import com.example.graphqlcontriesapp.domain.DetailedCountry
import com.example.graphqlcontriesapp.domain.SimpleCountry
import javax.inject.Inject

class ApolloCountryClient @Inject constructor(
    private val apolloClient: ApolloClient
) : CountryClient {

    override suspend fun getCountries(): List<SimpleCountry> {
        return apolloClient.query(CountriesQuery()).execute().data?.countries?.map {
            it.toSimpleCountry()
        } ?: emptyList()
    }

    override suspend fun getCountry(code: String): DetailedCountry? {
        return apolloClient.query(CountryQuery(code)).execute().data?.country?.toDetailedCountry()
    }
}