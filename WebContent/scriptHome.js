var ds_ListeDesMatchs;
var flag_Split = false;

function loadMatchList(){
	clearTable();
	$.get('GameList', function(listeMatch){
		ds_ListeDesMatchs = listeMatch;
		$.each(listeMatch, function(index,value){
			$.each(value, function(index2,value2){
				ajouterLigne(value2.equipeA, value2.equipeB, value2.temps);
			});
		});
	});
}

function clearTable(){
	var table = document.getElementById("tableMatch");
	while(table.rows.length >0){
		table.deleteRow(0);
	}
}

function ajouterLigne(equipeA, equipeB, temps){
	var tableau = document.getElementById("tableMatch");
    var ligne = tableau.insertRow(-1);
	ligne.onclick = (function(){
		if(flag_Split == false){
			$('.container').toggleClass('expanded');
			flag_Split = true;
		}
	});
	ligne.onmouseover = (function(){
		if(flag_Split == false){
			ligne.style.backgroundColor = "#9999ff";
		}
		else{
			ligne.style.backgroundColor = "#9999ff";
		}
	});
	ligne.onmouseout = (function(){
		if(flag_Split == false){
			ligne.style.backgroundColor = "#899ED3";
		}
		else{
			ligne.style.backgroundColor = "#C3CBDD";
		}
	});
    var colonne1 = ligne.insertCell(0);
    colonne1.innerHTML = equipeA;
    var colonne2 = ligne.insertCell(1);
    colonne2.innerHTML = temps;
    var colonne3 = ligne.insertCell(2);
    colonne3.innerHTML = equipeB;
}

