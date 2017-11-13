package example.destan.com.a011_mymemoapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_main_action,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == RequestCodes.ADDNOTE_REQUEST_CODE){
            if(resultCode == RESULT_OK)
                UtilKt.makeToastLong(this,"Saved");
            else
                UtilKt.makeToastLong(this,"Cancelled");
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void onAddNoteButtonClicked(View v){
        UtilKt.startActivityForResult(this,AddNoteActivity.class,RequestCodes.ADDNOTE_REQUEST_CODE);
    }

    public void onListAllNotesButtonClicked(View view) {
        UtilKt.startActivity(this,ListNotesActivity.class);
    }

    public void onExitButtonClicked(View v){
        this.finish();
    }
}
