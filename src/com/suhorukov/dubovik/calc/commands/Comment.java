package com.suhorukov.dubovik.calc.commands;

import com.suhorukov.dubovik.calc.ICommand;
import com.suhorukov.dubovik.calc.PreCondition;

import java.util.Map;
import java.util.Stack;

public class Comment implements ICommand {

    @Override
    @PreCondition()
    public void execute(String[] args) {
        System.out.println("\tПеречень команд для ввода в консоль:");
        System.out.println("\t\"PUSH\" - добавить данные в стек (число).");
        System.out.println("\t\"POP\" - удалить последнию запись из стека.");
        System.out.println("\t\"PRINT\" - печать верхнего элемента стека (без удаления из стека).");
        System.out.println("\t\"DEFINE\" - задать значение параметра. В дальнейшем везде использовать вместо \n" +
                                        "\t\t\t\tпараметра это значение. Например: \"DEFINE a 4\"");
        System.out.println("\t\"SQRT\", \"LOG\", \"EXP\", \"+\", \"-\", \"*\", \"/\" - Арифметические операции. " +
                                                            "Используют один или два верхних элемента \n" +
                                                            "\t\t\t\t\t\t\t\t\t\t\t\tстека, изымают их из стека, " +
                                                            "помещая результат назад.");
        System.out.println("\t\"QUIT\" - для выхода.\n");

    }
}
