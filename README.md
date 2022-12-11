# Projet Pokemons pour Petal

Hypotheses et remarques:
- j'ai considere qu'il fallait lire les donnees systematiquement sur l'url du fichier csv et non pas mettre dans l 'application le fichier csv
- l'url est stockee dans le fichier application.yml
- projet construit avec springboot 3.0 et java 19
- J'ai utilise le pattern DTO pour les pokemon
- Le repertoire postman contient mes tests postman realises sur l url localhost
- base de donnees h2 en memoire


VERBES: (Pour les codes retour, je m appuie sur https://www.restapitutorial.com/lessons/httpmethods.html)
- GET  http://localhost:8080/pokemons/id : cherche un pokemon par id, 200 si trouve, 404 sinon
- GET  http://localhost:8080/pokemons?page=5&size=3:  liste des pokemons par page [numero de page, et nombre d items par page], renvoie la liste des pokemons de la page + des meta infos+stat ok 200
- DELETE  http://localhost:8080/pokemons/id : efface un pokemon sur son id, no content si trouve (pas de body) , 404 sinon
- POST  http://localhost:8080/pokemons + body au format json, 201 si created,  409 - conflit si existe deja (en se basant sur l id)
- PUT http://localhost:8080/pokemons/21 + body au format json  200 si modifie, 404 si non trouve

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
