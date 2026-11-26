# Profil développeur – v2

Application Jetpack Compose qui affiche le profil d'une développeuse : avatar avec badge de disponibilité, nom, rôle, description, compétences, contact, bouton « Me contacter » et liste de projets. Les données sont locales.

## Composants Compose

Text, Button, Icon, Image, Card, OutlinedTextField (lecture seule).

## Layouts

- **Column** : structure de la carte de profil
- **Row** : avatar et nom sur la même ligne
- **Box** : badge de disponibilité superposé sur l'avatar
- **Spacer** : espaces entre les éléments
- **Surface** : fond de l'écran et badge

## Liste avec LazyColumn

L'écran est une `LazyColumn` : `item` affiche la carte de profil et le titre, `items` affiche chaque projet de `sampleProjects()` avec le composable réutilisable `ProjectCard`.

## Choix de thème

Le thème suit la démarche du TP7 : mêmes rôles de couleurs, mêmes arrondis et mêmes styles de titres, avec une palette bleue en clair et en sombre. Tous les composants utilisent MaterialTheme.colorScheme, typography et shapes

## Capture d'écran

![Thème clair et sombre](screenshots/capture2.png)

## Quelles notions du chapitre Compose UI avez-vous réutilisées ?

Les composants fondamentaux, les layouts, une liste avec `LazyColumn`, la personnalisation Material Design 3 (couleurs, typographie, formes, thème clair et sombre). L'écran est découpé en petits composables réutilisables, avec leurs données et un modifier en paramètres, et son rendu est vérifié avec des previews.