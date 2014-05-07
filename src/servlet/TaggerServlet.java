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

import tagger.HMMTagger;
import utils.ArrayUtils;

/**
 * Servlet implementation class Hello
 */
@WebServlet("/tagger")
public class TaggerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TaggerServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	
		// TODO Auto-generated method stub
		
		//response.setCharacterEncoding("utf8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json");
		PrintWriter w = response.getWriter();

		HttpSession session = request.getSession();
		HMMTagger tagger = (HMMTagger) session.getAttribute("tagger");
		if (tagger == null) {
			tagger = new HMMTagger(getServletContext().getRealPath(""));
			session.setAttribute("tagger", tagger);
		}
		//tagger = new HMMTagger(getServletContext().getRealPath("/"));

		JSONArray arr = new JSONArray();
		String s = (String) request.getParameter("s");
		if (s != null) {

			for (String c : tagger.sentenceIterator(s)) {
				JSONObject t = new JSONObject();
				t.put("s", tagger.tokenize(c));
				
				t.put("tag", ArrayUtils.join(tagger.getTags(c), " "));
				arr.add(t);
			}
		}
		w.println(arr.toJSONString());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
