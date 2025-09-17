Feature: L'utilisateur paie sa commande

  Scenario: payer la commande
    Given l'utilisateur a selectionné les produits
    When il clique sur le bouton 'proceed to checkout'
    Then une nouvelle page s'affcihe avec des champs à remplir
      | first name     |
      | last name      |
      | street address |
      | postcode       |
      | town           |
      | emmail address |
      