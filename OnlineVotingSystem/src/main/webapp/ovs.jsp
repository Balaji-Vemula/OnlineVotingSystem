<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online Voting System</title>
    <meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css"> 
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/css/mdb.css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
	
	<style>
	 body{
		background: linear-gradient(125deg,darkorange,white, darkgreen);
		background-repeat: no-repeat;
	    background-attachment: fixed;
	    background-position: center;
	    position:relative;
	    min-height:100vh;
	    padding-bottom:120px;
	}
	</style>
	
    </head>
<%@ include file="HeaderNoLogin.jsp" %>	
<body>
<div class="container animated fadeIn" style="margin-top:7%">
<div class="row">
<div class="col-md-12">
<h5 class="display-4 text-center font-weight-bold">Welcome to</h5>
<h5 class="display-2 text-center font-weight-bold">Online Voting System</h5>
</div>
</div>
</div>
</body>
<%@ include file="Footer.jsp" %>
</html>