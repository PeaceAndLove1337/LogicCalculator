package project;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class LogicExpressionTest {
    LogicExpression postfix = new LogicExpression();

    @Test
    public void isCorrectInputTesting() {
        String[] massGood = new String[]{"a∧  (¬b  ∧c  )↓d",
                "a", "a⊕a⊕a⊕b⊕c⊕d⊕k",
                "1↓ 1",
                "¬ 0",
                "¬0↓d↓A",
                "¬0↓d⊕(v↓(A))",
                "(¬0)↓d⊕(s↓(A))",
                "((¬0)↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))",
                "((  (¬0) ↓ (d) ⊕ (a↓(A))  ))",
                "((((((a))))))",
                "¬(¬(¬(¬(¬(¬(¬a))))))",
                "¬((¬((¬(¬a↓(¬d)⊕b)))))",
                "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))",
                "a|b∨c∧(¬d)↔v→(a⊕m)",

                "¬( ¬(A∨( ¬(B⊕ (¬C))))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧(¬B)∨(¬( ¬( ¬((¬C)→D)↓(¬(E∧((¬F)))))←(¬(¬G∧H↔(¬(I∧(¬K))))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨(¬E) )))→(¬(¬(F↔(¬(¬G←H⊕I)))→(¬K)∧L)    )   )"
        };


        //Testing right strings
        for (int i = 0; i < massGood.length; i++) {
            Assert.assertEquals(true, postfix.isCorrectInput(massGood[i]));
        }


    }

    @Test
    public void isCorrectSimplifiedInputTesting() {
        String[] massGood = new String[]{"a∧  (¬b  ∧c  )↓d",
                "a",
                "a⊕a⊕a⊕b⊕c⊕d⊕k",
                "1↓ 1",
                "¬ 0",
                "¬0↓d↓A",
                "¬0↓d⊕(v↓(A))",
                "¬0↓d⊕(s↓(A))",
                "(¬0↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))",
                "((  ¬0 ↓ (d) ⊕ (a↓(A))  ))",
                "((((((a))))))",
                "¬(¬(¬(¬(¬(¬(¬a))))))",
                "¬((¬((¬(¬a↓¬d⊕b)))))",
                "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))",
                "a|b∨c∧¬d↔v→(a⊕m)",
                "¬a⊕¬a⊕¬a⊕¬b⊕¬c⊕¬d⊕¬k",

                "¬( ¬(A∨( ¬(B⊕ ¬C)))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧¬B∨(¬( ¬( ¬(¬C→D)↓(¬(E∧¬F)))←(¬(¬G∧H↔(¬(I∧¬K)))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨¬E )))→(¬(¬(F↔(¬(¬G←H⊕I)))→¬K∧L)    )   )"};


        //Testing right strings
        for (int i = 0; i < massGood.length; i++) {
            Assert.assertEquals(true, postfix.isCorrectSimplifiedInput(massGood[i]));
        }

    }

    @Test
    public void translateToPostfixTesting() {
        String[] mass = new String[]{"a∧  (¬b  ∧c  )↓d",
                "a",
                "a⊕a⊕a⊕b⊕c⊕d⊕k",
                "1↓ 1",
                "¬ 0",
                "¬0↓d↓A",
                "¬0↓d⊕(v↓(A))",
                "¬0↓d⊕(s↓(A))",
                "(¬0↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))",
                "((  ¬0 ↓ (d) ⊕ (a↓(A))  ))",
                "((((((a))))))",
                "¬(¬(¬(¬(¬(¬(¬a))))))",
                "¬((¬((¬(¬a↓¬d⊕b)))))",
                "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))",
                "a|b∨c∧¬d↔v→(a⊕m)",
                "¬a⊕¬a⊕¬a⊕¬b⊕¬c⊕¬d⊕¬k",

                "¬( ¬(A∨( ¬(B⊕ ¬C)))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧¬B∨(¬( ¬( ¬(¬C→D)↓(¬(E∧¬F)))←(¬(¬G∧H↔(¬(I∧¬K)))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨¬E )))→(¬(¬(F↔(¬(¬G←H⊕I)))→¬K∧L)    )   )"};

        String[] massPostfix = new String[]{"ab¬c∧∧d↓",
                "a",
                "aa⊕a⊕b⊕c⊕d⊕k⊕",
                "11↓",
                "0¬",
                "0¬d↓A↓",
                "0¬d↓vA↓⊕",
                "0¬d↓sA↓⊕",
                "0¬d↓qA↓⊕",
                "0¬z↓zd⊕aA↓⊕⊕",
                "0¬d↓aA↓⊕",
                "a",
                "a¬¬¬¬¬¬¬",
                "a¬d¬↓b⊕¬¬¬",
                "ab←c←d←z←kss←←←",
                "ab←cd←←z←kss←←←",
                "ab|cd¬∧∨v↔am⊕→",
                "a¬a¬⊕a¬⊕b¬⊕c¬⊕d¬⊕k¬⊕",

                "ABC¬⊕¬∨¬D¬E∧¬F↓G¬H→¬↔¬←¬",
                "A¬B¬∧C¬D→¬EF¬∧¬↓¬G¬H∧IK¬∧¬↔¬←¬∨¬",
                "ABC¬D∧¬∧¬E¬∨¬←¬FG¬H←I⊕¬↔¬K¬L∧→¬→¬"};
        for (int i = 0; i < mass.length; i++) {
            Assert.assertEquals(massPostfix[i], postfix.translateToPostfix(mass[i]));
        }

    }

    @Test
    public void toSolveExpressionTesting() {
        String inputLogicExpression = "x|y∨z∧(¬a)↔b→(z⊕x) ";
        Byte[] zeroOneMass = new Byte[]{0, 1};

        Byte[] rightValues=new Byte[]{1,1,1,1,1,1,1,1,0,1,0,1,1,0,1,0,1,1,1,1,1,1,1,0,0,1,0,1,1,0,1,1};// Таблица истинности

        LogicExpression postfix = new LogicExpression();
        String logicExpressionInPostfix = postfix.translateToPostfix(inputLogicExpression);
        int i=0;
        for (Byte a : zeroOneMass) {
            for (Byte b : zeroOneMass) {
                for (Byte x : zeroOneMass) {
                    for (Byte y : zeroOneMass) {
                        for (Byte z : zeroOneMass) {
                            HashMap<Character, Byte> valuesOfVariables = new HashMap<>() {
                                {
                                    put('x', x);
                                    put('y', y);
                                    put('z', z);
                                    put('a', a);
                                    put('b', b);
                                }
                            };
                            Assert.assertEquals(rightValues[i],  postfix.toSolveExpression(logicExpressionInPostfix, valuesOfVariables));
                            i++;
                        }
                    }
                }
            }
        }





    }

    @Test
    public void takeTruthTableTesting(){
        LogicExpression expression = new LogicExpression();
        String[] logicExpressions = new String[]{"a∧  (¬b  ∧c  )↓d",
                "a",
                "a⊕a⊕a⊕b⊕c⊕d⊕k",
                "1↓ 1",
                "¬ 0",
                "¬0↓d↓A",
                "¬0↓d⊕(v↓(A))",
                "¬0↓d⊕(s↓(A))",
                "(¬0↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))",
                "((  ¬0 ↓ (d) ⊕ (a↓(A))  ))",
                "((((((a))))))",
                "¬(¬(¬(¬(¬(¬(¬a))))))",
                "¬((¬((¬(¬a↓¬d⊕b)))))",
                "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))",
                "a|b∨c∧¬d↔v→(a⊕m)",
                "¬a⊕¬a⊕¬a⊕¬b⊕¬c⊕¬d⊕¬k",

                "¬( ¬(A∨( ¬(B⊕ ¬C)))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))"};

        Byte[][] rightTables={{1,0,1,0,1,0,1,0,1,0,0,0,1,0,1,0},
                {0, 1},
                {0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1},
                {0},
                {1},
                {1, 1, 0, 0},
                {1, 0, 1, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0,},
                {1, 1, 0, 0, 0, 0, 0, 0,},
                {1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 0, 1, 1, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, },
                {0, 1},
                {1,0},
                {1, 1, 0, 0, 1, 0, 0, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1,
                        1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1,
                        1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                {1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1,
                        1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1},
                {1, 0, 0, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1,
                        0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0,
                        1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0,
                        1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1,
                        0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0,
                        0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0}};

        ArrayList<Byte> selection;
        for (int i = 0; i < logicExpressions.length; i++) {
            selection=new ArrayList<>();
            for (Byte elem:rightTables[i])
                selection.add(elem);
            Assert.assertEquals(selection,expression.takeTruthTable(logicExpressions[i]));
        }

        //Assert.assertEquals(Arrays.asList(b), expression.takeTruthTable(logicStr));
    }
}