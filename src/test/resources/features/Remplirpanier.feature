Feature: L'utilisateur selectionne un article et l'ajoute au panier

  Scenario: Ajouter un produit au panier
    Given que l'utilisateur est sur la liste des catégorie "chaussures"
    When il clique sur le bouton 'add to cart' d'un produit
    Then l'article s'ajoute au panier
    And le panier se met à jour avec le produit ajouté

 
  Scenario Outline: Ajouter plusieurs produits au panier et vérifier le anier
    Given je suis sur la liste des catégories "<catégorie>"
    When il clique sur le bouton 'add to cart'
    And il accède au panier 
    Then le panier contien le produit "<produit>"
    And il peut mettre à jour la quantité "<quantité>"
    And le prix se met à jour correctement

    Examples: 
      | catégorie   | produit  | quantité |
      | Chaussures  | sneakers |        2 |
      | Accessoires | gants    |        1 |
