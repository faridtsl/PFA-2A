package Servlets;

import java.io.IOException;
import java.math.BigInteger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Classes.TBallotEntity;
import Classes.TOptionEntity;
import Classes.TVoterEntity;
import Model.TBallotModel;
import Model.TOptionModel;
import Model.TVoterModel;
import Services.BallotService;
import Services.RSA;

/**
 * Servlet implementation class VoteServlet
 */
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		Integer ballotId = Integer.parseInt(request.getParameter("idBallot"));
		TBallotEntity b = new TBallotModel().getBallotById(ballotId);
		Integer voterId = (Integer) request.getSession().getAttribute("idUser");
		TOptionEntity op = new TOptionModel().getOption(Integer.parseInt(request.getParameter("conf")));
		TVoterEntity v = new TVoterModel().getVoterById(voterId);
		BigInteger vote = new BigInteger(op.getIdPrime()).multiply(new BigInteger(v.getIdPrime()));
        String s[] = b.getPublicKey().split(";");
        RSA rsa = new RSA(new BigInteger(s[1]),new BigInteger(s[0]));
		new BallotService().castVote(ballotId, voterId, rsa.encrypt(vote));
		response.sendRedirect("home.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
