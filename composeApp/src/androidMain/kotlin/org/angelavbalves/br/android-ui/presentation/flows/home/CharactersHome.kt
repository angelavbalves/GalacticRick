package org.angelavbalves.br.`android-ui`.presentation.flows.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import flows.common.RequestResult.RequestResult
import flows.home.presentation.ui.models.CharacterHomePresentation
import flows.home.presentation.ui.models.CharacterStatusPresentation
import flows.home.presentation.viewmodels.HomeViewModel
import org.angelavbalves.br.R

@Composable
fun CharactersHome(viewModel: HomeViewModel = HomeViewModel()) {
    val charactersState by viewModel.charactersState.collectAsState()

    CharactersBackground()

    when (charactersState) {
        is RequestResult.Loading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        is RequestResult.Success -> {
            RickAndMortyScreen((charactersState as RequestResult.Success<List<CharacterHomePresentation>>).data)
        }
        is RequestResult.Error -> {
            GenericError((charactersState as RequestResult.Error).exception.message)
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchCharacters()
    }
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
    }
}

@Composable
fun GenericError(error: String?) {
    Text(
        text = error ?: "An error occurred",
        color = MaterialTheme.colors.error,
        modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun RickAndMortyScreen(characters: List<CharacterHomePresentation>) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2E7D32))
            .padding(16.dp)
    ) {
        TopSection()
        SearchBar()
        StatusFilters()
        CharacterList(characters)
    }
}

@Composable
fun TopSection() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.rick_and_morty_home),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .padding(16.dp)
        )
//        AsyncImage(
//            modifier = Modifier.fillMaxWidth(),
//            model = ImageRequest.Builder(LocalContext.current)
//                .data("https://link_para_logo_rick_and_morty")
//                .build(),
//            contentDescription = null,
//            contentScale = ContentScale.FillWidth
//        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "E aí, gênio! O que você quer descobrir sobre esses imbecis?",
            color = Color.White,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun SearchBar() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp)
            .background(Color.White)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Digite o nome do personagem") },
            modifier = Modifier.weight(1f),
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            )
        )
        IconButton(onClick = { /* Implementar busca */ }) {
            Icon(
                painter = painterResource(id = R.drawable.fi_rr_search),
                contentDescription = "Search",
                tint = Color.Gray
            )
        }
    }
}

@Composable
fun StatusFilters() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        StatusFilter("Todos")
        StatusFilter("Vivo")
        StatusFilter("Morto")
        StatusFilter("Desconhecido")
    }
}

@Composable
fun StatusFilter(status: String) {
    Button(
        onClick = { /* Filtrar personagens */ },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
    ) {
        Text(text = status, color = Color.Black)
    }
}

@Composable
fun CharacterList(characters: List<CharacterHomePresentation>) {
    LazyColumn {
        items(characters) { character ->
            CharacterRow(character)
        }
    }
}

@Composable
fun CharacterRow(character: CharacterHomePresentation) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFF388E3C))
            .padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.imageUrl)
                .build(),
            contentDescription = character.characterName,
            modifier = Modifier
                .size(56.dp)
                .background(Color.White)
                .padding(4.dp),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(
                text = character.characterName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp
            )
            Text(
                text = character.episodes,
                color = Color.White,
                fontSize = 14.sp
            )
            Text(
                text = character.status.status,
                color = when (character.status) {
                    CharacterStatusPresentation.Alive-> Color.Green
                    CharacterStatusPresentation.Dead -> Color.Red
                    else -> Color.Gray
                },
                fontSize = 14.sp
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun RickAndMortyScreenPreview() {
//    val characters = listOf(
//        Character("Rick Sanchez", "https://link_para_imagem_rick", 51, "Vivo"),
//        Character("Morty Smith", "https://link_para_imagem_morty", 51, "Vivo"),
//        Character("Jerry Smith", "https://link_para_imagem_jerry", 39, "Vivo"),
//        Character("Abadango Cluster Princess", "https://link_para_imagem_abadango", 1, "Vivo"),
//        Character("Abradolf Lincler", "https://link_para_imagem_abradolf", 2, "Desconhecido"),
//        Character("Adjudicator Rick", "https://link_para_imagem_adjudicator", 1, "Morto"),
//        Character("Agency Director", "https://link_para_imagem_agency", 1, "Morto"),
//        Character("Alan Rails", "https://link_para_imagem_alan", 1, "Morto"),
//        Character("Doofus Rick", "https://link_para_imagem_doofus", 2, "Desconhecido")
//    )
//    RickAndMortyScreen(characters)
//}

