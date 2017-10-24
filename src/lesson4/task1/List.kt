@file:Suppress("UNUSED_PARAMETER")
package lesson4.task1

import lesson1.task1.discriminant
import java.lang.Math.pow
import java.lang.Math.sqrt

/**
 * Пример
 *
 * Найти все корни уравнения x^2 = y
 */
fun sqRoots(y: Double) =
        when {
            y < 0 -> listOf()
            y == 0.0 -> listOf(0.0)
            else -> {
                val root = Math.sqrt(y)
                // Результат!
                listOf(-root, root)
            }
        }

/**
 * Пример
 *
 * Найти все корни биквадратного уравнения ax^4 + bx^2 + c = 0.
 * Вернуть список корней (пустой, если корней нет)
 */
fun biRoots(a: Double, b: Double, c: Double): List<Double> {
    if (a == 0.0) {
        return if (b == 0.0) listOf()
        else sqRoots(-c / b)
    }
    val d = discriminant(a, b, c)
    if (d < 0.0) return listOf()
    if (d == 0.0) return sqRoots(-b / (2 * a))
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    return sqRoots(y1) + sqRoots(y2)
}

/**
 * Пример
 *
 * Выделить в список отрицательные элементы из заданного списка
 */
fun negativeList(list: List<Int>): List<Int> {
    val result = mutableListOf<Int>()
    for (element in list) {
        if (element < 0) {
            result.add(element)
        }
    }
    return result
}

/**
 * Пример
 *
 * Изменить знак для всех положительных элементов списка
 */
fun invertPositives(list: MutableList<Int>) {
    for (i in 0 until list.size) {
        val element = list[i]
        if (element > 0) {
            list[i] = -element
        }
    }
}

/**
 * Пример
 *
 * Из имеющегося списка целых чисел, сформировать список их квадратов
 */
fun squares(list: List<Int>) = list.map { it * it }

/**
 * Пример
 *
 * По заданной строке str определить, является ли она палиндромом.
 * В палиндроме первый символ должен быть равен последнему, второй предпоследнему и т.д.
 * Одни и те же буквы в разном регистре следует считать равными с точки зрения данной задачи.
 * Пробелы не следует принимать во внимание при сравнении символов, например, строка
 * "А роза упала на лапу Азора" является палиндромом.
 */
fun isPalindrome(str: String): Boolean {
    val lowerCase = str.toLowerCase().filter { it != ' ' }
    for (i in 0..lowerCase.length / 2) {
        if (lowerCase[i] != lowerCase[lowerCase.length - i - 1]) return false
    }
    return true
}

/**
 * Пример
 *
 * По имеющемуся списку целых чисел, например [3, 6, 5, 4, 9], построить строку с примером их суммирования:
 * 3 + 6 + 5 + 4 + 9 = 27 в данном случае.
 */
fun buildSumExample(list: List<Int>) = list.joinToString(separator = " + ", postfix = " = ${list.sum()}")

/**
 * Простая
 *
 * Найти модуль заданного вектора, представленного в виде списка v,
 * по формуле abs = sqrt(a1^2 + a2^2 + ... + aN^2).
 * Модуль пустого вектора считать равным 0.0.
 */
fun abs(v: List<Double>): Double {
    val sqr=mutableListOf<Double>()
    for (i in v) {
        sqr.add(i*i)
    }
    return sqrt(sqr.sum())
}


/**
 * Простая
 *
 * Рассчитать среднее арифметическое элементов списка list. Вернуть 0.0, если список пуст
 */
fun mean(list: List<Double>): Double =  when {
    list.isEmpty() -> 0.0
    else -> list.sum()/list.size
}

/**
 * Средняя
 *
 * Центрировать заданный список list, уменьшив каждый элемент на среднее арифметическое всех элементов.
 * Если список пуст, не делать ничего. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun center(list: MutableList<Double>): MutableList<Double> {
    if (list.size==0) return list
    val srd=mean(list)
    for ((index, element) in list.withIndex()) {
        list[index]=element-srd
    }
    return list
}

/**
 * Средняя
 *
 * Найти скалярное произведение двух векторов равной размерности,
 * представленные в виде списков a и b. Скалярное произведение считать по формуле:
 * C = a1b1 + a2b2 + ... + aNbN. Произведение пустых векторов считать равным 0.0.
 */
fun times(a: List<Double>, b: List<Double>): Double {
    var c=0.0
    for (i in 0 until a.size) c+=a[i]*b[i]
    return c
}

/**
 * Средняя
 *
 * Рассчитать значение многочлена при заданном x:
 * p(x) = p0 + p1*x + p2*x^2 + p3*x^3 + ... + pN*x^N.
 * Коэффициенты многочлена заданы списком p: (p0, p1, p2, p3, ..., pN).
 * Значение пустого многочлена равно 0.0 при любом x.
 */
fun polynom(p: List<Double>, x: Double): Double {
    if (p.isEmpty()) return 0.0
    var result=p.first()
    for (i in 1 until p.size) {
        result+=p[i]*pow(x, i*1.0)
    }
    return result
}

/**
 * Средняя
 *
 * В заданном списке list каждый элемент, кроме первого, заменить
 * суммой данного элемента и всех предыдущих.
 * Например: 1, 2, 3, 4 -> 1, 3, 6, 10.
 * Пустой список не следует изменять. Вернуть изменённый список.
 *
 * Обратите внимание, что данная функция должна изменять содержание списка list, а не его копии.
 */
fun accumulate(list: MutableList<Double>): MutableList<Double> {
    if (list.isEmpty()) return list
    var h: MutableList<Double>
    for (i in list.size-1 downTo 0) {
        h=list.subList(0, i)
        list[i]+= h.sum()
    }
    return list
}

/**
 * Средняя
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде списка множителей, например 75 -> (3, 5, 5).
 * Множители в списке должны располагаться по возрастанию.
 */
fun factorize(n: Int): List<Int> {
    var result=mutableListOf<Int>()
    var divisor=2
    var n=n
    while (divisor<=n) {
        if (n%divisor==0) {
            result.add(divisor)
            n/=divisor
        } else
            divisor++
    }
    return result
}

/**
 * Сложная
 *
 * Разложить заданное натуральное число n > 1 на простые множители.
 * Результат разложения вернуть в виде строки, например 75 -> 3*5*5
 */
fun factorizeToString(n: Int): String {
    val result= factorize(n)
    return result.joinToString(separator="*")
}

/**
 * Средняя
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием base > 1.
 * Результат перевода вернуть в виде списка цифр в base-ичной системе от старшей к младшей,
 * например: n = 100, base = 4 -> (1, 2, 1, 0) или n = 250, base = 14 -> (1, 3, 12)
 */
fun convert(n: Int, base: Int): List<Int> {
    var result1= mutableListOf<Int>()
    var mod=0
    var n=n
    while (n>0) {
        mod=n%base
        result1.add(mod)
        n/=base
    }
    return result1.reversed()
}

/**
 * Сложная
 *
 * Перевести заданное целое число n >= 0 в систему счисления с основанием 1 < base < 37.
 * Результат перевода вернуть в виде строки, цифры более 9 представлять латинскими
 * строчными буквами: 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: n = 100, base = 4 -> 1210, n = 250, base = 14 -> 13c
 */
fun convertToString(n: Int, base: Int): String {
    var result1= mutableListOf<Int>()
    var mod=0
    var n=n
    while (n>0) {
        mod=n%base
        result1.add(mod)
        n/=base
    }
    result1=result1.asReversed()
    var result2= mutableListOf<String>()
    for (i in 0 until result1.size) {
       if (result1[i]<10)
           result2.add(result1[i].toString())
        else {
           var Char=""
           when {
               result1[i]==10 -> Char="a"
               result1[i]==11 -> Char="b"
               result1[i]==12 -> Char="c"
               result1[i]==13 -> Char="d"
               result1[i]==14 -> Char="e"
               result1[i]==15 -> Char="f"
               result1[i]==16 -> Char="g"
               result1[i]==17 -> Char="h"
               result1[i]==18 -> Char="i"
               result1[i]==19 -> Char="j"
               result1[i]==20 -> Char="k"
               result1[i]==21 -> Char="l"
               result1[i]==22 -> Char="m"
               result1[i]==23 -> Char="n"
               result1[i]==24 -> Char="o"
               result1[i]==25 -> Char="p"
               result1[i]==26 -> Char="q"
               result1[i]==27 -> Char="r"
               result1[i]==28 -> Char="s"
               result1[i]==29 -> Char="t"
               result1[i]==30 -> Char="u"
               result1[i]==31 -> Char="v"
               result1[i]==32 -> Char="w"
               result1[i]==33 -> Char="x"
               result1[i]==34 -> Char="y"
               result1[i]==35 -> Char="z"
           }
           result2.add(Char)
       }
    }
    return result2.joinToString(separator="")
}


/**
 * Средняя
 *
 * Перевести число, представленное списком цифр digits от старшей к младшей,
 * из системы счисления с основанием base в десятичную.
 * Например: digits = (1, 3, 12), base = 14 -> 250
 */
fun decimal(digits: List<Int>, base: Int): Int {
    var result=0.0
    var count=(digits.size-1)*1.0
    for (element in digits) {
        result+=element*pow(base.toDouble(), count)
        count-=1
    }
    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести число, представленное цифровой строкой str,
 * из системы счисления с основанием base в десятичную.
 * Цифры более 9 представляются латинскими строчными буквами:
 * 10 -> a, 11 -> b, 12 -> c и так далее.
 * Например: str = "13c", base = 14 -> 250
 */
fun decimalFromString(str: String, base: Int): Int {
    var result=0.0
    var count=(str.length-1)*1.0
    var symbol: Int
    for ((index, char) in str.withIndex()) {
        when (char) {
            'a' -> symbol=10
            'b' -> symbol=11
            'c' -> symbol=12
            'd' -> symbol=13
            'e' -> symbol=14
            'f' -> symbol=15
            'g' -> symbol=16
            'h' -> symbol=17
            'i' -> symbol=18
            'j' -> symbol=19
            'k' -> symbol=20
            'l' -> symbol=21
            'm' -> symbol=22
            'n' -> symbol=23
            'o' -> symbol=24
            'p' -> symbol=25
            'q' -> symbol=26
            'r' -> symbol=27
            's' -> symbol=28
            't' -> symbol=29
            'u' -> symbol=30
            'v' -> symbol=31
            'w' -> symbol=32
            'x' -> symbol=33
            'y' -> symbol=34
            'z' -> symbol=35
            '0' -> symbol=0
            '1' -> symbol=1
            '2' -> symbol=2
            '3' -> symbol=3
            '4' -> symbol=4
            '5' -> symbol=5
            '6' -> symbol=6
            '7' -> symbol=7
            '8' -> symbol=8
            '9' -> symbol=9
            else -> symbol=str[index].toInt() // Почему-то .toInt() переводит символ '1' не в число 1, а вроде бы в 7.
        }
        result+=symbol*pow(base.toDouble(), count)
        count-=1
    }
    return result.toInt()
}

/**
 * Сложная
 *
 * Перевести натуральное число n > 0 в римскую систему.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: 23 = XXIII, 44 = XLIV, 100 = C
 */
fun roman(n: Int): String {
    var result=mutableListOf<String>()
    var n=n
    if (n>=1000) {
        for (i in 1..n/1000) result.add("M")
        n%=1000
    }
    when (n) {
        in 900 until 1000 -> {
            result.add("CM")
            n-=900
        }
        in 500 until 900 -> {
            result.add("D")
            n-=500
        }
        in 400 until 500 -> {
            result.add("CD")
            n-=400
        }
    }
    if (n in 100 until 400) {
        while (n>=100){
            result.add("C")
            n-=100
        }
    }
    when (n) {
        in 90 until 100 -> {
            result.add("XC")
            n-=90
        }
        in 50 until 90 -> {
            result.add("L")
            n-=50
        }
        in 40 until 50 -> {
            result.add("XL")
            n-=40
        }
    }
    if (n in 10 until 30) {
        while (n>=10){
            result.add("X")
            n-=10
        }
    }
    when (n) {
        9 -> result.add("IX")
        in 5 until 9 -> {
            result.add("V")
            n-=5
        }
        4 -> result.add("IV")
    }
    if (n in 1..3) {
        while (n>=1){
            result.add("I")
            n-=1
        }
    }
    return result.joinToString(separator="")
}

/**
 * Очень сложная
 *
 * Записать заданное натуральное число 1..999999 прописью по-русски.
 * Например, 375 = "триста семьдесят пять",
 * 23964 = "двадцать три тысячи девятьсот шестьдесят четыре"
 */
fun word2(n: Int): String = when (n) {
    1 -> "один"
    2 -> "два"
    3 -> "три"
    4 -> "четыре"
    5 -> "пять"
    6 -> "шесть"
    7 -> "семь"
    8 -> "восемь"
    9 -> "девять"
    10 -> "десять"
    11 -> "одиннадцать"
    12 -> "двенадцать"
    13 -> "тринадцать"
    14 -> "четырнадцать"
    15 -> "пятнадцать"
    16 -> "шестнадцать"
    17 -> "семнадцать"
    18 -> "восемнадцать"
    19 -> "девятнадцать"
    else  -> ""
}

fun word1(n: Int, count: Int): String {
    if (count==1) {
        return word2(n)
    }
    if (count==2) {
        return when (n) {
            in 2..3 -> word2(n)+"дцать"
            4 -> "сорок"
            in 5..8 -> word2(n)+"десят"
            9 -> "девяносто"
            else -> ""
        }
    }
    if (count==3) {
        return when (n) {
            1 -> "сто"
            2 -> "двести"
            in 3..4 -> word2(n)+"ста"
            in 5..9 -> word2(n)+"сот"
            else -> ""
        }
    }
    if (count==4) {
        return when (n) {
            1 -> "одна тысяча"
            2 -> "две тысячи"
            in 3..4 -> word2(n)+" тысячи"
            in 5..9 -> word2(n)+" тысяч"
            else -> "тысяч"
            }
    }
    if (count==5) {
        return when (n) {
            in 2..3 -> (word2(n)+"дцать")
            4 -> "сорок"
            in 5..8 -> (word2(n)+"десят")
            9 -> "девяносто"
            else -> ""
        }
    } else {
        return when (n) {
            1 -> "сто"
            2 -> "двести"
            in 3..4 -> word2(n)+"ста"
            else -> word2(n)+"сот"
        }
    }
}

fun russian(n: Int): String {
    var count=0
    var str=mutableListOf<String>()
    var word=""
    var symbol=0
    var n=n
    if (n%100 in 10..20) {
        count+=2
        word=word1(n%100, 1)
        n/=100
        str.add(word)
    }
    while (n>=1 && count<3){
        count+=1
        word= word1(n%10, count)
        str.add(word)
        n/=10
    }
    if (n%100 in 10..20) {
        count+=2
        word=word1(n%100, 1)+" тысяч"
        n/=100
        str.add(word)
    }
    while (n>=1){
        count+=1
        word= word1(n%10, count)
        str.add(word)
        n/=10
    }
    var result=mutableListOf<String>()
    while("" in str) str.remove("")
    for (i in str.size-1 downTo 0) result.add(str[i])
    return result.joinToString(separator=" ")
}