package com.assemble.entity;

public class Match {
	public Integer xvalue = 0;
	public Integer yvalue = 0;
	public Integer maxValue = 0;
	public String value;
	public Integer getXvalue() {
		return xvalue;
	}
	public void setXvalue(Integer xvalue) {
		this.xvalue = xvalue;
	}
	public Integer getYvalue() {
		return yvalue;
	}
	public void setYvalue(Integer yvalue) {
		this.yvalue = yvalue;
	}
	public Integer getMaxValue() {
		return maxValue;
	}
	public void setMaxValue(Integer maxValue) {
		this.maxValue = maxValue;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Match [xvalue=" + xvalue + ", yvalue=" + yvalue + ", maxValue=" + maxValue + ", value=" + value + "]";
	}
	
	
}
