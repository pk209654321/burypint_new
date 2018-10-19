package com.sscf.burypoint.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BuryPointDto implements Serializable{
	
	private String line;
	private String sendTime;
	private Integer source;//埋点数据来源1:ios2:安卓3:网页
	
	public Integer getSource() {
		return source;
	}
	public void setSource(Integer source) {
		this.source = source;
	}
	public String getLine() {
		return line;
	}
	public void setLine(String line) {
		this.line = line;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	
}
