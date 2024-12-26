import java.util.Scanner
import kotlin.system.exitProcess

class ArchiveScreen: Screen {
    private val archiveMap = mutableMapOf<Int, Archive>()

    fun start() {
        val scanner = Scanner(System.`in`)
        while (true) {
            Screen.printParagraph(true, archiveMap)
            if (scanner.hasNextInt()) {
                val userChoice = scanner.nextInt()
                when (userChoice) {
                    Screen.PARAGRAPH_EXIT   -> exit()
                    Screen.PARAGRAPH_CREATE -> {
                        create()
                        continue
                    }
                    in archiveMap.keys      -> open()
                    else -> {
                        println("Введенного числа нет в списке меню. Попробуйте еще раз...")
                        continue
                    }
                }

            } else {
                println("Необходимо ввести число, соответствующее пунктам меню. Попробуйте еще раз...")
                scanner.next()
                continue
            }
        }
    }

    override fun exit() {
        exitProcess(0)
    }

    override fun create() {
        val scanner = Scanner(System.`in`)

        println("Введите название архива")
        archiveMap[if (archiveMap.isEmpty()) 2 else archiveMap.keys.last() + 1] = Archive(scanner.nextLine())
    }

    override fun open() {
        TODO("Not yet implemented")
    }
}