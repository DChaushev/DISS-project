/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controller.services;

import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author Dimitar
 */
@Path("hello")
public class HelloResource {

    /**
     * Retrieves representation of an instance of
     * com.controller.services.HelloResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/plain")
    public String getText() {
        return "Hello!";
    }

}
