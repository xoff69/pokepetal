# Projet Pokemons pour Petal

Hypotheses et remarques:
- j'ai considere qu'il fallait lire les donnees systematiquement sur l'url du fichier csv et non pas mettre dans l 'application le fichier csv
- l'url est stockee dans le fichier application.yml
- construit avec springboot 3.0 et java 19
- J'ai utilise le pattern DTO pour les pokemon
- Le repertoire postman contient mes tests postman realises sur l url localhost

VERBES:
- GET  http://localhost:8080/pokemons/id : cherche un pokemon par id, 302 si trouve, 404 sinon
- GET  http://localhost:8080/pokemons/id : 
- DELETE  http://localhost:8080/pokemons/id : efface un pokemon sur son id, 204 si trouve (no content),404 sinon
- POST 
- PUT

Tests unitaires en place:
- Application: controller non null
- Test controller:
  - GET par identifiant trouve/non trouve
  - GET de tous les pokemons pagines
  - POST cree un pokemon et Put update
  - DELETE efface un pokemon
- CSV reader

RAPPEL DES INSTRUCTIONS:

L'objectif de l’exercice technique est de créer un petit projet qui exposera un API Restful à un éventuel client. Nous validerons le fonctionnement du projet via l’application Postman.

Il n'y a pas de cadre ou de technologie obligatoire, bien que nous recommandons l’utilisation du framework Ruby on Rails. Nous vous encourageons également à écrire des tests unitaires, bien que ce ne soit pas une obligation.

Vous aurez accès à un fichier en format CSV (lien ci-bas) qui contient une liste de Pokemon. Ce fichier fera office d’élément de base pour remplir la base de données (seeds), et nous vous demandons donc d’exposer un API permettant d’effectuer les actions CRUD (Create, Read, Update, Delete). Nous aimerions également obtenir une liste paginée de tous les Pokemon présents dans le fichier.

Une fois le projet terminé vous devez nous partager le lien du projet dans votre GitHub ou autre solution similaire.

Voici le lien pour le fichier CSV : https://gist.github.com/armgilles/194bcff35001e7eb53a2a8b441e8b2c6
