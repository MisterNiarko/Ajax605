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
		<button id="btRefresh" type="button" onclick="loadMatchList()">Refresh</button> 
		<table id="tableResult" border=1></table>
	</body>
</html> 