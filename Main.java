package project;


import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

interface ILogicExpression {
    Boolean isCorrectInput(String inputString);

    String translateToPostfix(String inputString);

    Byte toSolveExpression(String inputString);
}



/*
 * Реализовать поддержку различных скобок {}, [] для повышения читаемости. (реализоваться будет посредством проверки инпута)
 *
 *
 *
 * _________________________________________________________________________________________________________________________________________
 * Реализация таблиц истинности, возможно потребует использования бинарных операций.
 *_________________________________________________________________________________________________________________________________________
 *
 * Сделать тестовые примеры для таблиц истинности
 *
 *
 * Сравнить работоспособность на обоих способах инпута, сходятся ли они или нет.
 *
 *_________________________________________________________________________________________________________________________________________
 *  Проверить таблицы истинности для всех инпутов.
 *_________________________________________________________________________________________________________________________________________
 * Логика класса такова: экземпляр класса имеет несколько открытых методов: проверить корректность лог. выражения, преобразовать его в  польскую запись,
 *  построить таблицу истинности (), каждый метод последовательно вызывает предыдущие, если они есть.
 *
 */


class LogicExpression implements ILogicExpression {
    //String operations=" ¬   ∧ |     ∨ ↓   ⊕ ↔ → ←  ";//добавить множество и вместо строки заюзать множество
    HashSet<Character> operationsWithInversion = new HashSet<Character>(Arrays.asList('¬', '∧', '|', '∨', '↓', '⊕', '↔', '→', '←'));

    HashSet<Character> operations = new HashSet<Character>(Arrays.asList('∧', '|', '∨', '↓', '⊕', '↔', '→', '←'));

    HashSet<Character> symbols = new HashSet<Character>(Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
            'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1'));

    @Override
    public Boolean isCorrectInput(String inputString) {
        int bracketBalance = 0;
        //Boolean isRight=true;
        inputString = inputString.replace(" ", "");
        inputString = "=" + inputString + "=";
        //System.out.println(inputString);
        int i = 1;
        while (inputString.charAt(i) != '=') {

            if (inputString.charAt(i) == '¬') {
                if ((inputString.charAt(i - 1) == '(' || inputString.charAt(i - 1) == '=') && (inputString.charAt(i + 1) == '(' || symbols.contains(inputString.charAt(i + 1)))) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (symbols.contains(inputString.charAt(i))) {
                if ((inputString.charAt(i - 1) == '(' || inputString.charAt(i - 1) == '=' || operationsWithInversion.contains(inputString.charAt(i - 1))) &&
                        (operations.contains(inputString.charAt(i + 1)) || inputString.charAt(i + 1) == ')' || inputString.charAt(i + 1) == '=')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (operations.contains(inputString.charAt(i))) {
                if ((symbols.contains(inputString.charAt(i - 1)) || inputString.charAt(i - 1) == ')') &&
                        (symbols.contains(inputString.charAt(i + 1)) || inputString.charAt(i + 1) == '(')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (inputString.charAt(i) == '(') {
                if ((inputString.charAt(i - 1) == '(' || inputString.charAt(i - 1) == '=' || operationsWithInversion.contains(inputString.charAt(i - 1))) &&
                        (symbols.contains(inputString.charAt(i + 1)) || inputString.charAt(i + 1) == '¬' || inputString.charAt(i + 1) == '(')) {
                    bracketBalance++;
                    i++;
                    continue;
                } else
                    return false;
            } else if (inputString.charAt(i) == ')') {
                if ((symbols.contains(inputString.charAt(i - 1)) || inputString.charAt(i - 1) == ')') &&
                        (inputString.charAt(i + 1) == '=' || inputString.charAt(i + 1) == ')' || operations.contains(inputString.charAt(i + 1)))) {
                    bracketBalance--;
                    i++;
                    continue;
                } else
                    return false;
            } else
                return false;
        }

        if (bracketBalance == 0)
            return true;
        else
            return false;

    }

    //Упрощенный ввод позволяет использовать инверсию сразу после знака, не заключая инверсионное выражение в скобки. Т.е. Например a∧b∧(¬c)∧¬(¬a∧(¬d))=a∧b∧¬c∧¬(¬a∧¬d)
    //Таким образом читаемость сложных выражений повышается в разы
    public Boolean isCorrectSimplifiedInput(String inputString) {
        int bracketBalance = 0;
        //Boolean isRight=true;
        inputString = inputString.replace(" ", "");
        inputString = "=" + inputString + "=";

        int i = 1;
        while (inputString.charAt(i) != '=') {

            if (inputString.charAt(i) == '¬') {
                if ((inputString.charAt(i - 1) == '(' || inputString.charAt(i - 1) == '=' || operations.contains(inputString.charAt(i - 1))) && (inputString.charAt(i + 1) == '(' || symbols.contains(inputString.charAt(i + 1)))) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (symbols.contains(inputString.charAt(i))) {
                if ((inputString.charAt(i - 1) == '(' || inputString.charAt(i - 1) == '=' || operationsWithInversion.contains(inputString.charAt(i - 1))) &&
                        (operations.contains(inputString.charAt(i + 1)) || inputString.charAt(i + 1) == ')' || inputString.charAt(i + 1) == '=')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (operations.contains(inputString.charAt(i))) {
                if ((symbols.contains(inputString.charAt(i - 1)) || inputString.charAt(i - 1) == ')') &&
                        (symbols.contains(inputString.charAt(i + 1)) || inputString.charAt(i + 1) == '(' || inputString.charAt(i + 1) == '¬')) {
                    i++;
                    continue;
                } else
                    return false;
            } else if (inputString.charAt(i) == '(') {
                if ((inputString.charAt(i - 1) == '(' || inputString.charAt(i - 1) == '=' || operationsWithInversion.contains(inputString.charAt(i - 1))) &&
                        (symbols.contains(inputString.charAt(i + 1)) || inputString.charAt(i + 1) == '¬' || inputString.charAt(i + 1) == '(')) {
                    bracketBalance++;
                    i++;
                    continue;
                } else
                    return false;
            } else if (inputString.charAt(i) == ')') {
                if ((symbols.contains(inputString.charAt(i - 1)) || inputString.charAt(i - 1) == ')') &&
                        (inputString.charAt(i + 1) == '=' || inputString.charAt(i + 1) == ')' || operations.contains(inputString.charAt(i + 1)))) {
                    bracketBalance--;
                    i++;
                    continue;
                } else
                    return false;
            } else
                return false;
        }


        if (bracketBalance == 0)
            return true;
        else
            return false;

    }

    @Override
    public String translateToPostfix(String inputString) {
        if (isCorrectSimplifiedInput(inputString)) {

            String result = "";
            inputString = inputString.replace(" ", "");
            Stack<Character> operationsStack = new Stack<>();
            for (int i = 0; i < inputString.length(); i++) {
                if (symbols.contains(inputString.charAt(i))) {
                    result += inputString.charAt(i);
                } else if (inputString.charAt(i) == '(')
                    operationsStack.push(inputString.charAt(i));
                else if (inputString.charAt(i) == ')') {
                    Character ch = operationsStack.pop();
                    while (ch != '(') {
                        result += ch;
                        ch = operationsStack.pop();
                    }
                } else {
                    if (!(operationsStack.empty()) && (LogicFunctions.GetPriority(inputString.charAt(i))
                                <= LogicFunctions.GetPriority(operationsStack.peek()))) {
                        while((!(operationsStack.empty()) && (LogicFunctions.GetPriority(inputString.charAt(i))
                                <= LogicFunctions.GetPriority(operationsStack.peek()))))
                            result += operationsStack.pop();
                    }
                    operationsStack.push(inputString.charAt(i));
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

    @Override
    public Byte toSolveExpression(String inputString) {

        Byte result = 0; //Результат
        Stack<Byte> buffStack = new Stack<Byte>();
        for (Byte x=0;x<2;x++) {
            for (Byte y = 0; y < 2; y++) {
               for (Byte z = 0; z < 2; z++){

                    for (int i = 0; i < inputString.length(); i++) //Для каждого символа в строке
                    {
                        //Если символ - переменная, то читаем её и записываем на вершину стека
                        if ((inputString.charAt(i) == 'x') || (inputString.charAt(i) == 'y') || (inputString.charAt(i) == 'z')) {
                            switch (inputString.charAt(i)) {
                                case 'x':
                                    buffStack.push(x);
                                    break;
                                case 'y':
                                    buffStack.push(y);
                                    break;
                                case 'z':
                                    buffStack.push(z);
                                    break;
                            }
                        } else if (operationsWithInversion.contains(inputString.charAt(i))) //Если символ - оператор
                        {
                            if (inputString.charAt(i) == '¬') {
                                Byte top = buffStack.pop();
                                result = LogicFunctions.Inversion(top);
                                buffStack.push(result);
                            } else {
                                // если операция бинарная
                                //Берем два последних значения из стека
                                Byte secondOperand = buffStack.pop();
                                Byte firstOperand = buffStack.pop();

                                switch (inputString.charAt(i)) {
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

                    System.out.println(x+" "+y + " "+ z +"_"+ result );

                //Забираем результат всех вычислений из стека и возвращаем его
                }
            }

        }
        return result;
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

    }
}


public class Main {

    public static void main(String[] args) throws IOException {

        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); ¬' ,  '∧', '|',     '∨', '↓',   '⊕' ,'↔', '→', '←'
        //String inpString= reader.readLine();
        String inpString = "x|y∨z∧(¬x)↔y→(z⊕x) ";
                          //!(!x*(!y)+(!(!(!((!z)@x)#(!(y*((!z)))))@(!(!x*y=(!(z*(!x))))))))"
        LogicExpression postfix = new LogicExpression();
        String post = postfix.translateToPostfix(inpString);
        System.out.println(post);
        System.out.println(postfix.toSolveExpression(post));

    }
}
//ABC!D*!*!E!+!%!FG!HI#%!=!K!L*%!%! - последняя
/*    "x|y∨z∧(¬x)↔y→(z⊕x)                                   xy|zx¬∧∨y↔zx⊕→

 ((((x|y)∨(z∧(¬x)))↔y)→(z⊕x))                               xy|zx¬∧∨y↔zx⊕→
 String[] mass = new String[]{"a∧  (¬b  ∧c  )↓d", "a", "a⊕a⊕a⊕b⊕c⊕d⊕k", "1↓ 1", "¬ 0", "¬0↓d↓A", "¬0↓d⊕(v↓(A))", "(¬0)↓d⊕(s↓(A))", "((¬0)↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))", "((  (¬0) ↓ (d) ⊕ (a↓(A))  ))", "((((((a))))))", "¬(¬(¬(¬(¬(¬(¬a))))))", "¬((¬((¬(¬a↓(¬d)⊕b)))))", "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))", "a|b∨c∧(¬d)↔V→(a⊕m)","¬a⊕¬a⊕¬a⊕¬b⊕¬c⊕¬d⊕¬k",

                "¬( ¬(A∨( ¬(B⊕ (¬C))))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧(¬B)∨(¬( ¬( ¬((¬C)→D)↓(¬(E∧((¬F)))))←(¬(¬G∧H↔(¬(I∧(¬K))))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨(¬E) )))→(¬(¬(F↔(¬(¬G←H⊕I)))→(¬K)∧L)    )   )"};
        String[] massGood = new String[]{"a∧  (¬b  ∧c  )↓d", "a", "a⊕a⊕a⊕b⊕c⊕d⊕k", "1↓ 1", "¬ 0", "¬0↓d↓A", "¬0↓d⊕(v↓(A))", "¬0↓d⊕(s↓(A))", "(¬0↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))", "((  ¬0 ↓ (d) ⊕ (a↓(A))  ))", "((((((a))))))", "¬(¬(¬(¬(¬(¬(¬a))))))", "¬((¬((¬(¬a↓¬d⊕b)))))", "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))", "a|b∨c∧¬d↔V→(a⊕m)", "¬a⊕¬a⊕¬a⊕¬b⊕¬c⊕¬d⊕¬k",

                "¬( ¬(A∨( ¬(B⊕ ¬C)))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧¬B∨(¬( ¬( ¬(¬C→D)↓(¬(E∧¬F)))←(¬(¬G∧H↔(¬(I∧¬K)))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨¬E )))→(¬(¬(F↔(¬(¬G←H⊕I)))→¬K∧L)    )   )"};

        for (var i=0;i<mass.length;i++) {
            System.out.println(postfix.translateToPostfix(mass[i]));
            System.out.println(postfix.translateToPostfix(massGood[i]));
        }
 */