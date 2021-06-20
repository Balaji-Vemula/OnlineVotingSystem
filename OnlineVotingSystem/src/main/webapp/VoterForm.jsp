<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>OVS | Voters</title>
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
     padding-bottom:140px;
	}
	</style>
</head>
<body>
<%
	// To Prevent Back Button after Logout
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
	
	response.setHeader("pragma", "no-cache");// Http version 1.0
	
	response.setHeader("Expires", "0");// for proxies
	if(session.getAttribute("email")==null)
	{
		response.sendRedirect("ovs.jsp");
	}
%>
<%@ include file="Header.jsp" %>

	<div class="container my-5">
    <div class="col-md-5 mx-auto">
    <div class="bg-white p-3 text-white" style="border-radius:10px;">
    <h3 class="text-dark text-center font-weight-bold mb-4">Voter Registration</h3>
    <form action="InsertVoter" method="post">
	    <div class="form-group">
	      <input type="text" name="v_first_name" class="form-control" placeholder="First Name" required />
	    </div>
	    <div class="form-group">
	      <input type="text" name="v_last_name" class="form-control" placeholder="Last Name" required />
	    </div>
	    <div class="form-group">
	      <input type="email" name="v_email" class="form-control" placeholder="E-mail" required />
	    </div>
	    <div class="form-group">
	      <input type="number" name="v_age" class="form-control" placeholder="Age" required />
	    </div>
	    <div class="form-group">
	      <input type="password" name="v_pwd" class="form-control" placeholder="Password" required />
	    </div>
	    <div class="text-center">
	    <input type="submit" class="btn btn-success" value="Submit" />
	  	</div>
    </form>
    </div>
    </div>
    </div>


<%@ include file="Footer.jsp" %>
</body>
</html>