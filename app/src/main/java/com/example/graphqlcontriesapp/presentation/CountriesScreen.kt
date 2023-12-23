package com.example.graphqlcontriesapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.graphqlcontriesapp.domain.DetailedCountry
import com.example.graphqlcontriesapp.domain.SimpleCountry

@Composable
fun CountriesScreen(
    state: CountriesViewModel.CountriesState,
    onSelectedCountry: (code: String) -> Unit,
    onDismissDialog: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.countries) { country ->
                    CountryItem(country = country, modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelectedCountry(country.code)
                        }
                        .padding(
                            16.dp
                        ))
                }
            }
        }

        if (state.selectedCountry != null) {
            CountryDialog(
                country = state.selectedCountry, onDismiss = onDismissDialog,
                Modifier
                    .clip(
                        RoundedCornerShape(5.dp)
                    )
                    .background(Color.White)
                    .padding(16.dp)
            )
        }
    }

}

@Composable
private fun CountryDialog(
    country: DetailedCountry, onDismiss: () -> Unit, modifier: Modifier = Modifier
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Column(modifier = modifier) {
            Row(modifier = modifier.fillMaxWidth()) {
                Text(
                    text = country.emoji, fontSize = 30.sp
                )
                Spacer(
                    modifier = Modifier.width(
                        16.dp
                    )
                )

                Text(
                    text = country.name, fontSize = 24.sp
                )

            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Continent: " + country.continent
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Currency: " + country.currency
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Capital: " + country.capital
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Language(s): $joinedLanguages"
            )
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

@Composable
private fun CountryItem(
    country: SimpleCountry, modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier, verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = country.emoji, fontSize = 30.sp
        )
        Spacer(
            modifier = Modifier.width(
                16.dp
            )
        )

        Column(modifier = modifier.weight(1f)) {
            Text(
                text = country.name, fontSize = 24.sp
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = country.capital)
        }
    }
}