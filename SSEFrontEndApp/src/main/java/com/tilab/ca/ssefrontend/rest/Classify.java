package com.tilab.ca.ssefrontend.rest;

import com.tilab.ca.ssefrontend.ClassifyService;
import java.io.File;
import static java.lang.String.valueOf;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 */
@Path("classify")
public class Classify {

	
	
    /**
     * Method for the main classify interface.
	 * 
     */
    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
    public String getMetrics( @FormDataParam("text") String inputText,
//			@FormDataParam("file") File file, //TODO File not yet supported
			@FormDataParam("url") String url,
//			@FormDataParam("fileName") String fileName,
			@FormDataParam("numTopics") int numTopics,
			@FormDataParam("lang") String lang,
			@QueryParam("image_policy") @DefaultValue("BASIC") String imagePolicy ) {
		
		
		return ClassifyService.classify( 
				ofNullable(url),
				ofNullable(inputText),
				ofNullable(imagePolicy)
		);
				
    }
	
	/**
     * Stub method
     *
	 * @param url
     * @return String that will be returned as a text/plain response.
     */
	@Path("ping")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String toggleHighCPU() {
		return "I'm here";
    }
	
}
