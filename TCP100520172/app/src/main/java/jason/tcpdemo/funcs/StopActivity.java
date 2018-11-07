package jason.tcpdemo.funcs;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

import jason.tcpdemo.R;

public class StopActivity extends Activity {

    public static Context context ;
    private Button btnClientSend2;
    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    private class MyBtnClicker implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_tcpClientSend2:
                    Intent intent = new Intent();
                    intent.setClass(StopActivity.this, FuncTcpClient.class);
                    startActivity(intent);
                    break;
            }
        }

    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        context = this;
        bindID();
        bindListener();
        Ini();
    }
    private void bindListener(){
        btnClientSend2.setOnClickListener(myBtnClicker);
    }

    private void bindID(){
        btnClientSend2 = (Button) findViewById(R.id.btn_tcpClientSend2);
    }
    private void Ini() {
        btnClientSend2.setEnabled(true);
    }
}
