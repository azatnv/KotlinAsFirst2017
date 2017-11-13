@file:Suppress("UNUSED_PARAMETER")
package lesson5.task1

import sun.misc.Regexp
import java.util.regex.MatchResult

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
fun month(str: String): Int = when (str) {
    "января" -> 1
    "февраля" -> 2
    "марта" -> 3
    "апреля" -> 4
    "мая" -> 5
    "июня" -> 6
    "июля" -> 7
    "августа" -> 8
    "сентября" -> 9
    "октября" -> 10
    "ноября" -> 11
    "декабря" -> 12
    else -> str.toInt()
}
private val listMonth=listOf("января", "февраля", "марта", "апреля", "мая", "июня", "июля",
        "августа", "сентября", "октября", "ноября", "декабря/")
fun dateStrToDigit(str: String): String {
    val parts=str.split(" ")
    var result=mutableListOf<Int>()
    try {
        for (i in 0..2)
            if (i==1) result.add(listMonth.indexOf(parts[i])+1)
            else result.add(parts[i].toInt())
    } catch (e: NumberFormatException) {
        return ""
    } catch (e:IndexOutOfBoundsException) {
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
fun month2(str: String): String = when (str) {
    "01" -> "января"
    "02" -> "февраля"
    "03" -> "марта"
    "04" -> "апреля"
    "05" -> "мая"
    "06" -> "июня"
    "07" -> "июля"
    "08" -> "августа"
    "09" -> "сентября"
    "10" -> "октября"
    "11" -> "ноября"
    "12" -> "декабря"
    else -> ""
}
private val mapMonth=mapOf("01" to "января", "02" to "февраля", "03" to "марта",
        "04" to "апреля", "05" to "мая", "06" to "июня", "07" to "июля", "08" to "августа",
        "09" to "сентября", "10" to "октября", "11" to "ноября", "12" to "декабря")
fun dateDigitToStr(digital: String): String {
    val parts=digital.split(".")
    var result=mutableListOf<String>()
    if (parts.size>3) return ""
    try {
        try {
            for (i in 0..2)
                if (i==1) {
                    if (month2(parts[i])=="") return "" else
                        result.add(month2(parts[i]))
                } else result.add(parts[i].toInt().toString())

        } catch (e: NumberFormatException) {
            return ""
        }
    } catch (e:IndexOutOfBoundsException) {
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
fun helper(result: MutableList<String>, n: Int): MutableList<String> {
    val list= mutableListOf("")
    try {
        for (i in n until result.size) result[i] = result[i].toInt().toString()
    } catch (e: IndexOutOfBoundsException) { return list }
    return result
}
fun flattenPhoneNumber(phone: String): String {
    var result= mutableListOf<String>()
    for (char in phone) result.add("$char")
    result.removeAll(listOf(" ", "(", ")", "-"))
    try {
        try {
            if (result[0] == "+") helper(result, 1)
            else helper(result, 0)
        } catch ( e: NumberFormatException) {
            return ""
        }
    } catch (e: IndexOutOfBoundsException) { return "" }
    return result.joinToString(separator="")
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
        for (i in 0 until parts.size) {
            if (parts[i]=="%-" || parts[i]=="%%-" || parts[i]=="-" || parts[i]=="%") continue
            else if (parts[i]=="+" ||  parts[i]=="%+" ||  parts[i]=="%%+") result.add(parts[i-1].toInt())
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
    val result=Regex("""([а-яА-ЯёЁ]+)\s\1[\s$]""").find(str, startIndex=0)
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
    if (!(description.matches(Regex("""(.*\s\d+\.\d+(;[\s]|$))+""")))) {
        return ""
    }
    var parts=description.split("; ")
    var maxElement=0.0
    var result=""
    for (element in parts) {
        val parts1=element.split(" ")
        val product=parts1[0]
        val value=parts1[1].toDouble()
        if (value>maxElement) {
            maxElement=value
            result=product
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
fun helperRoman2(roman: String, count: Int, char: Char): Int {
    val find=Regex("""$char+""").find(roman, count)
    var result=0
    var count=count
    if (find!=null) {
        if (roman[count]==char) {
            count+=find.value.length
            result+= when (char){
                'M' -> 1000*find.value.length
                'C' -> 100*find.value.length
                'X' -> 10*find.value.length
                else -> find.value.length
            }
        }
        when (char) {
            'M' -> if (Regex("""CM""").containsMatchIn(roman)) result+=900
            'C' -> if (Regex("""XC""").containsMatchIn(roman)) result+=90
            else -> if (Regex("""IX""").containsMatchIn(roman)) result+=9
        }
    }
    return result
}

fun helperRoman1(roman: String, count: Int, char: Char): Int = when (char) {
    'D' -> if (roman[count]=='D') 500 else 400
    'L' -> if (roman[count]=='L') 50 else 40
    else -> if (roman[count]=='V') 5 else 4
}

fun fromRoman(roman: String): Int {
    if (!(roman.matches(Regex("""^(M{0,3})(D?C{0,3}|C[DM])(L?X{0,3}|X[LC])(V?I{0,3}|I[VX])$"""))) || roman.isEmpty()) {
        return -1
    }
    var result=0
    var count=0
    var symbolValue: Int
    symbolValue= helperRoman2(roman, count, 'M')
    result+=symbolValue
    count+=symbolValue/1000+(symbolValue%1000)/450
    if (Regex("""D""").containsMatchIn(roman)) {
        symbolValue= helperRoman1(roman, count, 'D')
        result+=symbolValue
        count+= if (symbolValue==500) 1 else 2
    }
    symbolValue=helperRoman2(roman, count, 'C')
    result+=symbolValue
    count+=symbolValue/100+(symbolValue%100)/45
    if (Regex("""L""").containsMatchIn(roman)) {
        symbolValue= helperRoman1(roman, count, 'L')
        result+=symbolValue
        count+= if (symbolValue==50) 1 else 2
    }
    symbolValue=helperRoman2(roman, count, 'X')
    result+=symbolValue
    count+=symbolValue/10+(symbolValue%10)/4
    if (Regex("""V""").containsMatchIn(roman)) {
        symbolValue=helperRoman1(roman, count, 'V')
        result+=symbolValue
        count+= if (symbolValue==5) 1 else 2
    }
    result+=helperRoman2(roman, count, 'I')
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
