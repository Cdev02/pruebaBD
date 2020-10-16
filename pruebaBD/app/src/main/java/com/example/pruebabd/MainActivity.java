package com.example.pruebabd;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lvResultados;
    ArrayList<String>listado=new ArrayList<>();
    myClass myClass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myClass=new myClass(this);
        myClass.startWork();
        lvResultados=findViewById(R.id.lvResultados);
        Desplegar();

    }
    private ArrayList<String> ListaUnidades() {
        Cursor myCursor;
        ArrayList<String>datos=new ArrayList<>();
        SQLiteDatabase db=myClass.getWritableDatabase();
        myCursor=db.rawQuery("select * from cliente",null);
        if(myCursor.moveToFirst()){
            do{
                String line=myCursor.getString(0)+"\n"+myCursor.getString(1)+"\n"+myCursor.getString(2)+
                        "\n"+myCursor.getString(3)+"\n"+myCursor.getInt(4)+"\n";
                        datos.add(line);
            }while(myCursor.moveToNext());
        }
        db.close();
        return datos;
    }
    private void Desplegar(){
        listado=ListaUnidades();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,listado);
        lvResultados.setAdapter(adapter);
    }
}
