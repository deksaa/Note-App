package example.destan.com.a011_mymemoapp

import java.io.Serializable
import java.time.LocalDateTime
import java.util.*

/**
 * Created by destan on 25.10.2017.
 */
class NoteInfo(title : String,text : String,createDate : Calendar) : Serializable{
    var id : Int
    var Title : String
    var Text : String
    var CreateDate : Calendar = createDate.clone() as Calendar
    var UpdateDate : Calendar = createDate.clone() as Calendar

    init {
        id = 0
        Title = title
        Text = text
        CreateDate = createDate
        UpdateDate = CreateDate
    }

    override fun equals(other: Any?): Boolean {
        var ni : NoteInfo = other as NoteInfo
        return ni.id == id
    }

    override fun toString(): String {
        return "${Title} \n ${UpdateDate.get(Calendar.DAY_OF_MONTH)}.${UpdateDate.get(Calendar.MONTH)}.${UpdateDate.get(Calendar.YEAR)}"
    }

}