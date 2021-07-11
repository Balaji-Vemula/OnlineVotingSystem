<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css"> 
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/css/mdb.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css">
  
  
  
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a href="Home_Voter.jsp" class="navbar-brand">
                <i class="fa fa-vote-yea"></i>&nbsp;&nbsp;<span style="color:darkorange">Online</span> Voting <span style="color:green">System</span>
            </a>

            <button class="navbar-toggler" data-toggle="collapse" data-target="#voter-navbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="voter-navbar">
            
                <ul class="navbar-nav align-items-center">
					
					<li class="nav-item px-2">
                        <a href="Home_Voter.jsp" class="nav-link">
                             Home
                        </a>
                    </li>
					
                    <li class="nav-item px-2">
                        <a href="/OnlineVotingSystem/ShowVoteCastPage" class="nav-link btn btn-md btn-primary">
                             Cast Your Vote
                        </a>
                    </li>
                    
                </ul>

                <ul class="navbar-nav ml-auto">
                    <li class="nav-item text-white nav-link">
                    	<i class="fa fa-sign-in-alt text-muted"></i><span class="text-capitalize"> <c:out value="${voter.v_first_name }"></c:out> <c:out value="${voter.v_last_name }"></c:out></span>
                    </li>
                    
                    <li class="nav-item">
                        <a href="/OnlineVotingSystem/logout" class="nav-link">
                            <i class="fa fa-sign-out-alt text-muted"></i> LogOut
                        </a>
                    </li>
                    
                </ul>    
                
            </div>

        </div>
    </nav>

	



</body>
</html>