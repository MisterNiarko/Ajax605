function loadMatchList(){
	$.get('GameList', function(listeMatch){
		$.each(listeMatch, function(index,value){
			$.each(value, function(index2,value2){
				ajouterLigne(value2.equipeA, value2.equipeB, value2.temps);
			});
		});
	});
}

function ajouterLigne(equipeA, equipeB, temps){
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
    colonne1.innerHTML = equipeA;
    var colonne2 = ligne.insertCell(1);
    colonne2.innerHTML = temps;
    var colonne3 = ligne.insertCell(2);
    colonne3.innerHTML = equipeB;
}
