import java.util.Scanner

class OpenedArchiveScreen(val archivesScreen: ArchivesScreen, val archive: Archive): Screen {
    private val noteMap = mutableMapOf<Int, Note>()

    init {
        archive.notes.forEach { note -> noteMap[if (noteMap.isEmpty()) 2 else noteMap.keys.last() + 1] = note }
    }

    fun start() {
        val scanner = Scanner(System.`in`)
        while (true) {
            Screen.printParagraph(false, noteMap)
            if (scanner.hasNextInt()) {
                when (val userChoice = scanner.nextInt()) {
                    Screen.PARAGRAPH_EXIT   -> exit()
                    Screen.PARAGRAPH_CREATE -> {
                        create()
                        continue
                    }
                    in noteMap.keys         -> open(userChoice)
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
        archivesScreen.start()
    }

    override fun create() {
        val scanner = Scanner(System.`in`)

        println("Введите название заметки")
        var name = scanner.nextLine().trim()
        while (name == "") {
            println("Имя не может быть пустым. Попробуйте еще раз...")
            name = scanner.nextLine().trim()
        }
        val note = Note(name)

        println("Введите содержание заметки")
        var content = scanner.nextLine().trim()
        while (content == "") {
            println("Содержание не может быть пустым. Попробуйте еще раз...")
            content = scanner.nextLine().trim()
        }
        note.content = content

        noteMap[if (noteMap.isEmpty()) 2 else noteMap.keys.last() + 1] = note
        archive.notes.add(note)
    }

    override fun open(paragraph: Int) {
        val noteScreen = NoteScreen(this, noteMap[paragraph]!!)
        noteScreen.edit()
    }
}