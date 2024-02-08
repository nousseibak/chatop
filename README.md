# Description :

ChâTop est une société de location immobilière dans une zone touristique.
L'application chatop a été créée pour cette société afin de permettre la mise en relation entre les futurs locataires et des propriétaires pour de la location saisonnière. 
Les utilisateurs (locataires ou propriétaires) peuvent : s'enregistrer, se connecter, voir les annonces, créer des annonces et ajouter des messages aux annonces.

# Technologies Utilisées :

- Java 17
- Spring Boot 3.2.2
- Spring Security avec authentification via JWT
- Base de donnée MySQL
- Documentation avec swagger
  
# Dépendandances ajoutées au projet :

- Lombok
- Mapstruct
- SpringDoc pour le Swagger
- Mysql
- Spring Security
- Jsonwebtoken

# Outils utilisés :
- Intellij
- Postman pour tester l'application
- MySql


# Pour lancer le projet le projet:

Avec Git, cloner le projet : https://github.com/nousseibak/chatop.git

Ouvrir le projet avec Intellij

Ajout de la base de données :

- Démarrer MySql
- Créer une base de données nommée chatop
- Importez le script qui se trouve à l'adresse : ressources/sql/script.sql

Dans le projet, modifier le fichier application.properties dans src/main/resources/application.properties avec les données de votre compte mySql et de la base de donnée créée

Clean install le projet

Lancer le projet 


# Pour accéder à la documentation swagger et pouvoir tester l'application :
Dans votre navigateur, aller à l'adresse :

localhost:3001/api/swagger-ui/index.html












