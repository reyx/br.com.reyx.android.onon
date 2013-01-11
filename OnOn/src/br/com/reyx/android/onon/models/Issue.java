package br.com.reyx.android.onon.models;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="item")
public class Issue {
	
	// @Element
    // private Info info;

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

    //@ElementList
    //private List<Section> sections;
    
    public Issue(
    		//@Element(name="info") Info info,
    		@Attribute(name="id") int id,
    		@Attribute(name="title") String title,
    		@Text String subtitle,
    		@Attribute(name="image") String image, 
    		@Attribute(name="type") String type,
    		@Attribute(name="min-version") String minVersion
    		//, @ElementList(name="sections") List<Section> sections
    		) 
	{
	   //this.info = info;
	   this.id = id;
	   this.title = title;
	   this.subtitle = subtitle;
	   this.image = image;
	   this.type = type;
	   this.minVersion = minVersion;
	   //this.sections = sections;
	}
    
    //public Info getInfo() {
		//return info;
	//}

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

	//public List<Section> getSections() {
		//return sections;
	//}

}
