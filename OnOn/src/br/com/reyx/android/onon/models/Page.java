package br.com.reyx.android.onon.models;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Path;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;
import org.simpleframework.xml.convert.Convert;
import org.simpleframework.xml.convert.Converter;
import org.simpleframework.xml.stream.InputNode;
import org.simpleframework.xml.stream.OutputNode;

@Root(name="item")
public class Page {
	@Attribute(name="row")
	private int row;

	@Attribute(name="col")
	private int column;

	@Attribute(name="id")
	private int id;

	@Attribute(name="title")
	private String title;

	@Text(required=false)
	private String subtitle;

	@Attribute(name="image")
	private String image;

	@Attribute(name="type")
	private String type;

	@Attribute(name="template")
	private String template;

	public Page(@Attribute(name="row") int row,
			@Attribute(name="col") int col,
			@Attribute(name="id") int id,
			@Attribute(name="title") String title,
			@Text String subtitle,
			@Attribute(name="image") String image, 
			@Attribute(name="type") String type,
			@Attribute(name="template") String template) 
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

		private String type;

		PageType(String type)
		{
			this.type = type;
		}

		public static PageType fromValue(String value) {
			if (value != null) {
				for (PageType type : values()) {  
					if (toEnum(type.type).equals(value)) {  
						return type;  
					}
				}  
			}

			throw new IllegalArgumentException("Invalid type: " + value);  
		}  

		public String toValue() {  
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

		private String template;
		
		PageTemplate(String template)
		{
			this.template = template;
		}

		public static PageTemplate fromValue(String value) {
			if (value != null) {
				for (PageTemplate type : values()) {  
					if (toEnum(type.template).equals(value)) {  
						return type;  
					}
				}  
			}

			throw new IllegalArgumentException("Invalid type: " + value);  
		}  

		public String toValue() {  
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

	public String getType() {
		return type;
	}

	public String getTemplate() {
		return template;
	}
	
	private static String toEnum(String value) {
		return value.replace("-", "").toUpperCase();
	}
}