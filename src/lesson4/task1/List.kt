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
    val mean=mean(list)
    for ((index, element) in list.withIndex()) {
        list[index]=element-mean
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
    for (i in 1 until list.size) {
        list[i]+=list[i-1]
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
    var result = mutableListOf<Int>()
    if (n==2) return listOf(2)
    var divisor=2
    var input=n
    while (divisor<=input && 2*divisor<=n) {
        if (input%divisor == 0) {
            result.add(divisor)
            input/=divisor
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
    if (n==0) return listOf(0) else
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
    var n = n
    var result1=convert(n, base)
    var result2 = mutableListOf<Char>()
    for (i in 0 until result1.size) {
        if (result1[i] < 10)
            result2.add(result1[i].toChar()+'0'.toInt())
        else {
            result2.add(result1[i].toChar()+'a'.toInt()-10)
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
    for (char in str) {
        symbol = if (char in '0'..'9') char-'0'
        else char-'a'+10
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
fun helperRoman(n: Int): Int = when (n) {
    in 100 until 400 -> (n/100)*100
    in 10 until 40 -> (n/10)*10
    in 1 until 4 -> n
    in 900 until 1000 -> 900
    in 500 until 900 -> 500
    in 400 until 500 -> 400
    in 90 until 100 -> 90
    in 50 until 90 -> 50
    in 40 until 50 -> 40
    in 5 until 9 -> 5
    else -> n
}
private val map=mapOf(1 to "I", 2 to "II", 3 to "III", 4 to "IV", 5 to "V", 9 to "IX",
        10 to "X", 20 to "XX", 30 to "XXX", 40 to "XL", 50 to "L", 90 to "XC",
        100 to "C", 200 to "CC", 300 to "CCC", 400 to "CD", 500 to "D", 900 to "CM",
        1000 to "M")
fun roman(n: Int): String {
    var result = mutableListOf<String>()
    var n = n
    while (n >= 1000) {
        result.add(map[1000].toString())
        n -= 1000
    }
    var a=400
    var b=1000
    var count=0
    while (n > 3) {
        count++
        if (count%2==1) {
            if (n in a until b) {
                result.add(map[helperRoman(n)].toString())
                n -= helperRoman(n)
            }
            a*=10
        }
        else {
            if (n in b until a) {
                result.add(map[helperRoman(n)].toString())
                n -= helperRoman(n)
            }
            b*=10
        }
        a/=10
        b/=10
    }
    if (n in 1..3) {
        result.add(map[n].toString())
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
    20 -> "двадцать"
    else  -> ""
}

fun count25(n:Int): String = when (n) {
    in 2..3 -> (word2(n)+"дцать")
    4 -> "сорок"
    in 5..8 -> (word2(n)+"десят")
    9 -> "девяносто"
    else -> ""
}

fun count36(n:Int):String =when (n) {
    1 -> "сто"
    2 -> "двести"
    in 3..4 -> word2(n)+"ста"
    in 5..9 -> word2(n)+"сот"
    else -> ""
}
fun word1(n: Int, count: Int): String = when (count) {
    1 -> word2(n)
    2 -> count25(n)
    3 -> count36(n)
    4 -> when (n) {
        1 -> "одна тысяча"
        2 -> "две тысячи"
        in 3..4 -> word2(n)+" тысячи"
        in 5..9 -> word2(n)+" тысяч"
        else -> "тысяч"
    }
    5 -> count25(n)
    else -> count36(n)
}

fun helper1(n: Int, count: Int): String {
    var n=n
    var count=count
    val word=word1(n%100, 1)
    return if (count==2) word
    else word+" тысяч"
}

fun helper2(n: Int, count: Int): String =
        word1(n%10, count)

fun russian(n: Int): String {
    var count=0
    var str=mutableListOf<String>()
    var n=n
    var rank=3
    while (count<6 && n>0) {
        if (n%100 in 10..20) {
            count+=2
            str.add(helper1(n%100, count))
            n/=100
        }
        while (n>=1 && count<rank) {
            count+=1
            str.add(helper2(n, count))
            n/=10
        }
        rank+=3
    }
    while("" in str) str.remove("")
    return str.asReversed().joinToString(separator = " ")
}

