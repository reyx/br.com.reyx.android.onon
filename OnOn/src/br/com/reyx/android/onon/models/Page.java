package br.com.reyx.android.onon.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root
public class Page {
	@Attribute(name="row")
	private int row;

    @Attribute(name="col")
    private int column;

    @Attribute(name="id")
    private int id;

    @Attribute(name="title")
    private String title;

    @Text
    private String subtitle;

    @Attribute(name = "image")
    private String image;

    @Attribute(name = "type")
    private PageType type;

    @Attribute(name = "template")
    private PageTemplate template;
    
    public Page(@Attribute(name="row") int row,
    		@Attribute(name="col") int col,
    		@Attribute(name="id") int id,
    		@Attribute(name="title") String title,
    		@Text String subtitle,
    		@Attribute(name="image") String image, 
    		@Attribute(name="type") PageType type,
    		@Attribute(name="template") PageTemplate template) 
	{
	   this.row = row;
	   this.column = col;
	   this.id = id;   
	   this.title = title;
	   this.subtitle = subtitle;
	   this.image = image;
	   this.type = type;
	   this.template = template;
	}
    
    public enum PageType {
        HORIZONTAL("horizontal"),
        HORIZONTAL2("horizontal-2"),        
        VERTICAL("vertical"),        
        VERTICAL2("vertical-2"),        
        VERTICAL3("vertical-3"),        
        MIDDLETEXT("middle-text"),        
        MIDDLEIMAGE("middle-image");
        
        @Attribute(name="type")
        private final String type;
        
        PageType(@Attribute(name="type") String type)
        {
        	this.type = type;
        }
        
        public String getType() {
            return type;
        }
    }
    
    public enum PageTemplate {
        ALBUM("album"),
        CARROSSEL("carrossel"),        
        DIMER("dimer"),        
        EDITORIAL("editorial"),
        VIDEO("video"),        
        GALERIA("galeria");
        
        @Attribute(name="template")
        private final String template;
        
        PageTemplate(@Attribute(name="template") String template)
        {
        	this.template = template;
        }
        
        public String getTemplate() {
            return template;
        }
    }

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
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

	public PageType getType() {
		return type;
	}

	public PageTemplate getTemplate() {
		return template;
	}
    
}