package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.IBaseRestService;
import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * @author Amir
 */
@Path("/board")
public interface IBoardRestService extends IBaseRestService {

    @POST
    @Path("/addCar")
    @Produces(MediaType.APPLICATION_JSON)
    Board addCar(@QueryParam("command") String command) throws BaseException;

    @POST
    @Path("/moveCar")
    @Produces(MediaType.APPLICATION_JSON)
    Board moveCar(@QueryParam("command") String command) throws BaseException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Board getCurrentBoard();

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Board initBoard(
            @QueryParam("width") Integer width,
            @QueryParam("height") Integer height,
            @QueryParam("count") Integer carCount) throws BaseException;
}
