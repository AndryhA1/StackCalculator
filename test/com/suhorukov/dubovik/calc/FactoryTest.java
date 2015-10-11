package com.suhorukov.dubovik.calc;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class FactoryTest {

    Stack<Double> testStack = new Stack<>();
    Map<String, Double> testContext = new HashMap<>();
    ResourceSource testResourceSource = type -> {
        switch (type) {
            case STACK:
                return testStack;
            case CONTEXT:
                return testContext;
            default:
                return null;
        }
    };

    @Test
    public void testGetCmdName() throws Exception {

        String[] testPUSH = {"PUSH", "10"};
        Factory factory = new Factory(testResourceSource);

        factory.getCmdName(testPUSH).execute(testPUSH);
        assertEquals(Double.valueOf(testPUSH[1]), testStack.lastElement());

        String[] testLOG = {"LOG"};
        String[] testEXP = {"EXP"};
        String[] testPOP = {"POP"};

        factory.getCmdName(testLOG).execute(testLOG);
        assertEquals(Double.valueOf(2.302585092994046), testStack.lastElement());

        factory.getCmdName(testEXP).execute(testEXP);
        assertEquals(Double.valueOf(10.000000000000002), testStack.lastElement());

        factory.getCmdName(testPOP).execute(testPOP);
        assertEquals(0, testStack.size());
    }

    @Test
    public void x1() throws Exception {

        Factory x1Factory = new Factory(testResourceSource);

        String[][] commandsArray = {
                {"DEFINE", "a", "10"},
                {"DEFINE", "b", "30"},
                {"DEFINE", "c", "20"},
                {"PUSH", "2"},
                {"PUSH", "a"},
                {"*"},
                {"PRINT"},       ///
                {"PUSH", "b"},
                {"PUSH", "0"},
                {"-"},
                {"PRINT"},       ///
                {"PUSH", "4"},
                {"PUSH", "a"},
                {"*"},
                {"PUSH", "c"},
                {"*"},
                {"PRINT"},       ///
                {"PUSH", "b"},
                {"PUSH", "b"},
                {"*"},
                {"PRINT"},       ///
                {"-"},
                {"PRINT"},       ///
                {"SQRT"},
                {"PUSH", "0"},
                {"-"},
                {"PRINT"},       ///
                {"+"},
                {"PRINT"},       ///
                {"/"},
                {"PRINT"},
        };

        for (String[] aCommandsArray : commandsArray) {
            x1Factory.getCmdName(aCommandsArray).execute(aCommandsArray);
        }
        System.out.println();
        assertEquals(Double.valueOf(-2.0), testStack.lastElement());
    }

    @Test
    public void x2() throws Exception {

        Factory x1Factory = new Factory(testResourceSource);

        String[][] commandsArray = {
                {"DEFINE", "a", "10"},
                {"DEFINE", "b", "30"},
                {"DEFINE", "c", "20"},
                {"PUSH", "2"},
                {"PUSH", "a"},
                {"*"},
                {"PRINT"},       ///
                {"PUSH", "b"},
                {"PUSH", "0"},
                {"-"},
                {"PRINT"},       ///
                {"PUSH", "4"},
                {"PUSH", "a"},
                {"*"},
                {"PUSH", "c"},
                {"*"},
                {"PRINT"},       ///
                {"PUSH", "b"},
                {"PUSH", "b"},
                {"*"},
                {"PRINT"},       ///
                {"-"},
                {"PRINT"},       ///
                {"SQRT"},
                {"PUSH", "0"},
                {"-"},
                {"PRINT"},       ///
                {"-"},
                {"PRINT"},       ///
                {"/"},
                {"PRINT"},
        };

        for (String[] aCommandsArray : commandsArray) {
            x1Factory.getCmdName(aCommandsArray).execute(aCommandsArray);
        }
        assertEquals(Double.valueOf(1.0), testStack.lastElement());
    }
}