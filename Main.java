package project;


import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

interface ILogicExpression {
    Boolean isCorrectInput(String inputString);

    String translateToPostfix(String inputString);

}



/*
 * Реализовать поддержку различных скобок {}, [] для повышения читаемости. (реализоваться будет посредством проверки инпута)
 *
 *
 *
 *_________________________________________________________________________________________________________________________________________
 *_________________________________________________________________________________________________________________________________________
 *

 * Программа должна будет поддерживать возможность изменения приоритетов операций.(Реализовано в методе GetPriority в LogicFunctions
 *
 *_________________________________________________________________________________________________________________________________________
 *_________________________________________________________________________________________________________________________________________
 * Логика класса такова: экземпляр класса имеет несколько открытых методов: проверить корректность лог. выражения, преобразовать его в  польскую запись,
 *  построить таблицу истинности (), каждый метод последовательно вызывает предыдущие, если они есть.
 *
 */


class LogicExpression implements ILogicExpression {
    private HashSet<Character> operationsWithInversion = new HashSet<Character>(Arrays.asList('¬', '∧', '|', '∨', '↓', '⊕', '↔', '→', '←'));

    private HashSet<Character> operations = new HashSet<Character>(Arrays.asList('∧', '|', '∨', '↓', '⊕', '↔', '→', '←'));

    private HashSet<Character> symbols = new HashSet<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1'));
    private HashSet<Character> symbolsWithoutOneZero = new HashSet<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'));

    @Override
    public Boolean isCorrectInput(String logicExpression) {
        Stack <Character> bracketBalanceStack=new Stack<>();

        logicExpression = logicExpression.replace(" ", "");

        logicExpression = "=" + logicExpression + "=";

        int i = 1;
        while (logicExpression.charAt(i) != '=') {

            if (logicExpression.charAt(i) == '¬') {
                if ((logicExpression.charAt(i - 1) == '(' || logicExpression.charAt(i - 1) == '{' ||
                        logicExpression.charAt(i - 1) == '[' || logicExpression.charAt(i - 1) == '=') &&
                        (logicExpression.charAt(i + 1) == '(' ||logicExpression.charAt(i + 1) == '{' ||
                                logicExpression.charAt(i + 1) == '['|| symbols.contains(logicExpression.charAt(i + 1)))) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (symbols.contains(logicExpression.charAt(i))) {
                if ((logicExpression.charAt(i - 1) == '(' || logicExpression.charAt(i - 1) == '{' ||
                        logicExpression.charAt(i - 1) == '['|| logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (operations.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == ')' || logicExpression.charAt(i + 1) == '}' ||
                                logicExpression.charAt(i + 1) == ']' ||logicExpression.charAt(i + 1) == '=')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (operations.contains(logicExpression.charAt(i))) {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')' || logicExpression.charAt(i - 1) == '}' ||
                        logicExpression.charAt(i - 1) == ']') &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '('||
                                logicExpression.charAt(i + 1) == '{'|| logicExpression.charAt(i + 1) == '[')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == '(') {
                if ((logicExpression.charAt(i - 1) == '(' ||logicExpression.charAt(i - 1) == '[' ||logicExpression.charAt(i - 1) == '{'
                        || logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬'
                                || logicExpression.charAt(i + 1) == '('|| logicExpression.charAt(i + 1) == '['|| logicExpression.charAt(i + 1) == '{')) {
                    bracketBalanceStack.push('(');
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == ')') {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')'||
                        logicExpression.charAt(i - 1) == '}'|| logicExpression.charAt(i - 1) == ']') &&
                        (logicExpression.charAt(i + 1) == '=' || logicExpression.charAt(i + 1) == ')'
                                || logicExpression.charAt(i + 1) == '}'|| logicExpression.charAt(i + 1) == ']'|| operations.contains(logicExpression.charAt(i + 1)))) {
                    if (bracketBalanceStack.peek()=='(')
                        bracketBalanceStack.pop();
                        else
                            bracketBalanceStack.push(')');
                    i++;
                    continue;
                } else
                    return false;
            }
            else if (logicExpression.charAt(i) == '{') {
                if ((logicExpression.charAt(i - 1) == '(' ||logicExpression.charAt(i - 1) == '[' ||logicExpression.charAt(i - 1) == '{'
                        || logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬'
                                || logicExpression.charAt(i + 1) == '('|| logicExpression.charAt(i + 1) == '['|| logicExpression.charAt(i + 1) == '{')) {
                    bracketBalanceStack.push('{');
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == '}') {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')'||
                        logicExpression.charAt(i - 1) == '}'|| logicExpression.charAt(i - 1) == ']') &&
                        (logicExpression.charAt(i + 1) == '=' || logicExpression.charAt(i + 1) == ')'
                                || logicExpression.charAt(i + 1) == '}'|| logicExpression.charAt(i + 1) == ']'|| operations.contains(logicExpression.charAt(i + 1)))) {
                    if (bracketBalanceStack.peek()=='{')
                        bracketBalanceStack.pop();
                    else
                        bracketBalanceStack.push('}');
                    i++;
                    continue;
                } else
                    return false;
            }
            else if (logicExpression.charAt(i) == '[') {
                if ((logicExpression.charAt(i - 1) == '(' ||logicExpression.charAt(i - 1) == '[' ||logicExpression.charAt(i - 1) == '{'
                        || logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬'
                                || logicExpression.charAt(i + 1) == '('|| logicExpression.charAt(i + 1) == '['|| logicExpression.charAt(i + 1) == '{')) {
                    bracketBalanceStack.push('[');
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == ']') {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')'||
                        logicExpression.charAt(i - 1) == '}'|| logicExpression.charAt(i - 1) == ']') &&
                        (logicExpression.charAt(i + 1) == '=' || logicExpression.charAt(i + 1) == ')'
                                || logicExpression.charAt(i + 1) == '}'|| logicExpression.charAt(i + 1) == ']'|| operations.contains(logicExpression.charAt(i + 1)))) {
                    if (bracketBalanceStack.peek()=='[')
                        bracketBalanceStack.pop();
                    else
                        bracketBalanceStack.push(']');
                    i++;
                    continue;
                } else
                    return false;
            }
            else
                return false;
        }

        if (bracketBalanceStack.isEmpty())
            return true;
        else
            return false;

    }

    //Упрощенный ввод позволяет использовать инверсию сразу после знака, не заключая инверсионное выражение в скобки. Т.е. Например a∧b∧(¬c)∧¬(¬a∧(¬d))=a∧b∧¬c∧¬(¬a∧¬d)
    //Таким образом читаемость сложных выражений повышается в разы
    public Boolean isCorrectSimplifiedInput(String logicExpression) {
        Stack <Character> bracketBalanceStack=new Stack<>();

        logicExpression = logicExpression.replace(" ", "");

        logicExpression = "=" + logicExpression + "=";

        int i = 1;
        while (logicExpression.charAt(i) != '=') {

            if (logicExpression.charAt(i) == '¬') {
                if ((logicExpression.charAt(i - 1) == '(' || logicExpression.charAt(i - 1) == '{' ||
                        logicExpression.charAt(i - 1) == '[' || logicExpression.charAt(i - 1) == '=' || operations.contains(logicExpression.charAt(i - 1))) &&
                        (logicExpression.charAt(i + 1) == '(' ||logicExpression.charAt(i + 1) == '{' ||
                                logicExpression.charAt(i + 1) == '['|| symbols.contains(logicExpression.charAt(i + 1)))) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (symbols.contains(logicExpression.charAt(i))) {
                if ((logicExpression.charAt(i - 1) == '(' || logicExpression.charAt(i - 1) == '{' ||
                        logicExpression.charAt(i - 1) == '['|| logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (operations.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == ')' || logicExpression.charAt(i + 1) == '}' ||
                                logicExpression.charAt(i + 1) == ']' ||logicExpression.charAt(i + 1) == '=')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (operations.contains(logicExpression.charAt(i))) {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')' || logicExpression.charAt(i - 1) == '}' ||
                        logicExpression.charAt(i - 1) == ']') &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬' ||  logicExpression.charAt(i + 1) == '('||
                                logicExpression.charAt(i + 1) == '{'|| logicExpression.charAt(i + 1) == '[')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == '(') {
                if ((logicExpression.charAt(i - 1) == '(' ||logicExpression.charAt(i - 1) == '[' ||logicExpression.charAt(i - 1) == '{'
                        || logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬'
                                || logicExpression.charAt(i + 1) == '('|| logicExpression.charAt(i + 1) == '['|| logicExpression.charAt(i + 1) == '{')) {
                    bracketBalanceStack.push('(');
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == ')') {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')'||
                        logicExpression.charAt(i - 1) == '}'|| logicExpression.charAt(i - 1) == ']') &&
                        (logicExpression.charAt(i + 1) == '=' || logicExpression.charAt(i + 1) == ')'
                                || logicExpression.charAt(i + 1) == '}'|| logicExpression.charAt(i + 1) == ']'|| operations.contains(logicExpression.charAt(i + 1)))) {
                    if (bracketBalanceStack.peek()=='(')
                        bracketBalanceStack.pop();
                    else
                        bracketBalanceStack.push(')');
                    i++;
                    continue;
                } else
                    return false;
            }
            else if (logicExpression.charAt(i) == '{') {
                if ((logicExpression.charAt(i - 1) == '(' ||logicExpression.charAt(i - 1) == '[' ||logicExpression.charAt(i - 1) == '{'
                        || logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬'
                                || logicExpression.charAt(i + 1) == '('|| logicExpression.charAt(i + 1) == '['|| logicExpression.charAt(i + 1) == '{')) {
                    bracketBalanceStack.push('{');
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == '}') {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')'||
                        logicExpression.charAt(i - 1) == '}'|| logicExpression.charAt(i - 1) == ']') &&
                        (logicExpression.charAt(i + 1) == '=' || logicExpression.charAt(i + 1) == ')'
                                || logicExpression.charAt(i + 1) == '}'|| logicExpression.charAt(i + 1) == ']'|| operations.contains(logicExpression.charAt(i + 1)))) {
                    if (bracketBalanceStack.peek()=='{')
                        bracketBalanceStack.pop();
                    else
                        bracketBalanceStack.push('}');
                    i++;
                    continue;
                } else
                    return false;
            }
            else if (logicExpression.charAt(i) == '[') {
                if ((logicExpression.charAt(i - 1) == '(' ||logicExpression.charAt(i - 1) == '[' ||logicExpression.charAt(i - 1) == '{'
                        || logicExpression.charAt(i - 1) == '=' || operationsWithInversion.contains(logicExpression.charAt(i - 1))) &&
                        (symbols.contains(logicExpression.charAt(i + 1)) || logicExpression.charAt(i + 1) == '¬'
                                || logicExpression.charAt(i + 1) == '('|| logicExpression.charAt(i + 1) == '['|| logicExpression.charAt(i + 1) == '{')) {
                    bracketBalanceStack.push('[');
                    i++;
                    continue;
                } else
                    return false;
            } else if (logicExpression.charAt(i) == ']') {
                if ((symbols.contains(logicExpression.charAt(i - 1)) || logicExpression.charAt(i - 1) == ')'||
                        logicExpression.charAt(i - 1) == '}'|| logicExpression.charAt(i - 1) == ']') &&
                        (logicExpression.charAt(i + 1) == '=' || logicExpression.charAt(i + 1) == ')'
                                || logicExpression.charAt(i + 1) == '}'|| logicExpression.charAt(i + 1) == ']'|| operations.contains(logicExpression.charAt(i + 1)))) {
                    if (bracketBalanceStack.peek()=='[')
                        bracketBalanceStack.pop();
                    else
                        bracketBalanceStack.push(']');
                    i++;
                    continue;
                } else
                    return false;
            }
            else
                return false;
        }

        if (bracketBalanceStack.isEmpty())
            return true;
        else
            return false;
    }

    @Override
    public String translateToPostfix(String logicExpression) {
        if (isCorrectSimplifiedInput(logicExpression)) {

            String result = "";
            logicExpression = logicExpression.replace(" ", "");
            Stack<Character> operationsStack = new Stack<>();
            for (int i = 0; i < logicExpression.length(); i++) {
                if (symbols.contains(logicExpression.charAt(i))) {
                    result += logicExpression.charAt(i);
                } else if (logicExpression.charAt(i) == '(')
                    operationsStack.push(logicExpression.charAt(i));
                else if (logicExpression.charAt(i) == ')') {
                    Character ch = operationsStack.pop();
                    while (ch != '(') {
                        result += ch;
                        ch = operationsStack.pop();
                    }
                } else {
                    if (!(operationsStack.empty()) && (LogicFunctions.GetPriority(logicExpression.charAt(i))
                            <= LogicFunctions.GetPriority(operationsStack.peek()))) {
                        while ((!(operationsStack.empty()) && (LogicFunctions.GetPriority(logicExpression.charAt(i))
                                <= LogicFunctions.GetPriority(operationsStack.peek()))))
                            result += operationsStack.pop();
                    }
                    operationsStack.push(logicExpression.charAt(i));
                }
            }

            while (!operationsStack.empty())
                result += operationsStack.pop();

            return result;
        } else {
            System.out.println("Входное логическое выражение неверно!");
            return null;
        }
    }


    public Byte toSolveExpression(String logicExpression, HashMap<Character, Byte> values) {
        Byte result = 0; //Результат
        Stack<Byte> buffStack = new Stack<Byte>();

        for (int i = 0; i < logicExpression.length(); i++) //Для каждого символа в строке
        {
            //Если символ - переменная, то читаем её и записываем на вершину стека
            if (symbols.contains(logicExpression.charAt(i))) {
                buffStack.push(values.get(logicExpression.charAt(i)));
            } else if (operationsWithInversion.contains(logicExpression.charAt(i))) //Если символ - оператор
            {
                if (logicExpression.charAt(i) == '¬') {
                    Byte top = buffStack.pop();
                    result = LogicFunctions.Inversion(top);
                    buffStack.push(result);
                } else {
                    // если операция бинарная
                    //Берем два последних значения из стека
                    Byte secondOperand = buffStack.pop();
                    Byte firstOperand = buffStack.pop();

                    switch (logicExpression.charAt(i)) {
                        case '↓':
                            result = LogicFunctions.PierceArrow(firstOperand, secondOperand);
                            break;
                        case '|':
                            result = LogicFunctions.ShefferStroke(firstOperand, secondOperand);
                            break;
                        case '↔':
                            result = LogicFunctions.Equivalention(firstOperand, secondOperand);
                            break;
                        case '→':
                            result = LogicFunctions.Implication(firstOperand, secondOperand);
                            break;
                        case '←':
                            result = LogicFunctions.ReversedImplication(firstOperand, secondOperand);
                            break;
                        case '⊕':
                            result = LogicFunctions.AddMod2(firstOperand, secondOperand);
                            break;
                        case '∨':
                            result = LogicFunctions.Disjunction(firstOperand, secondOperand);
                            break;
                        case '∧':
                            result = LogicFunctions.Conjunction(firstOperand, secondOperand);
                            break;

                    }
                    buffStack.push(result); //Результат вычисления записываем обратно в стек
                }
            }

        }
        if (!buffStack.isEmpty()) // для случая входной строки из одной переменной без операций
            result = buffStack.pop();

        return result;
    }


    public ArrayList<Byte> takeTruthTable(String logicExpression) {
          ArrayList<Byte> result = new ArrayList<>();

        HashSet<Character> variables = takeVariables(logicExpression);
        String postfixLogicExpression = translateToPostfix(logicExpression);
        HashMap<Character, Byte> values;
        if (!variables.isEmpty()) {
            Character[] variables_array = variables.toArray(new Character[variables.size()]);




            int i = 0;
            while (i != Math.pow(2, variables_array.length)) {
                String zeroOneSelection;
				
				 for (int v=0; v < variables_array.length - Integer.toBinaryString(i).length() ;v++)
                    zeroOneSelection+="0";
                zeroOneSelection+=Integer.toBinaryString(i);
				
                String[] selectionArray = zeroOneSelection.split("");


                values = new HashMap<>();

                for (int j = 0; j < variables_array.length; j++) {
                    values.put(variables_array[j], Byte.parseByte(selectionArray[j]));
                }
                values.put('0', (byte) 0);
                values.put('1', (byte) 1);
                result.add(toSolveExpression(postfixLogicExpression, values));

                i++;
            }
        } else {
            values = new HashMap<>();
            values.put('0', (byte) 0);
            values.put('1', (byte) 1);
            result.add(toSolveExpression(postfixLogicExpression, values));
        }

        return result;

    }
           
				
				
    private HashSet<Character> takeVariables(String logicExpression) {
        HashSet<Character> setOfVariables = new HashSet<>();
        for (int i = 0; i < logicExpression.length(); i++) {
            if (symbolsWithoutOneZero.contains(logicExpression.charAt(i)))
                setOfVariables.add(logicExpression.charAt(i));

        }
        return setOfVariables;
    }


    static private class LogicFunctions {

        static private byte Inversion(byte a) {
            if (a == 0) return 1;
            return 0;
        }

        static private byte ShefferStroke(byte a, byte b) {
            if ((a == 1) && (b == 1)) return 0;
            return 1;
        }

        static private byte PierceArrow(byte a, byte b) {
            if ((a == 0) && (b == 0)) return 1;
            return 0;
        }

        static private byte Equivalention(byte a, byte b) {
            if (((a == 0) && (b == 0)) ||
                    ((a == 1) && (b == 1))) return 1;
            return 0;
        }

        static private byte Implication(byte a, byte b) {
            if ((a == 1) && (b == 0)) return 0;
            return 1;
        }

        static private byte ReversedImplication(byte a, byte b) {
            if ((a == 0) && (b == 1)) return 0;
            return 1;
        }

        static private byte AddMod2(byte a, byte b) {
            if (((a == 0) && (b == 0)) ||
                    ((a == 1) && (b == 1))) return 0;
            return 1;
        }

        static private byte Disjunction(byte a, byte b) {
            if ((a == 0) && (b == 0)) return 0;
            return 1;
        }

        static private byte Conjunction(byte a, byte b) {
            if ((a == 1) && (b == 1)) return 1;
            return 0;
        }

        static private byte GetPriority(char ch) {
            switch (ch) {
                case '(':
                    return 0;
                case ')':
                    return 0;
                case '←':
                    return 1;
                case '→':
                    return 1;
                case '↔':
                    return 1;
                case '⊕':
                    return 1;
                case '↓':
                    return 2;
                case '∨':
                    return 2;
                case '|':
                    return 3;
                case '∧':
                    return 3;
                case '¬':
                    return 4;
                default:
                    return 0;
            }
        }

        static private byte GetPriority(char ch, byte ReversedImplicationPriority, byte ImplicationPriority, byte EquivalentionPriority, byte AddMod2Priority,
                                        byte PierceArrowPriority, byte DisjunctionPriority, byte ShefferStrokePriority, byte ConjunctionPriority, byte InversionPriority) {
            switch (ch) {
                case '(':
                    return 0;
                case ')':
                    return 0;
                case '←':
                    return ReversedImplicationPriority;
                case '→':
                    return ImplicationPriority;
                case '↔':
                    return EquivalentionPriority;
                case '⊕':
                    return AddMod2Priority;
                case '↓':
                    return PierceArrowPriority;
                case '∨':
                    return DisjunctionPriority;
                case '|':
                    return ShefferStrokePriority;
                case '∧':
                    return ConjunctionPriority;
                case '¬':
                    return InversionPriority;
                default:
                    return 0;
            }
        }

    }
}


public class Main {

    public static void main(String[] args) throws IOException {

        LogicExpression expression = new LogicExpression();


        expression.isCorrectInput("{{}({}{}{}[])()}");

        String inputLogicExpression = "a|b∨c∧¬d↔v→(a⊕m)";
        var elemsAr=expression.takeTruthTable(inputLogicExpression);
        Byte[] elems = elemsAr.toArray(new Byte[0]);
        System.out.println(elems[1]);
//

    }
}

