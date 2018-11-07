package jason.tcpdemo.funcs;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import jason.tcpdemo.MainActivity;
import jason.tcpdemo.R;


public class AdminiActivity extends Activity {

    private ListView listView1;
    private static final String[] CONTENTS = { "ADEN" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admini);
        init();
        Toast.makeText(AdminiActivity.this, "One Device", Toast.LENGTH_LONG).show();
    }

    private void  init(){
        listView1 = (ListView) findViewById(R.id.equip);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,CONTENTS);
        listView1.setAdapter(adapter);
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(AdminiActivity.this, CONTENTS[i], Toast.LENGTH_LONG).show();
                switch (i){
                    case 0: Intent intentst = new Intent();
                        intentst.setClass(AdminiActivity.this, SituaActivity.class);
                        startActivity(intentst);
                        break;
                }
            }

        });
    }


}
