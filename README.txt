Fonctionnalit�s :
[] Liste des matchs disponibles + choisir un match
[] Mise � jour aux deux minutes
[] Mise � jour sur demande

Infos affich�es :
[] Description statique du match : nom des �quipes
[] Description dynamique du match : chronom�tre et pointage
[] Description dynamique du match : compteurs et p�nalit�s

Ev�nements :
[] Ev�nements pour les buts
[] Ev�nements pour les p�nalit�s
[] Notifications des �v�nements sont faites dans une fen�tre "pop-up"

Paris

Fonctionnalit�s :
[] Choisir un match pour faire un pari
[] Faire un pari sur un match
[] Informer les parieurs de leur gain imm�diatement s'ils sont connect�s � la fin du match
[] Informer les parieurs de leur gain lorsqu'ils se reconnectent, s'ils n'ont pas d�j� re�u cette notification (utiliser les cookies par exemple pour le savoir ou garder l'�tat sur le serveur)

Impl�mentation
Ajax et dynamicit� de la page ou des pages
[] S�rialisation et envoi des requ�tes
[] Utilisation du DOM pour modifier la page(ne pas recharger la page en entier, mais en partie)
[] Utilisation de HttpXMLRequest ou l'�quivalent selon le framework utilis� pour les mises-�-jour 
[] Indication dans la page que la mise � jour est en cours(utilisation des �tats de la requ�tes Asynchrone ?)
[] R�ception, d�s�rialisation et utilisation des r�ponses

SERVEUR
[] Traitement des requ�tes http et des r�ponses : GET et/ou POST
[] Utilisation de thread pour traiter la requ�te selon le mod�le utilis� (thread-per-request)
