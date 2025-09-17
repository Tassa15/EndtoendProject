Feature: validation de paiement de la commande

  Scenario: l'utilisateur valide la commande après avoir rempli le formulaire
    Given que l'utilisateur est sur la page de paiement
    And il remplit les champs obligatoires
      | first name     |
      | last name      |
      | street address |
      | postcode       |
      | town           |
      | email address  |
    When il coche la case des CG
    And il clique sur le bouton 'payer la commande'
    Then la commande est validée

  Scenario Outline: L'utilisateur valide la commande avec des moyens de paiement différents
    Given l'utilisateur est sur la page de paiement
    And il a rempli tous les champs oligatoires
    When il choisit le "<moyen de paiement>"
    And il clique sur le bouton 'payer la commande'
    Then la commande est validée

    Examples: 
      | moyen de paiement       | statut    |
      | chèque                  | confirmée |
      | Paiement à la livraison | confirmée |

  Scenario Outline: Erreurs possibles lors de la validation du paiement
    Given l'utilisiateur est sur la page de paiement
    And il a rempli tous les champs obligatoires sauf "<champs manquant>"
    When il clique sur le bouton payer la commande
    Then un message d'erreur s'affiche
    And la commande n'est pas validée

    Examples: 
      | champ_manquant | action                      | message                                        |
      | aucun          | ne coche pas la case des CG | "Vous devez accepter les conditions générales" |
      | email address  | coche la case des CG        | "L'adresse email est obligatoire"              |
      | postcode       | coche la case des CG        | "Le code postal est obligatoire"               |
      | first name     | coche la case des CG        | "Le prénom est obligatoire"                    |
