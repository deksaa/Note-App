package example.destan.com.a011_mymemoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddNoteActivity extends Activity {
    private EditText m_editTextTitle,m_editTextText;
    private NoteInfo m_noteInfo = null;
    private Intent m_intent;

    private void initData(){
        m_intent = this.getIntent();
        m_noteInfo = (NoteInfo) m_intent.getSerializableExtra("NOTEINFO");

        if (m_noteInfo != null){
            m_editTextTitle.setText(m_noteInfo.getTitle());
            m_editTextText.setText(m_noteInfo.getText());
        }
    }

    private void initControls()
    {
        m_editTextTitle = this.findViewById(R.id.ADDNOTEACTIVITY_EDITTEXT_TITLE);
        m_editTextText = this.findViewById(R.id.ADDNOTEACTIVITY_EDITTEXT_NOTE);
    }

    private void initialize()
    {
        this.initControls();
        this.initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        this.initialize();
    }

    public void onSaveButtonClicked(View view) {
        Intent intent = new Intent();
        NoteInfo noteInfo;
        NotesDBHelper helper = new NotesDBHelper(this);

        String title = m_editTextTitle.getText().toString().trim();
        String text = m_editTextText.getText().toString();

        if (title.isEmpty()){
            UtilKt.makeToastLong(this,"Title should not be empty.");
            return;
        }

        if (m_noteInfo == null){
            //ADD NEW PART
            noteInfo = new NoteInfo(title,text,new GregorianCalendar());
            helper.insertNote(noteInfo);
            intent.putExtra("NOTEINFO",noteInfo);
        }else{
            //UPDATE PART
            m_noteInfo.setTitle(title);
            m_noteInfo.setText(text);
            m_noteInfo.setUpdateDate(new GregorianCalendar());
            helper.updateNotes(m_noteInfo);
            intent.putExtra("NOTEINFO",m_noteInfo);
        }
        this.setResult(RESULT_OK,intent);
        this.finish();
    }

    public void onCancelButtonClicked(View view) {
        this.finish();
    }
}

