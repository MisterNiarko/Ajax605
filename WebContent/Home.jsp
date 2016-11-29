<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>HockeyLive</title>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="scriptHome.js"></script>
	</head>

	<body onload="loadMatchList()">
		<table id="tableResult" border=1></table>
		<button id="btAddResult" type="button" onclick="ajouterLigne()">AddResult</button> 
	</body>
</html> 