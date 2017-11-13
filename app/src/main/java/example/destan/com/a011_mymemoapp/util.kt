package example.destan.com.a011_mymemoapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.Toast

/**
 * Created by destan on 26.10.2017.
 */

fun startActivity(c: Context, cls: Class<*>)
{
    var intent = Intent(c,cls)
    c.startActivity(intent)
}

fun startActivityForResult(a: Activity,cls: Class<*>,requestCode: Int)
{
    var intent = Intent(a, cls)
    a.startActivityForResult(intent,requestCode)
}

fun makeToastLong(c: Context,msg : String)
{
    Toast.makeText(c,msg,Toast.LENGTH_LONG).show()
}


fun makeToastShort(c: Context,msg : String)
{
    Toast.makeText(c,msg,Toast.LENGTH_SHORT).show()
}