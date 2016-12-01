Fonctionnalités :
[] Liste des matchs disponibles + choisir un match
[] Mise à jour aux deux minutes
[] Mise à jour sur demande

Infos affichées :
[] Description statique du match : nom des équipes
[] Description dynamique du match : chronomètre et pointage
[] Description dynamique du match : compteurs et pénalités

Evènements :
[] Evènements pour les buts
[] Evènements pour les pénalités
[] Notifications des évènements sont faites dans une fenêtre "pop-up"

Paris

Fonctionnalités :
[] Choisir un match pour faire un pari
[] Faire un pari sur un match
[] Informer les parieurs de leur gain immédiatement s'ils sont connectés à la fin du match
[] Informer les parieurs de leur gain lorsqu'ils se reconnectent, s'ils n'ont pas déjà reçu cette notification (utiliser les cookies par exemple pour le savoir ou garder l'état sur le serveur)

Implémentation
Ajax et dynamicité de la page ou des pages
[] Sérialisation et envoi des requêtes
[] Utilisation du DOM pour modifier la page(ne pas recharger la page en entier, mais en partie)
[] Utilisation de HttpXMLRequest ou l'équivalent selon le framework utilisé pour les mises-à-jour 
[] Indication dans la page que la mise à jour est en cours(utilisation des états de la requêtes Asynchrone ?)
[] Réception, désérialisation et utilisation des réponses

SERVEUR
[] Traitement des requêtes http et des réponses : GET et/ou POST
[] Utilisation de thread pour traiter la requête selon le modèle utilisé (thread-per-request)
