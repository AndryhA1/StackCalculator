package com.suhorukov.dubovik.calc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Stack;

public class PreConditionHandler implements InvocationHandler {

    private ICommand command;
    private PreCondition preCondition;
    private ResourceSource resourceSource;

    public PreConditionHandler(ICommand command, PreCondition preCondition, ResourceSource resourceSource) {
        this.command = command;
        this.resourceSource = resourceSource;
        this.preCondition = preCondition;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String[] arg = (String[]) args[0];
        if (preCondition.argSize() != arg.length) {
            System.out.printf("Размер аргумента: %s, необходимо: %s%n", arg.length, preCondition.argSize());
            return null;
        }

        Stack stack = (Stack) resourceSource.getResource(ArgType.STACK);
        if (stack.size() < preCondition.minStackSize()) {
            System.out.printf("Размер стека: %s, для этой операции необходимо: %s%n", stack.size(), preCondition.minStackSize());
            return null;
        }
        return method.invoke(command, args);
    }
}
