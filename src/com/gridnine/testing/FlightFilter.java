package com.gridnine.testing;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class FlightFilter implements FlightFilterService {
    private static final long MAX_GROUND_TIME_HOURS = 2;

    /**
     * Получить список рейсов, с проведенным временем на земле не более двух часов.
     *
     * @param flights список для фильтрации рейсов.
     * @return Список рейсов, исключая те, у которых проведенное время на земле не более двух часов.
     */
    @Override
    public List<Flight> getFlightsWithGroundTimeNotExceedingTwoHours(List<Flight> flights) {
        checkIfFlightsListIsNullOrEmpty(flights);
        List<Flight> flightSet = new ArrayList<>();

        for (Flight flight : flights) {
            if (flight.getSegments().size() > 1) {
                List<Segment> segments = flight.getSegments();
                long totalGroundTime = 0;

                for (int i = 0; i < segments.size() - 1; i++) {
                    long groundTime = segments.get(i).getArrivalDate().until(segments.get(i + 1).getDepartureDate(), ChronoUnit.HOURS);
                    totalGroundTime += Math.max(0, groundTime);
                }

                if (totalGroundTime <= MAX_GROUND_TIME_HOURS) {
                    flightSet.add(flight);
                }
            }
        }
        return flightSet;
    }

    /**
     * Получить все рейсы исключая те, у которых дата прилёта раньше даты вылета.
     *
     * @param flights список для фильтрации рейсов.
     * @return Список рейсов, исключая те, у которых дата прилёта раньше даты вылета.
     */
    @Override
    public List<Flight> getFlightsWithValidArrivalDate(List<Flight> flights) {
        checkIfFlightsListIsNullOrEmpty(flights);

        return flights.stream()
                .filter(flight -> flight.getSegments().stream()
                        .noneMatch(segment -> segment.getArrivalDate().isBefore(segment.getDepartureDate())))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Получить все рейсы, которые ещё не вылетели (вылетают после текущего момента времени).
     *
     * @param flights список для фильтрации рейсов.
     * @return Список рейсов, которые вылетают после текущего момента времени.
     */
    @Override
    public ArrayList<Flight> getFlightsAfterCurrentTime(List<Flight> flights) {
        checkIfFlightsListIsNullOrEmpty(flights);

        return flights.stream()
                .filter(flight -> !flight.getSegments().get(0).getDepartureDate().isBefore(LocalDateTime.now()))
                .collect(Collectors.toCollection(ArrayList::new));

    }

    private static void checkIfFlightsListIsNullOrEmpty(List<Flight> flights) {
        if (flights == null || flights.isEmpty()) {
            throw new IllegalArgumentException("Список рейсов пустой или нулевой");
        }
    }
}
