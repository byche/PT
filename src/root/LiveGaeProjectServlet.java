package root;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;

@SuppressWarnings("serial")
public class LiveGaeProjectServlet extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

		Entity user = new Entity("User");
		user.setProperty("name", req.getParameter("name"));
		user.setProperty("surname", req.getParameter("surname") );
		user.setProperty("login", req.getParameter("login"));
		user.setProperty("pwd", req.getParameter("pwd"));

		datastore.put(user); 

	/*	Key userKey = user.getKey();
		try {
			Entity employee = datastore.get(userKey);
		} catch (EntityNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		
	}
}
