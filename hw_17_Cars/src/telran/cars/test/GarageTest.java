package telran.cars.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.cars.dao.Garage;
import telran.cars.dao.GarageImpl;
import telran.cars.model.Car;

import static org.junit.jupiter.api.Assertions.*;

class GarageTest {
    Garage garage;
    Car[] cars;

    @BeforeEach
    void setUp() {
        garage = new GarageImpl(5);

        cars = new Car[4];
        cars[0] = new Car("111PE", "Govnovoz", "Zil", 5.8, "brown");
        cars[1] = new Car("222ZD", "Govnovoz", "Zil", 5.8, "brown");
        cars[2] = new Car("666EC", "Eva 01", "Tokyo-3", 66666, "purple-green");
        cars[3] = new Car("007AA", "Black Lightning", "Volga", 2.5, "black");

        for (Car car : cars){
            garage.addCar(car);
        }
    }

    @Test
    void addCar() {
        assertFalse(garage.addCar(cars[1]));
        assertEquals(4, garage.getGarageSize());

        assertFalse(garage.addCar(null));
        assertEquals(4, garage.getGarageSize());

        Car carToAdd1 = new Car("777EC", "Eva 02", "Nerv-03", 77777, "red-orange");
        assertTrue(garage.addCar(carToAdd1));
        assertEquals(5, garage.getGarageSize());

        Car carToAdd2 = new Car("888EC", "Eva 03", "Nerv-01", 88888, "black-pink");
        assertFalse(garage.addCar(carToAdd2));
        assertEquals(5, garage.getGarageSize());
    }

    @Test
    void removeCar() {
        assertNull(garage.removeCar(null));
        assertEquals(4, garage.getGarageSize());

        Car carToRemove = new Car("777EC", "Eva 02", "Nerv-03", 77777, "red-orange");
        assertNull(garage.removeCar(carToRemove.getRegNumber()));
        assertEquals(4, garage.getGarageSize());

        assertEquals(cars[2], garage.removeCar(cars[2].getRegNumber()));
        assertEquals(3, garage.getGarageSize());
        assertNull(garage.removeCar(cars[2].getRegNumber()));
        assertEquals(3, garage.getGarageSize());
    }

    @Test
    void getGarageSize() {
        assertEquals(4, garage.getGarageSize());

        garage.addCar(null);
        assertEquals(4, garage.getGarageSize());

        garage.removeCar(cars[1].getRegNumber());
        assertEquals(3, garage.getGarageSize());

        garage.addCar(cars[1]);
        assertEquals(4, garage.getGarageSize());

        garage.addCar(cars[1]);
        assertEquals(4, garage.getGarageSize());

        Car carToAdd1 = new Car("777EC", "Eva 02", "Nerv-03", 77777, "red-orange");
        garage.addCar(carToAdd1);
        assertEquals(5, garage.getGarageSize());

        Car carToAdd2 = new Car("888EC", "Eva 03", "Nerv-01", 88888, "black-pink");
        garage.addCar(carToAdd2);
        assertEquals(5, garage.getGarageSize());
    }

    @Test
    void findCarByRegNumber() {
        assertEquals(cars[2], garage.findCarByRegNumber(cars[2].getRegNumber()));
        assertNull(garage.findCarByRegNumber(""));
        assertNull(garage.findCarByRegNumber("1235136"));
    }

    @Test
    void findCarsByModel() {
        Car[] expectedCars = {cars[0], cars[1]};
        Car[] actualCars = garage.findCarsByModel(cars[1].getModel());
        assertArrayEquals(expectedCars, actualCars);

        expectedCars = new Car[0];
        assertArrayEquals(expectedCars, garage.findCarsByModel(""));
        assertArrayEquals(expectedCars, garage.findCarsByModel("Telega without horse"));
    }

    @Test
    void findCarsByCompany() {
        Car[] expectedCars = {cars[0], cars[1]};
        Car[] actualCars = garage.findCarsByCompany(cars[1].getCompany());
        assertArrayEquals(expectedCars, actualCars);

        expectedCars = new Car[0];
        assertArrayEquals(expectedCars, garage.findCarsByCompany(""));
        assertArrayEquals(expectedCars, garage.findCarsByCompany("American Telegi"));
    }

    @Test
    void findCarsByEngine() {
        Car[] expectedCars = {cars[0], cars[1], cars[3]};
        assertArrayEquals(expectedCars, garage.findCarsByEngine(2, 10));

        expectedCars = new Car[0];
        assertArrayEquals(expectedCars, garage.findCarsByEngine(0.1, 0.2));
    }

    @Test
    void findCarsByColor() {
        Car[] expectedCars = {cars[0], cars[1]};
        Car[] actualCars = garage.findCarsByColor(cars[1].getColor());
        assertArrayEquals(expectedCars, actualCars);

        expectedCars = new Car[0];
        assertArrayEquals(expectedCars, garage.findCarsByColor(""));
        assertArrayEquals(expectedCars, garage.findCarsByColor("asdfasghafh"));
    }
}