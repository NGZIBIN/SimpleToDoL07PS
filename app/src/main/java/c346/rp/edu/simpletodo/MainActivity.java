package c346.rp.edu.simpletodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
EditText et;
EditText et2;
Button btnAdd;
Button btnClear;
Button btnDel;
ArrayList al;
ArrayAdapter aa;
ListView lv;
Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et = findViewById(R.id.editText);
        btnAdd = findViewById(R.id.btnAdd);
        btnClear = findViewById(R.id.btnClear);
        lv = findViewById(R.id.listView);
        sp = findViewById(R.id.spinner);
        btnDel = findViewById(R.id.btnDel);
        et2 = findViewById(R.id.editText2);

        al = new ArrayList<String>();
        aa = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,al);
        lv.setAdapter(aa);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String addNew = et.getText().toString();
                al.add(addNew);
                aa.notifyDataSetChanged();
            }
        });
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                al.clear();
                aa.notifyDataSetChanged();
            }
        });
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        et.setHint("Type in a new tasks here");
                        btnDel.setEnabled(false);
                        btnAdd.setEnabled(true);
                        break;

                    case 1:
                        et.setHint("Remove a Task");
                        btnAdd.setEnabled(false);
                        btnDel.setEnabled(true);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = Integer.parseInt(et2.getText().toString());
                al.remove(pos);
                aa.notifyDataSetChanged();
                if(al.isEmpty()){
                    Toast.makeText(MainActivity.this,"You don't have any task to remove", Toast.LENGTH_LONG).show();
                }
                else if(pos < 0 || pos > al.size()){
                    Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
