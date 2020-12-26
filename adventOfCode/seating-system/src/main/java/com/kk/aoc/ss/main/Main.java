package com.kk.aoc.ss.main;

import com.kk.aoc.common.LineByLineReader;
import com.kk.aoc.common.utils.PrintUtils;
import com.kk.aoc.ss.model.Lobby;
import com.kk.aoc.ss.model.SeatState;
import com.kk.aoc.ss.model.VisibleSurroundingLobby;
import com.kk.aoc.ss.simulator.LobbyStateSimulator;
import com.kk.aoc.ss.simulator.SimulationResult;
import com.kk.aoc.ss.utils.SeatUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        LineByLineReader lineByLineReader = LineByLineReader.builder().separator("").inputFile(new File("src/main/resources/day11/input.txt")).build();
        lineByLineReader.open();

        List<String[]> lines = new ArrayList<>();
        while (lineByLineReader.hasNext()) {
            String[] tokens = lineByLineReader.next();
            if (tokens != null) {
                lines.add(tokens);
            }
        }
        int width = lines.get(0).length - 1;
        int height = lines.size();
        Lobby lobby = new VisibleSurroundingLobby(width, height);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                lobby.put(x, y, SeatState.fromValue(lines.get(y)[x]));
            }
        }
        LobbyStateSimulator stateSimulator = new LobbyStateSimulator(5);
        SimulationResult<Lobby> simulationResult = new SimulationResult<>(lobby, 1);
        while (simulationResult.getChangesCount() > 0) {
            System.err.println(PrintUtils.toString(simulationResult.getResult().getLobbyMatrix(), height, width));
            System.err.println("_____________________________________________");
            simulationResult = stateSimulator.simulateNextRound(simulationResult.getResult());
        }
        System.err.println("No changes detected. Taken seats: " + SeatUtils.calculateNumberOf(simulationResult.getResult().getLobbyMatrix(), SeatState.OCCUPIED));
    }
}
