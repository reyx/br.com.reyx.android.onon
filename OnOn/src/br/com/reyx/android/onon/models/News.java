package br.com.reyx.android.onon.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="item")
public class News {
	
	@Attribute(name = "id")
    private int id;

    @Attribute(name = "title")
    private String title;

    @Attribute(name = "subtitle")
    private String subtitle;

    @Text
    private String content;

    @Attribute(name = "image")
    private String image;

    @Attribute(name = "date")
    private String published;
        
    public News(@Attribute(name="id") int id,
    		@Attribute(name="subtitle") String title,
    		@Attribute(name="subtitle") String subtitle,
    		@Text String content,
    		@Attribute(name="image") String image,
    		@Attribute(name="date") String date) 
	{
	   this.id = id;
	   this.title = title;
	   this.subtitle = subtitle;
	   this.content = content;
	   this.image = image;
	   this.published = date;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public String getContent() {
		return content;
	}

	public String getImage() {
		return image;
	}

	public String getPublished() {
		return published;
	}    
    
}
