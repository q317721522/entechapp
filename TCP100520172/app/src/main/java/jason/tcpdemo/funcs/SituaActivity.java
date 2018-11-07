package jason.tcpdemo.funcs;

import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

import jason.tcpdemo.R;

public class SituaActivity extends Activity {
    private TextView textViewst,textView6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_situa);
        Ini();
        textViewst.setText("Running");
    }
    private void Ini(){
        textViewst= (TextView)findViewById(R.id.state1);
    }
}
