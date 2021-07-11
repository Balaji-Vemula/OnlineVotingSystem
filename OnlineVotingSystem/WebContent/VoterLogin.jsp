<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>

<html>
  <head>
  <title>OVS | Voter login</title>
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

<%@ include file="HeaderNoLogin.jsp" %>
  <c:if test="${lf == 'no'}">
        <section class="alert m-2 text-center bg-danger text-white alert-dismissible animated fadeInDown" style="position:fixed; width:99%;">
      <button class="close" data-dismiss="alert">
        <span style="color: black;">x</span></button>
      <span class="lead">Please, Enter correct credentials</span>
      </section>
      </c:if>

  <%
		if(session.getAttribute("lf")!="")
		{
			session.setAttribute("lf", "");
		}
	%>

  <div class="container mt-2">
	<div class="row">
	<div class="col-sm-8 col-md-4 mx-auto mt-5">
    <h2 class="text-center py-3 font-weight-bold">Voter Login</h2>
    <div class="bg-white p-3 text-white" style="border-radius:10px;">
      <form method="post" action="Voter_Login">
        <div class="input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="fa fa-envelope"></i>
            </span>
          </div>
          <input name="v_email" type="email" class="form-control text-black" placeholder="Email">
        
        </div>
        <div class="input-group mb-2">
          <div class="input-group-prepend">
            <span class="input-group-text">
              <i class="fa fa-key"></i>
            </span>
          </div>
          <input name="v_pwd" type="password" id="vmypassword" class="form-control text-black border-right-0" placeholder="Password">
        	<div class="input-group-append">
                    <span class="input-group-text bg-white">
                     <a onclick="myFunctionVoter()"><i class="fa fa-eye-slash" id="vpwdIcon"></i></a>
                    </span>
                  </div>
        </div>
        <div class="text-center">
          <input type="submit" class="btn btn-primary" name="Submit" value="Login">
        </div>
        </form>
    </div>
    </div>
    </div>
  </div>
<%@ include file="Footer.jsp" %>
</body>

  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/js/mdb.js"></script>
  <script>
  window.setTimeout(function() {
	    $(".alert").fadeTo(500, 0).slideUp(500, function(){
	        $(this).remove(); 
	    });
	}, 3000);
  
  function myFunctionVoter(){
      var i=document.getElementById("vmypassword");
      var j=document.getElementById("vpwdIcon");
      if(i.type==="password")
      {
          i.type="text";
          j.classList.replace("fa-eye-slash","fa-eye");
          
      }
      else
      {
          i.type="password";
          j.classList.replace("fa-eye","fa-eye-slash");
      }
  }
  </script>
</html>