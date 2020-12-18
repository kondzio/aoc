package com.kk.aoc.halting.program;

import com.kk.aoc.halting.log.Logger;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

@RequiredArgsConstructor
public class Program {
    private final Deque<ExecutionStep> executionStack = new ArrayDeque<>();
    private final Operation[] operationVector;

    public void execute(ProgramContext context) {
        try {
            execute(context, new LoopDetector(1), executionStack);
        } catch (LoopDetectException lde) {
            ExecutionStep breakPoint;
            while ((breakPoint = executionStack.pop()) != null) {
                breakPoint.getOperation().handleLoop(breakPoint.getContext());
                try {
                    execute(breakPoint.context, new LoopDetector(1), new ArrayDeque<>());
                    System.err.println("found: " + breakPoint.context);
                    break;
                } catch (LoopDetectException lde2Try) {
                    //noop
                }
            }
        }
    }

    private void execute(ProgramContext context, LoopDetector loopDetector, Deque<ExecutionStep> executionStack) {
        while (context.getCurrentPosition() < operationVector.length) {
            Operation operation = operationVector[context.getCurrentPosition()];
            loopDetector.detectLoop(operation, context);
            Logger.log(context.getCurrentPosition(), operation, context);
            ProgramContext prevContext = context.toBuilder().executedOperations(new HashSet<>(context.getExecutedOperations())).build();
            operation.execute(context);
            context.registerExecutedOperation(operation);
            if (OperationType.NOP == operation.getType() || OperationType.JMP == operation.getType()) {
                executionStack.push(ExecutionStep.builder().context(prevContext).operation(operation).build());
            }
        }
    }

    @Builder
    @Getter
    static class ExecutionStep {
        private ProgramContext context;
        private Operation operation;
    }
}
