@file:Suppress("UNUSED_PARAMETER")
package lesson2.task2

import lesson1.task1.sqr
import java.lang.Math.abs
import java.lang.Math.sqrt

/**
 * Пример
 *
 * Лежит ли точка (x, y) внутри окружности с центром в (x0, y0) и радиусом r?
 */
fun pointInsideCircle(x: Double, y: Double, x0: Double, y0: Double, r: Double) =
        sqr(x - x0) + sqr(y - y0) <= sqr(r)

/**
 * Простая
 *
 * Четырехзначное число назовем счастливым, если сумма первых двух ее цифр равна сумме двух последних.
 * Определить, счастливое ли заданное число, вернуть true, если это так.
 */
fun isNumberHappy(number: Int): Boolean {
    val dig4 = number % 10
    val dig3 = (number - dig4) / 10 % 10
    val dig2 = (number - dig4 - dig3 * 10) /100 % 10
    val dig1 = (number - dig4 - dig3 * 10 - dig2 * 100) /1000 % 10
    return when {
        dig1 + dig2 == dig3 + dig4 -> true
        else -> false
    }

}

/**
 * Простая
 *
 * На шахматной доске стоят два ферзя (ферзь бьет по вертикали, горизонтали и диагоналям).
 * Определить, угрожают ли они друг другу. Вернуть true, если угрожают.
 * Считать, что ферзи не могут загораживать друг друга.
 */
fun queenThreatens(x1: Int, y1: Int, x2: Int, y2: Int): Boolean {
    return when {
        x1 == x2 || y1 == y2 || abs(x1 - x2) == abs (y1 - y2) -> true
        else -> false
    }
}


/**
 * Простая
 *
 * Дан номер месяца (от 1 до 12 включительно) и год (положительный).
 * Вернуть число дней в этом месяце этого года по григорианскому календарю.
 */
fun daysInMonth(month: Int, year: Int): Int {

    return when(month) {
        1, 3, 5, 7, 8, 10, 12 -> return 31
        2 -> if ((year % 4 == 0) && (year % 100 != 0)) return 29
             else if ((year % 4 == 0) && (year % 1000 == 0)) return 29
             else return 28
        4, 6, 9, 11 -> return 30
        else -> 0
    }

}

/**
 * Средняя
 *
 * Проверить, лежит ли окружность с центром в (x1, y1) и радиусом r1 целиком внутри
 * окружности с центром в (x2, y2) и радиусом r2.
 * Вернуть true, если утверждение верно
 */
fun circleInside(x1: Double, y1: Double, r1: Double,
                 x2: Double, y2: Double, r2: Double): Boolean {
    val dist = sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1))
    if ( r2 >= r1 + dist) return true
    else return false

}

/**
 * Средняя
 *
 * Определить, пройдет ли кирпич со сторонами а, b, c сквозь прямоугольное отверстие в стене со сторонами r и s.
 * Стороны отверстия должны быть параллельны граням кирпича.
 * Считать, что совпадения длин сторон достаточно для прохождения кирпича, т.е., например,
 * кирпич 4 х 4 х 4 пройдёт через отверстие 4 х 4.
 * Вернуть true, если кирпич пройдёт
 */
fun brickPasses(a: Int, b: Int, c: Int, r: Int, s: Int): Boolean {
    if ((a <= r && b <= s) ||
            (b <= r && c <= s) ||
            (a <= s && b <= r) ||
            (b <= s && c <= r) ||
            (a <= r && c <= s) ||
            (a <= s && c <= r) ) return true
    else return false
}
