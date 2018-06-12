package com.mingrisoft.activity;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.mingrisoft.dao.OutaccountDAO;
import com.mingrisoft.model.Tb_outaccount;

//报废传感器
public class AddOutaccount extends Activity {
	protected static final int DATE_DIALOG_ID = 0;// 创建日期对话框常量
	EditText txtMoney, txtTime, txtMark;// 创建4个EditText对象
	Spinner spType,txtAddress;// 创建Spinner对象
	Button btnSaveButton;// 创建Button对象“保存”
	Button btnCancelButton;// 创建Button对象“取消”

	private int mYear;// 年
	private int mMonth;// 月
	private int mDay;// 日

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addoutaccount);// 设置布局文件
		txtMoney = (EditText) findViewById(R.id.txtMoney);// 获取金额文本框
		txtTime = (EditText) findViewById(R.id.txtTime);// 获取时间文本框
		txtAddress = (Spinner) findViewById(R.id.spType2);// 获取地点文本框
		txtMark = (EditText) findViewById(R.id.txtMark);// 获取备注文本框
		spType = (Spinner) findViewById(R.id.spType);// 获取类别下拉列表
		btnSaveButton = (Button) findViewById(R.id.btnSave);// 获取保存按钮
		btnCancelButton = (Button) findViewById(R.id.btnCancel);// 获取取消按钮

		txtTime.setOnClickListener(new OnClickListener() {// 为时间文本框设置单击监听事件

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				showDialog(DATE_DIALOG_ID);// 显示日期选择对话框
			}
		});

		btnSaveButton.setOnClickListener(new OnClickListener() {// 为保存按钮设置监听事件

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						String strMoney = txtMoney.getText().toString();// 获取传感器ID
						if (!strMoney.isEmpty()) {// 判断传感器ID不为空
							// 创建OutaccountDAO对象
							OutaccountDAO outaccountDAO = new OutaccountDAO(
									AddOutaccount.this);
							// 创建Tb_outaccount对象
							Tb_outaccount tb_outaccount = new Tb_outaccount(
									outaccountDAO.getMaxId() + 1, strMoney, txtTime
											.getText().toString(), spType
											.getSelectedItem().toString(),
									txtAddress.getSelectedItem().toString(), txtMark
											.getText().toString());
							outaccountDAO.add(tb_outaccount);// 添加报废传感器
							// 弹出信息提示
							Toast.makeText(AddOutaccount.this, "〖报废传感器〗数据添加成功！",
									Toast.LENGTH_SHORT).show();
						} else {
							Toast.makeText(AddOutaccount.this, "请输入传感器ID！",
									Toast.LENGTH_SHORT).show();
						}
					}
				});

		btnCancelButton.setOnClickListener(new OnClickListener() {// 为取消按钮设置监听事件

					@Override
					public void onClick(View arg0) {
						// TODO Auto-generated method stub
						txtMoney.setText("");// 设置金额文本框为空
						txtMoney.setHint("15111xxx");// 为金额文本框设置提示
						txtTime.setText("");// 设置时间文本框为空
						txtTime.setHint("2011-01-01");// 为时间文本框设置提示
						txtAddress.setSelection(0);// 设置地点文本框为空
						txtMark.setText("");// 设置备注文本框为空
						spType.setSelection(0);// 设置类别下拉列表默认选择第一项
					}
				});

		final Calendar c = Calendar.getInstance();// 获取当前系统日期
		mYear = c.get(Calendar.YEAR);// 获取年份
		mMonth = c.get(Calendar.MONTH);// 获取月份
		mDay = c.get(Calendar.DAY_OF_MONTH);// 获取天数

		updateDisplay();// 显示当前系统时间
	}

	@Override
	protected Dialog onCreateDialog(int id){// 重写onCreateDialog方法
		switch (id) {
		case DATE_DIALOG_ID:// 弹出日期选择对话框
			return new DatePickerDialog(this, mDateSetListener, mYear, mMonth,
					mDay);
		}
		return null;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			mYear = year;// 为年份赋值
			mMonth = monthOfYear;// 为月份赋值
			mDay = dayOfMonth;// 为天赋值
			updateDisplay();// 显示设置的日期
		}
	};

	private void updateDisplay() {
		// 显示设置的时间
		txtTime.setText(new StringBuilder().append(mYear).append("-")
				.append(mMonth + 1).append("-").append(mDay));
	}
}
