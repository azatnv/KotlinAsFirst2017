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
    var m=abs(n)
    do {
        m/=10
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
    var a=m
    var b=n
    var nod=max(m,n)
    while (a!=b) {
        if (a > b) {
            a-=b
            nod=a
        } else {
            b-=a
            nod=b
        }
    }
    return m*n/nod
}

/**
 * Простая
 *
 * Для заданного числа n > 1 найти минимальный делитель, превышающий 1
 */
fun minDivisor(n: Int): Int {
    var k=0
    for (i in 2..n) {
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
    for (i in m..n) {
        if (sqrt(i*1.0)%1.0==0.0) return true
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
    var m=0.0 // последий/очеередной "член ряда" (как я понял это слагаемое, которое мы прибавляем к sin(x) и сравниваем его модуль с eps)
    var result=0.0 // - это sin(x)
    var f=1 // аргумент факториала
    var pow1=2.0 // степень (-1)^pow1
    var powX=1.0 // степенгь x^powX
    if (abs(eps)>abs(x)) return x
    do {
        result+=pow(x, powX)/ factorial(f)* pow(-1.0,pow1)
        m=pow(x, powX)/ factorial(f)* pow(-1.0,pow1)
        f+=2
        powX+=2.0
        pow1++
    } while (abs(m)>=abs(eps))
    return result
}


/**
 * Средняя
 *
 * Для заданного x рассчитать с заданной точностью eps
 * cos(x) = 1 - x^2 / 2! + x^4 / 4! - x^6 / 6! + ...
 * Нужную точность считать достигнутой, если очередной член ряда меньше eps по модулю
 */
fun cos(x: Double, eps: Double): Double {
    var m=0.0 // "очередной член ряда"
    var result=1.0 // =cos(x)
    var f=2 // факториал
    var pow1=1.0 // (-1)^pow1
    var powX=2.0 //  x^powX
    if (abs(eps)>abs(x)) return x
    do {
        result+=pow(x, powX)/ factorial(f)* pow(-1.0,pow1)
        m=pow(x, powX)/ factorial(f)* pow(-1.0,pow1)
        f+=2
        powX+=2.0
        pow1++
    } while (abs(m)>=abs(eps))
    return result
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
fun isPalindrome(n: Int): Boolean =
        revert(n)==n


/**
 * Средняя
 *
 * Для заданного числа n определить, содержит ли оно различающиеся цифры.
 * Например, 54 и 323 состоят из разных цифр, а 111 и 0 из одинаковых.
 */
fun hasDifferentDigits(n: Int): Boolean = when {
    (n<10) -> false
    (n%10!=n/10%10)-> true
    else -> hasDifferentDigits(n/10)
}


/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из квадратов целых чисел:
 * 149162536496481100121144...
 * Например, 2-я цифра равна 4, 7-я 5, 12-я 6.
 */
fun squareSequenceDigit(n: Int): Int {
    var a=n
    var count=1
    var result: Int
    var sqr=1
    while (count<a) {
        a+=-count
        sqr+=1
        result=sqr*sqr
        count=digitNumber(result)
    }
    result=sqr*sqr
    while (count>a) {
        result/=10
        count+=-1
    }
    return result%10
}

/**
 * Сложная
 *
 * Найти n-ю цифру последовательности из чисел Фибоначчи (см. функцию fib выше):
 * 1123581321345589144...
 * Например, 2-я цифра равна 1, 9-я 2, 14-я 5.
 */
fun fibSequenceDigit(n: Int): Int {
    var a=n-1
    var count=1
    var result: Int
    var fib1=1
    var fib2=1
    if (n==1) return 1
    while (count<a) {
        a+=-count
        fib1+=fib2
        fib2=fib1-fib2
        result=fib1
        count=digitNumber(result)
    }
    result=fib1
    while (count>a) {
        result/=10
        count+=-1
    }
    return result%10
}
