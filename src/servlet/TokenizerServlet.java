package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import tokenizer.VietTokenizerWrapper;
import utils.ArrayUtils;
import utils.StringIterator;

/**
 * Servlet implementation class TokenizerServlet
 */
@WebServlet("/token")
public class TokenizerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TokenizerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf8");
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		HttpSession session = request.getSession();
		VietTokenizerWrapper tokenizer = (VietTokenizerWrapper) session
				.getAttribute("tokenizer");
		if (tokenizer == null) {
			tokenizer = new VietTokenizerWrapper(request.getServletContext()
					.getRealPath(""));
		}
		String par = request.getParameter("s");
		if (par ==  null)
			par = "";
	
		writer.println(ArrayUtils.join(tokenizer.tokenize(par), " "));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
