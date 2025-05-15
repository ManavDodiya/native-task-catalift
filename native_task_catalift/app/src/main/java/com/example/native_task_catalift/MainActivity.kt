package com.example.native_task_catalift

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.with
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.native_task_catalift.ui.theme.Native_task_cataliftTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Native_task_cataliftTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "mentor") {
                    composable("mentor") {
                        MentorInfoScreen(navController)
                    }
                    composable("notifications") {
                        NotificationScreen(navController)  // Pass navController here
                    }
                }
            }
        }
    }
}

data class Experience(
    val title : String,
    val company : String,
    val dataRange : String,
    val location : String,
    val description: List<String>,
    val logoUrl : String
)

@Composable
fun MentorInfoScreen(navController: NavController) {
    val experinceList = remember {
        listOf(
            Experience(
                "User Experience Designer",
                "Alphabet Incorporation",
                "Aug 2024 - Present",
                "Pune, Maharashtra",
                listOf(
                    "Creating and refining design samples to kickstart projects.",
                    "Prototyping and building landing pages.",
                    "Learning HTML and CSS for front-end integration."
                ),
                logoUrl = "https://upload.wikimedia.org/wikipedia/commons/2/2f/Google_2015_logo.svg"
            )
        )
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            BannerSection(navController)
            ProfileSection()
            TagSection()
            ActionButtons()
            BioSection()
            SectionTitle("Experience")
            experinceList.forEach {
                ExperienceCard(it)
            }
            experinceList.forEach {
                ExperienceCard(it)
            }
            SectionTitle("Education")
            experinceList.forEach {
                ExperienceCard(it)
            }
            experinceList.forEach {
                ExperienceCard(it)
            }
        }
    }
}

@Composable
fun BannerSection(navController: NavController) {
    Box(
        modifier = Modifier
            .height(200.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.banner),
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        IconButton(
            onClick = {
                navController.navigate("notifications")
            },
            modifier = Modifier
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = "Notifications",
                tint = Color.White,
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }
}

@Composable
fun ProfileSection() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.profile_photo),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .offset(y = (-50).dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Text(modifier = Modifier.offset(y = (-35).dp),
            text = "Manav Dodiya",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )

        Row(
            modifier = Modifier.offset(y = (-35).dp),
            verticalAlignment = Alignment.CenterVertically) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD700))
            Text("4.5", modifier = Modifier.padding(start = 4.dp))
        }
        Text(
            modifier = Modifier.offset(y = (-35).dp),
            text = "Android Developer",
            fontSize = 15.sp)
    }
}

@Composable
fun TagSection() {
    Row(
        modifier = Modifier
            .padding(8.dp)
            .offset(y = (-10).dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Google  |  Oxford University  |  Information Technology",
            color = Color.Blue,
            fontSize = (12.5).sp
        )
    }
}



@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ActionButtons() {
    var isConnected by remember { mutableStateOf(false) }
    val buttonText by animateColorAsState(
        targetValue = if (isConnected) Color.Gray else Color.White,
        animationSpec = tween(durationMillis = 500)
    )
    val buttonBackground by animateColorAsState(
        targetValue = if (isConnected) Color.LightGray else Color.Blue,
        animationSpec = tween(durationMillis = 500)
    )

    Row(modifier = Modifier.padding(8.dp)) {
        Button(
            onClick = {
                isConnected = !isConnected
            },
            shape = RoundedCornerShape(24.dp),
            modifier = Modifier.padding(end = 8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonBackground,
                contentColor = buttonText
            )
        ) {
            AnimatedContent(
                targetState = isConnected,
                transitionSpec = {
                    slideInVertically { height -> height } + fadeIn() with
                            slideOutVertically { height -> -height } + fadeOut()
                }
            ) { connected ->
                if (connected) {
                    Text("Requested")
                } else {
                    Text("Connect")
                }
            }
        }
        OutlinedButton(
            onClick = {},
            shape = RoundedCornerShape(24.dp)
        ) {
            Text("Message")
        }
    }
}

@Composable
fun BioSection() {
    Text(
        text = "I’m an IT Engineering student at JG University, focused on DSA, SQL, Python, and Java. Passionate about backend development and building efficient, scalable systems.I enjoy problem-solving and exploring backend tools and frameworks. Eager to learn and grow through hands-on projects and real-world experience.",
        modifier = Modifier.padding(16.dp),
        fontSize = 14.sp
    )
}

@Composable
fun SectionTitle(title: String) {
    Column(horizontalAlignment = AbsoluteAlignment.Left) {
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp)
        )
    }
}

@Composable
fun ExperienceCard(experience: Experience) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalAlignment = Alignment.Top
    ) {
        Image(
            painter = painterResource(id = R.drawable.google_logo),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(experience.title, fontWeight = FontWeight.Bold)
            Text(experience.company)
            Text("${experience.dataRange} \u2022 ${experience.location}", fontSize = 12.sp)
            experience.description.forEach {
                Text("- $it", fontSize = 12.sp)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Notifications") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "No notifications", fontSize = 18.sp)
        }
    }
}