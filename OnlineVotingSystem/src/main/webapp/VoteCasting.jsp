<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>OVS | Vote Casting</title>
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
	if(session.getAttribute("v_email")==null)
	{
		response.sendRedirect("ovs.jsp");
	}
%>
<%@ include file="HeaderVoterLogin.jsp" %>

	<c:if test="${voter.v_age<18}">
	<section class="alert m-2 text-center alert-info alert-dismissible animated fadeInDown" style="position:fixed; width:99%;">
      <button class="close" data-dismiss="alert">
        <span style="color: black;">x</span></button>
      <span class="lead">You are not eligible for casting vote.</span>
      </section>
      </c:if>

	<c:if test="${voter.v_age>17}">
	<c:if test="${voter.v_cast==false }">
	
	<section class="alert m-2 text-center alert-info alert-dismissible animated fadeInDown" style="position:fixed; width:99%;">
      <button class="close" data-dismiss="alert">
        <span style="color: black;">x</span></button>
      <span class="lead">Your vote has been already recorded successfully. Thank you.</span>
      </section>
      </c:if>
      </c:if>

   <%
		if(session.getAttribute("lf")!="")
		{
			session.setAttribute("lf", "");
		}
	%>

	
	<div class="container bg-white mb-4" style="border-radius: 15px; margin-top:70px;">
    <div class="py-3">
     
    <h2 class="text-dark text-center font-weight-bold">List of Candidates</h2>
    
    
    
        <table class="table table-hover">
            
            <thead>
            <tr>
                <th class="font-weight-bolder text-center">Name</th>
                <th class="font-weight-bolder text-center">Party Name</th>
                <c:if test="${voter.v_age>17 }">
                <c:if test="${voter.v_cast==true }">
                <th class="font-weight-bolder text-center">Cast Voting</th>
                </c:if></c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="candidate" items="${listCandidates}">
                <tr>
                    <td class="text-center"><c:out value="${candidate.c_first_name}" />&nbsp;<c:out value="${candidate.c_last_name}" /></td>
                    <td class="text-center"><c:out value="${candidate.party_name}" /></td>
                    <c:if test="${voter.v_age>17 }">
                    <c:if test="${voter.v_cast==true }">
                    <td class="text-center align-items-center py-2">
                        <a href="/OnlineVotingSystem/CastVote?party_name=<c:out value='${candidate.party_name}' />" class="btn btn-primary btn-sm font-weight-bold" style="border-radius:20px">Vote</a></td>
                        </c:if></c:if>
                 </tr>
              </c:forEach>
              </tbody>
              </table>
              </div></div>


<%@ include file="Footer.jsp" %>
</body>
	<script>
	  window.setTimeout(function() {
		    $(".alert").fadeTo(500, 0).slideUp(500, function(){
		        $(this).remove(); 
		    });
		}, 3000);
	  </script>
</html>