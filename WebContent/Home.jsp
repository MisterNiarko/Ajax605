<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>HockeyLive</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="scriptHome.js"></script>
		<link rel="stylesheet" type="text/css" href="home.css" />
	</head>

	<body onload="loadMatchList()">
	<div class="container">
    	<div class="left">
			<button id="btRefresh" type="button" onclick="loadMatchList()">Refresh</button>
			<h1>LISTE DES MATCHS EN COURS</h1>
			<div class="table">
				<table id="tableMatch" class="tableMatch" ></table>
			</div>
		</div>
		<div class="right">
			<table id="tableMatchDetail"></table>
		</div>
	</div>
	</body>
</html> 