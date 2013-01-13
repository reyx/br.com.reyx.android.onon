package br.com.reyx.android.onon.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name="info")
public class Info {

    @Attribute(name="title", required=false)
    private String title;

    @Attribute(name="logo", required=false)
    private String logo;

    @Attribute(name="background", required=false)
    private String background;

    @Text(required=false)
    private String content;
	
	public Info(@Attribute(name="title", required=false) String title,
    		@Attribute(name="logo", required=false) String logo, 
    		@Attribute(name="background", required=false) String background,
    		@Text(required=false) String content) 
	{
	   this.title = title;
	   this.logo = logo;
	   this.background = background;
	   this.content = content;
	}

	public String getTitle() {
		return title;
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
