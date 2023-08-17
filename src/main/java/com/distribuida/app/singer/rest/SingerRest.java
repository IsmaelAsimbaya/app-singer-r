package com.distribuida.app.singer.rest;

import com.distribuida.app.singer.db.Singer;
import com.distribuida.app.singer.repo.SingerRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/singers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class SingerRest {

    @Inject
    SingerRepository rep;

    @GET
    public List<Singer> findAll() {
        return rep.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response getById(@PathParam("id") Integer id) {
        var singer = rep.findByIdOptional(id);
        if (singer.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.ok(singer.get()).build();
    }

    @POST
    public Response create(Singer s) {
        rep.persist(s);
        return Response.status(Response.Status.CREATED.getStatusCode(),"singer created").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Singer singerObj) {
        Singer singer = rep.findById(id);
        singer.setFirst_name(singerObj.getFirst_name());
        singer.setLast_name(singerObj.getLast_name());
        singer.setBirth_date(singerObj.getBirth_date());
        singer.setVersion(singerObj.getVersion());

        return Response.ok().build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.deleteById(id);
        return Response.ok().build();
    }
}
