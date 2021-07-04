############################################### EXPLICAION DU CODE ########################################################################### 

  

    Le code source se compose de quatre packages chacun r�alise un traitement dans notre syst�me multi agents

�	Package VenteLivre : elle contient trois classe pour le premier sc�nario, la classe AgentAcheteurLivre 
    qui repr�sente l�agent acheteur, et la classe AgentVendeurLivre qui repr�sente l�agent vendeur, et en fin 
    une interface graphique impl�ment� dans la classe VendeurLivreGui.
    
�	Package agents : elle contient deux classes AgentAcheteur1 et AgentVendeur1 sur lequel sont impl�ment�s 
    les fonctions, les comportements des agents acheteurs et vendeurs pour le deuxi�me sc�nario.   
    
�	Package produit : elle dispose d�une seule classe appel�e Produit, sur laquelle est impl�ment� le constructeur
    Produit avec ces attributs (prix, d�signation, livraison et �tat). Elle est utilis�e dans les deux sc�narios.
    
�	Package sc�nario1Containers : elle contient les classes de d�marrage du premier sc�nario, la classe MainContainer 
    est le container principal pour le d�marrage de RMA, et la classe MainContainer pour le d�marrage des agents.
    
�	Package sc�nario2Containers : elle contient les classes de d�marrage du deuxi�me sc�nario, la classe MainContainer 
    est le container principal pour le d�marrage de RMA, et la classe MainContainer pour le d�marrage des agents.
  
  
  
  
  
    ******************************************************************************************************
    *                                           EXECUTION                                                *
    *                                                                                                    *      
    ******************************************************************************************************     
  Pour �xecuter le premier sc�nario il faut lancer les classes ci-dessous du package sc�nario1Containers  :

  1-lancer le container MainContainer pour le d�marrage du container principal et RMA
  2-lancer le container Main pour le d�mararge des agents 

  Pour �xecuter le deuxi�me sc�nario il faut ex�cuter les classe ci-dessous du package sc�nario2Containers :
  1-lancer le container MainContainer pour le d�marrage du container principal et RMA
  2-lancer le container AgentContainer pour le d�mararge des agents



