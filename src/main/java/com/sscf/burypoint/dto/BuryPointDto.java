package com.sscf.burypoint.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class BuryPointDto implements Serializable{
	
	private String line;
	private String sendTime;
	private Integer source;//埋点数据来源1:ios2:安卓3:网页
	private Integer logType;//日志类型1:用户访问日志,2:用户行为日志
	
	public Integer getLogType() {
		return logType;
	}
	public void setLogType(Integer logType) {
		this.logType = logType;
	}
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
