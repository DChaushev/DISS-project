package com.imagesHolder.services;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Dimitar
 */
@Path("picturesHolder")
public class PicturesHolderResource {

    private static final String ENCODED_IMAGE_KEY = "encodedImage";
    private static final String NUMBER_OF_CLUSTERS_KEY = "clusters";

    private final Map<String, String> picturesHolder;

    /**
     * Creates a new instance of PicturesHolderResource
     */
    public PicturesHolderResource() {
        picturesHolder = new HashMap<>();
    }

    /**
     * Retrieves representation of an instance of
     * com.imagesHolder.services.PicturesHolderResource
     *
     * @param jsonString
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getImage(String jsonString) {
        Response response;
        try {
            JSONObject jsonObject = (JSONObject) new org.json.simple.parser.JSONParser().parse(jsonString);

            String imageString = (String) jsonObject.get(ENCODED_IMAGE_KEY);
            String numberOfClustersString = (String) jsonObject.get(NUMBER_OF_CLUSTERS_KEY);

            String key = numberOfClustersString + imageString;

            if (picturesHolder.containsKey(key)) {
                JSONObject result = new JSONObject();
                result.put(ENCODED_IMAGE_KEY, picturesHolder.get(key));
                response = Response.ok().entity(result.toJSONString()).build();
            } else {
                response = Response.ok().entity(null).build();
            }

        } catch (ParseException ex) {
            Logger.getLogger(PicturesHolderResource.class.getName()).log(Level.SEVERE, null, ex);

            response = Response.serverError().build();
        }
        return response;
    }

    /**
     *
     * @param jsonString
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response postImage(String jsonString) {
        Response response;
        try {
            JSONObject jsonObject = (JSONObject) new org.json.simple.parser.JSONParser().parse(jsonString);

            String imageString = (String) jsonObject.get(ENCODED_IMAGE_KEY);
            String numberOfClustersString = (String) jsonObject.get(NUMBER_OF_CLUSTERS_KEY);

            String key = numberOfClustersString + imageString;

            if (!picturesHolder.containsKey(key)) {
                picturesHolder.put(key, imageString);
                response = Response.ok().build();
            } else {
                response = Response.ok().entity(null).build();
            }

        } catch (ParseException ex) {
            Logger.getLogger(PicturesHolderResource.class.getName()).log(Level.SEVERE, null, ex);

            response = Response.serverError().build();
        }
        return response;
    }

}
