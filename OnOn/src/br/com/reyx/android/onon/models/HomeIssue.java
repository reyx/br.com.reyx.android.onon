package br.com.reyx.android.onon.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="item")
public class HomeIssue {
	
	public static final String ID = "Id";
	public static final String FEATURED = "Image";
	public static final String TITLE = "Title";
	public static final String SUBTITLE = "Subtitle";

    @Attribute
    private int id;

	@Attribute
    private String title;
    
    @Text(required=false)
    private String subtitle;
    
    @Attribute
    private String image;
    
    @Attribute
    private String type;
    
    @Attribute(name="min-version")
    private String minVersion;
    
    public HomeIssue (@Attribute(name="id") int id,
    		@Attribute(name="title") String title,
    		@Text String subtitle,
    		@Attribute(name="image") String image, 
    		@Attribute(name="type") String type,
    		@Attribute(name="min-version") String minVersion) {
	   this.id = id;
	   this.title = title;
	   this.subtitle = subtitle;
	   this.image = image;
	   this.type = type;
	   this.minVersion = minVersion;
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

	public String getImage() {
		return image;
	}

	public String getType() {
		return type;
	}

	public String getMinVersion() {
		return minVersion;
	}

}
