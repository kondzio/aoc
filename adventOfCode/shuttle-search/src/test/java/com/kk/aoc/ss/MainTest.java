package com.kk.aoc.ss;

import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.ss.model.DepartureDetails;
import com.kk.aoc.ss.model.IntervalTimeSchedule;
import com.kk.aoc.ss.model.TimeSchedule;
import com.kk.aoc.ss.model.TimeScheduleBrowser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class MainTest {
    private Input initialize(String path) throws FileNotFoundException {
        LineByLineReader lineByLineReader = LineByLineReader.builder().separator("\\,").inputFile(new File(path)).build();
        lineByLineReader.open();
        int arrival = Integer.parseInt(lineByLineReader.next(1).get(0)[0]);

        List<String[]> inputSchedules = lineByLineReader.next(1);
        List<TimeSchedule> busSchedules = new ArrayList<>();
        for (String schedule : inputSchedules.get(0)) {
            if (!"x".equals(schedule)) {
                busSchedules.add(new IntervalTimeSchedule(Integer.parseInt(schedule)));
            }
        }
        return new Input(arrival, busSchedules);
    }

    @Test
    public void sample1Test() throws FileNotFoundException {
        Input input = initialize("src/test/resources/input.txt");
        TimeScheduleBrowser browser = new TimeScheduleBrowser(input.getTimeSchedules());
        DepartureDetails departureDetails = browser.findNextBus(input.getArrival());
        Assertions.assertEquals(295, departureDetails.getBusNo() * (departureDetails.getDepartureTime() - input.getArrival()));
    }

    @Test
    public void party1Test() throws FileNotFoundException {
        Input input = initialize("D:\\poligon\\aoc\\adventOfCode\\src\\main\\resources\\day13\\input.txt");
        TimeScheduleBrowser browser = new TimeScheduleBrowser(input.getTimeSchedules());
        DepartureDetails departureDetails = browser.findNextBus(input.getArrival());
        Assertions.assertEquals(2215, departureDetails.getBusNo() * (departureDetails.getDepartureTime() - input.getArrival()));
    }

    @RequiredArgsConstructor
    @Getter
    private static class Input {
        private final int arrival;
        private final List<TimeSchedule> timeSchedules;
    }
}
