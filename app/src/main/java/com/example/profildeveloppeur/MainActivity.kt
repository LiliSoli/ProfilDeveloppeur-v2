package com.example.profildeveloppeur

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.profildeveloppeur.ui.theme.ProfilDeveloppeurTheme
import androidx.compose.material.icons.Icons
import androidx.compose.ui.res.painterResource
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.foundation.lazy.items

data class ProjectUi(
    val id: Int,
    val title: String,
    val description: String
)

fun sampleProjects(): List<ProjectUi> {
    return listOf(
        ProjectUi(
            id = 1,
            title = "GreenCork",
            description = "Application web B2B pour un importateur de vins"
        ),
        ProjectUi(
            id = 2,
            title = "Notae",
            description = "Projet de groupe réalisé pendant la formation CDA"
        ),
        ProjectUi(
            id = 3,
            title = "Profil développeur",
            description = "Application Android avec Jetpack Compose"
        )
    )
}

fun sampleSkills(): List<String> {
    return listOf("Java", "Spring Boot", "React", "Kotlin", "Compose")
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProfilDeveloppeurTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ProfileScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Surface(
        color = Color.LightGray,
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ProfileCard(
                    name = "Aurélie S.",
                    role = "Développeuse Java",
                    description = "En alternance chez Worldline",
                    skills = sampleSkills(),
                    isAvailable = true,
                    email = "aurelie.s@email.com",
                    location = "Tours, France",
                    onContactClick = {
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            item {
                Text(
                    text = "Mes projets",
                    style = MaterialTheme.typography.titleLarge
                )
            }
            items(
                items = sampleProjects(),
                key = { project -> project.id }
            ) { project ->
                ProjectCard(project = project)
            }
        }
    }
}

@Composable
fun ProfileCard(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    email: String,
    location: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        ProfileCardContent(
            name = name,
            role = role,
            description = description,
            skills = skills,
            isAvailable = isAvailable,
            email = email,
            location = location,
            onContactClick = onContactClick
        )
    }
}

@Composable
fun ProfileCardContent(
    name: String,
    role: String,
    description: String,
    skills: List<String>,
    isAvailable: Boolean,
    email: String,
    location: String,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp)
    ) {
        ProfileHeader(
            name = name,
            role = role,
            isAvailable = isAvailable
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = description,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = "Compétences :",
            modifier = Modifier.padding(bottom = 4.dp)
        )
        skills.forEach { skill ->
            SkillItem(
                skill = skill,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
        ContactField(
            label = "Email",
            value = email,
            icon = Icons.Default.Email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
        )
        ContactField(
            label = "Localisation",
            value = location,
            icon = Icons.Default.LocationOn,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
        )
        ContactButton(
            onContactClick = onContactClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        )
    }
}

@Composable
fun ProfileAvatar(
    name: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(id = android.R.drawable.ic_menu_gallery),
            contentDescription = "Avatar de $name",
            modifier = Modifier
                .size(80.dp)
        )
        Availability(
            isAvailable = isAvailable,
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )
    }
}

@Composable
fun ProfileHeader(
    name: String,
    role: String,
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        ProfileAvatar(
            name = name,
            isAvailable = isAvailable
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Text(text = role, fontSize = 16.sp, color = Color.DarkGray)
        }
    }
}

@Composable
fun SkillItem(
    skill: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "• $skill",
        modifier = modifier
    )
}

@Composable
fun Availability(
    isAvailable: Boolean,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.small,
        tonalElevation = 6.dp
    ) {
        Text(
            text = if (isAvailable) {
                "Disponible"
            } else {
                "Indisponible"
            },
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun ContactField(
    label: String,
    value: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = {},
        readOnly = true,
        label = { Text(text = label) },
        leadingIcon = {
            Icon(imageVector = icon, contentDescription = null)
        },
        modifier = modifier
    )
}

@Composable
fun ContactButton(
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onContactClick,
        modifier = modifier
    ) {
        Text(text = "Me contacter")
    }
}

@Composable
fun ProjectCard(
    project: ProjectUi,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth()) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = project.title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardAvailablePreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Aurélie S.",
            role = "Développeuse Java",
            description = "En alternance chez Worldline",
            skills = sampleSkills(),
            isAvailable = true,
            email = "aurelie.s@email.com",
            location = "Tours, France",
            onContactClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileCardUnavailablePreview() {
    ProfilDeveloppeurTheme {
        ProfileCard(
            name = "Thomas M.",
            role = "Développeur Java",
            description = "Développeur Java",
            skills = listOf("Java", "Spring", "Nuxeo"),
            isAvailable = false,
            email = "thomas.m@email.com",
            location = "Paris, France",
            onContactClick = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp)
        )
    }
}