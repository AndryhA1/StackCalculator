package com.suhorukov.dubovik.calc.commands;

import com.suhorukov.dubovik.calc.ArgType;
import com.suhorukov.dubovik.calc.ICommand;
import com.suhorukov.dubovik.calc.In;
import com.suhorukov.dubovik.calc.PreCondition;

import java.util.Map;
import java.util.Stack;

public class Define implements ICommand {

    @In(type = ArgType.CONTEXT)
    Map<String, Double> context;

    @Override
    @PreCondition(argSize = 3)
    public void execute(String[] args) {
        try {
            context.put(args[1], Double.valueOf(args[2]));
        } catch (NumberFormatException e) {
            e.printStackTrace();
//            System.out.println("Папаметр: " + args[2] + " неверный.");
        }
    }
}
