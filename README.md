# Caterpillar Display

Projet réalisé lors de ma Zème année de D.U.T Informatique.

Le but de ce projet était de travailler sur l'envoie de données sur le réseau en utilisant le protocole TCP.


# Explications du projet
En supposant que le message soit « Bonjour » et	que	nous ayons 8 machines numérotées de	0 à	7, au début de l’affichage la machine	0 affichera	la lettre r, la	machine 1 affichera la lettre u, …,	la machine 6 affichera la lettre B et la machine 7 un fond vide. Une période de temps plus tard l’affichage sera décalé : la machine 0 affichera la lettre	u, la machine 1 affichera la lettre o, …, la machine 5 affichera la lettre B, la machine 6 un fond vide et la machine 7 affichera la lettre r. Le défilement du mot sera sans fin.

## Features

- Utilisation de Threads pour autoriser plusieurs connexions simultanées
- Choix du nombre de machines dans les paramètres de lancement
- Fenêtre JavaFX afin d'afficher la lettre reçue pour chaque clients

## Futurs ajouts potentiels
- Ajout d'une IHM (JavaFX) pour lancer le serveur
- Ajout d'une IHM Client pour lancer la connexion vers le serveur

