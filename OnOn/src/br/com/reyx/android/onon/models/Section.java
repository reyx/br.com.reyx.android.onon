package br.com.reyx.android.onon.models;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Section {
	
	public static String ID = "SectionId";
	public static String NAME = "SectionName";
	public static String COLOR = "SectionColor";
	public static String IMAGE = "SectionImage";
	
	@Attribute
    private String name;

    @Attribute
    private String color;

    @Attribute
    private String image;

    @ElementList(name="item", inline=true)
    private List<Page> pages;
    
    public Section(@Attribute(name="name") String name, 
    		@Attribute(name="color") String color, 
    		@Attribute(name="image") String image,
    		@ElementList(name="item", inline=true) List<Page> pages) 
	{
	   this.color = color;
	   this.name = name;
	   this.image = image;
	   this.pages = pages;
	}

	public String getName() {
		return name;
	}

	public String getColor() {
		return color;
	}

	public String getImage() {
		return image;
	}

	public List<Page> getPages() {
		return pages;
	}
    
}
