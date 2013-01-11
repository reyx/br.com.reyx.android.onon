package br.com.reyx.android.onon.models;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root
public class Home {
	@ElementList(name="issues")
    private List<Issue> issues;
	
	public Home(@ElementList(name="issues") List<Issue> issues) {
		this.issues = issues;
	}

	public List<Issue> getIssues() {
		return issues;
	}
}
