package com.suhorukov.dubovik.calc;

public class Main {

    public static void main(String[] args) throws Exception {

        Calculator calculator = new Calculator();
        if (args.length > 0)
            calculator.startCalc(args[0]);
        else
            calculator.startCalc(null);
    }
}
