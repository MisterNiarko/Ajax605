var ds_ListeDesMatchs;
var flag_Split = false;
var j = 0;

function onStart(){
	loadMatchList();
	startEventNotifier();
	setInterval("loadMatchList()",2000);
	}

function startEventNotifier(){
	var eventSource = new EventSource("http://localhost:8080/TP3/Event");
	console.log("startEventNotifier lancé...");
	eventSource.onopen = function(){
		console.log("SSE connecté !\n");
	};
	eventSource.onmessage = function(evt){
		alert(evt.data);
	};
	eventSource.onError = function(){
		console.log("SSE Error");
	};
}

function loadMatchList(){
	clearTable("tableMatch");
	$.get('GameList', function(listeMatch){
		ds_ListeDesMatchs = listeMatch;
		$.each(listeMatch, function(index,value){
			$.each(value, function(index2,value2){
				ajouterLigne(value2.id, value2.equipeA, value2.equipeB, value2.temps);
			});
		});
	});
}

function loadMatchDetail(id){
	clearTable("tableMatchDetail");
	var tableau = document.getElementById("tableMatchDetail");
	
	$.get('GameDetail', {id: id}, function(matchDetail){
		
	    var ligne = tableau.insertRow(-1);
	    var colonne1 = ligne.insertCell(0);
	    colonne1.innerHTML = "Temps de match";
	    var colonne2 = ligne.insertCell(1);
	    colonne2.innerHTML = matchDetail.temps;
	    
	    ligne = tableau.insertRow(-1);
	    colonne1 = ligne.insertCell(0);
	    colonne1.innerHTML = "Periode";
	    colonne2 = ligne.insertCell(1);
	    colonne2.innerHTML = matchDetail.periode;
	    
	    ligne = tableau.insertRow(-1);
	    colonne1 = ligne.insertCell(0);
	    colonne1.innerHTML = "Score local";
	    colonne2 = ligne.insertCell(1);
	    colonne2.innerHTML = matchDetail.scoreLocal;
	    
	    ligne = tableau.insertRow(-1);
	    colonne1 = ligne.insertCell(0);
	    colonne1.innerHTML = "Score visiteur";
	    colonne2 = ligne.insertCell(1);
	    colonne2.innerHTML = matchDetail.scoreVisiteur;
	    
	    $.get('GameEvent', {id: id}, function(matchEvent){
			$.each(matchEvent, function(index,value){
				var ligne = tableau.insertRow(-1);
			    var colonne1 = ligne.insertCell(0);
			    colonne1.innerHTML = value.message;
			    var colonne2 = ligne.insertCell(1);
			    colonne2.innerHTML = value.temps;
			});
		});
	    
	});
	
}
	
function clearTable(table){
	var table = document.getElementById(table);
	while(table.rows.length >0){
		table.deleteRow(0);
	}
}

function ajouterLigne(id, equipeA, equipeB, temps){
	var tableau = document.getElementById("tableMatch");
    var ligne = tableau.insertRow(-1);
	ligne.onclick = (function(){
		if(flag_Split == false){
			$('.container').toggleClass('expanded');
			flag_Split = true;
		}
		loadMatchDetail(id);
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
    colonne1.innerHTML = id;
    var colonne2 = ligne.insertCell(1);
    colonne2.innerHTML = equipeA;
    var colonne3 = ligne.insertCell(2);
    colonne3.innerHTML = temps;
    var colonne4 = ligne.insertCell(3);
    colonne4.innerHTML = equipeB;
}

