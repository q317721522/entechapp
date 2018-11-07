package jason.tcpdemo.funcs;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Handler;

import jason.tcpdemo.MainActivity;
import jason.tcpdemo.R;
import jason.tcpdemo.SetActivity;
import jason.tcpdemo.coms.TcpClient;





public class FuncTcpClient extends Activity {
    public static Context context ;
    private Button btnClientSend;
    private Button btnSet;
    public static  TcpClient tcpClient ;
    private MyBtnClicker myBtnClicker = new MyBtnClicker();
    ExecutorService exec = Executors.newCachedThreadPool();
    private static int i=0;
    private static int erro_log=0;
    private static String sss="00";
    private MyBroadcastReceiver myBroadcastReceiver = new MyBroadcastReceiver();
    private final MyHandler myHandler = new MyHandler(this);
    private class MyBtnClicker implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){

                case R.id.btn_tcpClientSend:

//                    Toast.makeText(FuncTcpClient.this, "2", Toast.LENGTH_LONG).show();
                    try {
                        exec.execute(new Runnable() {
                            @Override
                            public void run() {
                                tcpClient.send(String.valueOf(SetActivity.Set_data));
                            }
                        });
//                    tcpClient.send(String.valueOf(SetActivity.Set_data));

                    }catch (RuntimeException ae)
                    {
                        Toast.makeText(FuncTcpClient.this, "服务器连接失败", Toast.LENGTH_LONG).show();
//                        erro_log=1;
                    }
                    catch (Exception ae)
                    {
                        Toast.makeText(FuncTcpClient.this, "Erro2", Toast.LENGTH_LONG).show();
                    }

                    finally {
//                        Toast.makeText(FuncTcpClient.this, "xx", Toast.LENGTH_LONG).show();
                    }
                    if(sss!="00") {
                        Intent intent = new Intent();
                        intent.setClass(FuncTcpClient.this, StopActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(FuncTcpClient.this, "服务器连接失败", Toast.LENGTH_LONG).show();
                    }
                    break;
                case R.id.btn_tcpset:

                    Intent intent2 = new Intent();
                    intent2.setClass(FuncTcpClient.this, SetActivity.class);
                    startActivity(intent2);
                    break;
            }
        }
    }
    @Override
    public void onBackPressed()
    {
//        Toast.makeText(FuncTcpClient.this, "ok", Toast.LENGTH_LONG).show();
        Intent intent3 = new Intent();
        intent3.setClass(FuncTcpClient.this, MainActivity.class);
        startActivity(intent3);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tcp_client);
        context = this;
        bindID();
        bindListener();
        bindReceiver();
        Ini();
    }
    //接收事件
    private class MyHandler extends android.os.Handler{
        private WeakReference<FuncTcpClient> mActivity;

        MyHandler(FuncTcpClient activity){
            mActivity = new WeakReference<FuncTcpClient>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            if (mActivity != null){
                switch (msg.what){

                    case 1:    sss=msg.obj.toString();
                                  Toast.makeText(FuncTcpClient.this, sss, Toast.LENGTH_LONG).show();
                       break;
                }
            }
        }
    }


    private void bindID(){
        btnClientSend = (Button) findViewById(R.id.btn_tcpClientSend);
//        btnset = (Button) findViewById(R.id.btn_tcpset);
        btnSet=(Button) findViewById(R.id.btn_tcpset);
    }

    private void bindListener(){
        btnClientSend.setOnClickListener(myBtnClicker);
//        btnset.setOnClickListener(myBtnClicker);
        btnSet.setOnClickListener(myBtnClicker);
    }

    private class MyBroadcastReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String mAction = intent.getAction();
            switch (mAction){
                case "tcpClientReceiver":
                    String msg = intent.getStringExtra("tcpClientReceiver");
                    Message message = Message.obtain();
                    message.what = 1;
                    message.obj = msg;
                    myHandler.sendMessage(message);
                    break;
            }
        }
    }
    private void bindReceiver(){
        IntentFilter intentFilter = new IntentFilter("tcpClientReceiver");
        registerReceiver(myBroadcastReceiver,intentFilter);
    }
    private void Ini(){
        btnClientSend.setEnabled(true);
        btnSet.setEnabled(true);
//        Toast.makeText(FuncTcpClient.this, "1", Toast.LENGTH_LONG).show();
        if(i==0) {
                 tcpClient = new TcpClient("114.115.132.62", 8887);
                  exec.submit(tcpClient);
                  i=1;
                  }
//        Toast.makeText(FuncTcpClient.this, "00", Toast.LENGTH_LONG).show();
    }
}



