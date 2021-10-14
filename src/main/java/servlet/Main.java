package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AccountDAO;
import model.Account;

/**
 * Servlet implementation class Main
 */
@WebServlet("/Main")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String para = request.getParameter("action");
		if (para != null && para.equals("logout")) {
			HttpSession session = request.getSession(true);
			session.invalidate();//セッションスコープ全てを破棄する
			//			session.removeAttribute("account");//セッションスコープ内のaccountだけを破棄する
			System.out.println("セッション情報を破棄しました");
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		} else if (para != null && para.equals("ura")) {
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/loginok.jsp");
			rd.forward(request, response);
		} else {
			RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
			rd.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");

		//データベース行きます
		AccountDAO dao = new AccountDAO();
		Account account = dao.findOne(id, pass);

		if (account == null) {
			//リクエストスコープにエラーメッセージ格納
			request.setAttribute("errorMsg", "アカウントが見つかりませんでした");
			RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
			rd.forward(request, response);
		} else {
			//セッションスコープにアカウント情報格納
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			session.setAttribute("pay", "注文情報");//セッションスコープに複数の情報を入れる練習のために作成
			//フォワード
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/loginok.jsp");
			rd.forward(request, response);
		}
	}

}
