package com.suhorukov.dubovik.calc.commands;

import com.suhorukov.dubovik.calc.ArgType;
import com.suhorukov.dubovik.calc.ICommand;
import com.suhorukov.dubovik.calc.In;
import com.suhorukov.dubovik.calc.PreCondition;

import java.util.Map;
import java.util.Stack;


public class Push implements ICommand {

    @In(type = ArgType.STACK)
    private Stack<Double> stack;

    @In(type = ArgType.CONTEXT)
    private Map<String, Double> context;

    @Override
    @PreCondition(argSize = 2)
    public void execute(String[] args) {

        if (context.containsKey(args[1])) {         // Ищем переменную.
            stack.push(context.get(args[1]));       // Если нашли добавляем в стек.
        } else {
            try {
                stack.push(Double.valueOf(args[1]));    // Если не нашли, конвертируем значение и добавляем в стек.
            } catch (NumberFormatException e) {
                e.printStackTrace();
//                System.out.println("Параметр: " + args[1] + " не найден."); // На случай если вдруг буква, а не цифра
            }
        }
    }
}
