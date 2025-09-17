Feature: Navigation dans la boutique

  Scenario: l'utilisateur accès à la catégorie chaussure depuis le menu Boutique
    Given je suis sur la page d'accueil
    When je survole la souris sur le menu "Boutique"
    Then un sous menu s'affiche avec trois options:
      | t-shirt     |
      | chaussures  |
      | accessoires |

  Scenario Outline: Selectionner une option à partir de sous menu
    Given je suis sur le sous menu
    When je clique sur l'option "<option>"
    Then une nouvelle page s'ouvre avec la liste de "<catégorie>" contenant :
      | image                |
      | nom                  |
      | prix                 |
      | bouton 'add to cart' |

    Examples: 
      | menu        |
      | t-shirt     |
      | Chaussures  |
      | accessoires |