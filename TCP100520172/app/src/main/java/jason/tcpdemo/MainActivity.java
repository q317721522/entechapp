package jason.tcpdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jason.tcpdemo.coms.TcpClient;
import jason.tcpdemo.funcs.AdminiActivity;
import jason.tcpdemo.funcs.FuncTcpClient;
import jason.tcpdemo.funcs.FuncTcpServer;

public class MainActivity extends Activity {

    private RadioButton radioBtnServer,radioBtnClient;
    private Button btnFuncEnsure,btnFuncEnsure2;
//    private TextView txtShowFunc;
//    private MyRadioButtonCheck myRadioButtonCheck = new MyRadioButtonCheck();
    private MyButtonClick myButtonClick = new MyButtonClick();

    public static  TcpClient tcpClient =new TcpClient("114.115.132.62",8887);;

    ExecutorService exec = Executors.newCachedThreadPool();

    private Button btnClientSend;



    private class MyButtonClick implements Button.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_FunctionEnsure:
//                        tcpClient = new TcpClient("114.115.132.62",8887);
//                        exec.execute(tcpClient);
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, FuncTcpClient.class);
                        startActivity(intent);
                        break;
                case R.id.btn_FunctionEnsure2:
                    Intent intent2 = new Intent();
                    intent2.setClass(MainActivity.this, AdminiActivity.class);
                    startActivity(intent2);
                    break;


             }
        }
    }

    //退出程序
    public void exit(View view)
    {
        android.os.Process.killProcess(android.os.Process.myPid());
    }
    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
//
//    private long lastBack = 0;
//    @Override
//    public void onBackPressed()
//    {
//        if (lastBack == 0 || System.currentTimeMillis() - lastBack > 2000) {
//            Toast.makeText(MainActivity.this, "再按一次返回退出程序", Toast.LENGTH_SHORT).show();
//            lastBack = System.currentTimeMillis();
//            return;
//        }
//        android.os.Process.killProcess(android.os.Process.myPid());
//
////        super.onBackPressed();
//    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.function);
        bindID();
        bindListener();
    }

    private void bindID() {
//        radioBtnServer = (RadioButton) findViewById(R.id.radio_Server);
//        radioBtnClient = (RadioButton) findViewById(R.id.radio_Client);
        btnFuncEnsure2 = (Button) findViewById(R.id.btn_FunctionEnsure2);
        btnFuncEnsure = (Button) findViewById(R.id.btn_FunctionEnsure);
//        txtShowFunc = (TextView) findViewById(R.id.txt_ShowFunction);
    }

    private void bindListener(){
//        radioBtnClient.setOnCheckedChangeListener(myRadioButtonCheck);
//        radioBtnServer.setOnCheckedChangeListener(myRadioButtonCheck);
        btnFuncEnsure2.setOnClickListener(myButtonClick);
        btnFuncEnsure.setOnClickListener(myButtonClick);
    }


}
