function loadMatchList(){
	$.get('GameList', function(listeMatch){
		$.each(listeMatch, function(index,value){
			console.log(value.domicile.nom);
		});
	});
}

function ajouterLigne(match){
    var tableau = document.getElementById("tableResult");
    var ligne = tableau.insertRow(-1);
	ligne.onclick = (function(){
		alert("test");
	});
	ligne.onmouseover = (function(){
		ligne.style.backgroundColor = "#9999ff";
	});
	ligne.onmouseout = (function(){
		ligne.style.backgroundColor = "#ffffff";
	});
    var colonne1 = ligne.insertCell(0);
    colonne1.innerHTML = "test1";
    var colonne2 = ligne.insertCell(1);
    colonne2.innerHTML = "test2";
}
