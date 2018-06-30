package ir.amv.snippets.tbatask.board.impl;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.IBoardBusinessApi;
import ir.amv.snippets.tbatask.board.IBoardRestService;
import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.Path;

/**
 * @author Amir
 */
@Service
public class BoardRestServiceImpl implements IBoardRestService {

    @Autowired
    private IBoardBusinessApi boardBusinessApi;

    @Override
    public Board addCar(String command) throws BaseException {
        return boardBusinessApi.addCar(command);
    }

    @Override
    public Board moveCar(final String command) throws BaseException {
        return boardBusinessApi.moveCar(command);
    }

    @Override
    public Board getCurrentBoard() {
        return boardBusinessApi.getCurrentBoard();
    }

    @Override
    public Board initBoard(final Integer width, final Integer height, final Integer carCount) throws BaseException {
        return boardBusinessApi.initBoard(width, height, carCount);
    }
}
