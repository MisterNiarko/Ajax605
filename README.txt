Fonctionnalit�s :
[x] Liste des matchs disponibles + choisir un match
[x] Mise � jour aux deux minutes
[x] Mise � jour sur demande

Infos affich�es :
[x] Description statique du match : nom des �quipes
[x] Description dynamique du match : chronom�tre et pointage
[x] Description dynamique du match : compteurs et p�nalit�s

Ev�nements :
[x] Ev�nements pour les buts
[x] Ev�nements pour les p�nalit�s
[x] Notifications des �v�nements sont faites dans une fen�tre "pop-up"

Paris

Fonctionnalit�s :
[x] Choisir un match pour faire un pari
[x] Faire un pari sur un match
[x] Informer les parieurs de leur gain imm�diatement s'ils sont connect�s � la fin du match
[x] Informer les parieurs de leur gain lorsqu'ils se reconnectent, s'ils n'ont pas d�j� re�u cette notification (utiliser les cookies par exemple pour le savoir ou garder l'�tat sur le serveur)

Impl�mentation
Ajax et dynamicit� de la page ou des pages
[x] S�rialisation et envoi des requ�tes
[x] Utilisation du DOM pour modifier la page(ne pas recharger la page en entier, mais en partie)
[x] Utilisation de HttpXMLRequest ou l'�quivalent selon le framework utilis� pour les mises-�-jour 
[x] Indication dans la page que la mise � jour est en cours(utilisation des �tats de la requ�tes Ajax)
[x] R�ception, d�s�rialisation et utilisation des r�ponses

SERVEUR
[x] Traitement des requ�tes http et des r�ponses : GET et/ou POST
[x] Utilisation de thread pour traiter la requ�te selon le mod�le utilis� (thread-per-request)
