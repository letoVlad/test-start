package com.gridnine.testing;


import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Flight> flights = FlightBuilder.createFlights();
        for (int i = 0; i < flights.size(); i++) {
            System.out.println(flights.get(i) + " - " + i);
        }

        System.out.println("====");
        System.out.println(getFlightsWithArrivalDateBeforeDepartureDate(flights));

    }

    /**
     * Сегменты с датой прилёта раньше даты вылета.
     *
     * @param flights
     * @return
     */
    static LinkedHashSet<Flight> getFlightsWithArrivalDateBeforeDepartureDate(List<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException("Список рейсов пуст или нулевой");
        }

        LinkedHashSet<Flight> flightSet = new LinkedHashSet<>();

//        for (Flight flight : flights) {
//            var arrivalDate = flight.getSegments().get(0).getArrivalDate();
//            if (arrivalDate.isBefore(flight.getSegments().get(0).getDepartureDate())) {
//                flightSet.add(flight);
//            }
//        }

        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .anyMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toCollection(LinkedHashSet::new));



    }

    /**
     * Получить все рейсы, вылетевшие до текущего времени.
     *
     * @param flights список для фильтрации рейсов.
     * @return Список рейсов, вылетевших до текущего момента времени.
     */
    static LinkedHashSet<Flight> getFlightsBeforeCurrentTime(List<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException("Список рейсов пуст или нулевой");
        }

        LocalDateTime localDateTime = LocalDateTime.now();
        LinkedHashSet<Flight> flightSet = new LinkedHashSet<>();

        for (Flight flight : flights) {
            if (flight.getSegments().get(0).getDepartureDate().isBefore(localDateTime)) {
                flightSet.add(flight);
            }
        }

        return flightSet;
    }
}