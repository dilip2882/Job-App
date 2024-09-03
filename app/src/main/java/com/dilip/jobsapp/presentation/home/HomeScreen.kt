package com.dilip.jobsapp.presentation.home


import android.R
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.dilip.jobsapp.State
import com.dilip.jobsapp.data.model.Result
import com.dilip.jobsapp.utils.NavRoute

@Composable
fun HomeScreen(navController: NavHostController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState = viewModel.state.collectAsState()
    val searchText = remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
    ) {
        //search bar
        SearchBar(text = searchText.value, onSearch = {
            searchText.value = it
            viewModel.getJobs(text = it)
        })
        Spacer(modifier = Modifier.height(16.dp))
        // categories

        //rest of data
        when (uiState.value) {
            is State.Loading -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                    Text(text = "Loading...")
                }
            }

            is State.Error -> {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Failed!")
                    Text(text = (uiState.value as State.Error).error)
                    Button(onClick = { viewModel.getJobs(searchText.value) }) {
                        Text(text = "Retry")
                    }
                }
            }

            else -> {
                val data = (uiState.value as State.Success).data
                JobsListView(news = data.results) {
//                    navController.navigate(NavRoute.createJobsDetailsRoute(it))

                }
            }
        }
    }
}

@Composable
fun JobsListView(
    news: List<Result>,
    onClick: (Result) -> Unit
) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(text = "Jobs")
        }
        items(news) { job ->
            JobsItem(job, onClick = { onClick(job) })

        }
    }
}

@Composable
fun JobsItem(job: Result, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Red.copy(alpha = 0.2f))
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = job.title,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(16.dp)),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Text(
                text = job.status.toString(),
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.TopCenter)
            )
            Text(
                text = job.salary_max.toString(),
                color = Color.White,
                modifier = Modifier.align(Alignment.BottomEnd)
            )

//            Text(
//                text = job.whatsapp_no.toString(),
//                color = Color.White,
//                modifier = Modifier.align(Alignment.BottomStart)
//            )
        }
    }
}

@Composable
fun SearchBar(text: String, onSearch: (String) -> Unit) {

    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = text,
            onValueChange = onSearch,
            label = { Text(text = "Search") },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.fillMaxWidth()
        )
        Image(
            painter = painterResource(id = R.drawable.ic_menu_search),
            contentDescription = null,
            modifier = Modifier
                .padding(end = 16.dp)
                .align(Alignment.CenterEnd)
        )
    }

}