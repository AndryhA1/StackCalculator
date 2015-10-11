package com.suhorukov.dubovik.calc;
/**
 * Stack Calculator.
 */

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class Calculator {

    Stack<Double> stack = new Stack<>();
    Map<String, Double> context = new HashMap<>();
    ResourceSource resourceSource = type -> {
        switch (type) {
            case STACK:
                return stack;
            case CONTEXT:
                return context;
            default:
                return null;
        }
    };
    private String[] argsCommands;
    private String stringScanner;
    private Scanner scanner;

    void startCalc(String inFile) throws Exception {

        if (inFile != null) {
            Factory factory = new Factory(resourceSource);
            scanner = new Scanner(new File(inFile));
            while (scanner.hasNextLine()) {
                stringScanner = scanner.nextLine();
                argsCommands = stringScanner.split(" ");
                factory.getCmdName(argsCommands).execute(argsCommands);
            }
        } else {
            readConsole();
        }
    }

    private void readConsole() throws Exception {

        Factory factory = new Factory(resourceSource);

        boolean checkStart = true;
        scanner = new Scanner(System.in);

        while (checkStart) {
            System.out.println("Введите параметры (для выхода введите 'QUIT'):");
            stringScanner = scanner.nextLine();
            if ("QUIT".equals(stringScanner) || "quit".equals(stringScanner)) {
                checkStart = false;
            } else {
                argsCommands = stringScanner.split(" ");

                factory.getCmdName(argsCommands).execute(argsCommands);
            }
        }
    }
}
