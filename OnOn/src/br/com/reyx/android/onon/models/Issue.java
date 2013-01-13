package br.com.reyx.android.onon.models;

import java.util.List;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name="item")
public class Issue {
	
	public static final String ID = "IssueId";
	public static final String FEATURED = "IssueImage";
	public static final String TITLE = "IssueTitle";
	
	@Element(name="info")
    private Info info;    

    @ElementList(name="sections")
    private List<Section> sections;
    
    public Issue (
    		@Element(name="info") Info info,
    		@ElementList(name="sections") List<Section> sections) {
	   this.info = info;
	   this.sections = sections;
	}
    
    public Info getInfo() {
		return info;
	}

	public List<Section> getSections() {
		return sections;
	}

}