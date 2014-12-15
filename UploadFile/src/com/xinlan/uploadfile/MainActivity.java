package com.xinlan.uploadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;

import network.NetClient;
import network.RequestParams;
import network.TextHttpResponseHandler;
import lib.picturechooser.SelectPictureActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	public static final int SELECT_FROM_ABLUM = 7;
	private TextView list;
	private Button selectBtn;
	private Button uploadBtn;

	private List<String> fileList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		list = (TextView) findViewById(R.id.list);
		selectBtn = (Button) findViewById(R.id.select);
		uploadBtn = (Button) findViewById(R.id.upload);

		selectBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.this.startActivityForResult(new Intent(
						MainActivity.this, SelectPictureActivity.class),
						SELECT_FROM_ABLUM);
			}
		});
		uploadBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent it = new Intent();
				it.setClass(MainActivity.this, UploadService.class);
				StringBuilder sb = new StringBuilder();
				String[] lists = new String[fileList.size()];
				for (int i = 0; i < fileList.size(); i++) {
					lists[i] = fileList.get(i);
				}// end for
				it.putExtra("filelist", lists);
				System.out.println("start service");
				MainActivity.this.startService(it);
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK) {
			switch (requestCode) {
			case SELECT_FROM_ABLUM:// Ñ¡ÔñÍ¼Æ¬·µ»Ø
				String picPath = data.getStringExtra("imgPath");
				fileList.add(picPath);

				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < fileList.size(); i++) {
					sb.append(fileList.get(i)).append("\n");
				}// end for i
				System.out.println(sb);
				list.setText(sb.toString());

				// RequestParams params = new RequestParams();
				// try {
				// params.put("file1", new File(picPath));
				// } catch (FileNotFoundException e) {
				// e.printStackTrace();
				// }
				// NetClient.post("http://192.168.0.105:8080/demo1/work",
				// params, new TextHttpResponseHandler() {
				//
				// @Override
				// public void onSuccess(int statusCode, Header[] headers,
				// String responseString) {
				// System.out.println(responseString);
				// }
				//
				// @Override
				// public void onFailure(int statusCode, Header[] headers,
				// String responseString, Throwable throwable) {
				// System.out.println(responseString);
				// }
				// });
				break;
			}// end switch
		}
	}
}
