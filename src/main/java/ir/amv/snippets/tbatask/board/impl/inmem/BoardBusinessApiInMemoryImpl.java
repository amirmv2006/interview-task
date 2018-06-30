package ir.amv.snippets.tbatask.board.impl.inmem;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.CarMoveType;
import ir.amv.snippets.tbatask.board.IBoardBusinessApi;
import ir.amv.snippets.tbatask.board.exc.InvalidCarMoveCommandException;
import ir.amv.snippets.tbatask.board.impl.CarMoverThread;
import ir.amv.snippets.tbatask.car.Car;
import ir.amv.snippets.tbatask.car.ICarBusinessApi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static ir.amv.snippets.tbatask.TbaTaskApplication.IN_MEMORY_PROFILE;

/**
 * @author Amir
 */
@Component
@Profile(IN_MEMORY_PROFILE)
public class BoardBusinessApiInMemoryImpl
        implements IBoardBusinessApi, ApplicationContextAware {

    public static final String COMMAND_DELIMITER = ",";
    @Autowired
    private ICarBusinessApi carBusinessApi;
    @Value("${board.default.width}")
    private Integer defaultWidth;
    @Value("${board.default.height}")
    private Integer defaultHeight;
    @Value("${board.default.cars.count}")
    private Integer defaultCarsCount;
    @Value("${board.default.speed}")
    private Integer defaultSpeed;

    private Board currentBoard;
    private ConcurrentMap<String, CarMoverThread> carMoverIdMap = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() throws BaseException {
        initBoard(defaultWidth, defaultHeight, defaultCarsCount);
    }

    @Override
    public Board addCar(final String command) throws BaseException {
        Car car = carBusinessApi.createCar(currentBoard);
        currentBoard.getCars().add(car);
        moveCar(car.getId() + COMMAND_DELIMITER + command);
        return currentBoard;
    }

    @Override
    public Board moveCar(final String command) throws BaseException {
        StringTokenizer tokenizer = new StringTokenizer(command, COMMAND_DELIMITER);
        String carId = tokenizer.nextToken();
        Car car = carBusinessApi.getCarById(carId);
        String moveCommand = tokenizer.nextToken();
        CarMoveType carMoveType = CarMoveType.fromCommand(moveCommand.toUpperCase());
        CarMoverThread carMoverThread = carMoverIdMap.get(carId);
        if (carMoverThread == null) {
            carMoverThread = applicationContext.getBean(CarMoverThread.class);
            carMoverIdMap.put(carId, carMoverThread);
        }
        Integer speed = defaultSpeed;
        if (tokenizer.hasMoreTokens()) {
            try {
                speed = Integer.valueOf(tokenizer.nextToken());
            } catch (NumberFormatException e) {
                throw new BaseException("Invalid Speed", e);
            }
        }
        carMoverThread.moveCar(currentBoard, car, carMoveType, speed);
        return currentBoard;
    }

    @Override
    public Board getCurrentBoard() {
        return currentBoard;
    }

    @Override
    public Board initBoard(final Integer width, final Integer height, final Integer carCount) throws BaseException {
        carMoverIdMap.forEach((s, carMoverThread) -> {
            carBusinessApi.removeCar(carMoverThread.getCar().getId());
            carMoverThread.kill();
        });
        carMoverIdMap.clear();
        currentBoard = new Board();
        currentBoard.setWidth(width);
        currentBoard.setHeight(height);
        currentBoard.setCars(new ArrayList<>());
        Random random = new Random();
        for (Integer i = 0; i < carCount; i++) {
            int randomeMove = random.nextInt(CarMoveType.values().length);
            addCar(CarMoveType.values()[randomeMove].getCommand());
        }
        return currentBoard;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
