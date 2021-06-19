<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>OVS | Delete Voter</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/css/mdb.css">
    <link rel="stylesheet" href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">
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
        <div class="container">
        <div class="row">
          <div class="col-md-6 mx-auto my-4">
          <div class="bg-white center text-white" style="border-radius: 10px;">
          
            <h2 class="text-dark font-weight-bold text-center p-3">Delete Voter Confirmation</h2>
            
            
        		<table class="table table-hover">
            
                <c:if test="${dvoter != null}">
                    <input type="hidden" name="voter_id" value="<c:out value='${dvoter.voter_id}' />" />
                </c:if> 
               <tbody class="text-center">
            <tr>
                <th>Voter ID: </th>
                <td>
                    <c:out value='${dvoter.voter_id}' />
                </td>
            </tr>
                    
            <tr>
                <th>First Name: </th>
                <td>
                    <c:out value='${dvoter.v_first_name}' />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <c:out value='${dvoter.v_last_name}' />
                </td>
            </tr>
            
            <tr>
                <th>E-mail: </th>
                <td>
                    <c:out value='${dvoter.v_email}' />
                </td>
            </tr>
            
            <tr>
                <th>Age: </th>
                <td>
                    <c:out value='${dvoter.v_age}' />
                </td>
            </tr>
            
            <tr>
                <th>Status: </th>
                <td class="text-center">
                    <c:if test="${dvoter.v_age<18}">
                    N/A
                    </c:if>
                    <c:if test="${dvoter.v_age>17}">
                    <c:if test="${dvoter.v_cast==true}">
                    Not Voted Yet
                    </c:if>
                    <c:if test="${dvoter.v_cast!= true}">
                    Voted
                    </c:if>
                    </c:if>
                    
                    </td>
            </tr>
            
            <tr><th class="text-center" colspan="2">Do you want to delete this Voter?</tr>
            <tr>
            	<td >
            	<a class="btn btn-danger btn-sm" href="/OnlineVotingSystem/DeleteVoter?voter_id=<c:out value='${dvoter.voter_id}' />">Yes</a></td>
                <td >
                    <a class="btn btn-success btn-sm" href="/OnlineVotingSystem/voters_list">No</a>
    
                </td>
            </tr>
            </tbody>
        </table>
   
          
          </div>
          </div></div></div>
    
    
    
</body>
<%@ include file="FooterWithNoId.jsp" %>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.min.js" integrity="sha384-Atwg2Pkwv9vp0ygtn1JAojH0nYbwNJLPhwyoVbhoPwBhjQPR5VtM2+xf0Uwh9KtT" crossorigin="anonymous"></script>
	 <script src="https://cdnjs.cloudflare.com/ajax/libs/mdbootstrap/4.12.0/js/mdb.js"></script>


</html>