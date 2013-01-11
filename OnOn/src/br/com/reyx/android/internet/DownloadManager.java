package br.com.reyx.android.internet;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

public class DownloadManager extends AsyncTask<String, String, String> {
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(String... aurl) {
		int count;

		try {
			URL url = new URL(aurl[0]);
			URLConnection connection = url.openConnection();
			connection.connect();
		
			int lenghtOfFile = connection.getContentLength();
			Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);

			InputStream input = new BufferedInputStream(url.openStream());
			OutputStream output = new FileOutputStream(Environment.getExternalStorageDirectory().getPath() + "/some_photo_from_gdansk_poland.jpg");
		
			byte data[] = new byte[1024];
		
			long total = 0;
	
			while ((count = input.read(data)) != -1) {
				total += count;
				publishProgress("" + (int)((total*100) / lenghtOfFile));
				output.write(data, 0, count);
			}
	
			output.flush();
			output.close();
			input.close();
		} catch (Exception e) {}
		
		return null;
	}
	
	protected void onProgressUpdate(String... progress) {
		 Log.d("ANDRO_ASYNC", progress[0]);
	}

	@Override
	protected void onPostExecute(String unused) {
		
	}
}