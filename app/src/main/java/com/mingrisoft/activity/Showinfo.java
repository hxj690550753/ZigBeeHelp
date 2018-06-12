package com.mingrisoft.activity;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mingrisoft.dao.FlagDAO;
import com.mingrisoft.dao.InaccountDAO;
import com.mingrisoft.dao.OutaccountDAO;
import com.mingrisoft.model.Tb_flag;
import com.mingrisoft.model.Tb_inaccount;
import com.mingrisoft.model.Tb_outaccount;

public class Showinfo extends Activity {
	public static final String FLAG = "id";// 定义一个常量，用来作为请求码
	Button btnoutinfo, btnininfo, btnflaginfo;// 创建3个Button对象
	ListView lvinfo;// 创建ListView对象
	String strType = "";// 创建字符串，记录管理类型

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showinfo);// 设置布局文件

		lvinfo = (ListView) findViewById(R.id.lvinfo);// 获取布局文件中的ListView组件
		btnoutinfo = (Button) findViewById(R.id.btnoutinfo);// 获取布局文件中的报废传感器按钮
		btnininfo = (Button) findViewById(R.id.btnininfo);// 获取布局文件中的新增传感器按钮
		btnflaginfo = (Button) findViewById(R.id.btnflaginfo);// 获取布局文件中的便签信息按钮

		//ShowInfo(R.id.btnoutinfo);// 默认显示支出信息

		btnoutinfo.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ShowInfo(R.id.btnoutinfo);// 显示报废传感器
					}
				});

		btnininfo.setOnClickListener(new OnClickListener() {// 为收入信息按钮设置监听事件
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ShowInfo(R.id.btnininfo);// 显示新增传感器
					}
				});
		btnflaginfo.setOnClickListener(new OnClickListener() {// 为便签信息按钮设置监听事件
					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						ShowInfo(R.id.btnflaginfo);// 显示便签信息
					}
				});

		lvinfo.setOnItemClickListener(new OnItemClickListener(){// 为ListView添加项单击事件
			// 重写onItemClick方法
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String strInfo = String.valueOf(((TextView) view).getText());// 记录单击的项信息
				String strid = strInfo.substring(0, strInfo.indexOf('|'));// 从项信息中截取编号
				Intent intent = null;// 创建Intent对象
				if (strType == "btnflaginfo") {// 判断如果是便签信息
					intent = new Intent(Showinfo.this, FlagManage.class);// 使用FlagManage窗口初始化Intent对象
					intent.putExtra(FLAG, strid);// 设置要传递的数据
					startActivity(intent);// 执行Intent，打开相应的Activity
				}
				if (strType == "btnininfo") {// 判断如果是新增传感器
					intent = new Intent(Showinfo.this, InfoManage.class);// 使用FlagManage窗口初始化Intent对象
					intent.putExtra(FLAG, strid);// 设置要传递的数据
					startActivity(intent);// 执行Intent，打开相应的Activity
				}
				if (strType == "btnoutinfo") {// 判断如果是报废传感器
					intent = new Intent(Showinfo.this, InfoManage.class);// 使用FlagManage窗口初始化Intent对象
					intent.putExtra(FLAG, strid);// 设置要传递的数据
					startActivity(intent);// 执行Intent，打开相应的Activity
				}
			}
		});
	}

	private void ShowInfo(int intType) {// 用来根据传入的管理类型，显示相应的信息
		String[] strInfos = null;// 定义字符串数组，用来存储收入信息
		ArrayAdapter<String> arrayAdapter = null;// 创建ArrayAdapter对象
		Intent intent = null;// 创建Intent对象
		switch (intType) {// 以intType为条件进行判断
		case R.id.btnoutinfo:// 如果是报废按钮btnoutinfo
			strType = "outinfo";// 为strType变量赋值
			/*
			intent = new Intent(Showinfo.this, TotalChart.class);// 使用TotalChart窗口初始化Intent对象
			intent.putExtra("passType", strType);// 设置要传递的数据
			startActivity(intent);// 执行Intent，打开相应的Activity
			*/
			OutaccountDAO outinfo = new OutaccountDAO(Showinfo.this);// 创建FlagDAO对象
			// 获取所有便签信息，并存储到List泛型集合中
			List<Tb_outaccount> listOutinfo = outinfo.getScrollData(0,
					(int) outinfo.getCount());
			strInfos = new String[listOutinfo.size()];// 设置字符串数组的长度
			int n_out = 0;// 定义一个开始标识
			for (Tb_outaccount tb_inaccount : listOutinfo) {// 遍历List泛型集合
				// 将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
				strInfos[n_out] = "** 条目"+tb_inaccount.getid() + "\n ID| "
						+ String.valueOf(tb_inaccount.getMoney()) + "\n Project| "
						+ tb_inaccount.getType() + "\n Type| "
						+ tb_inaccount.getAddress()+ "\n Infomation| "
						+ tb_inaccount.getMark() + "\n Time| "
						+ tb_inaccount.getTime();
				n_out++;// 标识加1
			}

			// 使用字符串数组初始化ArrayAdapter对象
			arrayAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, strInfos);
			lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
			break;
		case R.id.btnininfo:// 如果是新增按钮btnininf
			strType = "ininfo";// 为strType变量赋值
			/*
			intent = new Intent(Showinfo.this, TotalChart.class);// 使用TotalChart窗口初始化Intent对象
			intent.putExtra("passType", strType);// 设置要传递的数据
			startActivity(intent);// 执行Intent，打开相应的Activity
			*/
			InaccountDAO ininfo = new InaccountDAO(Showinfo.this);// 创建FlagDAO对象
			// 获取所有便签信息，并存储到List泛型集合中
			List<Tb_inaccount> listIninfo = ininfo.getScrollData(0,
					(int) ininfo.getCount());
			strInfos = new String[listIninfo.size()];// 设置字符串数组的长度
			int n_in = 0;// 定义一个开始标识
			for (Tb_inaccount tb_inaccount : listIninfo) {// 遍历List泛型集合
				// 将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
				strInfos[n_in] = "** 条目"+tb_inaccount.getid() + "\n ID| "
						+ String.valueOf(tb_inaccount.getMoney()) + "\n Project| "
						+ tb_inaccount.getType() + "\n Type| "
						+ tb_inaccount.getHandler()+ "\n Infomation| "
						+ tb_inaccount.getMark() + "\n Time| "
						+ tb_inaccount.getTime();
				n_in++;// 标识加1
			}

			// 使用字符串数组初始化ArrayAdapter对象
			arrayAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, strInfos);
			lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
			break;
		case R.id.btnflaginfo:// 如果是btnflaginfo按钮
			strType = "btnflaginfo";// 为strType变量赋值
			FlagDAO flaginfo = new FlagDAO(Showinfo.this);// 创建FlagDAO对象
			// 获取所有便签信息，并存储到List泛型集合中
			List<Tb_flag> listFlags = flaginfo.getScrollData(0,
					(int) flaginfo.getCount());
			strInfos = new String[listFlags.size()];// 设置字符串数组的长度
			int n = 0;// 定义一个开始标识
			for (Tb_flag tb_flag : listFlags) {// 遍历List泛型集合
				// 将便签相关信息组合成一个字符串，存储到字符串数组的相应位置
				strInfos[n] = tb_flag.getid() + "|" + tb_flag.getFlag();
				if (strInfos[n].length() > 15)// 判断便签信息的长度是否大于15
					strInfos[n] = strInfos[n].substring(0, 15) + "……";// 将位置大于15之后的字符串用……代替
				n++;// 标识加1
			}
			
			// 使用字符串数组初始化ArrayAdapter对象
			arrayAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_list_item_1, strInfos);
			lvinfo.setAdapter(arrayAdapter);// 为ListView列表设置数据源
			break;
		}
	}

}
