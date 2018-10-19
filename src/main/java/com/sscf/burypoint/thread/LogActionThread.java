package com.sscf.burypoint.thread;

import org.slf4j.Logger;

import com.alibaba.fastjson.JSON;
import com.sscf.burypoint.dto.BuryPointDto;

public class LogActionThread implements Runnable{
	
	private Logger logForAction;
	private Logger logger;
	
	private BuryPointDto line;

	public  LogActionThread() {
		
		
	}
	
	public LogActionThread(Logger logForAction,Logger logger,BuryPointDto line) {
		this.logForAction=logForAction;
		this.logger=logger;
		this.line=line;
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long begin0 = System.currentTimeMillis();
		try {
			logForAction.debug(JSON.toJSONString(line));
		} catch (Exception e) {
			logger.error("打印日志失败"+e.getMessage());
		}
		long end0 = System.currentTimeMillis();
		logger.debug("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
	}

}
