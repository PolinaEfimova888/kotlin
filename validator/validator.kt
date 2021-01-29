class ValidatorException(message: String): Exception(message)

class Validator(var pass: String=" ", var order: String=" ") {
    val N = 10
    val rules = rules(pass)

    public fun check() {

        if (checkOrders()) {
            val numbers_orders = order.split(" ")

            for (symb in numbers_orders) {
                when (symb.toInt()) {
                    in 0..N -> checkOrder(symb.toInt())
                    else -> print("Правила не существует")
                }
            }
        } else {
            print("Правила не заданы")
        }
    }

    private fun checkOrders(): Boolean {
        if (!(order.any() {it.isDigit() })) return false
        if (order.isBlank()) return false

        for (o in order) {
            if (!(o in '0'..'9')) order.replace(o, ' ', false)
        }

        order = order.replace("\\s+".toRegex(), "")

        for (i in 0..9) {
            for (j in 0..order.length-2) {
                var jj = j+1
                var count = 0

                if (order[j].toInt() - '0'.toInt()==i) count++

                while (jj<order.length) {
                    if ((order[jj].toInt() - '0'.toInt() == i)&&(count==1)) {
                        order = order.replaceRange(jj, jj+1,"")
                    }
                    jj++
                }

            }
        }

        addWhitespaces()

        return true
    }

    private fun addWhitespaces() {
        val space = ' '

        var i = 0
        while (i < order.length-1) {
            if (order[i] != ' ') {

                val part_order_start = order.substring(0, i + 1)
                val part_order_end = order.substring(i+1, order.length)
                order = "$part_order_start$space$part_order_end"

                i+=2
            }
        }

    }

    public fun checkOrder(number: Int) {
        when (number) {
            0 -> if (!rules.checkPasswordLength()) println("Короткий пароль!")
            1 -> if (!rules.checkLetters()) println("Пароль должен содержать буквы как в нижнем, так и в верхнем регистре :с")
            2 -> if (!rules.checkNumbers()) println("Нет цифр!")
            3 -> if (!rules.chechSpecialSymbols()) println("Пароль должен содержать минимум два специальных символа!")
            4 -> if (!rules.checkInDict()) println("Очен простой пароль :с")
            5 -> if (!rules.checkEntropy()) println("Низкий уровень энтропии")
            else -> throw RulesException("Такого правила не существует")
        }
    }
}
