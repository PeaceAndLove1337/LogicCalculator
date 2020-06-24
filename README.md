# Logic-Calculator
Данный репозиторий создан с целью  согласования и корректирования правок в моем проекте. 
Суть данной программы в том, чтобы по заданному логическому выражению построить таблицу истинности. 
В данном репозитории имеется как код класса и его методов, которые выполняют основные манипуляции, 
так и тесты к основным методам класса.

Главный класс - LogicExpression наследуется от интерфейса ILogicExpression
Данный класс содержит в себе следующие методы:

1)Boolean isCorrectInput(String logicExpression) - проверяет, является ли входная строка верным, правильно заданным лог. выражением.

2)Boolean isCorrectSimplifiedInput(String logicExpression)- проверяет то же самое, однако лишь с тем условием, что инверсия может стоять сразу после бинарной операции,
    в предыдущем методе этого не допускалось. Пример:a∧b∧(¬c)∧¬(¬a∧(¬d))=a∧b∧¬c∧¬(¬a∧¬d)
    
3)String translateToPostfix(String logicExpression) - переводит заданное логическое выражение в постфиксную запись для дальнейшего вычисления.

4)Byte toSolveExpression(String logicExpression, HashMap<Character, Byte> values) - метод, вычисляющий результат логического выражения по заданному словарю. 
Словарь :"переменная"- "ее значение, 0 или 1".

5)Byte[] takeTruthTable(String logicExpression)-метод, возвращающий для данного логического выражению массив байт, являющийся таблицей истинности.
Набор строится следующим образом: все переменные выражения сортируются по алфавитному порядку и данному набору последовательно прибавляется единица. 

Пример: Выражение AvB∧C имеет следующую таблицу истинности 
A B C РЕЗУЛЬТАТ:
0 0 0   0
0 0 1   0
0 1 0   0
0 1 1   1
1 0 0   1
1 0 1   1   
1 1 0   1
1 1 1   1

Так же главный класс содержит в себе статический класс LogicFunctions для представления и манипуляций с логическими функциями.

Данный проект будет реализован с интерфейсом пользователя на ПК, а затем и на Android.