package test.metrics;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

public class SimpleServlet extends HttpServlet {

	private static final long serialVersionUID = -977083570839354632L;
	
//	@Autowired
	private BusinessProcessor processor;

	public SimpleServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//		processor.process();
		
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>Metrics</h1>");
	}
}