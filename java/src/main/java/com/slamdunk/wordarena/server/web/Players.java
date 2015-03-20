package com.slamdunk.wordarena.server.web;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Root resource (exposed at "myresource" path)
 */
@Path("/players/{pseudo}")
public class Players {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayer(@PathParam("pseudo") String pseudo) {
        return 
        	"{"
	    		+ "_id : '" + pseudo + "'," 
	    		+ "phone : 01234567890123456,"
	    		+ "score : 12,"
	    		+ "title : 'THRACE',"
	    		+ "blessings : ['VENUS','JUPITER'],"
	    		+ "gameIds : [12,35,633]"
    		+ "}";
    }
}
