# SAE 3.02 - Développer des Applications communicantes
## Devaux Tristan - BUT RT2-C2 - IUT de Roanne

## Informations importantes

Ce dépôt est (jusqu'à la date de rendu du travail) en privé et n'est accessible qu'aux correcteurs de mon travail.
A termes, ce dépôt sera rendu public et tout le monde pourra télécharger cette application, modifier son code et jouir de ses fonctionnalités.

## Introduction

Bienvenue sur le dépôt de mon projet individuel dans le cadre de la SAE3.02 de mon BUT Réseaux et Télécoms en Cybersécurité. Ce projet a pour objectif de créer une application Android capable de gérer des connexions entre clients et serveurs en utilisant le protocole TCP/IP. Le développement sera réalisé en Java et XML, avec Android Studio comme environnement de développement.

## Consignes du Projet

### Langages Utilisés
- Java (Pas de Kotlin)
- XML

### Fonctionnalités Requises
1. Développer une application Android capable de fonctionner en mode client et en mode serveur.
2. Prendre en charge les protocoles TCP et UDP pour les communications.
3. Envoyer et recevoir des structures de données, telles que des adresses IP, des objets JSON, et d'autres objets complexes.
4. Gérer le multithreading pour permettre au serveur de gérer plusieurs clients en parallèle.
5. Implémenter la possibilité pour le client d'indiquer le type de service souhaité au serveur.
6. Mettre en place un système de gestion des droits d'accès entre les utilisateurs.

## Travail réalisé

### Architecture du Projet
Le projet est structuré de manière à respecter les normes Android Studio. Les packages et les classes sont organisés de manière logique pour assurer la lisibilité et la maintenance du code.

### Protocoles de Communication
Les protocoles TCP et UDP sont implémentés de manière robuste pour assurer une communication fiable entre les clients et le serveur.

### Gestion des Données
La gestion des données inclut la transmission d'adresses IP, de structures JSON, et d'objets complexes. Les mécanismes de sérialisation/désérialisation sont soigneusement mis en place.

### Multithreading
Le serveur est conçu pour gérer simultanément plusieurs clients grâce à une architecture multithread.

### Services Personnalisés
Le système prend en charge la personnalisation des services en fonction des indications fournies par le client.

### Gestion des Droits d'Accès
Un système de gestion des droits d'accès est implémenté pour garantir la sécurité entre les différents utilisateurs de l'application.

## Rapport

Un rapport détaillé expliquant les différentes étapes de création, le code source, et les résultats finaux sera fourni.

## Tests

Des tests ont été effectués avec des points d'accès Wi-Fi et de vrais smartphones pour valider le bon fonctionnement de l'application.
Smartphone utilisés dans le cadre de mes tests personnels :
- Samsung Galaxy S20
- Samsung Galaxy S21Fe
- Samsung Galaxy A14

## Remarque

Le fichier .apk n'est pas fourni seul, mais l'ensemble du projet est disponible sur ce dépôt.

## Comment Installer l'Application sur Android depuis Android Studio

Pour tester l'application sur un appareil Android, suivez ces étapes simples :

### Prérequis

- Assurez-vous d'avoir Android Studio installé sur votre machine.
- Connectez votre appareil Android à votre ordinateur via un câble USB.

### Étapes d'Installation

1. Clonez ce dépôt sur votre machine en utilisant la commande suivante dans votre terminal :

`git clone https://github.com/triztandevzzX/ApplicationTCP`

2. Ouvrez Android Studio.

3. Dans Android Studio, ouvrez le projet en sélectionnant le répertoire cloné.

4. Attendez que le projet se synchronise avec Gradle.

5. Assurez-vous que votre appareil Android est détecté par Android Studio. Vous devriez le voir répertorié dans la barre d'outils.

6. Sélectionnez le périphérique cible dans Android Studio.

7. Cliquez sur le bouton `Run` (icône de lecture) dans la barre d'outils ou utilisez le raccourci clavier (généralement Maj + F10) pour compiler et installer l'application sur votre appareil Android.

8. Suivez les instructions à l'écran pour autoriser l'installation de l'application sur votre appareil.

9. Une fois l'installation terminée, l'application devrait être disponible sur votre écran d'accueil.

10. Lancez l'application et explorez ses fonctionnalités!

N'oubliez pas que si vous rencontrez des problèmes lors de l'installation, assurez-vous que votre appareil est en mode développeur et que le débogage USB est activé.

