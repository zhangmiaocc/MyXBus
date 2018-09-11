package com.zm.myxbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.mcxiaoke.bus.Bus;
import com.mcxiaoke.bus.annotation.BusReceiver;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 注册
        Bus.getDefault().register(this);

        /**
         *  BusEvent: String event
         *  BusEvent: Object event
         *  顺序不固定
         */
//        Bus.getDefault().post("sss");

        /**
         *  BusEvent: Object event
         *  BusEvent: SomeEvent event
         *  顺序不固定
         */
        Bus.getDefault().post(new SomeEvent(2));

        /**
         *  BusEvent: Object event
         */
//        Bus.getDefault().post(new Object());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // 取消注册
        Bus.getDefault().unregister(this);
    }

    /**
     * handle your event
     * 这里处理事件
     */
    @BusReceiver
    public void onStringEvent(String event) {
        Log.d("BusEvent", "String event");
    }

    /**
     * SomeEventClass表示任意的自定义类
     * handle your event
     * 这里处理事件
     */
    @BusReceiver
    public void onSomeEvent(SomeEvent event) {
        if (event.i == 1) {
            Log.d("BusEvent", "" + event.i);
        } else {
            Log.d("BusEvent", "1111");
        }
    }

    /**
     * 不建议使用Object，会收到所有类型的事件
     * handle your event
     * 这里处理事件
     */
    @BusReceiver
    public void onObjectEvent(Object event) {
        Log.d("BusEvent", "Object event");
    }
}
