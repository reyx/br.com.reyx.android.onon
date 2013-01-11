package br.com.reyx.android.onon.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="data")
public class NewsRoot {
	
	@ElementList(name="news")
    private List<News> news;
	
	public NewsRoot(@ElementList(name="news") List<News> news) {
		this.news = news;
	}

	public List<News> getNews() {
		return news;
	}
	
}
