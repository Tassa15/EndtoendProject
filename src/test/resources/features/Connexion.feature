
Feature: Connexion à l'application

  Scenario: L'utilisateur accède au site
    Given j'accède à l'url de la page d'accueil
    When je clique sur le bouton "S'authentifier"
    Then je suis redirigé vers la page de connexion


  Scenario Outline: l'utilisateur se connecte avec des identifiants valides
    Given que j'accède à la page de connexion
    When je saisis le nom utilisateur "<username>" et le mot de passe "<password>"
    And je clique sur le bouton login
    Then je devrais voir la page d'accueil connectée

    Examples: 
      | username | password      |
      | Dadit    | Tassadit.91   |
      | Tassadit | Thassadhith91 |
  

  Scenario Outline: L'utilisateur essaie de se connecter avec des identifiants invalides
    Given que j'accède à la page de connexion
    When je saisis le nom d'utilisateur "<username>" et le mot de passe "<password>"
    And je clique sur le bouton login
    Then je devrais voir un message d'erreur "<messageErreur>"

    Examples:
      | username  | password    | messageErreur                |
      | Dadit     | mauvaisMDP  | Nom d'utilisateur ou mot de passe incorrect |
      | Inconnu   | Tassadit.91 | Nom d'utilisateur ou mot de passe incorrect |
      
      
      
      
      
      
      