package com.picturesHolder.services;

import com.picturesHolder.singleton.PicturesHolder;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
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
@Path("")
public class PicturesHolderResource {

    private static final String ENCODED_IMAGE_KEY = "encodedImage";
    private static final String NUMBER_OF_CLUSTERS_KEY = "clusters";
    private static final String RESULT = "result";

    private final PicturesHolder picturesHolder;

    /**
     * Creates a new instance of PicturesHolderResource
     */
    public PicturesHolderResource() {
        picturesHolder = PicturesHolder.getInstance();
    }

    /**
     * Retrieves representation of an instance of
     * com.imagesHolder.services.PicturesHolderResource
     *
     * @param jsonString
     * @return an instance of java.lang.String
     */
    @POST
    @Path("getImage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response getImage(String jsonString) {
        Response response;
        try {
            JSONObject jsonObject = (JSONObject) new org.json.simple.parser.JSONParser().parse(jsonString);

            String imageString = (String) jsonObject.get(ENCODED_IMAGE_KEY);
            String numberOfClustersString = (String) jsonObject.get(NUMBER_OF_CLUSTERS_KEY);

            String key = numberOfClustersString + imageString;

            if (picturesHolder.contains(key)) {
                JSONObject result = new JSONObject();
                result.put(ENCODED_IMAGE_KEY, picturesHolder.get(key));
                response = Response.ok().entity(result.toJSONString()).build();
            } else {
                response = Response.noContent().build();
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
    @Path("postImage")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postImage(String jsonString) {
        Response response;
        try {
            JSONObject jsonObject = (JSONObject) new org.json.simple.parser.JSONParser().parse(jsonString);

            String imageString = (String) jsonObject.get(ENCODED_IMAGE_KEY);
            String numberOfClustersString = (String) jsonObject.get(NUMBER_OF_CLUSTERS_KEY);
            String result = (String) jsonObject.get(RESULT);

            String key = numberOfClustersString + imageString;

            if (!picturesHolder.contains(key)) {
                picturesHolder.put(key, result);
            }
            response = Response.ok().build();

        } catch (ParseException ex) {
            Logger.getLogger(PicturesHolderResource.class.getName()).log(Level.SEVERE, null, ex);

            response = Response.serverError().build();
        }

        return response;
    }

}
