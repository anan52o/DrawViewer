package com.guangcheng.img.drawviewer;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {


    private static final String TAG = "MainActivity";
    private DrawViewer dv = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onHanlderTest();
    }
    private final static int MSG_UPDATE_START  = 1;
    private final static int MSG_UPDATE_STOP   = 2;
    private final static int MSG_UPDATE_REGION = 3;
    private void onHanlderTest(){
//        dv = new DrawViewer(this);
//        new Thread(new myTestThread()).start();
        CustomView customView = new CustomView(this);
        customView.setLayoutParams(new ViewGroup.LayoutParams(200,300));
        this.setContentView(customView);
    }


    Handler myHandler = new Handler(){

        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case MSG_UPDATE_START:
                    Log.i(TAG,"revice MSG_UPDATE_START msg");
                    break;
                case MSG_UPDATE_STOP:
                    Log.i(TAG,"revice MSG_UPDATE_STOP msg");
                    break;
                case MSG_UPDATE_REGION:
                    Log.i(TAG,"revice MSG_UPDATE_REGION msg");
                    /**
                     * Invalidate the whole view. If the view is visible,
                     * onDraw(Canvas) will be called at some point in the future.
                     * This must be called from a UI thread. To call from a non-UI
                     * thread, call postInvalidate().
                     */
                    dv.invalidate();//实际调用的函数是DrawViewer中的onDraw()函数
                    break;

            }

            super.handleMessage(msg);
        }
    };


    class myTestThread implements Runnable{

        private void sendMsg(int what){
            Message message = new Message();
            message.what = what;
            myHandler.sendMessage(message);

        }


        @Override
        public void run() {
            this.sendMsg(MSG_UPDATE_START);
            while (!Thread.currentThread().isInterrupted()){

                try{
                    this.sendMsg(MSG_UPDATE_REGION);
                    Thread.sleep(500);
                }catch (InterruptedException e){
                    Thread.currentThread().interrupt();
                }

                this.sendMsg(MSG_UPDATE_STOP);

            }
        }
    }



}
