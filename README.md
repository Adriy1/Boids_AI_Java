# Projet-Java-2a

Pour compiler lancer: $ make

L'objectif de ce projet était d'implémenter des agents autonomes (boids).
La version la plus intéressante du projet se situe dans le dossier evolve. Un algorithme génétique y est implémenté ainsi qu'un neurone de machine learning pour améliorer les performances des agents autonome.

 ---

## Balle
Pour executer le jeu avec les balles lancer: $ make exeSimBall

Parametres de la simulation dans BallsSimulator.java

A chaque restart la position des balles et leurs vitesses sont aléatoires. /!\ pas de collision entre balles

## LifeGame

Simplement le jeu de la vie avec une interface graphique java

lancer: $ make exeLifeGame

---

## Immigration

Pour lancer l'immigration: $ make exeSimUnivers
Initialiser avec Debut puis lancer Lecture
/!\ Ne pas mettre trop de case sinon la simulation est très lente

Parametres de la simulation dans UniversSimulator.java

Univers est une classe qui peut être adaptée à plusieurs modelisation. Il s'agit d'une grille qu'on initialise, et modifie comme voulu dans la simulation.

---

## schelling

Pour lancer l'immigration: $ make exe2SimUnivers
Initialiser avec Debut puis lancer Lecture

Parametres de la simulation dans UniversSimulator.java (nb de couleur + taille de la grille) et Univers.java (limite nb voisin accepté)

Le code peut être améliorer en terme de propeté (virer trucs inutile etc...)

plus la limite est haute plus la segregation va vite
plus le nb de couleur est grand moins la segregation est rapide
la taille de la grille ou le nombre de case libre ne semble pas jouer un role très important et terme de vitesse.

/!\ Si la limite est trop basse ou le nb de couleur trop grand, la segregation ne peut jamais arriver ! En effet, tout le monde veut toujours démenager !

Pour l'instant seulement des nuances de gris mais pourquoi pas faire en couleur.

--- 

## boid

Il s'agit d'une simulation d'agents autonomes fonctionnant comme un banc de poisson. 

lancer simple boid : make exeBoids

---

## Evolve 


Evolve ajoute des requins chasseurs de poissons. Les requins apprennent de leurs erreurs de trajectoire et profite de l'algorithme pour améliorer leur performance au cours des générations.

pour lancer evolve : make exeEvolve
