<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>OVS | Home</title>
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
	if(session.getAttribute("email")==null)
	{
		response.sendRedirect("ovs.jsp");
	}
%>
<%@ include file="Header.jsp" %>

<div class="container mt-5 animated fadeIn">
<h5 class="display-4 text-capitalize font-weight-bold"> Welcome, <c:out value="${admin.first_name}"></c:out>&nbsp;<c:out value="${admin.last_name}"></c:out>! </h5>
</div>


<div class="container mt-3 mb-5">
    <div class="row animated fadeIn">
      <div class="col-md-4 mt-3">
        <div class="card text-center">
          <div class="card-header text-white" style="background:orange;">
            <div class="row align-items-center">
              <div class="col">
                <h3 class="display-3"><c:if test="${v_count<10 }">0</c:if> ${v_count}</h3>
                <h6>No of Voters</h6>
              </div>
            </div>
          </div>
          <div class="card-footer">
            <h5>
              <a href="/OnlineVotingSystem/voters_list" style="color:orange;">View Details <i class="fa fa-arrow-circle-right"></i></a>
            </h5>
          </div>
        </div>
      </div>

      <div class="col-md-4 mt-3">
        <div class="card text-center">
          <div class="card-header text-dark">
            <div class="row align-items-center">
              <div class="col">
                <h3 class="display-3"><c:if test="${v_count_voted<10 }">0</c:if>${v_count_voted}</h3>
                <h6>No of Voters Voted</h6>
              </div>
            </div>
          </div>
          <div class="card-footer">
            <h5>
              <a href="/OnlineVotingSystem/voters_list" class="text-dark">View Details <i class="fa fa-arrow-circle-right"></i></a>
            </h5>
          </div>
        </div>
      </div>

      <div class="col-md-4 mt-3">
        <div class="card text-center">
          <div class="card-header bg-success text-white">
            <div class="row align-items-center">
              <div class="col">
                <h3 class="display-3"><c:if test="${c_count<10 }">0</c:if>${c_count}</h3>
                <h6>No of Candidates</h6>
              </div>
            </div>
          </div>
          <div class="card-footer">
            <h5>
              <a href="/OnlineVotingSystem/Candidates_list" class="text-success">View Details <i class="fa fa-arrow-circle-right"></i></a>
            </h5>
          </div>
        </div>
      </div>
    </div>
  </div>
<div style="margin-bottom: 77px;"></div>
<%@ include file="FooterWithNoId.jsp" %>
</body>
</html>