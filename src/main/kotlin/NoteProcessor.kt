import kotlin.system.exitProcess

class NoteProcessor {
    private val archives = mutableListOf<Archive>()
    private val screenMenu = ScreenMenu()

    fun start() {
        while (true) {
            openArchivesMenu()
        }
    }

    private fun openArchivesMenu() {
        while (true) {
            val listArchive = archives.map { it.name to { openNotesMenu(it) } }.toMutableList()
            // Решил, что будет удобно, если Выход всегда будет на 0, а создание элемента на 1 в меню выбора
            listArchive.addFirst("Создать архив" to { createArchive() })
            listArchive.addFirst("Выход" to { exit() })

            val exit = screenMenu.open(
                true,
                listArchive
            )
            if (exit) break
        }
    }

    private fun createArchive() {
        val name = screenMenu.readInput("Введите название архива:")
        archives.add(Archive(name))
    }

    private fun openNotesMenu(archive: Archive) {
        while (true) {
            val listNote = archive.notes.map { it.name to { showAndEditNote(it) } }.toMutableList()
            listNote.addFirst("Создать заметку" to { createNote(archive) })
            listNote.addFirst("Выход" to {})

            val exit = screenMenu.open(
                false,
                listNote
            )
            if (exit) break
        }
    }

    private fun createNote(archive: Archive) {
        val title = screenMenu.readInput("Введите название заметки:")
        val content = screenMenu.readInput("Введите содержание заметки:")

        archive.notes.add(Note(title, content))
    }

    private fun showAndEditNote(note: Note) {
        println("\nСодержание: ${note.content}")
        note.content += screenMenu.readInput("Дополните заметку:")
    }

    private fun exit() {
        exitProcess(0)
    }
}