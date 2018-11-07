package jason.tcpdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jason.tcpdemo.coms.TcpClient;
import jason.tcpdemo.funcs.FuncTcpClient;
import jason.tcpdemo.funcs.StopActivity;

public class SetActivity extends Activity {
    private Button btnSet;
    public static Context context ;
    private Button btnClientSend2;
    ExecutorService exec = Executors.newCachedThreadPool();
    public static TcpClient tcpClient=new TcpClient("114.115.132.62", 8887); ;
    private SetActivity.MyBtnClicker myBtnClicker = new SetActivity.MyBtnClicker();
    SeekBar seekBar5,seekBar;
    private TextView txtRcv2,txtRcv;
    public static int Set_data=210,Set_Size=2,Set_num=10;
    @Override
    public View findViewById(int id) {
        return super.findViewById(id);
    }

    private class MyBtnClicker implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btn_tcpset3:

//                    exec.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            tcpClient.send("ss");
//                        }
//                    });

                    Set_data=Set_Size*100+Set_num;

                    Intent intent = new Intent();
                    intent.setClass(SetActivity.this, FuncTcpClient.class);
                    startActivity(intent);
                    break;
            }
        }

    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        context = this;
        bindID();
        bindListener();
        Ini();
//        SIZE滑条1
        seekBar5.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                if(i==1) {
                    Set_Size=i;
                    txtRcv2.setText("   Min ");
                }else if(i==2){
                    Set_Size=i;
                    txtRcv2.setText("Regular");
                }else if(i==3){
                    Set_Size=i;
                    txtRcv2.setText("  Large ");
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        QTY滑条
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Set_num=i;
                txtRcv.setText(String.valueOf(i));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    private void bindListener(){
        btnSet.setOnClickListener(myBtnClicker);
    }

    private void bindID(){
        btnSet = (Button) findViewById(R.id.btn_tcpset3);
        seekBar5=(SeekBar)findViewById(R.id.seekBar5);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        txtRcv2 = (TextView) findViewById(R.id.textView2);
        txtRcv=(TextView) findViewById(R.id.textView);
        }
    private void Ini() {
        btnSet.setEnabled(true);
        Set_Size=2;Set_num=10;
    }
}
