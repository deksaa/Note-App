package example.destan.com.a011_mymemoapp

import android.content.Context

/**
 * Created by destan on 25.10.2017.
 */

private var g_notes = ArrayList<NoteInfo>()
private var g_curId : Int = 0

class NotesDBHelper(c:Context){
    private var m_context:Context = c

    fun insertNote(ni : NoteInfo) : Boolean
    {
        if (g_curId == Int.MAX_VALUE)
            return false

        ni.id = ++g_curId
        g_notes.add(ni)

        return true
    }

    fun updateNotes(ni : NoteInfo) : Boolean{
        var index = g_notes.indexOf(ni)

        if (index == -1)
            return false

        g_notes.set(index,ni)

        return true
    }

    fun getNotes() : ArrayList<NoteInfo>{
        return g_notes;
    }
}