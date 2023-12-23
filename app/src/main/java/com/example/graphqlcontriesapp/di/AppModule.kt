package com.example.graphqlcontriesapp.di

import com.apollographql.apollo3.ApolloClient
import com.example.graphqlcontriesapp.data.ApolloCountryClient
import com.example.graphqlcontriesapp.domain.CountryClient
import com.example.graphqlcontriesapp.domain.GetCountriesUseCase
import com.example.graphqlcontriesapp.domain.GetCountryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient {
        return ApolloClient.Builder().serverUrl("https://countries.trevorblades.com/graphql")
            .build()
    }

    @Provides
    @Singleton
    fun provideCountryClient(apolloClient: ApolloClient): CountryClient {
        return ApolloCountryClient(apolloClient)
    }

    @Provides
    @Singleton
    fun provideGetCountriesUseCase(countryClient: ApolloCountryClient): GetCountriesUseCase {
        return GetCountriesUseCase(countryClient)
    }

    @Provides
    @Singleton
    fun provideGetCountryUseCase(countryClient: ApolloCountryClient): GetCountryUseCase {
        return GetCountryUseCase(countryClient)
    }
}