@file:Suppress("UNUSED_PARAMETER")
package lesson2.task1

import lesson1.task1.discriminant
import java.lang.Math.abs
import kotlin.math.max
import kotlin.math.sqrt

/**
 * Пример
 *
 * Найти число корней квадратного уравнения ax^2 + bx + c = 0
 */
fun quadraticRootNumber(a: Double, b: Double, c: Double): Int {
    val discriminant = discriminant(a, b, c)
    return when {
        discriminant > 0.0 -> 2
        discriminant == 0.0 -> 1
        else -> 0
    }
}

/**
 * Пример
 *
 * Получить строковую нотацию для оценки по пятибалльной системе
 */
fun gradeNotation(grade: Int): String = when (grade) {
    5 -> "отлично"
    4 -> "хорошо"
    3 -> "удовлетворительно"
    2 -> "неудовлетворительно"
    else -> "несуществующая оценка $grade"
}

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + sqrt(d)) / (2 * a)
    val y2 = (-b - sqrt(d)) / (2 * a)
    val y3 = max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    val age1 = age % 10
    val age2 = (age - age1) / 10 % 10
    return when {
        age1 == 1 && age2 != 1  -> "$age год"
        age1 == 2 && age2 != 1  -> "$age года"
        age1 == 3 && age2 != 1  -> "$age года"
        age1 == 4 && age2 != 1  -> "$age года"
        else -> "$age лет"
    }
}

/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double  {
    val halfDistance = (v1 * t1 + v2 * t2 + v3 * t3) / 2.0
    val path1 = v1 * t1
    val path2 = v2 * t2
    val path3 = v3 * t3
    when {
        halfDistance <= path1 -> return  halfDistance / v1
        halfDistance > path1 && halfDistance <= (path1 + path2) -> return  t1 + (halfDistance - path1) / v2
        halfDistance > (path1 + path2) && halfDistance <= (path1 + path2 + path3) -> return t1 + t2 + (halfDistance - (path1 + path2)) / v3
        else -> return 0.0
    }
}
    /**
     * Простая
     *
     * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
     * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
     * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
     * и 3, если угроза от обеих ладей.
     * Считать, что ладьи не могут загораживать друг друга
     */
    fun whichRookThreatens(kingX: Int, kingY: Int,
                           rookX1: Int, rookY1: Int,
                           rookX2: Int, rookY2: Int): Int {
        when {
            (kingX == rookX1) && (kingX == rookX2) -> return 3
            (kingX == rookX1) && (kingY == rookY2) -> return 3
            (kingY == rookY1) && (kingY == rookY2) -> return 3
            (kingY == rookY1) && (kingX == rookX2) -> return 3
            (kingX == rookX1) && (kingX != rookX2) && (kingY != rookY2) -> return 1
            (kingY == rookY1) && (kingX != rookX2) && (kingY != rookY2) -> return 1
            (kingX == rookX2) && (kingX != rookX1) && (kingY != rookY1) -> return 2
            (kingY == rookY2) && (kingX != rookX1) && (kingY != rookY1) -> return 2
            else -> return 0
         }
    }

    /**
     * Простая
     *
     * На шахматной доске стоят черный король и белые ладья и слон
     * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
     * Проверить, есть ли угроза королю и если есть, то от кого именно.
     * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
     * и 3, если угроза есть и от ладьи и от слона.
     * Считать, что ладья и слон не могут загораживать друг друга.
     */
    fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                              rookX: Int, rookY: Int,
                              bishopX: Int, bishopY: Int): Int {
        when {
            (kingX == rookX) && abs(kingX - bishopX) == abs( kingY -  bishopY) -> return 3
            (kingY == rookY) && abs(kingX - bishopX) == abs( kingY -  bishopY) -> return 3
            (kingX == rookX) && abs(kingX - bishopX) != abs( kingY -  bishopY) -> return 1
            (kingY == rookY) && abs(kingX - bishopX) != abs( kingY -  bishopY) -> return 1
            (kingX != rookX) && abs(kingX - bishopX) == abs( kingY -  bishopY) -> return 2
            (kingY != rookY) && abs(kingX - bishopX) == abs( kingY -  bishopY) -> return 2
            else -> return 0
        }

    }

    /**
     * Простая
     *
     * Треугольник задан длинами своих сторон a, b, c.
     * Проверить, является ли данный треугольник остроугольным (вернуть 0),
     * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
     * Если такой треугольник не существует, вернуть -1.
     */
    fun triangleKind(a: Double, b: Double, c: Double): Int {
        var maxLine = 0.0
        var maxInd: Int = -0
        var result: Int = -2
//        println("a = $a :: b = $b :: c = $c")

        if((a >= b) && (b >= c)) {
            maxLine = a
            maxInd = 1
        }
        if((a >= b) && (a >= c)) {
            maxLine = a
            maxInd = 1
        }
        if((b >= a) && (a >= c)) {
            maxLine = b
            maxInd = 2
        }
        if((a >= b) && (c >= a)) {
            maxLine = c
            maxInd = 3
        }
        if((b >= a) && (b >= c)) {
            maxLine = b
            maxInd = 2
        }
        if((b >= a) && (b <= c)) {
           maxLine = c
           maxInd = 3
        }
 //       println("maxLine = $maxLine :: maxInd = $maxInd")

        if(maxInd == 1 && maxLine >= b + c) return -1
        if(maxInd == 1 && maxLine * maxLine == b * b + c * c) result = 1
        if(maxInd == 1 && maxLine * maxLine > b * b + c * c) result =  2
        if(maxInd == 1 && maxLine * maxLine < b * b + c * c) result =  0
        if(maxInd == 2 && maxLine >= (a + c)) return -1
        if(maxInd == 2 && maxLine * maxLine == a * a + c * c) result = 1
        if(maxInd == 2 && maxLine * maxLine > a * a + c * c) result = 2
        if(maxInd == 2 && maxLine * maxLine < a * a + c * c) result = 0
        if(maxInd == 3 && maxLine >= a + b) return -1
        if(maxInd == 3 && maxLine * maxLine == b * b + a * a) result = 1
        if(maxInd == 3 && maxLine * maxLine > b * b + a * a) result = 2
        if(maxInd == 3 && maxLine * maxLine < b * b + a * a) result = 0


        return result
    }

    /**
     * Средняя
     *
     * Даны четыре точки на одной прямой: A, B, C и D.
     * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
     * Найти длину пересечения отрезков AB и CD.
     * Если пересечения нет, вернуть -1.
     */
    fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
        return when {
            (a < c) && (b < c) -> -1
            (a < c) && (b >= c) && (b < d) -> b - c
            (a < c) && (b >= d) -> d - c
            (a > c) && (a > d) -> -1
            (a > c) && (b < d) -> b - a
            (a > c) && (b > d) -> d - a
            else -> -1
        }
    }
