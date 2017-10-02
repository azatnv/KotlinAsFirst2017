@file:Suppress("UNUSED_PARAMETER")
package lesson3.task1

import lesson1.task1.sqr
import java.lang.Math.*

/**
 * Пример
 *
 * Вычисление факториала
 */
fun factorial(n: Int): Double {
    var result = 1.0
    for (i in 1..n) {
        result = result * i // Please do not fix in master
    }
    return result
}

/**
 * Пример
 *
 * Проверка числа на простоту -- результат true, если число простое
 */
fun isPrime(n: Int): Boolean {
    if (n < 2) return false
    for (m in 2..Math.sqrt(n.toDouble()).toInt()) {
        if (n % m == 0) return false
    }
    return true
}

/**
 * Пример
 *
 * Проверка числа на совершенность -- результат true, если число совершенное
 */
fun isPerfect(n: Int): Boolean {
    var sum = 1
    for (m in 2..n/2) {
        if (n % m > 0) continue
        sum += m
        if (sum > n) break
    }
    return sum == n
}

/**
 * Пример
 *
 * Найти число вхождений цифры m в число n
 */
fun digitCountInNumber(n: Int, m: Int): Int =
        when {
            n == m -> 1
            n < 10 -> 0
            else -> digitCountInNumber(n / 10, m) + digitCountInNumber(n % 10, m)
        }

/**
 * Тривиальная
 *
 * Найти количество цифр в заданном числе n.
 * Например, число 1 содержит 1 цифру, 456 -- 3 цифры, 65536 -- 5 цифр.
 */
fun digitNumber(n: Int): Int {
    var count=0
    var m=n
    do {
        m=n/10
        count++
    } while (m>0)
    return count
}

/**
 * Простая
 *1, 1, 2, 3, 5, 8,
 * Найти число Фибоначчи из ряда 13, 21, ... с номером n.
 * Ряд Фибоначчи определён следующим образом: fib(1) = 1, fib(2) = 1, fib(n+2) = fib(n) + fib(n+1)
 */
fun fib(n: Int): Int {
    if (n==1 || n==2) return 1
    return fib(n-2)+fib(n-1)
}

/**
 * Простая
 *
 * Для заданных чисел m и n найти наименьшее общее кратное, то есть,
 * минимальное число k, которое делится и на m и на n без остатка
 */
fun lcm(m: Int, n: Int): Int {
    var k= maxOf(m, n)
    var nok=0
    for (i in  k..m*n) {
        if (i%m==0 && i%n==0) {
            nok=i
            break
        }
    }
    return nok
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k=0
    for (i in 2..n) {
        if (n%i==0) k=i
        break
    }
    return k
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти максимальный делитель, меньший n
 */
fun maxDivisor(n: Int): Int {
    var k=0
    for (i in n-1 downTo  1) {
        if (n%i==0) {
            k=i
            break
        }
    }
    return k
}

/**
 * Простая
 *
 * Определить, являются ли два заданных числа m и n взаимно простыми.
 * Взаимно простые числа не имеют общих делителей, кроме 1.
 * Например, 25 и 49 взаимно простые, а 6 и 8 -- нет.
 */
fun isCoPrime(m: Int, n: Int): Boolean {
    var x= minOf(m,n)
    for (i in 2..x) {
        if (m%i==0 && n%i==0) return false
    }
    return true
}

/**
 * Простая
 *
 * Для заданных чисел m и n, m <= n, определить, имеется ли хотя бы один точный квадрат между m и n,
 * то есть, существует ли такое целое k, что m <= k*k <= n.
 * Например, для интервала 21..28 21 <= 5*5 <= 28, а для интервала 51..61 квадрата не существует.
 */
fun squareBetweenExists(m: Int, n: Int): Boolean {
    var k: Double=0.0
    for (i in m..n) {
        k=i*1.0
        if (sqr (Math.floor(sqrt(k)))==i*1.0) return true
    }
    return false
}

/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * sin(x) = x - x^3 / 3! + x^5 / 5! - x^7 / 7! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun sin(x: Double, eps: Double): Double {
    var m=0.0
    var t=0.0
    var s=1
    var k=2.0
    var H=1.0
    if (abs(eps)>abs(x)) return x
    while (abs(m)>=abs(eps)) {
        t+=pow(x, H)/ factorial(s)* pow(-1.0,k)
        m=pow(x, H)/ factorial(s)* pow(-1.0,k)
        s += 2
        H+=+2.0
        k++
    }
    return t
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var m=0.0
    var t=1.0
    var s=2
    var k=1.0
    var H=2.0
    if (abs(eps)>abs(x)) return x
    do {
        t+=pow(x, H)/ factorial(s)* pow(-1.0,k)
        m=pow(x, H)/ factorial(s)* pow(-1.0,k)
        s += 2
        H+=+2.0
        k++
    } while (abs(m)>=abs(eps))
    return t
}

/**
 * Средняя
 *
 * Поменять порядок цифр заданного числа n на обратный: 13478 -> 87431.
 * Не использовать строки при решении задачи.
 */
fun revert(n: Int): Int {
    var t = n
    var k=0
    var m=0
    while (t>0) {
        m=t%10
        k=k*10+m
        t/=10
    }
    return k
}

/**
 * Средняя
 *
 * Проверить, является ли заданное число n палиндромом:
 * первая цифра равна последней, вторая -- предпоследней и так далее.
 * 15751 -- палиндром, 3653 -- нет.
 */
fun isPalindrome(n: Int): Boolean {
    var t = n
    var k=0
    var m=0
    while (t>0) {
        m=t%10
        k=k*10+m
        t/=10
    }
    return k==n
}

/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean {
    return if (n<10) false else {
        if (n % 10 != n / 10 % 10) true else hasDifferentDigits(n / 10)
    }
}

fun sqr(x: Int) = x * x

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var k=0
    var t=0
    var m=0
    var c=0
    var r=0
    loop@ for (i in 1..n) {
        k=sqr(i)
        t=k
        while (k>0) {
            m++
            k/=10
        }
        c=c+m
        when {
            (c<n) -> continue@loop
            (c<=n) -> {
                while (c!=n) {
                    t/=10
                    c--
                }
                r=t%10
                return (r)
                break@loop
            }
        }
    }
 return r
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int = TODO()
