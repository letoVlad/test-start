package com.gridnine.testing;


import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static com.gridnine.testing.FlightFilter.*;

public class Main {
    public static void main(String[] args) {
        FlightFilterService flightFilterService = new FlightFilter();
        List<Flight> flights = FlightBuilder.createFlights();

        System.out.println("Получить список всех перелетов исключая рейсы: где вылет до текущего момента времени");
        System.out.println(flightFilterService.getFlightsAfterCurrentTime(flights));

        System.out.println("Получить список всех перелетов исключая рейсы: где сегменты с датой прилёта раньше даты вылета");
        System.out.println(flightFilterService.getFlightsWithValidArrivalDate(flights));

        System.out.println("Получить список всех перелетов исключая рейсы: где проведенное время на земле не более двух часов");
        System.out.println(flightFilterService.getFlightsWithGroundTimeNotExceedingTwoHours(flights));
    }

}
