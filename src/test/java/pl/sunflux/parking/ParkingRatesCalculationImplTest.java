package pl.sunflux.parking;

import org.junit.Test;
import pl.sunflux.entity.*;
import pl.sunflux.entity.constants.DriverTypeEnum;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by maciej on 14.09.17.
 */
public class ParkingRatesCalculationImplTest {

    @Test
    public void testCalculationForVip() throws ParseException, NoCalculationException {
        Currency currency = new Currency();
        currency.setCurrencyCourseToPLN(new BigDecimal(1));

        Driver driver = new Driver();
        driver.setDriverTypeEnum(DriverTypeEnum.VIP);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriver(driver);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ParkingMeterUsage parkingMeterUsage = new ParkingMeterUsage();
        parkingMeterUsage.setCurrency(currency);
        parkingMeterUsage.setDateStart(df.parse("2017-09-14 13:00:00"));
        parkingMeterUsage.setDateEnd(df.parse("2017-09-14 14:00:00"));
        parkingMeterUsage.setParkingMeter(new ParkingMeter());
        parkingMeterUsage.setVehicle(vehicle);

        ParkingRatesCalculationInterface parkingRatesCalculation = new ParkingRatesCalculationImpl();
        BigDecimal result = parkingRatesCalculation.calculateParkingFee(parkingMeterUsage);

        assertEquals(new BigDecimal(0), result);
    }

    @Test
    public void testCalculationForRegular() throws ParseException, NoCalculationException {
        Currency currency = new Currency();
        currency.setCurrencyCourseToPLN(new BigDecimal(1));

        Driver driver = new Driver();
        driver.setDriverTypeEnum(DriverTypeEnum.REGULAR);

        Vehicle vehicle = new Vehicle();
        vehicle.setDriver(driver);

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        ParkingMeterUsage parkingMeterUsage = new ParkingMeterUsage();
        parkingMeterUsage.setCurrency(currency);
        parkingMeterUsage.setParkingMeter(new ParkingMeter());
        parkingMeterUsage.setVehicle(vehicle);

        ParkingRatesCalculationInterface parkingRatesCalculation = new ParkingRatesCalculationImpl();

        String[] dateStart = new String[]{"2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00", "2017-09-14 13:00:00"};
        String[] dateEnd = new String[]{"2017-09-14 14:00:00", "2017-09-14 15:00:00", "2017-09-14 16:00:00", "2017-09-14 17:00:00", "2017-09-14 18:00:00", "2017-09-14 19:00:00"};
        Integer[] expectedResult = new Integer[]{1, 3, 7, 15, 31, 63};

        for (int i = 0; i < dateEnd.length; i++) {
            parkingMeterUsage.setDateStart(df.parse(dateStart[i]));
            parkingMeterUsage.setDateEnd(df.parse(dateEnd[i]));
            BigDecimal result = parkingRatesCalculation.calculateParkingFee(parkingMeterUsage);
            assertEquals(new BigDecimal(expectedResult[i]), result);
        }
    }

    @Test(expected = NoCalculationException.class)
    public void testNoCalculationException() {

    }


}
