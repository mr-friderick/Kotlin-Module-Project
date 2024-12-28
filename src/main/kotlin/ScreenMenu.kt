import java.util.Scanner

class ScreenMenu {
    companion object {
        const val PARAGRAPH_EXIT = 0
    }

    private val scanner = Scanner(System.`in`)

    fun open(itArchive: Boolean, options: List<Pair<String, () -> Unit>>): Boolean {
        while (true) {
            println("\nСписок ${if (itArchive) "архивов" else "заметок"}:")

            options.forEachIndexed { i, v ->
                println("$i. ${v.first}")
            }

            val input = scanner.nextLine()
            if (input.toIntOrNull() != null) {
                val choice = input.toInt()
                if (choice in options.indices) {
                    options[choice].second()
                    return choice == PARAGRAPH_EXIT
                } else {
                    println("Введенного числа нет в списке меню. Попробуйте еще раз...")
                }
            } else {
                println("Необходимо ввести число, соответствующее пунктам меню. Попробуйте еще раз...")
            }
        }
    }

    fun readInput(title: String): String {
        println(title)

        var input = scanner.nextLine().trim()
        while (input == "") {
            println("Ввод не может быть пустым. Попробуйте еще раз...")
            input = scanner.nextLine().trim()
        }

        return input
    }
}