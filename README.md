## Multi-Agent System Platform - E-commerce negotiation - Auction products
***
## Explication du code 
 #### Le code source se compose de quatre packages chacun réalise un traitement dans notre système multi agents

*	Package `VenteLivre` : ìl contient trois classe pour le premier scénario, la classe `AgentAcheteurLivre` 
    qui représente l’agent acheteur, et la classe `AgentVendeurLivre` qui représente l’agent vendeur, et en fin 
    une interface graphique implémenté dans la classe `VendeurLivreGui`.
    
*	Package `agents` : il contient deux classes `AgentAcheteur1` et `AgentVendeur1` sur lequel sont implémentés 
    les fonctions, les comportements des agents acheteurs et vendeurs pour le deuxième scénario.   
    
*	Package `produit` : il dispose d’une seule classe appelée `Produit`, sur laquelle est implémenté le constructeur
    Produit avec ces attributs (prix, désignation, livraison et état). Elle est utilisée dans les deux scénarios.
    
*	Package `scénario1Containers` : il contient les classes de démarrage du premier scénario, la classe `MainContainer` 
    est le container principal pour le démarrage de RMA, et la classe `MainContainer` pour le démarrage des agents.
    
*	Package `scénario2Containers` : il contient les classes de démarrage du deuxième scénario, la classe `MainContainer` 
    est le container principal pour le démarrage de RMA, et la classe `MainContainer` pour le démarrage des agents.
  
  
  
## Exécution   
  Pour éxecuter le premier scénario il faut lancer les classes ci-dessous du package `scénario1Containers`  :

  1. lancer le container `MainContainer` pour le démarrage du container principal et RMA
  2. lancer le container `Main` pour le démararge des agents 

  #### Pour éxecuter le deuxième scénario il faut exécuter les classe ci-dessous du package scénario2Containers :
  1. lancer le container `MainContainer` pour le démarrage du container principal et RMA
  2. lancer le container `AgentContainer` pour le démararge des agents
