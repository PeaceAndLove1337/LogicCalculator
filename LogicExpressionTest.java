package project;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class LogicExpressionTest {
    LogicExpression postfix = new LogicExpression();

    @Test
    public void isCorrectInputTesting() {
        String[] massGood = new String[]{"a∧  (¬b  ∧c  )↓d", "a", "a⊕a⊕a⊕b⊕c⊕d⊕k", "1↓ 1", "¬ 0", "¬0↓d↓A", "¬0↓d⊕(v↓(A))", "(¬0)↓d⊕(s↓(A))", "((¬0)↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))", "((  (¬0) ↓ (d) ⊕ (a↓(A))  ))", "((((((a))))))", "¬(¬(¬(¬(¬(¬(¬a))))))", "¬((¬((¬(¬a↓(¬d)⊕b)))))", "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))", "a|b∨c∧(¬d)↔v→(a⊕m)",

                "¬( ¬(A∨( ¬(B⊕ (¬C))))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧(¬B)∨(¬( ¬( ¬((¬C)→D)↓(¬(E∧((¬F)))))←(¬(¬G∧H↔(¬(I∧(¬K))))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨(¬E) )))→(¬(¬(F↔(¬(¬G←H⊕I)))→(¬K)∧L)    )   )"};


        //Testing right strings
        for (int i = 0; i < massGood.length; i++) {
            Assert.assertEquals(true, postfix.isCorrectInput(massGood[i]));
        }


    }

    @Test
    public void isCorrectSimplifiedInputTesting() {
        String[] massGood = new String[]{"a∧  (¬b  ∧c  )↓d", "a", "a⊕a⊕a⊕b⊕c⊕d⊕k", "1↓ 1", "¬ 0", "¬0↓d↓A", "¬0↓d⊕(v↓(A))", "¬0↓d⊕(s↓(A))", "(¬0↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))", "((  ¬0 ↓ (d) ⊕ (a↓(A))  ))", "((((((a))))))", "¬(¬(¬(¬(¬(¬(¬a))))))", "¬((¬((¬(¬a↓¬d⊕b)))))", "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))", "a|b∨c∧¬d↔v→(a⊕m)", "¬a⊕¬a⊕¬a⊕¬b⊕¬c⊕¬d⊕¬k",

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
        String[] mass = new String[]{"a∧  (¬b  ∧c  )↓d", "a", "a⊕a⊕a⊕b⊕c⊕d⊕k", "1↓ 1", "¬ 0", "¬0↓d↓A", "¬0↓d⊕(v↓(A))", "(¬0)↓d⊕(s↓(A))", "((¬0)↓d⊕(q↓(A)))",
                "(((¬0)↓z⊕(z⊕d⊕(a↓(A)))))", "((  (¬0) ↓ (d) ⊕ (a↓(A))  ))", "((((((a))))))", "¬(¬(¬(¬(¬(¬(¬a))))))", "¬((¬((¬(¬a↓(¬d)⊕b)))))", "a←b←c←d←z←(k←(s←s))",
                "a←b←(c←(d))←z←(k←(s←s))", "a|b∨c∧(¬d)↔v→(a⊕m)",

                "¬( ¬(A∨( ¬(B⊕ (¬C))))←(¬( ¬(¬D∧E)↓F↔(¬(¬G→H)) )))",
                "¬(¬A∧(¬B)∨(¬( ¬( ¬((¬C)→D)↓(¬(E∧((¬F)))))←(¬(¬G∧H↔(¬(I∧(¬K))))) )) )",
                "¬( ¬(A←(¬(¬(B∧(¬(¬C∧D)))∨(¬E) )))→(¬(¬(F↔(¬(¬G←H⊕I)))→(¬K)∧L)    )   )"};

        String[] massPostfix = new String[]{"ab¬c∧∧d↓", "a", "aa⊕a⊕b⊕c⊕d⊕k⊕", "11↓", "0¬", "0¬d↓A↓", "0¬d↓vA↓⊕", "0¬d↓vA↓⊕", "0¬d↓vA↓⊕",
                "0¬z↓zd⊕aA↓⊕⊕", "0¬d↓aA↓⊕", "a", "a¬¬¬¬¬¬¬", "a¬d¬↓b⊕¬¬¬", "ab←c←d←z←kss←←←",
                "ab←c←d←z←kss←←←", "ab|cd¬∧∨v↔am⊕→",

                "ABC¬⊕¬∨¬D¬E∧¬F↓G¬H→¬↔¬←¬",
                "A¬B¬∧C¬D→¬EF¬∧¬↓¬G¬H∧IK¬∧¬↔¬←¬∨¬",
                "ABC¬D∧¬∧¬E¬∨¬←¬FG¬H←I⊕¬↔¬K¬L∧→¬→¬"};
        for (int i = 0; i < mass.length; i++) {
            //Assert.assertEquals(massPostfix[i], postfix.translateToPostfix(mass[i]));
            System.out.println(postfix.translateToPostfix(mass[i]));
        }

    }

    @Test
    public void toSolveExpressionTesting () {
        /* TODO :20.06.2020 */
    }
}