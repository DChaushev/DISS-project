package com.controller.services;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Dimitar
 */
@Path("image-segmentation")
public class ImageSegmentationResource {

    /**
     * Retrieves representation of an instance of
     * com.controller.services.ImageSegmentationResource
     *
     * @param stream
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes("*/*")
    public Response segmentateImage(InputStream stream) {
        Response response;
        try {
            //TODO: image is null!
            BufferedImage image = ImageIO.read(stream);
            response = Response.ok().build();
        } catch (IOException | NullPointerException ex) {
            Logger.getLogger(ImageSegmentationResource.class.getName()).log(Level.SEVERE, null, ex);
            response = Response.serverError().entity(ex.getMessage()).build();
        }
        return response;
    }

}
