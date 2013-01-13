package br.com.reyx.android.internet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.GetChars;

public class Download {
	
	public static final String ROOT_URL = "http://issues.mobilepublish.com.br/pt-BR/mobile/";
	
	public static InputStream getStringUrl(String urlString) throws IOException {
	    URL url = new URL(urlString);
	    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	    conn.setReadTimeout(10000 /* milliseconds */);
	    conn.setConnectTimeout(15000 /* milliseconds */);
	    conn.setRequestMethod("GET");
	    conn.setDoInput(true);
	    // Starts the query
	    conn.connect();
	    return conn.getInputStream();
	}
	
	public static Bitmap getBitmapFromURL(ContentResolver cr, String src, String path) {
        try {
        	File file = new File(path);
        	if (!file.exists()) {
        		File filePath = new File(file.getParent());
        		filePath.mkdirs();
        		
        		URL url = new URL(src);
            	Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            	
            	if (file.createNewFile()) {
            		FileOutputStream fOut = new FileOutputStream(file);
            		
            		// 100 means no compression, the lower you go, the stronger the compression
                	image.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                	fOut.flush();
                	fOut.close();
            	}            	

            	// MediaStore.Images.Media.insertImage(cr, file.getAbsolutePath(), file.getName(), file.getName());
            	
            	return image;
        	} else {
        		FileInputStream fi = new FileInputStream(file);
        		return BitmapFactory.decodeStream(fi);
        	}
        } catch (IOException e) {
        	File file = new File(path);
        	file.delete();
            e.printStackTrace();
            return null;
        }
    }
}
