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

public class GoogleAuthServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private DatastoreService datastore;
	private final static String MSG_KEY="msgkey";
	
	private static String googleID = "631926326971-kca9jdv0uot5oghths0h2je81qvipfft.apps.googleusercontent.com";

	
	
    public GoogleAuthServlet() {
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
		
		
		Cache cache=null;
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
	    Map props = new HashMap();
	    props.put(GCacheFactory.EXPIRATION_DELTA, 3600);
	    props.put(MemcacheService.SetPolicy.ADD_ONLY_IF_NOT_PRESENT, true);
	    try {
	      	        CacheFactory cacheFactory = CacheManager.getInstance().getCacheFactory();
	        cache = cacheFactory.createCache(props);
	
	     } catch (CacheException e) {}
	    
	    
	    System.out.println("bonjour je suis le servlet googleAuth");
	    
	    System.out.println(request.toString());

	    
	    
	    
	}
	    
	   

	
		
		
	


	
}

