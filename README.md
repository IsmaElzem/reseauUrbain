# reseauUrbain

Simulateur d'un réseau urbain classique, en créant un graphe à partir de noeud, qui contiennent un arret et la liste des noeuds fils. Ce projet a été fait pour le module "Porgrammation orienté objet". L'interface graphique a été donné par le professeur de ce module. Pour toute question complémentaire, envoyez moi un message :) 

Principale fonction :

    automatisationGraphe(): Crée le graphe à partir des Arrets générés au début de la classe reseauUrbain. Elle a pour but de créer les noeuds afin de faire le graphe. 
    
    dijkstra() : Trouve le plus court chemin pour un passager. 
    
    deplacementPixel(): Déplace les transports selon les arrêts. 
    
    
    Pour compiler : 
        javac -classpath ./src/ ./src/test/Main.java -d ./bin/
        
    Pour executer : 
        java -classpath ./bin test.Main


