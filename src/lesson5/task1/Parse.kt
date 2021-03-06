@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

import sun.misc.Regexp
import java.lang.Math.abs
import java.util.regex.MatchResult
import javax.swing.SizeRequirements

/**
 * Пример
 *
 * Время представлено строкой вида "11:34:45", содержащей часы, минуты и секунды, разделённые двоеточием.
 * Разобрать эту строку и рассчитать количество секунд, прошедшее с начала дня.
 */
fun timeStrToSeconds(str: String): Int {
    val parts = str.split(":")
    var result = 0
    for (part in parts) {
        val number = part.toInt()
        result = result * 60 + number
    }
    return result
}

/**
 * Пример
 *
 * Дано число n от 0 до 99.
 * Вернуть его же в виде двухсимвольной строки, от "00" до "99"
 */
fun twoDigitStr(n: Int) = if (n in 0..9) "0$n" else "$n"

/**
 * Пример
 *
 * Дано seconds -- время в секундах, прошедшее с начала дня.
 * Вернуть текущее время в виде строки в формате "ЧЧ:ММ:СС".
 */
fun timeSecondsToStr(seconds: Int): String {
    val hour = seconds / 3600
    val minute = (seconds % 3600) / 60
    val second = seconds % 60
    return String.format("%02d:%02d:%02d", hour, minute, second)
}

/**
 * Пример: консольный ввод
 */
fun main(args: Array<String>) {
    println("Введите время в формате ЧЧ:ММ:СС")
    val line = readLine()
    if (line != null) {
        val seconds = timeStrToSeconds(line)
        if (seconds == -1) {
            println("Введённая строка $line не соответствует формату ЧЧ:ММ:СС")
        }
        else {
            println("Прошло секунд с начала суток: $seconds")
        }
    }
    else {
        println("Достигнут <конец файла> в процессе чтения строки. Программа прервана")
    }
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15 июля 2016".
 * Перевести её в цифровой формат "15.07.2016".
 * День и месяц всегда представлять двумя цифрами, например: 03.04.2011.
 * При неверном формате входной строки вернуть пустую строку
 */

private val listMonth=listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
        "августа", "сентября", "октября", "ноября", "декабря")
fun dateStrToDigit(str: String): String {
    val parts=str.split(" ")
    val result=mutableListOf<Int>()
    if (parts.size!=3) return ""
    try {
        for (i in 0..2)
            if (i==1) {
                if (listMonth.indexOf(parts[1])+1==0) return "" else
                    result.add(listMonth.indexOf(parts[i])+1)
            }
            else result.add(parts[i].toInt())
    } catch (e: NumberFormatException) {
        return ""
    }
    return String.format("%02d.%02d.%d", result[0], result[1], result[2])
}

/**
 * Средняя
 *
 * Дата представлена строкой вида "15.07.2016".
 * Перевести её в строковый формат вида "15 июля 2016".
 * При неверном формате входной строки вернуть пустую строку
 */

fun dateDigitToStr(digital: String): String {
    val parts=digital.split(".")
    var result=mutableListOf<String>()
    if (parts.size!=3) return ""
    try {
        for (i in 0..2)
            if (i==1) {
                if (parts[i].toInt() !in 1..12) return "" else
                    result.add(listMonth[parts[i].toInt()-1])
            } else result.add(parts[i].toInt().toString())
    } catch (e: NumberFormatException) {
            return ""
    }
    return result.joinToString(separator=" ")
}

/**
 * Средняя
 *
 * Номер телефона задан строкой вида "+7 (921) 123-45-67".
 * Префикс (+7) может отсутствовать, код города (в скобках) также может отсутствовать.
 * Может присутствовать неограниченное количество пробелов и чёрточек,
 * например, номер 12 --  34- 5 -- 67 -98 тоже следует считать легальным.
 * Перевести номер в формат без скобок, пробелов и чёрточек (но с +), например,
 * "+79211234567" или "123456789" для приведённых примеров.
 * Все символы в номере, кроме цифр, пробелов и +-(), считать недопустимыми.
 * При неверном формате вернуть пустую строку
 */


fun flattenPhoneNumber(phone: String): String {
    val list=listOf(' ', '-', ')', '(')
    val result=phone.filter{it !in list}
    return if (!Regex("""^\+?\d+$""").matches(result)) ""
    else result
}

/**
 * Средняя
 *
 * Результаты спортсмена на соревнованиях в прыжках в длину представлены строкой вида
 * "706 - % 717 % 703".
 * В строке могут присутствовать числа, черточки - и знаки процента %, разделённые пробелами;
 * число соответствует удачному прыжку, - пропущенной попытке, % заступу.
 * Прочитать строку и вернуть максимальное присутствующее в ней число (717 в примере).
 * При нарушении формата входной строки или при отсутствии в ней чисел, вернуть -1.
 */
fun bestLongJump(jumps: String): Int {
    var parts=jumps.split(" ")
    var result= mutableListOf<Int>()
    try {
        for (i in 0 until parts.size) {
            if (parts[i]=="-" || parts[i]=="%" || parts[i]=="") continue
            else result.add(parts[i].toInt())
        }
    } catch (e: NumberFormatException) {
        return -1
    }
    return if (result.isEmpty()) -1
    else result.max()!!
}

/**
 * Сложная
 *
 * Результаты спортсмена на соревнованиях в прыжках в высоту представлены строкой вида
 * "220 + 224 %+ 228 %- 230 + 232 %%- 234 %".
 * Здесь + соответствует удачной попытке, % неудачной, - пропущенной.
 * Высота и соответствующие ей попытки разделяются пробелом.
 * Прочитать строку и вернуть максимальную взятую высоту (230 в примере).
 * При нарушении формата входной строки вернуть -1.
 */
fun bestHighJump(jumps: String): Int {
    var parts=jumps.split(" ")
    var result= mutableListOf<Int>()
    try {
        for (i in 0 until parts.size-1 step 2) {
            if (Regex("""^[%-]*\+[%-]*$""").matches(parts[i+1]) &&
                    Regex("""^\d+$""").matches(parts[i])) result.add(parts[i].toInt())
        }
    } catch (e: NumberFormatException) {
        return -1
    }
    return if (result.isEmpty()) -1
    else result.max()!!
}

/**
 * Сложная
 *
 * В строке представлено выражение вида "2 + 31 - 40 + 13",
 * использующее целые положительные числа, плюсы и минусы, разделённые пробелами.
 * Наличие двух знаков подряд "13 + + 10" или двух чисел подряд "1 2" не допускается.
 * Вернуть значение выражения (6 для примера).
 * Про нарушении формата входной строки бросить исключение IllegalArgumentException
 */
fun plusMinus(expression: String): Int {
    if (!(expression.matches(Regex("""(\d+\s[+-]\s)*\d+""")))) {
        throw IllegalArgumentException()
    }
    var parts=expression.split(" ")
    var result=parts[0].toInt()
    for (i in 1 until parts.size) {
        when {
            parts[i]=="+" -> result+=parts[i+1].toInt()
            parts[i]=="-" -> result-=parts[i+1].toInt()
        }
    }
    return result
}

/**
 * Сложная
 *
 * Строка состоит из набора слов, отделённых друг от друга одним пробелом.
 * Определить, имеются ли в строке повторяющиеся слова, идущие друг за другом.
 * Слова, отличающиеся только регистром, считать совпадающими.
 * Вернуть индекс начала первого повторяющегося слова, или -1, если повторов нет.
 * Пример: "Он пошёл в в школу" => результат 9 (индекс первого 'в')
 */
fun firstDuplicateIndex(str: String): Int {
    val str=str.toLowerCase()
    val result=Regex("""(\S+)\s\1(?:\s|$)""").find(str, startIndex=0)
    return if (result!=null) {
        return result.range.start
    }
    else -1
}

/**
 * Сложная
 *
 * Строка содержит названия товаров и цены на них в формате вида
 * "Хлеб 39.9; Молоко 62.5; Курица 184.0; Конфеты 89.9".
 * То есть, название товара отделено от цены пробелом,
 * а цена отделена от названия следующего товара точкой с запятой и пробелом.
 * Вернуть название самого дорогого товара в списке (в примере это Курица),
 * или пустую строку при нарушении формата строки.
 * Все цены должны быть положительными
 */

fun mostExpensive(description: String): String {
    if (!(description.matches(Regex("""(.*\s\d+(\.\d+)?(;\s|$|;))+""")))) {
        return ""
    }
    var parts=description.split("; ", ";").filter{it!=""}
    var maxElement=-1.0
    var result=""
    for (element in parts) {
        if (element.split(" ")[1].toDouble()>maxElement) {
            result=element.split(" ")[0]
            maxElement=element.split(" ")[1].toDouble()
        }
    }
    return result
}

/**
 * Сложная
 *
 * Перевести число roman, заданное в римской системе счисления,
 * в десятичную систему и вернуть как результат.
 * Римские цифры: 1 = I, 4 = IV, 5 = V, 9 = IX, 10 = X, 40 = XL, 50 = L,
 * 90 = XC, 100 = C, 400 = CD, 500 = D, 900 = CM, 1000 = M.
 * Например: XXIII = 23, XLIV = 44, C = 100
 *
 * Вернуть -1, если roman не является корректным римским числом
 */
private val valueRoman1=mapOf("I" to 1, "II" to 2, "III" to 3, "IV" to 4, "V" to 5, "IX" to 9,
        "X" to 10, "XX" to 20, "XXX" to 30, "XL" to 40, "L" to 50, "XC" to 90,
        "C" to 100, "CC" to 200, "CCC" to 300, "CD" to 400, "D" to 500, "CM" to 900, "M" to 1000)

private val valueRoman2=mapOf('M' to "CM", 'C' to "XC", 'X' to "IX", 'D' to "CD", 'L' to "XL", 'V' to "IV")

fun helperRoman(roman: String, count: Int, char: Char): Pair<Int, Int> {
    val find=Regex("""$char+""").find(roman, count)
    var result=0
    var countSymbol=0
    if (find!=null) {
        if (roman[count]==char) {
            countSymbol+=find.value.length
            result+=valueRoman1[char.toString()]!!*countSymbol
        }
        val regexRoman=valueRoman2[char].toString()
        if (Regex("""$regexRoman""").containsMatchIn(roman)) {
            countSymbol+=2
            result+=valueRoman1[valueRoman2[char]]!!
        }
    }
    return Pair(result, countSymbol)
}

fun fromRoman(roman: String): Int {
    if (!(roman.matches(Regex("""^(M*)(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$"""))) || roman.isEmpty())
        return -1
    var result=0
    var count=0
    var t: Pair<Int, Int>
    val list=listOf('M', 'D', 'C', 'L', 'X', 'V', 'I')
    for (element in list) {
        t = helperRoman(roman, count, element)
        result += t.first
        count += t.second
    }
    return result
}

/**
 * Очень сложная
 *
 * Имеется специальное устройство, представляющее собой
 * конвейер из cells ячеек (нумеруются от 0 до cells - 1 слева направо) и датчик, двигающийся над этим конвейером.
 * Строка commands содержит последовательность команд, выполняемых данным устройством, например +>+>+>+>+
 * Каждая команда кодируется одним специальным символом:
 *	> - сдвиг датчика вправо на 1 ячейку;
 *  < - сдвиг датчика влево на 1 ячейку;
 *	+ - увеличение значения в ячейке под датчиком на 1 ед.;
 *	- - уменьшение значения в ячейке под датчиком на 1 ед.;
 *	[ - если значение под датчиком равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей следующей командой ']' (с учётом вложенности);
 *	] - если значение под датчиком не равно 0, в качестве следующей команды следует воспринимать
 *  	не следующую по порядку, а идущую за соответствующей предыдущей командой '[' (с учётом вложенности);
 *      (комбинация [] имитирует цикл)
 *  пробел - пустая команда
 *
 * Изначально все ячейки заполнены значением 0 и датчик стоит на ячейке с номером N/2 (округлять вниз)
 *
 * После выполнения limit команд или всех команд из commands следует прекратить выполнение последовательности команд.
 * Учитываются все команды, в том числе несостоявшиеся переходы ("[" при значении под датчиком не равном 0 и "]" при
 * значении под датчиком равном 0) и пробелы.
 *
 * Вернуть список размера cells, содержащий элементы ячеек устройства после завершения выполнения последовательности.
 * Например, для 10 ячеек и командной строки +>+>+>+>+ результат должен быть 0,0,0,0,0,1,1,1,1,1
 *
 * Все прочие символы следует считать ошибочными и формировать исключение IllegalArgumentException.
 * То же исключение формируется, если у символов [ ] не оказывается пары.
 * Выход за границу конвейера также следует считать ошибкой и формировать исключение IllegalStateException.
 * Считать, что ошибочные символы и непарные скобки являются более приоритетной ошибкой чем выход за границу ленты,
 * то есть если в программе присутствует некорректный символ или непарная скобка, то должно быть выброшено
 * IllegalArgumentException.
 * IllegalArgumentException должен бросаться даже если ошибочная команда не была достигнута в ходе выполнения.
 *
 */
fun computeDeviceCells(cells: Int, commands: String, limit: Int): List<Int> = TODO()


fun spamUser34(text: String): List<String> {
    if (!Regex("""^(.*\s[1-9][0-9]?:[0-9]{2}(\n|$))+""").matches(text)) return listOf()
    var parts=text.split("\n")
    var result= mutableListOf<String>()
    for (i in 0 until parts.size-1) {
        val parts1 = parts[i].split(" ", ":")
        val minuts1 = parts1[1].toInt() * 60 + parts1[2].toInt()
        for (g in i+1 until parts.size) {
            val parts2 = parts[g].split(" ", ":")
            val minuts2 = parts2[1].toInt() * 60 + parts2[2].toInt()
            if (parts1[0]==parts2[0] && abs(minuts1-minuts2)<2 && parts1[0] !in result) {
                result.add(parts1[0])
            }
        }
    }
    return result
}