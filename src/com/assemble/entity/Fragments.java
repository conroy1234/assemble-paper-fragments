package com.assemble.entity;

public class Fragments {
	public String fragment = "";
	public String data = "";
	public Match match = null;
	public String attachFragments;
	public String getFrag1() {
		return fragment;
	}
	public void setFrag1(String frag1) {
		this.fragment = frag1;
	}
	public String getFrag2() {
		return data;
	}
	public void setFrag2(String frag2) {
		this.data = frag2;
	}
	public Match getMatch() {
		return match;
	}
	public void setMatch(Match match) {
		this.match = match;
	}
	public String getMergedFragment() {
		return attachFragments;
	}
	public void setMergedFragment(String mergedFragment) {
		this.attachFragments = mergedFragment;
	}
	@Override
	public String toString() {
		return "Fragments [frag1=" + fragment + ", frag2=" + data + ", mergedFragment=" + attachFragments + "]";
	}
	
	
}
