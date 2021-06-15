<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Online Voting | Home</title>
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
	}
	</style>
</head>
<body>
<%
	// To Prevent Back Button after Logout
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
	
	response.setHeader("pragma", "no-cache");// Http version 1.0
	
	response.setHeader("Expires", "0");// for proxies
	if(session.getAttribute("v_email")==null)
	{
		response.sendRedirect("ovs.jsp");
	}
%>
<%@ include file="HeaderVoterLogin.jsp" %>

<div class="container mt-5 animated fadeIn">
<h5 class="display-4 text-capitalize font-weight-bold"> Welcome, <c:out value="${voter.v_first_name}"></c:out>&nbsp;<c:out value="${voter.v_last_name}"></c:out>! </h5>


<c:if test="${voter.v_age<18}">
<div>
<p class="lead text-danger">You have no authorization to cast a vote because the Indian Government requires minimum 18 years of age for an Indian Citizen. </p>
</div>
</c:if>
<c:if test="${voter.v_cast==true}">
<div>
<p class="lead text-info">Now, You can cast your vote.</p>
</div>
</c:if>
<c:if test="${voter.v_cast==false}">
<div>
<p class="lead text-success">Your vote has been successfully recorded. Thank you.</p>
</div>
</c:if>

</div>
<%@ include file="Footer.jsp" %>
</body>
</html>