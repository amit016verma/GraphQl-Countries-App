package com.example.graphqlcontriesapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue


import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.graphqlcontriesapp.presentation.CountriesScreen
import com.example.graphqlcontriesapp.presentation.CountriesViewModel
import com.example.graphqlcontriesapp.ui.theme.GraphQLContriesAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GraphQLContriesAppTheme {
                val viewModel = hiltViewModel<CountriesViewModel>()

                val state by viewModel.state.collectAsState()

                CountriesScreen(state = state,
                    onSelectedCountry = viewModel::getSelectedCountry,
                    onDismissDialog = viewModel::dismissCountryDialog)

            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GraphQLContriesAppTheme {
        Greeting("Android")
    }
}