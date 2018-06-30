package ir.amv.snippets.tbatask.board;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.IBaseRestService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * rest service API for board.
 * @author Amir
 */
@Path("/board")
public interface IBoardRestService extends IBaseRestService {

    /**
     * adds a car to the current board.
     * @param command the command for adding the car which consists of the {@link CarMoveType#command} and optionally
     *               a delimiter
     *                ({@link ir.amv.snippets.tbatask.board.impl.inmem.BoardBusinessApiInMemoryImpl#COMMAND_DELIMITER
     *                } and speed which is an integer
     * @return the updated board.
     * @throws BaseException if there is a problem parsing command
     */
    @POST
    @Path("/addCar")
    @Produces(MediaType.APPLICATION_JSON)
    Board addCar(@QueryParam("command") String command) throws BaseException;

    /**
     * updates a car on the current board.
     * @param command the command for adding the car which consists of the carId, a
     *                delimiter ({@link ir.amv.snippets.tbatask.board.impl.inmem.BoardBusinessApiInMemoryImpl#COMMAND_DELIMITER
     *                }), {@link CarMoveType#command} and optionally a delimiter
     *                ({@link ir.amv.snippets.tbatask.board.impl.inmem.BoardBusinessApiInMemoryImpl#COMMAND_DELIMITER
     *                }) and speed which is an integer
     * @return the updated board.
     * @throws BaseException if there is a problem parsing command
     */
    @POST
    @Path("/updateCar")
    @Produces(MediaType.APPLICATION_JSON)
    Board moveCar(@QueryParam("command") String command) throws BaseException;

    /**
     * returns the current board.
     * @return current board.
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Board getCurrentBoard();

    /**
     * removes the current board and initialize a new one.
     * @param width    the width for the new board.
     * @param height   the height for the new board.
     * @param carCount the number of cards on the new board.
     * @return the new board.
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    Board initBoard(
            @QueryParam("width") Integer width,
            @QueryParam("height") Integer height,
            @QueryParam("count") Integer carCount);
}
