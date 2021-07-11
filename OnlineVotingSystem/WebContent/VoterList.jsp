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

	<c:if test="${fv =='insert'}">
        <section class="alert m-2 alert-primary text-center alert-dismissible animated fadeInDown" style="position:fixed; width:99%;">
      <button class="close" data-dismiss="alert">
        <span style="color: black;">x</span></button>
      <span class="lead">A new voter added successfully</span>
      </section>
      <%
      	session.setAttribute("fv", "");
      %>
      </c:if>
      
      <c:if test="${fv =='delete'}">
        <section class="alert m-2 alert-danger text-center alert-dismissible animated fadeInDown" style="position:fixed; width:99%;">
      <button class="close" data-dismiss="alert">
        <span style="color: black;">x</span></button>
      <span class="lead">Voter deleted successfully</span>
      </section>
      <%
      	session.setAttribute("fv", "");
      %>
      </c:if>
	
	<div class="container bg-white mb-4 pb-2" style="border-radius: 15px;margin-top:70px">
    <div class="py-3">
    <div class="row align-items-center">
    <div class= "col-md-10">
    <h2 class="text-dark pl-5 font-weight-bold">List of Voters</h2>
    </div>
    <div class="col-md-2">
    <a href="/OnlineVotingSystem/NewVoterForm" class="btn btn-success btn-md">Add voter</a>
    </div>
    </div>
    </div>
        <table class="table table-responsive-md">
            
            <thead>
            <tr>
                <th class="font-weight-bolder text-center">S no</th>
                <th class="font-weight-bolder text-center">Voter Id</th>
                <th class="font-weight-bolder text-center">First Name</th>
                <th class="font-weight-bolder text-center">Last Name</th>
                <th class="font-weight-bolder text-center">E-mail</th>
                <th class="font-weight-bolder text-center">Age</th>
                <th class="font-weight-bolder text-center">Status</th>
                <th class="font-weight-bolder text-center">Delete</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="voter" items="${listVoters}">
                <tr>
                    <td class="text-center"><c:out value="${voter.vsno}" /></td>
                    <td class="text-center"><c:out value="${voter.voter_id}" /></td>
                    <td class="text-center"><c:out value="${voter.v_first_name}" /></td>
                    <td class="text-center"><c:out value="${voter.v_last_name}" /></td>
                    <td class="text-center"><c:out value="${voter.v_email}" /></td>
                    <td class="text-center"><c:out value="${voter.v_age}" /></td>
                    <td class="text-center">
                    <c:if test="${voter.v_age<18}">
                    N/A
                    </c:if>
                    <c:if test="${voter.v_age>17}">
                    <c:if test="${voter.v_cast==true}">
                    Not Voted Yet
                    </c:if>
                    <c:if test="${voter.v_cast!= true}">
                    Voted
                    </c:if>
                    </c:if>
                    
                    </td>
                    <c:if test="${voter.v_cast==true}">
                    <td class="text-center align-items-center py-2">
                        <a href="/OnlineVotingSystem/ShowDeleteVoter?voter_id=<c:out value='${voter.voter_id}' />" class="btn btn-danger btn-sm font-weight-bold" style="border-radius:20px">Delete</a></td>
                    
                    </c:if>
                    <c:if test="${voter.v_cast!=true}">
                    <td class="text-center align-items-center py-2">
                        <button class="btn btn-danger btn-sm font-weight-bold" style="border-radius:20px" disabled>Delete</button></td>
                    </c:if>
                    </tr>
                    
                    </c:forEach>
                    </tbody>
                    </table>
                    
                    </div>


<%@ include file="Footer.jsp" %>
<script>
  window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 3000);
  </script>
</body>
</html>