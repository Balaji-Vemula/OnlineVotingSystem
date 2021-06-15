<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
  <head>
  <title>Online Voting | Admin login</title>
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
	.center {
	  margin: auto;
	  width: 50%;
	  padding: 10px;
	}
	.input-group:hover{
		box-shadow: 0 5px 5px grey;
	}
  </style>


</head>


<body>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/js/mdb.js"></script>

  

  <%@ include file="HeaderNoLogin.jsp" %>
  <div class="container-fluid">
	<div class="my-5">
    <h2 class="text-center py-3">Administrator Login</h2>
    <div class="bg-white mt-3 center text-white" style="width: 25%; border-radius:10px;">
      <form method="post" action="admin_login">
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="fa fa-user-circle"></i>
            </span>
          </div>
          <input name="email" type="email" class="form-control text-black" placeholder="Email">
        
        </div>
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="fa fa-key"></i>
            </span>
          </div>
          <input name="pass" type="password" class="form-control text-black" placeholder="Password">
        </div>
        <div class="text-center">
          <input type="submit" class="btn btn-primary" name="Submit" value="Login">
        </div>
    </div>
    </div>
  </div>
</body>
<%@ include file="Footer.jsp" %>
</html>