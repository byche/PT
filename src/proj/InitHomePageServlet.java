package proj;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import root.AddWelcomeServlet;
import net.sf.jsr107cache.Cache;
import net.sf.jsr107cache.CacheException;
import net.sf.jsr107cache.CacheFactory;
import net.sf.jsr107cache.CacheManager;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.jsr107cache.GCacheFactory;

public class InitHomePageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatastoreService datastore;
	private final static String MSG_KEY="msgkey";

	
	
    public InitHomePageServlet() {
        super();
        // TODO Auto-generated constructor stub
        datastore = DatastoreServiceFactory.getDatastoreService();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request,response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("coucou");
		Cache cache=null;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
	    Map props = new HashMap();
	    props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
	    props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
	    try {
	      	        CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	        cache = cacheFactory.createCache(props);
	
	     } catch (CacheException e) {
	     }
	    
	    String msg=getWelcomeMsg(datastore,cache);
		
		
        request.setAttribute("message", msg); // This will be available as ${message}
        request.getRequestDispatcher("/index.jsp").forward(request, response);
        
        System.out.println(msg);

		
		response.setContentType("text/plain");
		
		
		}
	
		
		
	

	private String getWelcomeMsg(DatastoreService datastore, Cache cache) {
	if( cache.get(MSG_KEY)!=null){
		return (String)cache.get(MSG_KEY);
	}else{
		Filter msgWelcomeFilter =
				  new FilterPredicate(AddWelcomeServlet.WELCOME_MSG_MSG_ENTITY_PROPERTY,
				                      FilterOperator.NOT_EQUAL,
				                      null);
		
		// Use class Query to assemble a query
		Query q = new Query(AddWelcomeServlet.WELCOME_MSG_ENTITY_KEY).setFilter(msgWelcomeFilter);
		
		
		// Use PreparedQuery interface to retrieve results
		PreparedQuery pq = datastore.prepare(q);

		String welcomeString="";
		for (Entity result : pq.asIterable()) {
			welcomeString = (String) result.getProperty(AddWelcomeServlet.WELCOME_MSG_MSG_ENTITY_PROPERTY);
		}
		
		cache.put(MSG_KEY, welcomeString);
		return welcomeString;
	}
}
	
}

