package br.com.reyx.android.onon.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root
public class Info {
	@Attribute(required=false)
    private int id;

    @Attribute
    private String title;

    @Attribute
    public String name;

    @Attribute
    private String logo;

    @Attribute
    private String background;

    @Text
    private String content;
	
	public Info(@Attribute(name="id") int id,
    		@Attribute(name="title") String title,
    		@Attribute(name="name") String name,
    		@Attribute(name="logo") String logo, 
    		@Attribute(name="background") String background,
    		@Text String content) 
	{
	   this.id = id;
	   this.title = title;
	   this.name = name;
	   this.logo = logo;
	   this.background = background;
	   this.content = content;
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getName() {
		return name;
	}

	public String getLogo() {
		return logo;
	}

	public String getBackground() {
		return background;
	}

	public String getContent() {
		return content;
	}
}
