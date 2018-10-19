package com.sscf.burypoint.rabbit;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.sscf.burypoint.constant.ActionLog;
import com.sscf.burypoint.constant.ConstantInter;
import com.sscf.burypoint.constant.ConstantsUtil;
import com.sscf.burypoint.dto.BuryPointDto;
import com.sscf.burypoint.thread.LogActionThread;

@Component
public class BuryPointReceive {
	// private static final String QUEUE_BURY_POINT="bury.point";
	// private static final String
	// POINT_PATH="C:\\Users\\lenovo\\Desktop\\add0.txt";
	private static final Logger logger = LoggerFactory.getLogger(BuryPointReceive.class);
	private static final Logger logForAction= LoggerFactory.getLogger(ActionLog.class);//打印到特定的文件夹中
	
	/*@RabbitListener(queues = ConstantInter.QUEUE_BURY_POINT) // 监听器监听指定的Queue
	public void processC(BuryPointDto line) {
		long begin0 = System.currentTimeMillis();
		logger.debug("-----" + JSON.toJSONString(line));
		// 将line存放在本地
		FileOutputStream out = null;
		FileOutputStream outSTr = null;
		BufferedOutputStream Buff = null;
		FileWriter fw = null;

		File fileFo = new File(ConstantsUtil.pathFolder);
		File file = new File(ConstantsUtil.path);
		try {
			// 判断文件夹
			if (!fileFo.exists() && !fileFo.isDirectory()) {
				fileFo.mkdir();
			}

			// 判断文件
			if (!file.exists()) {
				file.createNewFile();
			}
			logger.debug("文件大小" + file.length());
			// 判断文件大小
			if (file.length() / (1024*1024) > 5) {// 大于500m
				File[] listFiles = fileFo.listFiles();
				int length = listFiles.length;
				file.renameTo(new File(ConstantsUtil.path + length));
				file.createNewFile();
			}
		} catch (Exception e) {
			logger.debug("创建文件夹失败");
		}
		try {
			outSTr = new FileOutputStream(file, true);
			Buff = new BufferedOutputStream(outSTr);
			
			Buff.write((line.getLine() + "," + line.getSendTime()+","+ line.getSource()+","+line.getLogType()+ "\n").getBytes());
			Buff.flush();
			Buff.close();
			long end0 = System.currentTimeMillis();
			log.debug(JSON.toJSONString(line));
			logger.debug("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("存储到:" + ConstantsUtil.path + "--失败");
		}
	}*/
	
	
	/*@RabbitListener(queues = ConstantInter.QUEUE_BURY_POINT) // 监听器监听指定的Queue
	public void process(BuryPointDto line) {
		logger.debug("------------------------------开始打印日志信息");
		printLog(line);
	}*/
	@RabbitListener(queues = ConstantInter.QUEUE_BURY_POINT) // 监听器监听指定的Queue
	public void processT(BuryPointDto line) {
		logger.debug("------------------------------开始打印日志信息");
		for (int i=1;i<=10;i++) {
			Thread t=new Thread(new LogActionThread(logForAction, logger, line));
			t.start();
		}
	
	}
	
	//打印日志方法
	public void printLog(BuryPointDto line) {
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
