@file:Suppress("UNUSED_PARAMETER", "unused")
package lesson7.task1

/**
 * Ячейка матрицы: row = ряд, column = колонка
 */
data class Cell(val row: Int, val column: Int)

/**
 * Интерфейс, описывающий возможности матрицы. E = тип элемента матрицы
 */
interface Matrix<E> {
    /** Высота */
    val height: Int

    /** Ширина */
    val width: Int

    /**
     * Доступ к ячейке.
     * Методы могут бросить исключение, если ячейка не существует или пуста
     */
    operator fun get(row: Int, column: Int): E
    operator fun get(cell: Cell): E

    /**
     * Запись в ячейку.
     * Методы могут бросить исключение, если ячейка не существует
     */
    operator fun set(row: Int, column: Int, value: E)
    operator fun set(cell: Cell, value: E)
}

/**
 * Простая
 *
 * Метод для создания матрицы, должен вернуть РЕАЛИЗАЦИЮ Matrix<E>.
 * height = высота, width = ширина, e = чем заполнить элементы.
 * Бросить исключение IllegalArgumentException, если height или width <= 0.
 */
fun <E> createMatrix(height: Int, width: Int, e: E): Matrix<E> = MatrixImpl(height, width, e)

/**
 * Средняя сложность
 *
 * Реализация интерфейса "матрица"
 */
class MatrixImpl<E>(override val height: Int, override val width: Int, e: E) : Matrix<E> {

    private val map = mutableMapOf<Cell, E>()

    init {
        require(width > 0 && height > 0)
        for (i in 0 until height) {
            for (j in 0 until width) {
                map[Cell(i, j)]=e
            }
        }
    }

    override fun get(row: Int, column: Int): E =if (row in 0 until height && column in 0 until width)
        map[Cell(row, column)]!! else throw IndexOutOfBoundsException()

    override fun get(cell: Cell): E  =get(cell.row, cell.column)

    override fun set(row: Int, column: Int, value: E) {
        if (row in 0 until height && column in 0 until width)
            map[Cell(row, column)]=value else throw IndexOutOfBoundsException()
    }

    override fun set(cell: Cell, value: E) {
        set(cell.row, cell.column, value)
    }

    override fun equals(other: Any?) =
            other is MatrixImpl<*> &&
            height == other.height &&
            width == other.width

    override fun toString(): String {
        val sb = StringBuilder()
        sb.append("[")
        for (row in 0 until height) {
            sb.append("[")
            for (column in 0 until width) {
                sb.append(this[row, column])
                if (height-1!=column) sb.append(" ")
            }
            sb.append("]")
        }
        sb.append("]")
        return "$sb"
    }
}