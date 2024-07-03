package org.angelavbalves.br.`android-ui`.presentation.flows.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.angelavbalves.br.R

@Composable
fun CharactersHome() {

}

@Composable
fun CharactersBackground() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.backgroud_app_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_home),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
        )

    }
}

@Composable
fun CharactersList() {

}

@Composable
fun CharactersListItem() {

}
