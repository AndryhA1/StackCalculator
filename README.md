# StackCalculator

1)
Написать стэковый калькулятор, который принимает в качестве аргумента командой строки имя
файла содержащего команды. Если нет аргумента то использовать стандартный ввод для чтения
команд. Использовать вещественные числа.
Реализовать следующий набор команд:

 • # - строка комментарий
 • POP, PUSH — работа со стэком
 • Арифметические операции + , - , * , /, sqrt. Используют один или два верхних элемента
стека, изымают их из стека, помещая результат назад
 • PRINT — печать верхнего элемента стека (без удаления из стека)
 • DEFINE — задать значение параметра. В дальнейшем везде использовать вместо
параметра это значение. Например:
DEFINE a 4
PUSH a
SQRT
PRINT
Должно вывести 2
Написать Unit test который будет исполнять тестовую программу для решения квадратного
уравнения с помощью данного калькулятора по формуле:
X1 = (-b + sqrt(b*b – 4ac)) / 2a
X2 = (-b - sqrt(b*b – 4ac)) / 2a

2)
Создать класс фактори для создания команд. То есть создать отельный класс который будет
иметь с аргументом имя команды и возвращать реализацию интерфейса Command для
данной команды. Реализовать следующие возможности:
1. Конфигурация списка команд через property файл factory класса. Это позволит
добавлять команды без перекомпиляции остального проекта. Добавить новые
арифметические команды: EXP, LOG
2. Реализовать Dependency injection для команд калькулятора: добавить аннотацию
@In(arg={STACK, CONTEXT}) для полей классов реализующих команды
калькулятора. При создание команды в фактори классе проверять наличие подобных
аннотаций и устанавливать данные поля до первого вызова команды. Реализовать
STACK, CONTEXT как перечисление (Enum)
3. Дополнительно
Добавить аннотацию для описания требований к состоянию программы перед
исполнением команды. Например в классе команде AddCommand иметь возможность
аннотировать метод execute следующим образом:
…...
@PreCondition(stackSize=2, argsSize=0)
public void execute …....
….........
Таким образом можно сосредоточить основные проверки верности аргументов и
состояния стека в одном месте, а не повторять их в разных командах. Для реализации
необходимо использовать Proxy.
