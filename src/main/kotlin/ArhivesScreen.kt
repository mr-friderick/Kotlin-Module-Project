import java.util.Scanner
import kotlin.system.exitProcess

class ArchivesScreen: Screen {
    private val archiveMap = mutableMapOf<Int, Archive>()

    fun start() {
        val scanner = Scanner(System.`in`)
        while (true) {
            Screen.printParagraph(true, archiveMap)
            if (scanner.hasNextInt()) {
                when (val userChoice = scanner.nextInt()) {
                    Screen.PARAGRAPH_EXIT   -> exit()
                    Screen.PARAGRAPH_CREATE -> {
                        create()
                        continue
                    }
                    in archiveMap.keys      -> open(userChoice)
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
        var name = scanner.nextLine().trim()
        while (name == "") {
            println("Имя не может быть пустым. Попробуйте еще раз...")
            name = scanner.nextLine().trim()
        }

        archiveMap[if (archiveMap.isEmpty()) 2 else archiveMap.keys.last() + 1] = Archive(name)
    }

    override fun open(paragraph: Int) {
        val openedArchiveScreen = OpenedArchiveScreen(this, archiveMap[paragraph]!!)
        openedArchiveScreen.start()
    }
}