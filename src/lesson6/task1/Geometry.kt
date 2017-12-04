@file:Suppress("UNUSED_PARAMETER")
package lesson6.task1

import lesson1.task1.sqr
import java.lang.Math.*

/**
 * Точка на плоскости
 */
data class Point(val x: Double, val y: Double) {
    /**
     * Пример
     *
     * Рассчитать (по известной формуле) расстояние между двумя точками
     */
    fun distance(other: Point): Double = Math.sqrt(sqr(x - other.x) + sqr(y - other.y))
}

/**
 * Треугольник, заданный тремя точками (a, b, c, см. constructor ниже).
 * Эти три точки хранятся в множестве points, их порядок не имеет значения.
 */
class Triangle private constructor(private val points: Set<Point>) {

    private val pointList = points.toList()

    val a: Point get() = pointList[0]

    val b: Point get() = pointList[1]

    val c: Point get() = pointList[2]

    constructor(a: Point, b: Point, c: Point): this(linkedSetOf(a, b, c))
    /**
     * Пример: полупериметр
     */
    fun halfPerimeter() = (a.distance(b) + b.distance(c) + c.distance(a)) / 2.0

    /**
     * Пример: площадь
     */
    fun area(): Double {
        val p = halfPerimeter()
        return Math.sqrt(p * (p - a.distance(b)) * (p - b.distance(c)) * (p - c.distance(a)))
    }

    /**
     * Пример: треугольник содержит точку
     */
    fun contains(p: Point): Boolean {
        val abp = Triangle(a, b, p)
        val bcp = Triangle(b, c, p)
        val cap = Triangle(c, a, p)
        return abp.area() + bcp.area() + cap.area() <= area()
    }

    override fun equals(other: Any?) = other is Triangle && points == other.points

    override fun hashCode() = points.hashCode()

    override fun toString() = "Triangle(a = $a, b = $b, c = $c)"
}

/**
 * Окружность с заданным центром и радиусом
 */
data class Circle(val center: Point, val radius: Double) {
    /**
     * Простая
     *
     * Рассчитать расстояние между двумя окружностями.
     * Расстояние между непересекающимися окружностями рассчитывается как
     * расстояние между их центрами минус сумма их радиусов.
     * Расстояние между пересекающимися окружностями считать равным 0.0.
     */
    fun distance(other: Circle): Double {
        return if (center.distance(other.center)<=radius+other.radius) 0.0
        else center.distance(other.center)-(radius+other.radius)
    }

    /**
     * Тривиальная
     *
     * Вернуть true, если и только если окружность содержит данную точку НА себе или ВНУТРИ себя
     */
    fun contains(p: Point): Boolean = center.distance(p)<=radius
}

/**
 * Отрезок между двумя точками
 */
data class Segment(val begin: Point, val end: Point) {
    override fun equals(other: Any?) =
            other is Segment && (begin == other.begin && end == other.end || end == other.begin && begin == other.end)

    override fun hashCode() =
            begin.hashCode() + end.hashCode()
}

/**
 * Средняя
 *
 * Дано множество точек. Вернуть отрезок, соединяющий две наиболее удалённые из них.
 * Если в множестве менее двух точек, бросить IllegalArgumentException
 */
fun diameter(vararg points: Point): Segment {
    if (points.size<2) throw IllegalArgumentException()
    var distance=0.0
    var segment=Segment(Point(0.0, 0.0), Point(0.0, 0.0))
    for (i in 0 until points.size-1){
        for (k in i+1 until points.size) {
            if (points[i].distance(points[k])>distance) {
                distance = points[i].distance(points[k])
                segment=Segment(points[i], points[k])
            }

        }
    }
    return segment
}

/**
 * Простая
 *
 * Построить окружность по её диаметру, заданному двумя точками
 * Центр её должен находиться посередине между точками, а радиус составлять половину расстояния между ними
 */
fun circleByDiameter(diameter: Segment): Circle {
    val center=Point((diameter.begin.x+diameter.end.x)/2, (diameter.begin.y+diameter.end.y)/2)
    val radius=center.distance(diameter.begin)
    return Circle(center, radius)
}
/**
 * Прямая, заданная точкой point и углом наклона angle (в радианах) по отношению к оси X.
 * Уравнение прямой: (y - point.y) * cos(angle) = (x - point.x) * sin(angle)
 * или: y * cos(angle) = x * sin(angle) + b, где b = point.y * cos(angle) - point.x * sin(angle).
 * Угол наклона обязан находиться в диапазоне от 0 (включительно) до PI (исключительно).
 */
class Line private constructor(val b: Double, val angle: Double) {
    init {
        assert(angle >= 0 && angle < Math.PI) { "Incorrect line angle: $angle" }
    }

    constructor(point: Point, angle: Double): this(point.y * Math.cos(angle) - point.x * Math.sin(angle), angle)

    /**
     * Средняя
     *
     * Найти точку пересечения с другой линией.
     * Для этого необходимо составить и решить систему из двух уравнений (каждое для своей прямой)
     */
    fun crossPoint(other: Line): Point {
        val b1=b
        val cos1=angle
        val b2=other.b
        val cos2=other.angle
        val abscissa=(b2*cos(cos1)-b1*cos(cos2))/sin(cos1-cos2)
        val ordinate=(b2*sin(cos1)-b1*sin(cos2))/sin(cos1-cos2)
        return Point(abscissa, ordinate)
    }

    override fun equals(other: Any?) = other is Line && angle == other.angle && b == other.b

    override fun hashCode(): Int {
        var result = b.hashCode()
        result = 31 * result + angle.hashCode()
        return result
    }

    override fun toString() = "Line(${Math.cos(angle)} * y = ${Math.sin(angle)} * x + $b)"
}

/**
 * Средняя
 *
 * Построить прямую по отрезку
 */
fun lineBySegment(s: Segment): Line {
    val angle=atan2(abs(s.begin.y-s.end.y), abs(s.begin.x-s.end.x))
    val angleBySegment=if (s.end.x>s.begin.x) {
        if (s.end.y>=s.begin.y) angle else PI-angle
    } else
        if (s.end.x==s.begin.x) angle
    else {
        if (s.end.y>s.begin.y) PI-angle else angle
    }
    return Line(s.begin, angleBySegment)
}

/**
 * Средняя
 *
 * Построить прямую по двум точкам
 */
fun lineByPoints(a: Point, b: Point): Line =lineBySegment(Segment(a, b))


/**
 * Сложная
 *
 * Построить серединный перпендикуляр по отрезку или по двум точкам
 */
fun bisectorByPoints(a: Point, b: Point): Line {
    val angleBisector= when {
        a.x==b.x -> 0.0
        b.y>a.y -> {
            val angle=atan2((b.y-a.y), (b.x-a.x))
            if (angle>=PI/2) angle-PI/2
            else angle+PI/2
        }
        b.y<a.y -> {
            val angle=atan2((a.y-b.y), (a.x-b.x))
            if (angle>=PI/2) angle-PI/2
            else angle+PI/2
        }
        else -> PI/2
    }
    val midPoint=Point((b.x+a.x)/2, (b.y+a.y)/2)
    return Line(midPoint, angleBisector)
}

/**
 * Средняя
 *
 * Задан список из n окружностей на плоскости. Найти пару наименее удалённых из них.
 * Если в списке менее двух окружностей, бросить IllegalArgumentException
 */
fun findNearestCirclePair(vararg circles: Circle): Pair<Circle, Circle> {
    if (circles.size<2) throw IllegalArgumentException()
    var minRange=circles[0].distance(circles[1])
    var result=Pair(circles[0], circles[1])
    for (i in 0 until circles.size-1) {
        for (k in i+1 until circles.size) {
            val range=circles[i].distance(circles[k])
            if (range<minRange && range>=0) {
                result=Pair(circles[i], circles[k])
                minRange=range
            }
        }
    }
    return result
}

/**
 * Сложная
 *
 * Дано три различные точки. Построить окружность, проходящую через них
 * (все три точки должны лежать НА, а не ВНУТРИ, окружности).
 * Описание алгоритмов см. в Интернете
 * (построить окружность по трём точкам, или
 * построить окружность, описанную вокруг треугольника - эквивалентная задача).
 */
fun circleByThreePoints(a: Point, b: Point, c: Point): Circle {
    val center=bisectorByPoints(a, b).crossPoint(bisectorByPoints(b, c))
    val radius=center.distance(a)
    return Circle(center, radius)
}

/**
 * Очень сложная
 *
 * Дано множество точек на плоскости. Найти круг минимального радиуса,
 * содержащий все эти точки. Если множество пустое, бросить IllegalArgumentException.
 * Если множество содержит одну точку, вернуть круг нулевого радиуса с центром в данной точке.
 *
 * Примечание: в зависимости от ситуации, такая окружность может либо проходить через какие-либо
 * три точки данного множества, либо иметь своим диаметром отрезок,
 * соединяющий две самые удалённые точки в данном множестве.
 */
fun minContainingCircle(vararg points: Point): Circle {
    if (points.isEmpty()) throw IllegalArgumentException()
    if (points.size==1) return Circle(points[0], 0.0)
    val resultListCircle=mutableListOf<Circle>()
    for (i in 0 until points.size-1){
        for (g in i+1 until points.size){
            val diameter=points[i].distance(points[g])
            val midPoint=Point((points[i].x+points[g].x)/2, (points[i].y+points[g].y)/2)
            val flag=(0 until points.size).all{points[it].distance(midPoint)<=diameter/2}
            if (flag) resultListCircle.add(Circle(midPoint, diameter/2))
        }
    }
    if (points.size>2) {
        for (i in 0 until points.size-2) {
            for (g in i+1 until points.size-1) {
                for (k in g+1 until points.size) {
                    val circle=circleByThreePoints(points[i], points[g], points[k])
                    val flag=(0 until points.size).all{points[it].distance(circle.center)<=circle.radius}
                    if (flag) resultListCircle.add(circle)
                }
            }
        }
    }
    var result=resultListCircle[0]
    for(element in resultListCircle) {
        if (result.radius>element.radius)
            result=element
    }
    return result
}
