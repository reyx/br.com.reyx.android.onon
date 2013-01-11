package br.com.reyx.android.onon;

import java.io.IOException;
import java.io.InputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParserException;

import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import br.com.reyx.android.internet.Download;
import br.com.reyx.android.onon.models.Home;
import br.com.reyx.android.onon.models.Issue;

public class MainActivity extends Activity {

	public static final String WIFI = "Wi-Fi";
    public static final String ANY = "Any";
    private static final String URL = "http://issues.mobilepublish.com.br/pt-BR/mobile/Home/data.xml";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		loadPage();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
      
    // Uses AsyncTask to download the XML home data from issues.mobilepublish.com.br
    public void loadPage() {
        new DownloadXmlTask().execute(URL);  
    }
    
    // Implementation of AsyncTask used to download XML feed from stackoverflow.com.
    private class DownloadXmlTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            try {
                return loadXmlFromNetwork(urls[0]);
            } catch (IOException e) {
                return getResources().getString(R.string.connection_error);
            } catch (XmlPullParserException e) {
                return getResources().getString(R.string.xml_error);
            }
        }

        @Override
        protected void onPostExecute(String result) {
        	
        }
    }
    
	 // Uploads XML from stackoverflow.com, parses it, and combines it with
	 // HTML markup. Returns HTML string.
    private Home loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
    	InputStream stream = null;
	        
	    try {
	        stream = Download.downloadUrl(urlString);
	        
	        Serializer serializer = new Persister();
	        Home home = serializer.read(Home.class, stream);
	     
	        return home;
     	} catch (Exception e) {
			e.printStackTrace();
		} finally {
     		if (stream != null) {
     			stream.close();
     		} 
     	}
	     
	    return null;
    }
    
	 // Since this is an object collection, use a FragmentStatePagerAdapter,
	 // and NOT a FragmentPagerAdapter.
	 public class DemoCollectionPagerAdapter extends FragmentStatePagerAdapter {
	     public DemoCollectionPagerAdapter(FragmentManager fm) {
	         super(fm);
	     }
	
	     @Override
	     public Fragment getItem(int i) {
	         Fragment fragment = new DemoObjectFragment();
	         Bundle args = new Bundle();
	         // Our object is just an integer :-P
	         args.putInt(DemoObjectFragment.ARG_OBJECT, i + 1);
	         fragment.setArguments(args);
	         return fragment;
	     }
	
	     @Override
	     public int getCount() {
	    	 return 100;
	     }
	
	     @Override
	     public CharSequence getPageTitle(int position) {
	         return "OBJECT " + (position + 1);
	     }
	}
	 
	// Instances of this class are fragments representing a single
	// object in our collection.
	public static class DemoObjectFragment extends Fragment {
	    public static final String ARG_OBJECT = "object";

	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	        // The last two arguments ensure LayoutParams are inflated
	        // properly.
	        View rootView = inflater.inflate(
	                R.layout.activity_main, container, false);
	        Bundle args = getArguments();
	        ((TextView) rootView.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
	        return rootView;
	    }
	}

}
