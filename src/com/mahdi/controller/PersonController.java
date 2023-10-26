package com.mahdi.controller;

import com.mahdi.model.entity.Person;
import com.mahdi.model.service.PersonService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("person")
public class PersonController {

    @Path("save")
    @Produces("text/plain")
    @GET
    public String save(@QueryParam("name") String name, @QueryParam("family") String family, @QueryParam("salary") long salary) {
        try {
            return PersonService.getInstance().save(new Person(name, family, salary));
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @Path("find-all")
    @Produces("text/plain")
    @GET
    public String findAll() {
        try {
            return PersonService.getInstance().findAll();
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
    @Path("test")
    @Produces("text/plain")
    @GET
    public String test(@QueryParam("name") String name, @QueryParam("family") String family,
                       @QueryParam("salary") long salary,
                       @QueryParam("x") int x,
                       @QueryParam("y") int y) {
        try {
            return PersonService.getInstance().test(new Person(name, family, salary), x, y);
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
