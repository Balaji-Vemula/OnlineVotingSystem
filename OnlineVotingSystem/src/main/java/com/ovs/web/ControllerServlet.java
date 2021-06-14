package com.ovs.web;

import com.ovs.dao.AdminDAO;
import com.ovs.dao.CandidateDAO;
import com.ovs.dao.VoterDAO;
import com.ovs.model.Admin;
import com.ovs.model.Candidate;
import com.ovs.model.Voter;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/")
public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private AdminDAO adminDAO;
    private VoterDAO voterDAO;
    private CandidateDAO candidateDAO;
    public void init() {
 
        adminDAO = new AdminDAO("jdbc:mysql://localhost:3306/mini", "root", "root");
        voterDAO = new VoterDAO("jdbc:mysql://localhost:3306/mini", "root", "root");
        candidateDAO= new CandidateDAO("jdbc:mysql://localhost:3306/mini", "root", "root");

    }
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	doPost(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	String action = request.getServletPath();
    	try {
	        switch (action) {
	        case "/admin_login":
	            CheckAdminLoginPage(request, response);
	            break;
	        case "/show_admin_login":
	            showAdminLoginPage(request, response);
	            break;
	        case "/voters_list":
	            showVotersList(request, response);
	            break;
	        case "/Candidates_list":
	            showCandidatesList(request, response);
	            break;
	        case "/resultofvoting":
	        	showResults(request,response);
	        case "/logout":
	            DoLogout(request, response);
				break;
	        default:
	            ShowWelcomePage(request, response);
	            break;
	        }
    	} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
 
    
    
    private void showResults(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	List<Candidate> listResults=candidateDAO.listAllCadidates();
    	request.setAttribute("listResults", listResults);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("Result.jsp");
        dispatcher.forward(request, response);
		
	}

	private void showCandidatesList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	List<Candidate> listCandidates=candidateDAO.listAllCadidates();
    	request.setAttribute("listCandidates", listCandidates);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("CandidatesList.jsp");
        dispatcher.forward(request, response);
    	
		
	}

	private void showVotersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	List<Voter> listVoters=voterDAO.listAllVoters();
    	request.setAttribute("listVoters", listVoters);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("VoterList.jsp");
        dispatcher.forward(request, response);
    	
		
	}

	private void DoLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("logout yes");
    	HttpSession session = request.getSession();
		session.removeAttribute("email");
		session.removeAttribute("admin");
		session.invalidate();
		response.sendRedirect("login.jsp");
		
	}

	private void CheckAdminLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
    	String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		//if(uname.equals("Balaji")&& pass.equals("Vemula"))// for static checking username and password
		
		try {
			Admin existingAdmin=adminDAO.getAdmin(email, pass);
			if(existingAdmin!=null)
			{
				HttpSession session=request.getSession();
				session.setAttribute("email", email);
				session.setAttribute("admin", existingAdmin);
				response.sendRedirect("Home_Admin.jsp");
			}
			else
			{
				response.sendRedirect("login.jsp");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void showAdminLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    	
		
	}
    
    private void ShowWelcomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("ovs.jsp");
        dispatcher.forward(request, response);
		
	}
    
}