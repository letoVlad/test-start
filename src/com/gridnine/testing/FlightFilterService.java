package com.gridnine.testing;

import java.util.List;

public interface FlightFilterService {
    List<Flight> getFlightsWithGroundTimeNotExceedingTwoHours(List<Flight> flights);

    List<Flight> getFlightsWithValidArrivalDate(List<Flight> flights);

    List<Flight> getFlightsAfterCurrentTime(List<Flight> flights);
}
