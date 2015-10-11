package com.suhorukov.dubovik.calc.commands;

import com.suhorukov.dubovik.calc.ICommand;

import com.suhorukov.dubovik.calc.*;
import java.util.Stack;

public class Add implements ICommand {

    @In(type = ArgType.STACK)
    Stack<Double> stack;

    @Override
    @PreCondition(minStackSize = 2)
    public void execute(String[] args) {

            stack.push(stack.pop() + stack.pop());
    }
}
