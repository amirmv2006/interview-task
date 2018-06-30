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

    private Board currentBoard;
    private ConcurrentMap<String, CarMoverThread> carMoverIdMap = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;

    @PostConstruct
    private void init() throws BaseException {
        currentBoard = new Board();
        currentBoard.setWidth(defaultWidth);
        currentBoard.setHeight(defaultHeight);
        currentBoard.setCars(new ArrayList<>());
        Random random = new Random();
        for (Integer i = 0; i < defaultCarsCount; i++) {
            createCar();
            int randomeMove = random.nextInt(CarMoveType.values().length);
            moveCar(currentBoard.getCars().get(i).getId() + COMMAND_DELIMITER + CarMoveType.values()[randomeMove].getCommand());
        }
    }

    @Override
    public Board createCar() {
        Car car = carBusinessApi.createCar(currentBoard);
        currentBoard.getCars().add(car);
        return currentBoard;
    }

    @Override
    public Board moveCar(final String command) throws BaseException {
        StringTokenizer tokenizer = new StringTokenizer(command, COMMAND_DELIMITER);
        String carId = tokenizer.nextToken();
        Car car = carBusinessApi.getCarById(carId);
        String moveCommand = tokenizer.nextToken();
        CarMoveType carMoveType = CarMoveType.fromCommand(moveCommand);
        CarMoverThread carMoverThread = carMoverIdMap.get(carId);
        if (carMoverThread == null) {
            carMoverThread = applicationContext.getBean(CarMoverThread.class);
            carMoverIdMap.put(carId, carMoverThread);
        }
        carMoverThread.moveCar(currentBoard, car, carMoveType);
        return currentBoard;
    }

    @Override
    public void setApplicationContext(final ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
