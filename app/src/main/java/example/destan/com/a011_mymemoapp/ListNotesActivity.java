package example.destan.com.a011_mymemoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class ListNotesActivity extends Activity implements ListView.OnItemClickListener {

    private ArrayAdapter<NoteInfo> m_listAdapter;
    private ListView m_listViewListAll;
    private ArrayList<NoteInfo> notes;
    private NotesDBHelper helper;
    private NoteInfo m_curNote;

    private void initControls(){
        m_listViewListAll = this.findViewById(R.id.LISTNOTESACTIVITY_LISTVIEW_LISTALL);
        m_listViewListAll.setOnItemClickListener(this);
    }

    private void initData(){
        helper = new NotesDBHelper(this);
        notes = helper.getNotes();
        m_listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,notes);
        m_listViewListAll.setAdapter(m_listAdapter);
    }

    private void initialize(){
        this.initControls();
        this.initData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_notes);
        this.initialize();
        m_listAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RequestCodes.ADDNOTE_REQUEST_CODE){
            if(resultCode == RESULT_OK) {
                UtilKt.makeToastLong(this, "Saved.");
                NoteInfo noteInfo = (NoteInfo)data.getSerializableExtra("NOTEINFO");
                m_listAdapter.insert(noteInfo,0);
                m_listAdapter.notifyDataSetChanged();
            }
            else if(requestCode == RequestCodes.UPDATENOTE_REQUEST_CODE){
                if (resultCode == RESULT_OK){
                    //Updated.
                    m_listAdapter.remove(m_curNote);
                    NoteInfo noteInfo = (NoteInfo)data.getSerializableExtra("NOTEINFO");
                    m_listAdapter.insert(noteInfo,0);
                    UtilKt.makeToastLong(this,"Updated.");
                    m_listAdapter.notifyDataSetChanged();
                }
            }
            else
                UtilKt.makeToastLong(this,"Cancelled");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onAddNoteButtonClicked(View view) {
        UtilKt.startActivityForResult(this,AddNoteActivity.class,RequestCodes.ADDNOTE_REQUEST_CODE);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent intent = new Intent(this,AddNoteActivity.class);

        m_curNote = m_listAdapter.getItem(i);
        intent.putExtra("NOTEINFO",m_curNote);
        this.startActivityForResult(intent,RequestCodes.UPDATENOTE_REQUEST_CODE);
    }

}
