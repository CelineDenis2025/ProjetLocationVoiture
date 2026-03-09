package com.accenture.service;

import com.accenture.exception.VehiculeException;
import com.accenture.model.Car;
import com.accenture.model.enums.CarTypes;
import com.accenture.model.enums.FuelType;
import com.accenture.model.enums.NbDoors;
import com.accenture.model.enums.Transmission;
import com.accenture.service.dto.CarRequestDto;
import com.accenture.service.dto.CarResponseDto;
import com.accenture.service.dto.VehiculeRequestDto;
import com.accenture.service.fake.FakeCarDao;
import com.accenture.service.fake.FakeCarMapper;
import com.accenture.utils.Messages;
import org.junit.jupiter.api.*;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.StaticMessageSource;

import java.util.Locale;

public class CarServiceImplTest {

    private FakeCarDao fakeCarDao;
    private FakeCarMapper fakeCarMapper;
    private CarServiceImpl carService;

    @BeforeEach
    void setUp() {
        fakeCarDao = new FakeCarDao();
        fakeCarMapper = new FakeCarMapper();

        carService = new CarServiceImpl(fakeCarDao, fakeCarMapper, messageAccessor());
    }

    private MessageSourceAccessor messageAccessor() {
        StaticMessageSource sms = new StaticMessageSource();
        sms.addMessage(Messages.CAR_ID_NOT_FOUND, Locale.getDefault(), Messages.CAR_ID_NOT_FOUND);
        sms.addMessage(Messages.CAR_NULL, Locale.getDefault(), Messages.CAR_NULL);
        sms.addMessage(Messages.VEHICULE_BRAND_NULL, Locale.getDefault(), Messages.VEHICULE_BRAND_NULL);
        sms.addMessage(Messages.VEHICULE_MODEL_NULL, Locale.getDefault(), Messages.VEHICULE_MODEL_NULL);
        sms.addMessage(Messages.VEHICULE_COLOR_NULL, Locale.getDefault(), Messages.VEHICULE_COLOR_NULL);
        sms.addMessage(Messages.CAR_NB_PLACES_NULL, Locale.getDefault(), Messages.CAR_NB_PLACES_NULL);
        sms.addMessage(Messages.CAR_FUEL_TYPE_NULL, Locale.getDefault(), Messages.CAR_FUEL_TYPE_NULL);
        sms.addMessage(Messages.CAR_NB_DOORS_NULL, Locale.getDefault(), Messages.CAR_NB_DOORS_NULL);
        sms.addMessage(Messages.CAR_TRANSMISSION_NULL, Locale.getDefault(), Messages.CAR_TRANSMISSION_NULL);
        sms.addMessage(Messages.CAR_AIR_CONDITIONING_NULL, Locale.getDefault(), Messages.CAR_AIR_CONDITIONING_NULL);
        sms.addMessage(Messages.CAR_NB_LUGGAGE_NULL, Locale.getDefault(), Messages.CAR_NB_LUGGAGE_NULL);
        sms.addMessage(Messages.CAR_TYPE_NULL, Locale.getDefault(), Messages.CAR_TYPE_NULL);
        return new MessageSourceAccessor(sms);
    }

    private Car car(int id, String brand, String model, String color, int nbPlace, FuelType fuelType, NbDoors nbDoors, Transmission transmission, boolean airConditioning, int nbLuggage, CarTypes carTypes) {
        Car car = new Car();

        car.setId(id);
        car.setBrand(brand);
        car.setColor(color);
        car.setModel(model);
        car.setNbPlaces(nbPlace);
        car.setFuelType(fuelType);
        car.setNbDoors(nbDoors);
        car.setTransmission(transmission);
        car.setAirConditioning(airConditioning);
        car.setNbLuggage(nbLuggage);
        car.setCarTypes(carTypes);

        return car;
    }

    @Nested
    @DisplayName("addCar")
    class AddCarTest {

        @Test
        @DisplayName("OK")
        void addCar_ok() {

            VehiculeRequestDto vehicule = new VehiculeRequestDto(
                    "Renault",
                    "Clio",
                    "rouge"
            );

            CarRequestDto requestDto = new CarRequestDto(
                    vehicule,
                    4,
                    FuelType.ESSENCE,
                    NbDoors.FIVE,
                    Transmission.MANUAL,
                    true,
                    3,
                    CarTypes.LUXURY_CAR);

            CarResponseDto responseDto = carService.addCar(requestDto);

            Assertions.assertEquals("Renault", responseDto.vehiculeResponseDto().brand());
            Assertions.assertEquals("Clio", responseDto.vehiculeResponseDto().model());
            Assertions.assertEquals("rouge", responseDto.vehiculeResponseDto().color());
            Assertions.assertEquals(4, responseDto.nbPlaces());
            Assertions.assertEquals(FuelType.ESSENCE, responseDto.fuelType());
            Assertions.assertEquals(NbDoors.FIVE, responseDto.nbDoors());
            Assertions.assertEquals(Transmission.MANUAL, responseDto.transmission());
            Assertions.assertEquals(true, responseDto.airConditioning());
            Assertions.assertEquals(3, responseDto.nbLuggage());
            Assertions.assertEquals(CarTypes.LUXURY_CAR, responseDto.carTypes());
            Assertions.assertEquals(1, fakeCarDao.store.size());
        }
    }

    @Test
    @DisplayName("invalid (null)")
    void addCar_invalid_null() {
        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(null));
    }

    @Test
    @DisplayName("invalid (brand empty)")
    void addCar_brand_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (model empty)")
    void addCar_model_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (color empty)")
    void addCar_color_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                ""
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (nb place empty)")
    void addCar_nb_place_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                null,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (fuel type empty)")
    void addCar_fuel_type_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                null,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (nb door empty)")
    void addCar_nb_door_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                null,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (transmission empty)")
    void addCar_transmission_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                null,
                true,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (air conditioning empty)")
    void addCar_air_conditioning_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                null,
                3,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (nb luggage empty)")
    void addCar_nb_luggage_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                null,
                CarTypes.LUXURY_CAR);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Test
    @DisplayName("invalid (car type empty)")
    void addCar_car_type_Empty() {

        VehiculeRequestDto vehicule = new VehiculeRequestDto(
                "Renault",
                "Clio",
                "rouge"
        );

        CarRequestDto requestDto = new CarRequestDto(
                vehicule,
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                null);

        Assertions.assertThrows(VehiculeException.class, () -> carService.addCar(requestDto));
    }

    @Nested
    @DisplayName("deleteCar")
    class DeleteCarTest {

        @Test
        @DisplayName("not found")
        void delete_car_not_found() {
            Assertions.assertThrows(VehiculeException.class, () -> carService.deleteCar(99));
        }
    }

    @Test
    @DisplayName("car existed")
    void delete_car_existed() {

        fakeCarDao.store.put(1, car(
                1,
                "Renault",
                "Clio",
                "rouge",
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR
        ));

        Assertions.assertThrows(VehiculeException.class, () -> carService.deleteCar(1));
        Assertions.assertTrue(fakeCarDao.store.containsKey(1));
    }

    @Test
    @DisplayName("OK")
    void delete_car_ok() {
        fakeCarDao.store.put(1, car(
                1,
                "Renault",
                "Clio",
                "rouge",
                4,
                FuelType.ESSENCE,
                NbDoors.FIVE,
                Transmission.MANUAL,
                true,
                3,
                CarTypes.LUXURY_CAR
        ));
        carService.deleteCar(1);
        Assertions.assertFalse(fakeCarDao.store.containsKey(1));
    }


    @Nested
    @DisplayName("find")
    class FindCarTest {

        @Test
        @DisplayName("Find by id : not found")
        void find_car_by_id_not_found() {
            Assertions.assertThrows(VehiculeException.class, () -> carService.findById(99));
        }

        @Test
        @DisplayName("Find by id : OK")
        void find_car_by_id_ok() {
            fakeCarDao.store.put(1, car(
                    1,
                    "Renault",
                    "Clio",
                    "rouge",
                    4,
                    FuelType.ESSENCE,
                    NbDoors.FIVE,
                    Transmission.MANUAL,
                    true,
                    3,
                    CarTypes.LUXURY_CAR
            ));

            CarResponseDto responseDto = carService.findById(1);

            Assertions.assertEquals("Renault", responseDto.vehiculeResponseDto().brand());
            Assertions.assertEquals("Clio", responseDto.vehiculeResponseDto().model());
            Assertions.assertEquals("rouge", responseDto.vehiculeResponseDto().color());
            Assertions.assertEquals(4, responseDto.nbPlaces());
            Assertions.assertEquals(FuelType.ESSENCE, responseDto.fuelType());
            Assertions.assertEquals(NbDoors.FIVE, responseDto.nbDoors());
            Assertions.assertEquals(Transmission.MANUAL, responseDto.transmission());
            Assertions.assertEquals(true, responseDto.airConditioning());
            Assertions.assertEquals(3, responseDto.nbLuggage());
            Assertions.assertEquals(CarTypes.LUXURY_CAR, responseDto.carTypes());
            Assertions.assertEquals(1, fakeCarDao.store.size());
        }
    }

    @Nested
    @DisplayName("Partially update Car")
    class PartiallyUpdateCarTest {

        @Test
        @DisplayName("OK")
        void update_car_ok() {
            fakeCarDao.store.put(1, car(
                    1,
                    "Renault",
                    "Clio",
                    "rouge",
                    4,
                    FuelType.ESSENCE,
                    NbDoors.FIVE,
                    Transmission.MANUAL,
                    true,
                    3,
                    CarTypes.LUXURY_CAR
            ));

            VehiculeRequestDto vehicule = new VehiculeRequestDto(
                    "Citroën",
                    "Picasso",
                    "noire"
            );

            CarRequestDto requestDto = new CarRequestDto(
                    vehicule,
                    4,
                    FuelType.ESSENCE,
                    NbDoors.FIVE,
                    Transmission.MANUAL,
                    true,
                    4,
                    CarTypes.LUXURY_CAR);

            CarResponseDto carResponseDto = carService.partiallyUpdateCar(1, requestDto);

            Assertions.assertEquals("Citroën", fakeCarDao.store.get(1).getBrand());
            Assertions.assertEquals("Picasso", fakeCarDao.store.get(1).getModel());
            Assertions.assertEquals("noire", fakeCarDao.store.get(1).getColor());
            Assertions.assertEquals(4, carResponseDto.nbPlaces());
            Assertions.assertEquals(FuelType.ESSENCE, carResponseDto.fuelType());
            Assertions.assertEquals(NbDoors.FIVE, carResponseDto.nbDoors());
            Assertions.assertEquals(Transmission.MANUAL, carResponseDto.transmission());
            Assertions.assertEquals(true, carResponseDto.airConditioning());
            Assertions.assertEquals(4, fakeCarDao.store.get(1).getNbLuggage());
            Assertions.assertEquals(CarTypes.LUXURY_CAR, carResponseDto.carTypes());
        }

        @Test
        @DisplayName("Not Found")
        void partially_update_car_not_found() {
            VehiculeRequestDto vehicule = new VehiculeRequestDto(
                    "Citroën",
                    "Picasso",
                    "noire"
            );

            CarRequestDto requestDto = new CarRequestDto(
                    vehicule,
                    4,
                    FuelType.ESSENCE,
                    NbDoors.FIVE,
                    Transmission.MANUAL,
                    true,
                    4,
                    CarTypes.LUXURY_CAR);

            Assertions.assertThrows(VehiculeException.class, () -> carService.partiallyUpdateCar(1, requestDto));
        }

        @Test
        @DisplayName("no change")
        void partially_update_car_no_change() {
            fakeCarDao.store.put(1, car(
                    1,
                    "Renault",
                    "Clio",
                    "rouge",
                    4,
                    FuelType.ESSENCE,
                    NbDoors.FIVE,
                    Transmission.MANUAL,
                    true,
                    3,
                    CarTypes.LUXURY_CAR
            ));

            CarResponseDto carResponseDto = carService.partiallyUpdateCar(
                    1,
                    new CarRequestDto(
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null
                    ));

            Assertions.assertEquals("Renault", fakeCarDao.store.get(1).getBrand());
            Assertions.assertEquals("Clio", fakeCarDao.store.get(1).getModel());
            Assertions.assertEquals("rouge", fakeCarDao.store.get(1).getColor());
            Assertions.assertEquals(4, carResponseDto.nbPlaces());
            Assertions.assertEquals(FuelType.ESSENCE, carResponseDto.fuelType());
            Assertions.assertEquals(NbDoors.FIVE, carResponseDto.nbDoors());
            Assertions.assertEquals(Transmission.MANUAL, carResponseDto.transmission());
            Assertions.assertEquals(true, carResponseDto.airConditioning());
            Assertions.assertEquals(3, fakeCarDao.store.get(1).getNbLuggage());
            Assertions.assertEquals(CarTypes.LUXURY_CAR, carResponseDto.carTypes());
        }
    }

}
