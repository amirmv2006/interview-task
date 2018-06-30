package ir.amv.snippets.tbatask.board.impl;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.IBoardBusinessApi;
import ir.amv.snippets.tbatask.board.IBoardRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * implementation for {@link IBoardRestService}.
 * @author Amir
 */
@Service
public class BoardRestServiceImpl implements IBoardRestService {

    @Autowired
    private IBoardBusinessApi boardBusinessApi;

    /**
     * @inheritDoc
     */
    @Override
    public Board addCar(String command) throws BaseException {
        return boardBusinessApi.addCar(command);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Board moveCar(final String command) throws BaseException {
        return boardBusinessApi.updateCar(command);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Board getCurrentBoard() {
        return boardBusinessApi.getCurrentBoard();
    }

    /**
     * @inheritDoc
     */
    @Override
    public Board initBoard(final Integer width, final Integer height, final Integer carCount) {
        return boardBusinessApi.initBoard(width, height, carCount);
    }
}
