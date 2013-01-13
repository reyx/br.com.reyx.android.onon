package br.com.reyx.android.onon.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Home {
	@ElementList(name="issues")
    private List<HomeIssue> issues;
	
	public Home(@ElementList(name="issues") List<HomeIssue> issues) {
		this.issues = issues;
	}

	public List<HomeIssue> getIssues() {
		return issues;
	}
}
