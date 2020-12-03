# kanban

Projet TSE - Springboot



## A propos
Kanban signifie « étiquette » en japonais. La méthode Kanban fonctionne sur une système de cartes ou d'étiquettes, appelées Kanban, qui correspondent à une commande précise du client. C'est cette commande qui déclenche la chaîne de production. Ces cartes indiquent continuellement les tâches à réaliser, quand les réaliser, et les tâches déjà réalisées (le stock disponible par exemple).

## Infos utiles
- J'ai annoté au maximum le projet avec une ***Javadoc***. Les annontations sont particulièrement présentes dans les packages `service`, `domain` et dans les tests unitaires.

- Implémentation de divers services pour les `Tasks`
	- Création, suppression d'une `Task`
	- Récupération des `Task` , `TaskType` et `TaskStatus`
	- Changement de status d'une `Task`
	- Vérification des logs 
- Implémentation de tests unitaires pour tester ces services
