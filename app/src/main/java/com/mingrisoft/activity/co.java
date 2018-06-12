package com.mingrisoft.activity;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import android.os.Handler;
import android.os.Message;

public class co extends Activity {
	TextView data;
	TextView time;
	private static final int msgKey1 = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.co);
		data=(TextView)findViewById(R.id.co_Date);//获取布局管理器中添加的WebView组件
		time=(TextView)findViewById(R.id.co_Time);//获取布局管理器中添加的WebView组件
		//new TimeThread().start();
	}

	public class TimeThread extends  Thread{
		@Override
		public void run() {
			super.run();
			do{
				try {
					Thread.sleep(1000);
					Message msg = new Message();
					msg.what = msgKey1;
					mHandler.sendMessage(msg);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}while (true);
		}
	}
	private Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what){
				case msgKey1:
					SimpleDateFormat    formatter    =   new    SimpleDateFormat    ("yyyy年MM月dd日    HH:mm:ss     ");
					Date    curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
					String    str    =    formatter.format(curDate);
					time.setText(str);
					break;
				default:
					break;
			}
		}
	};
}
