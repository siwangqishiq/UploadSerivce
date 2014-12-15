package com.xinlan.uploadfile;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.Header;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;

import network.NetClient;
import network.RequestParams;
import network.TextHttpResponseHandler;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Looper;

public class UploadService extends Service {
	private static ExecutorService threadPool = Executors.newSingleThreadExecutor();//线程池
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		String files[]= intent.getStringArrayExtra("filelist");
		List<String> fileList = new ArrayList<String>();
		for(int i=0;i<files.length;i++){
			fileList.add(files[i]);
		}//end for i
		System.out.println("主线程--->"+Thread.currentThread());
		
		
		threadPool.submit(new UploadTask(fileList.get(0)));
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	private final class UploadTask implements Runnable{
		private String path;
		
		public UploadTask(String path){
			this.path = path;
		}
		
		@Override
		public void run() {
			final String url="http://192.168.0.105:8080/demo1/work";
			HttpClient httpclient= new DefaultHttpClient();
			httpclient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
			HttpPost httpPost= new HttpPost(url);
			HttpParams parms = new BasicHttpParams();  
			 parms.setParameter( "charset", "gbk");  
			 File f= new File( path);
			 MultipartEntity mpEntity = new MultipartEntity(); //文件传输
			 
			/**
			 * HttpParams parms = new BasicHttpParams();  
parms.setParameter( "charset", "gbk");  
HttpClient httpClient = new DefaultHttpClient(parms);  
HttpPost httppost = new HttpPost(uploadURL);  
File f= con.getFileStreamPath( fileName);  
Log. e("my", f.getAbsolutePath());  
MultipartEntity mpEntity = new MultipartEntity();  
FileBody  file= new FileBody(f);  
mpEntity.addPart( "file", file);  
httppost.setEntity(mpEntity);  
			 */
		}
	}//end inner class

	@Override
	public void onDestroy() {
		super.onDestroy();
		threadPool.shutdownNow();
	}
}//end class
