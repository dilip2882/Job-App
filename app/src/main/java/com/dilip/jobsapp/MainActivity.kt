package com.dilip.jobsapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.dilip.jobsapp.presentation.home.HomeScreen
import com.dilip.jobsapp.presentation.job_details.JobsDetailsScreen
import com.dilip.jobsapp.ui.theme.JobsAppTheme
import com.dilip.jobsapp.utils.NavRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JobsAppTheme {
                val navController = rememberNavController()
                val isBottomBarVisible = remember {
                    mutableStateOf(true)
                }
                Scaffold(
                    bottomBar = {
                        AnimatedVisibility(visible = isBottomBarVisible.value) {
                            BottomAppBar(containerColor = Color.Blue.copy(alpha = 0.3f)) {
                                val currentRoute =
                                    navController.currentBackStackEntryAsState().value?.destination?.route
                                bottomNavItems.forEach {
                                    NavigationBarItem(
                                        icon = {
                                            Image(
                                                imageVector = it.icon,
                                                contentDescription = null
                                            )
                                        },
                                        label = { Text(text = it.title) },
                                        selected = currentRoute == it.route,
                                        onClick = {
                                            navController.navigate(it.route) {
                                                popUpTo(navController.graph.startDestinationId) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = NavScreen.Home.route
                        ) {
                            composable(NavScreen.Home.route) {
                                HomeScreen(navController = navController)
                                isBottomBarVisible.value = true
                            }
                            composable("/details/news={news}&isLocal={isLocal}") {
                                val newsJson = it.arguments?.getString("jobs")
                                val isLocal = it.arguments?.getString("isLocal").toBoolean()
                                val jobs = NavRoute.getJobsFromRoute(newsJson!!)
                                JobsDetailsScreen(
                                    navController = navController,
                                    jobs,
                                    isLocal ?: false
                                )
                                isBottomBarVisible.value = false
                            }


                        }
                    }
                }
            }
        }
    }
}

sealed class NavScreen(val route: String, val icon: ImageVector, val title: String) {
    object Home : NavScreen("/home", Icons.Filled.Home, "Home")
    object Bookmarks : NavScreen("/bookmarks", Icons.Filled.Favorite, "Bookmarks")
}

val bottomNavItems = listOf(
    NavScreen.Home,
    NavScreen.Bookmarks
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    JobsAppTheme {
    }
}