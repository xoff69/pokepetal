# Projet Pokemons pour Petal

Hypotheses et remarques:
- j'ai considere qu'il fallait lire les donnees systematiquement sur l'url du fichier csv et non pas mettre dans l 'application le fichier csv
- l'url est stockee dans le fichier application.yml
- projet construit avec springboot 3.0 et java 19, usage de lombok
- Le repertoire postman contient mes tests postman realises sur l url localhost
- base de donnees h2 en memoire, les id de la table pokemon sont pris soit sur les url, soit dans le fichier csv
ie pas de generation de l id
- pour post et put , si l id est present dans le body aussi, il n est pas utilise, c est celui de  l url qui prevaut
- pas de champ obligatoire a part les id sur les url, un pokemon peut donc etre cree avec un nom vide

VERBES: (Pour les codes retour, je m appuie sur https://www.restapitutorial.com/lessons/httpmethods.html)

- GET  http://localhost:8080/pokemons/{id} : cherche un pokemon par id, 200 si trouve+le contenu, 404 sinon
- GET  http://localhost:8080/pokemons?page={numeropage}&size={nb items par page}:  liste des pokemons par page [numero de page, et nombre d items par page], renvoie la liste des pokemons de la page + des meta infos+stat ok 200
- DELETE  http://localhost:8080/pokemons/{id} : efface un pokemon sur son id, no content si trouve (pas de body) , 404 sinon
- POST  http://localhost:8080/pokemons/{id} + body au format json, 201 si created,  409 - conflit si existe deja (en se basant sur l id present sur l url)
  {
  "name": "nouveau pokemon fffffffffff",
  "type1": "Normal",
  "type2": "Flying",
  "total": 262,
  "hp": 40,
  "attack": 60,
  "defense": 30,
  "soAtk": 31,
  "spDef": 31,
  "speed": 70,
  "generation": 1,
  "legendary": false
  }
- PUT http://localhost:8080/pokemons/{id} + body au format json  200 si modifie, 404 si non trouve
  {
  "name": "nouveau pokemon fffffffffff",
  "type1": "Normal",
  "type2": "Flying",
  "total": 262,
  "hp": 40,
  "attack": 60,
  "defense": 30,
  "soAtk": 31,
  "spDef": 31,
  "speed": 70,
  "generation": 1,
  "legendary": false
  }
Tests unitaires en place:
- Application: controller non null
- Test controller:
  - GET par identifiant trouve/non trouve
  - GET de tous les pokemons pagines
  - POST cree un pokemon et Put update
  - DELETE efface un pokemon
- test couche de service: les methodes principales
- CSV reader

RAPPEL DES INSTRUCTIONS:

L'objectif de l’exercice technique est de créer un petit projet qui exposera un API Restful à un éventuel client. Nous validerons le fonctionnement du projet via l’application Postman.

Il n'y a pas de cadre ou de technologie obligatoire, bien que nous recommandons l’utilisation du framework Ruby on Rails. Nous vous encourageons également à écrire des tests unitaires, bien que ce ne soit pas une obligation.

Vous aurez accès à un fichier en format CSV (lien ci-bas) qui contient une liste de Pokemon. Ce fichier fera office d’élément de base pour remplir la base de données (seeds), et nous vous demandons donc d’exposer un API permettant d’effectuer les actions CRUD (Create, Read, Update, Delete). Nous aimerions également obtenir une liste paginée de tous les Pokemon présents dans le fichier.

Une fois le projet terminé vous devez nous partager le lien du projet dans votre GitHub ou autre solution similaire.

Voici le lien pour le fichier CSV : https://gist.github.com/armgilles/194bcff35001e7eb53a2a8b441e8b2c6
