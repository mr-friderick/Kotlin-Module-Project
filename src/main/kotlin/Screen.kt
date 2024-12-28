interface Screen {
    companion object {
        const val PARAGRAPH_EXIT   = 0
        const val PARAGRAPH_CREATE = 1

        fun <T: ElementNotes> printParagraph(itArchive: Boolean, map: MutableMap<Int, T>) {
            println("Список ${if (itArchive) "архивов" else "заметок"}:")
            println("$PARAGRAPH_EXIT. Выход")
            println("$PARAGRAPH_CREATE. Создать ${if (itArchive) "архив" else "заметку"}")
            map.forEach { (i, v) ->  println("$i. ${v.name}") }
        }
    }

    fun exit()
    fun create()
    fun open(paragraph: Int)
}

