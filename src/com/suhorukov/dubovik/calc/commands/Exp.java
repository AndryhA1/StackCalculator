package com.suhorukov.dubovik.calc.commands;

import com.suhorukov.dubovik.calc.ArgType;
import com.suhorukov.dubovik.calc.ICommand;
import com.suhorukov.dubovik.calc.In;
import com.suhorukov.dubovik.calc.PreCondition;

import java.util.Map;
import java.util.Stack;

public class Exp implements ICommand {

    @In(type = ArgType.STACK)
    Stack<Double> stack;

    @Override
    @PreCondition(minStackSize = 1)
    public void execute(String[] args) {

        stack.push(Math.exp(stack.pop()));
    }
}
