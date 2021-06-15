<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Online Voting System</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/css/mdb.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
    </head>
<body>



	<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container">
            <a href="ovs.jsp" class="navbar-brand">
                <i class="fa fa-vote-yea"></i>&nbsp;&nbsp;<span style="color:darkorange">Online</span> Voting <span style="color:green">System</span>
            </a>

            <button class="navbar-toggler" data-toggle="collapse" data-target="#nologin-navbar">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="nologin-navbar">
				<ul class="navbar-nav ml-auto">
                    <li class="nav-item px-2">
                        <a href="/OnlineVotingSystem/show_admin_login" class="nav-link">
                            Admin Login
                        </a>
                    </li>
                    
                    <li class="nav-item px-2">
                        <a href="/OnlineVotingSystem/show_voter_login" class="nav-link">
                             Voter Login
                        </a>
                    </li>
                    
                </ul>    
                
            </div>

        </div>
    </nav>
</body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
  <script src="JS/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.js"></script>
  <script src="JS/popper.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.js"></script>
  <script src="JS/bootstrap.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/js/mdb.js"></script>
  <script src="JS/mdb.js"></script>
</html>