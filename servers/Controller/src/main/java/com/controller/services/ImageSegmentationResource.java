package com.controller.services;

import com.controller.segmentator.ImageSegmentator;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.DatatypeConverter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * REST Web Service
 *
 * @author Dimitar
 */
@Path("image-segmentation")
public class ImageSegmentationResource {

    private static final String JPG_FORMAT = "jpg";
    private static final String ENCODED_IMAGE_KEY = "encodedImage";
    private static final String NUMBER_OF_CLUSTERS_KEY = "clusters";

    /**
     * Retrieves representation of an instance of
     * com.controller.services.ImageSegmentationResource
     *
     * @param json
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response segmentateImage(String json) {
        try {
            JSONObject jsonObject = (JSONObject) new JSONParser().parse(json);

            String imageString = (String) jsonObject.get(ENCODED_IMAGE_KEY);
            String numberOfClustersString = (String) jsonObject.get(NUMBER_OF_CLUSTERS_KEY);
            int numberOfClusters = Integer.valueOf(numberOfClustersString);

            BufferedImage image = decodeImage(imageString);

            if (image == null) {
                //TODO: handle error
            }

            BufferedImage segmentImage = ImageSegmentator.segmentImage(image, numberOfClusters);

            String encodeImage = encodeImage(segmentImage);
            JSONObject result = new JSONObject();

            result.put(ENCODED_IMAGE_KEY, encodeImage);

            return Response.ok().entity(result.toJSONString()).build();
        } catch (ParseException | IOException ex) {
            Logger.getLogger(ImageSegmentationResource.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Response.serverError().build();
    }

    public BufferedImage decodeImage(String base64String) {
        byte[] parseBase64Binary = DatatypeConverter.parseBase64Binary(base64String);
        try (ByteArrayInputStream bis = new ByteArrayInputStream(parseBase64Binary)) {
            BufferedImage image = ImageIO.read(bis);
            return image;
        } catch (IOException ex) {
            Logger.getLogger(ImageSegmentationResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String encodeImage(BufferedImage image) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, JPG_FORMAT, baos);
        return DatatypeConverter.printBase64Binary(baos.toByteArray());
    }

}
