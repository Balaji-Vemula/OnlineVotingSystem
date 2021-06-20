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
	        case "/InsertVoter":
	        	InsertAVoter(request,response);
	        	break;
	        case "/InsertCandidate":
	        	InsertACandidate(request,response);
	        	break;
	        case "/show_admin_login":
	            showAdminLoginPage(request, response);
	            break;
	        case "/voters_list":
	            showVotersList(request, response);
	            break;
	        case "/WinnersList":
	        	showWinnersList(request,response);
	        case "/Candidates_list":
	            showCandidatesList(request, response);
	            break;
	        case "/logout":
	            DoLogout(request, response);
				break;
	        case "/NewVoterForm":
	        	RegisterVoter(request,response);
	        	break;
	        case "/NewCandidateForm":
	        	RegisterCandidate(request,response);
	        	break;
	        case "/show_voter_login":
	        	showVoterLoginPage(request, response);
	            break;
	        case "/Voter_Login":
	        	CheckVoterLoginPage(request, response);
	            break;
	        case "/ShowVoteCastPage":
	        	showVoteCast(request,response);
	        	break;
	        case "/ShowDeleteCandidate":
	        	showDeleteCandidatePage(request,response);
	        	break;
	        case "/ShowDeleteVoter":
	        	showDeleteVoterPage(request,response);
	        	break;
	        case "/CastVote":
	        	doCastVote(request,response);
	        	break;
	        case "/DeleteVoter":
	        	doDeleteVoter(request,response);
	        	break;
	        case "/DeleteCandidate":
	        	doDeleteCandidate(request,response);
	        case "/ResetPolling":
	        	doResetPolling(request,response);
	        	break;
	        case "/Home_Admin":
	        	dynamicCards(request,response);
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
 
    

	

	

	private void dynamicCards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("ovs.jsp");
		}
		else {
			List<Voter> listVoters=voterDAO.listAllVoters();
			List<Voter> listVoted=voterDAO.listAllVoted();
			List<Candidate> listCandidates=candidateDAO.listAllCadidates();
			session.setAttribute("v_count", listVoters.size());
			session.setAttribute("v_count_voted", listVoted.size());
			session.setAttribute("c_count", listCandidates.size());
			response.sendRedirect("Home_Admin.jsp");
		
			
		}
	}
	
	private void CheckAdminLoginPage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
    	String email=request.getParameter("email");
		String pass=request.getParameter("pass");
		HttpSession session=request.getSession();
		
		try {
			Admin existingAdmin=adminDAO.getAdmin(email, pass);
			if(existingAdmin!=null)
			{
				session.setAttribute("email", email);
				session.setAttribute("admin", existingAdmin);
				dynamicCards(request, response);
			}
			else
			{
				session.setAttribute("lf","no");
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

	private void doResetPolling(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("ovs.jsp");
		}
		else 
		{
			voterDAO.resetPolling();
			candidateDAO.resetPolling();
			session.setAttribute("reset", "yes");
			response.sendRedirect("WinnersList");
		}
		
	}

	private void doDeleteCandidate(HttpServletRequest request, HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("ovs.jsp");
		}
		else 
		{
	        int c_id = Integer.parseInt(request.getParameter("c_id"));
	        Candidate dc = new Candidate(c_id);
	        candidateDAO.deleteCandidate(dc);
	        session.setAttribute("fc", "delete");
	        response.sendRedirect("Candidates_list");
		}
	}

	private void showDeleteCandidatePage(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("ovs.jsp");
		}
		else {
			int c_id = Integer.parseInt(request.getParameter("c_id"));
	        Candidate existingCandidate = candidateDAO.getCandidate(c_id);
	        request.setAttribute("dcandidate", existingCandidate);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteCandidateConfirm.jsp");
	        dispatcher.forward(request, response);
		}
	}

	private void doDeleteVoter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("ovs.jsp");
		}
		else {
	        int voter_id = Integer.parseInt(request.getParameter("voter_id"));
	        Voter dv = new Voter(voter_id);
	        voterDAO.deleteVoter(dv);
	        session.setAttribute("fv", "delete");
	        response.sendRedirect("voters_list");
		}
	}

	private void showDeleteVoterPage(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException, ServletException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
			int voter_id = Integer.parseInt(request.getParameter("voter_id"));
	        Voter existingVoter = voterDAO.getVoter(voter_id);
	        request.setAttribute("dvoter", existingVoter);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("DeleteVoterConfirm.jsp");
	        dispatcher.forward(request, response);
		}
	}

	private void doCastVote(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies
		Voter voter=(Voter) session.getAttribute("voter");
		if(session.getAttribute("v_email")==null)
		{
			response.sendRedirect("VoterLogin.jsp");
		}
		else if(voter.getV_cast()==true) 
		{
			
			String party_name=request.getParameter("party_name");
			Candidate candidate=new Candidate(party_name);
			boolean status=candidateDAO.incrementVote(candidate);
			if(status) {
				String v_email=(String) session.getAttribute("v_email");
				Voter vt=new Voter(v_email);
				voterDAO.changeVCast(vt);
				
				voter.setV_cast(false);
				session.setAttribute("voter", voter);
				response.sendRedirect("Home_Voter.jsp");
			}
			else
			{
				response.sendRedirect("VoterLogin.jsp");
			}
		}
		
	}

	private void showVoteCast(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies
		Voter voter=(Voter) session.getAttribute("voter");
		if(session.getAttribute("v_email")==null)
		{
			response.sendRedirect("VoterLogin.jsp");
		}
		else
		{
		List<Candidate> listCandidates=candidateDAO.listAllCadidates();
    	request.setAttribute("listCandidates", listCandidates);
		RequestDispatcher dispatcher = request.getRequestDispatcher("VoteCasting.jsp");
        dispatcher.forward(request, response);
		}
		
	}

	private void CheckVoterLoginPage(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String v_email=request.getParameter("v_email");
		String v_pwd=request.getParameter("v_pwd");
		HttpSession session=request.getSession();
		try {
			Voter existingVoter=voterDAO.getVoter(v_email, v_pwd);
			if(existingVoter!=null)
			{
				session.setAttribute("lf","");
				session.setAttribute("v_email", v_email);
				session.setAttribute("voter", existingVoter);
				response.sendRedirect("Home_Voter.jsp");
			}
			else
			{
				session.setAttribute("lf","no");
				response.sendRedirect("VoterLogin.jsp");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	private void showVoterLoginPage(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher dispatcher = request.getRequestDispatcher("VoterLogin.jsp");
        dispatcher.forward(request, response);
		
	}

	private void RegisterCandidate(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("CandidateForm.jsp");
        dispatcher.forward(request, response);
		}
	}

	private void InsertACandidate(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
		String c_first_name = request.getParameter("c_first_name");
		String c_last_name = request.getParameter("c_last_name");
		int c_age = Integer.parseInt(request.getParameter("c_age"));
		String party_name = request.getParameter("party_name");
		
		Candidate newCandidate=new Candidate(c_first_name, c_last_name, c_age, party_name);
		candidateDAO.insertCandidate(newCandidate);
		session.setAttribute("fc", "insert");
		response.sendRedirect("Candidates_list");
		}
	}

	private void InsertAVoter(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
		String v_first_name = request.getParameter("v_first_name");
		String v_last_name = request.getParameter("v_last_name");
		String v_email = request.getParameter("v_email");
		int v_age = Integer.parseInt(request.getParameter("v_age"));
		String v_pwd = request.getParameter("v_pwd");
		
		Voter newVoter= new Voter(v_first_name, v_last_name, v_email, v_age, v_pwd);
		voterDAO.insertVoter(newVoter);
		session.setAttribute("fv", "insert");
		response.sendRedirect("voters_list");
		}
	}

	private void RegisterVoter(HttpServletRequest request, HttpServletResponse response) throws Exception, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
		RequestDispatcher dispatcher = request.getRequestDispatcher("VoterForm.jsp");
        dispatcher.forward(request, response);
		}
	}

	private void showWinnersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
		List<Candidate> list=candidateDAO.listAll();
		request.setAttribute("list", list);
		List<Voter> count=voterDAO.listAllVoted();
		session.setAttribute("count", count.size());
		RequestDispatcher dispatcher=request.getRequestDispatcher("Winners.jsp");
		dispatcher.forward(request, response);
		}
		
	}

	private void showCandidatesList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
    	List<Candidate> listCandidates=candidateDAO.listAllCadidates();
    	request.setAttribute("listCandidates", listCandidates);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("CandidatesList.jsp");
        dispatcher.forward(request, response);
		}
    	
		
	}

	private void showVotersList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");//Http 1.1
		
		response.setHeader("pragma", "no-cache");// Http version 1.0
		
		response.setHeader("Expires", "0");// for proxies

		if(session.getAttribute("email")==null)
		{
			response.sendRedirect("login.jsp");
		}
		else {
    	List<Voter> listVoters=voterDAO.listAllVoters();
    	request.setAttribute("listVoters", listVoters);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("VoterList.jsp");
        dispatcher.forward(request, response);
		}
    	
		
	}

	private void DoLogout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
    	HttpSession session = request.getSession();
    	if(session.getAttribute("email") != null) {
		session.removeAttribute("email");
		session.removeAttribute("admin");
		session.invalidate();
		response.sendRedirect("login.jsp");
    	}
    	else if( session.getAttribute("v_email") != null) {
    		session.removeAttribute("v_email");
    		session.removeAttribute("voter");
    		session.invalidate();
    		response.sendRedirect("VoterLogin.jsp");
    	}
		
	}

	

	private void showAdminLoginPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		session.setAttribute("lf", "");
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    	
		
	}
    
    private void ShowWelcomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	RequestDispatcher dispatcher = request.getRequestDispatcher("ovs.jsp");
        dispatcher.forward(request, response);
		
	}
    
}