var ds_ListeDesMatchs;
var idMatchDetail = 0;
var flag_Split = false;
var j = 0;

function onStart(){
	loadMatchList();
	startEventNotifier();
	setInterval("loadMatch()",2000);
	}

function startEventNotifier(){
	var eventSource = new EventSource("http://localhost:8080/TP3/Event");
	console.log("startEventNotifier lancé...");
	eventSource.onopen = function(){
		console.log("SSE connecté !\n");
	};
	eventSource.onmessage = function(evt){
		//alert(evt.data);
		console.log(evt.data);
		var obj = JSON.parse(evt.data);
		console.log(obj.message.message);
		var match;
		$.each(ds_ListeDesMatchs, function(index,value){
			$.each(value, function(index2,value2){
				if(value2.id == obj.id){ match = value2;}
			});
		});
		console.log(match);
		if(obj.type == 0){
			alert("GOAL !!\nMatch : " + match.equipeA + " vs " + match.equipeB + "\n"+obj.message.message);
		}
		else if(obj.type == 1){
			alert("Penalité !\nMatch : " + match.equipeA+ " vs "+match.equipeB+"\n"+obj.message.message);
		}
	};
	eventSource.onError = function(){
		console.log("SSE Error");
	};
}

function loadMatch(){
	loadMatchList();
	loadMatchDetail(idMatchDetail);
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
		colonne1.style.textAlign = "right";
		colonne1.innerHTML = matchDetail.local.nom;
		var colonne2 = ligne.insertCell(1);
		colonne2.style.textAlign="center";
		colonne2.innerHTML = matchDetail.scoreLocal + "-" + matchDetail.scoreVisiteur;
		var colonne3 = ligne.insertCell(2);
		colonne3.innerHTML = matchDetail.visiteur.nom;
			    
		ligne = tableau.insertRow(-1);
	    colonne1 = ligne.insertCell(0);
	    colonne1.innerHTML = "";
	    colonne2 = ligne.insertCell(1);
	    colonne2.style.textAlign="center";
	    colonne2.innerHTML = secondsToHms(matchDetail.temps);
	    
	    ligne = tableau.insertRow(-1);
	    colonne1 = ligne.insertCell(0);
	    colonne1.innerHTML = "";
	    colonne2 = ligne.insertCell(1);
	    colonne2.innerHTML = "Periode " + matchDetail.periode;
	});
	
}

function loadOptions(id){
	var sel = document.getElementById("choiceBet");
	removeOptions(sel);
	
	$.get('GameDetail', {id: id}, function(matchDetail){
		
		var option = document.createElement("option");
		option.text = matchDetail.local.nom;
		sel.add(option);
		var option2 = document.createElement("option");
		option2.text = matchDetail.visiteur.nom;
		sel.add(option2);
	});
}

function removeOptions(obj) {
	if (obj == null) return;
	if (obj.options == null) return;
	while (obj.options.length > 0) {
		obj.remove(0);
	}
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
		idMatchDetail=id;
		loadOptions(id)
	});
	ligne.onmouseover = (function(){
		ligne.style.backgroundColor = "#CFD3F8";
	});
	ligne.onmouseout = (function(){
		ligne.style.backgroundColor = "#FEFEFE";
	});
	var colonne1 = ligne.insertCell(0);
    colonne1.innerHTML = id;
    var colonne2 = ligne.insertCell(1);
    colonne2.innerHTML = equipeA;
    var colonne3 = ligne.insertCell(2);
    colonne3.innerHTML = secondsToHms(temps);
    var colonne4 = ligne.insertCell(3);
    colonne4.innerHTML = equipeB;
}

function secondsToHms(d) {
	d = Number(d);
	var h = Math.floor(d / 3600);
	var m = Math.floor(d % 3600 / 60);
	var s = Math.floor(d % 3600 % 60);
	return ((h > 0 ? h + ":" + (m < 10 ? "0" : "") : "") + m + ":" + (s < 10 ? "0" : "") + s); 
}

function parier(){
	console.log(document.getElementById("valueBet").value);
	$.post("GameBet", {matchID: idMatchDetail, nomEquipe: document.getElementById("choiceBet").value, montantPari: document.getElementById("valueBet").value}, function(confirmation){
		if(confirmation == -1){
			alert("Il est trop tard pour parier sur ce match.");
		}
		else{}
	});
}
