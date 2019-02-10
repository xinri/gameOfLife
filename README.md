Le jeu de la vie est un automate cellulaire créé par J.H.Conway

les règles sont simples : des cellules sont soit mortes '-' soit vivantes '*'

A chaque pas de temps, les cellules meurent ou naissent en suivant les règles suivantes :

1. une cellule entourée de plus de 3 cellules vivantes meurt a cause de la surpopulation,
2. une cellule entourée de moins de 2 cellules vivantes meurt à cause de la solitude,
3. une cellule morte entourée d'exactement 3 cellules vivantes naît.


Le but du jeu est de donner le résultat après 73 pas de temps de la grille ci-dessous