package com.gridnine.testing;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.gridnine.testing.FlightBuilder.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


class FlightFilterTest {


    @Test
    void getFlightsWithGroundTimeNotExceedingTwoHours() {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilterService flightFilterService = new FlightFilter();
        List<Flight> flightList = flightFilterService.getFlightsWithGroundTimeNotExceedingTwoHours(flights);

        assertEquals(1, flightList.size());
    }

    @Test
    void getFlightsWithValidArrivalDate() {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilterService flightFilterService = new FlightFilter();
        List<Flight> flightList = flightFilterService.getFlightsWithValidArrivalDate(flights);

        assertEquals(5, flightList.size());
    }

    @Test
    void getFlightsAfterCurrentTime() {
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilterService flightFilterService = new FlightFilter();
        List<Flight> flightList = flightFilterService.getFlightsWithValidArrivalDate(flights);

        assertEquals(5, flightList.size());
    }
}