package br.com.reyx.android.onon;

import java.io.IOException;
import java.io.InputStream;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.xmlpull.v1.XmlPullParserException;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import br.com.reyx.android.internet.Download;
import br.com.reyx.android.onon.models.Home;
import br.com.reyx.android.onon.models.HomeIssue;
import br.com.reyx.android.onon.models.Issue;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MainActivity extends FragmentActivity implements ActionBar.TabListener {

	public static final String WIFI = "Wi-Fi";
	public static final String ANY = "Any";
	private static final String HOME_URL = Download.ROOT_URL + "Home/";
	private static final String HOME_DATA_URL = HOME_URL + "data.xml";
	private static final String HOME_IMAGE_URL = HOME_URL + "media/featured/";

	Home home;    
	DemoCollectionPagerAdapter mDemoCollectionPagerAdapter;
	ViewPager mViewPager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mDemoCollectionPagerAdapter = new DemoCollectionPagerAdapter(getSupportFragmentManager());

		loadPage();
	}

	@Override
	public void onTabReselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabSelected(ActionBar.Tab arg0, FragmentTransaction arg1) {		
	}

	@Override
	public void onTabUnselected(ActionBar.Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	// Uses AsyncTask to download the XML home data from issues.mobilepublish.com.br
	public void loadPage() {
		new DownloadXmlTask().execute(HOME_DATA_URL);
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
	private String loadXmlFromNetwork(String urlString) throws XmlPullParserException, IOException {
		InputStream stream = null;

		try {
			stream = Download.getStringUrl(urlString);

			Serializer serializer = new Persister();
			home = serializer.read(Home.class, stream);

			// Set up the ViewPager, attaching the adapter.
			mViewPager = (ViewPager) findViewById(R.id.pager);
			mViewPager.setAdapter(mDemoCollectionPagerAdapter);

			return "";
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

			HomeIssue issue = home.getIssues().get(i);

			args.putString(HomeIssue.ID, String.valueOf(issue.getId()));
			args.putString(HomeIssue.FEATURED, issue.getImage());
			args.putString(HomeIssue.SUBTITLE, issue.getSubtitle());
			args.putString(HomeIssue.TITLE, issue.getTitle());
			fragment.setArguments(args);
			return fragment;
		}

		@Override
		public int getCount() {
			int count = home.getIssues().size(); 
			return count;
		}

		@Override
		public CharSequence getPageTitle(int position) {
			return home.getIssues().get(position).getTitle();
		}
	}

	// Instances of this class are fragments representing a single
	// object in our collection.
	public static class DemoObjectFragment extends Fragment {
		private RetrieveImageTask retrieveImageTask;

		@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setRetainInstance(true);
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			// The last two arguments ensure LayoutParams are inflated
			// properly.
			View rootView = inflater.inflate(R.layout.fragment_section_home, container, false);
			final Bundle args = getArguments();
			ImageView featured = (ImageView) rootView.findViewById(R.id.issueFeatured);
			TextView title = (TextView) rootView.findViewById(R.id.issueTitle);
			TextView subtitle = (TextView) rootView.findViewById(R.id.issueSubtitle);

			featured.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(), IssueActivity.class);
					intent.putExtra(HomeIssue.ID, args.getString(HomeIssue.ID));
					startActivity(intent);
				}

			});

			retrieveImageTask = new RetrieveImageTask(getActivity(), featured);
			retrieveImageTask.execute(HOME_IMAGE_URL + args.getString(HomeIssue.FEATURED));

			subtitle.setText(args.getString(HomeIssue.SUBTITLE));
			title.setText(args.getString(HomeIssue.TITLE));

			return rootView;
		}

		@Override
		public void onActivityCreated(Bundle savedInstanceState) {
			super.onActivityCreated(savedInstanceState);
		}

	}

	private static class RetrieveImageTask extends AsyncTask<String, Void, Bitmap> {
		ImageView bmImage;
		private Context mContext;

		public RetrieveImageTask(Context context, ImageView bmImage) {
			this.bmImage = bmImage;
			this.mContext = context;
		}

		protected Bitmap doInBackground(String... urls) {
			try {
				String src = urls[0];
				String path = mContext.getExternalFilesDir(Environment.DIRECTORY_PICTURES) + src.replace(Download.ROOT_URL, ""); 
				return Download.getBitmapFromURL(mContext.getContentResolver(), src, path);
			} catch (Exception e) {
				return null;
			}
		}

		protected void onPostExecute(Bitmap bmp) {
			bmImage.setImageBitmap(bmp);
		}
	}

}
