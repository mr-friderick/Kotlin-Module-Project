abstract class ElementNotes(val name: String)

class Archive(name: String) : ElementNotes(name) {
    val notes: MutableList<Note> = mutableListOf()
}

class Note(name: String, var content: String) : ElementNotes(name)