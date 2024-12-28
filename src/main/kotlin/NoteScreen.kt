import java.util.Scanner

class NoteScreen(val openedArchiveScreen: OpenedArchiveScreen, val note: Note) {
    fun edit() {
        println(note.content)

        val scanner = Scanner(System.`in`)
        note.content += scanner.nextLine()

        openedArchiveScreen.start()
    }
}