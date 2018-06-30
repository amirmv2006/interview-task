package ir.amv.snippets.tbatask.board.impl.inmem;

import ir.amv.snippets.tbatask.BaseException;
import ir.amv.snippets.tbatask.board.Board;
import ir.amv.snippets.tbatask.board.CarMoveType;
import ir.amv.snippets.tbatask.board.IBoardBusinessApi;
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
 * In-Memory implementation of {@link IBoardBusinessApi}.
 * @author Amir
 */
@Component
@Profile(IN_MEMORY_PROFILE)
public class BoardBusinessApiInMemoryImpl
        implements IBoardBusinessApi, ApplicationContextAware {

    public static final String COMMAND_DELIMITER = ",";
    @Autowired
    private ICarBusinessApi carBusinessApi;
    /**
     * default width for default board creation.
     */
    @Value("${board.default.width}")
    private Integer defaultWidth;
    /**
     * default height for default board creation.
     */
    @Value("${board.default.height}")
    private Integer defaultHeight;
    /**
     * default car count for default board creation.
     */
    @Value("${board.default.cars.count}")
    private Integer defaultCarsCount;
    /**
     * default speed for default board creation and when car speed is not specified.
     */
    @Value("${board.default.speed}")
    private Integer defaultSpeed;

    /**
     * the current board.
     */
    private Board currentBoard;
    /**
     * a carId to {@link CarMoverThread} map which holds all the {@link CarMoverThread}s
     */
    private ConcurrentMap<String, CarMoverThread> carMoverIdMap = new ConcurrentHashMap<>();
    /**
     * {@link ApplicationContext} used for fetching instances of {@link CarMoverThread}
     */
    private ApplicationContext applicationContext;

    /**
     * init method which will create a default board with default parameters in application.properties
     */
    @PostConstruct
    private void init() {
        initBoard(defaultWidth, defaultHeight, defaultCarsCount);
    }

    /**
     * @inheritDoc
     */
    @Override
    public Board addCar(final String command) throws BaseException {
        Car car = carBusinessApi.createCar(currentBoard);
        currentBoard.getCars().add(car);
        updateCar(car.getId() + COMMAND_DELIMITER + command);
        return currentBoard;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Board updateCar(final String command) throws BaseException {
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

    /**
     * @inheritDoc
     */
    @Override
    public Board getCurrentBoard() {
        return currentBoard;
    }

    /**
     * @inheritDoc
     */
    @Override
    public Board initBoard(final Integer width, final Integer height, final Integer carCount) {
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
            try {
                addCar(CarMoveType.values()[randomeMove].getCommand());
            } catch (BaseException ignored) {
                // command is generated by dev, assumed it has no problem
            }
        }
        return currentBoard;
    }

    /**
     * @inheritDoc
     */
    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
