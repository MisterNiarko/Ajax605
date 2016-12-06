<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>HockeyLive</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="scriptHome.js"></script>
		<link rel="stylesheet" type="text/css" href="home.css" />
	</head>

	<body onload="onStart()">
	<div id="dialog"></div>
	<div class="container">
    	<div class="left">
			<h1>LISTE DES MATCHS EN COURS</h1>
			<table id="tableMatch" class="tableMatch" ></table>
			<img src="Loading_icon.gif" id="loadingGif" hidden>
			<button id="btRefresh" type="button" onclick="loadMatchList()">Refresh</button>
		</div>
		<div class="right">
			<table id="tableMatchDetail"></table>
			<div>
				<p>Parier 
				<input type="number" id="valueBet" name="quantity" min="0.1" max="1000">
				$ sur
				<select id="choiceBet" size=1></select></p>
				
			</div>
			<button id="btParier" type="button" onclick="parier()">Parier</button>
		</div>
	</div>
	</body>
</html> 