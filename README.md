## Multi-Agent System Platform - E-commerce negotiation - Auction products
***
## Explication du code 
 #### Le code source se compose de quatre packages chacun réalisant un traitement dans notre système multi-agents.

*	Package `VenteLivre` : ìl contient trois classe pour le premier scénario, la classe `AgentAcheteurLivre` 
    représente l’agent acheteur, et la classe `AgentVendeurLivre` représente l’agent vendeur, et en fin 
    une interface graphique implémentée dans la classe `VendeurLivreGui`.
    
*	Package `agents` : il contient deux classes `AgentAcheteur1` et `AgentVendeur1` sur lesquelles sont implémentées
    les fonctions et les comportements des agents acheteurs et vendeurs pour le deuxième scénario.   
    
*	Package `produit` : il dispose d’une seule classe appelée `Produit`, sur laquelle est implémenté le constructeur
    Produit avec ses attributs (prix, désignation, livraison et état). Elle est utilisée dans les deux scénarios.
    
*	Package `scénario1Containers` : il contient les classes de démarrage du premier scénario. La classe `MainContainer` 
    est le container principal pour le démarrage de RMA, et la classe `MainContainer` pour le démarrage des agents.
    
*	Package `scénario2Containers` : il contient les classes de démarrage du deuxième scénario. La classe `MainContainer` 
    est le container principal pour le démarrage de RMA, et la classe `MainContainer` pour le démarrage des agents.
  
  
  
## Exécution   
  Pour éxecuter le premier scénario, il faut lancer les classes ci-dessous du package `scénario1Containers`  :

  1. lancer le container `MainContainer` pour démarrer le container principal et RMA
  2. lancer le container `Main` pour démarrer les agents 

  #### Pour éxecuter le deuxième scénario il faut exécuter les classe ci-dessous du package scénario2Containers :
  1. lancer le container `MainContainer` pour démarrer le container principal et RMA
  2. lancer le container `AgentContainer` pour démarrer les agents
