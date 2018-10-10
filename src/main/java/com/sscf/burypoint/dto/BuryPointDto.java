package com.sscf.burypoint.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BuryPointDto implements Serializable{
	
	private String line;
	private String sendTime;
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
