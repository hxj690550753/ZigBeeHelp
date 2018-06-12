package com.mingrisoft.activity;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

public class text extends Activity {
	EditText txtFlag;// 创建EditText组件对象
	Button btnflagSaveButton;// 创建Button组件对象
	Button btnflagCancelButton;// 创建Button组件对象

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		WebView webview=(WebView)findViewById(R.id.webView1);//获取布局管理器中添加的WebView组件
		//创建一个字符串构建器，将要显示的HTML内容放置在该构建器中
		StringBuilder sb=new StringBuilder();
		sb.append("<div>《ZigBee环境监测助手》使用帮助：</div>");
		sb.append("<ul>");
		sb.append("<li>修改密码：选择“系统设置”模块可以修改登录密码，项目运行时，默认没有密码。</li>");
		sb.append("<li>新增传感器：选择“新增传感器”模块可以添加传感器信息；选择“查看已有传感器”模块可以查看、修改或删除传感器信息。</li>");
		sb.append("<li>报废传感器：选择“报废传感器”模块可以添加报废传感器信息；选择“已报废传感器”模块可以查看、修改或删除已报废传感器信息。</li>");
		sb.append("<li>便签管理：选择“传感器管理便签”模块可以添加传感器管理便签信息；</li>");
		sb.append("<li>退出系统：选择“退出”模块可以退出《ZigBee环境监测助手通》项目。</li>");
		sb.append("</ul>");
		webview.loadDataWithBaseURL(null, sb.toString(),"text/html","utf-8",null);//加载数据
	}
}
