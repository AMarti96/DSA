package edu.upc.dsa;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Path("/json")
public class JSONService extends Dao {

    public List<track> tracks;

    public JSONService() throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        tracks = new ArrayList<>();
    }

    @GET
    @Path("/got/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public track getTrack(@PathParam("id") int id) {
        return tracks.get(id);
    }

    @GET
    @Path("/get")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTrackInJSON() throws ClassNotFoundException, SQLException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        String json;
        track track=new track();
        tracks=track.select(); //Lo hacemos para que al consultar la base de datos nos coje la clase en la que estamos
        json = new Gson().toJson(tracks);
        return json;

    }
    @POST
    @Path("/post")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTrackInJSON(track track) throws SQLException, NoSuchMethodException, ClassNotFoundException, IllegalAccessException, InvocationTargetException {
        track.getSinger();
        track.getTitle();
        track.insert();
        String result = "track saved : " + track.getSinger();
        return Response.status(201).entity(result).build();
    }

}