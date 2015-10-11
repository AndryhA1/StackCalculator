package com.suhorukov.dubovik.calc;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Читать команды с properties, реализовать метод getCmdName
 * In(arg=(S,C))
 * Factory-inject
 * PreCondition+Proxy
 */
public class Factory {

    private static final String FILE_NAME = "commands.properties";
    private static final ICommand NOT_FOUND_COMMAND = args -> System.out.println("Команда не найдена!");
    Map<String, ICommand> commandsMap = new HashMap<>();


    Factory(ResourceSource resourceSource) throws Exception {

        Properties properties = new Properties();

        try (InputStream input = Calculator.class.getResourceAsStream(FILE_NAME)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Не удаётся прочитать файл!");
        }

        for (String commandName : properties.stringPropertyNames()) {
            try {
                Class cla = Class.forName(properties.getProperty(commandName));
                commandsMap.put(commandName, (ICommand) cla.newInstance());
            } catch (Exception e) {
                System.out.println("Не удалось найти команду: " + commandName);  // т.е. класс
            }
        }

        for (Map.Entry<String, ICommand> entry : commandsMap.entrySet()) {

            ICommand command = entry.getValue();
            Class<? extends ICommand> commandClass = entry.getValue().getClass();
            Method execute = commandClass.getMethod("execute", String[].class);
            PreCondition preCondition = execute.getDeclaredAnnotation(PreCondition.class);

            ICommand commandProxy = (ICommand) Proxy.newProxyInstance(commandClass.getClassLoader(),
                    commandClass.getInterfaces(),
                    new PreConditionHandler(entry.getValue(), preCondition, resourceSource));
            commandsMap.put(entry.getKey(), commandProxy);

            for (Class tmpCls = commandClass; !Object.class.equals(tmpCls); ) {
                for (Field f : commandClass.getDeclaredFields()) {
                    In in = f.getDeclaredAnnotation(In.class);
                    if (in != null) {
                        f.setAccessible(true);
                        ArgType type = in.type();
                        f.set(command, resourceSource.getResource(type));
                    }
                }
                tmpCls = tmpCls.getSuperclass();
            }
        }
    }

    ICommand getCmdName(String[] args) throws Exception {

        return commandsMap.getOrDefault(args[0].toUpperCase(), NOT_FOUND_COMMAND);
    }
}
