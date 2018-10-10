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
import com.sscf.burypoint.constant.ConstantInter;
import com.sscf.burypoint.constant.ConstantsUtil;
import com.sscf.burypoint.dto.BuryPointDto;

@Component
public class BuryPointReceive {
	// private static final String QUEUE_BURY_POINT="bury.point";
	// private static final String
	// POINT_PATH="C:\\Users\\lenovo\\Desktop\\add0.txt";
	private static final Logger logger = LoggerFactory.getLogger(BuryPointReceive.class);

	@RabbitListener(queues = ConstantInter.QUEUE_BURY_POINT) // 监听器监听指定的Queue
	public void processC(BuryPointDto line) {
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
			long begin0 = System.currentTimeMillis();
			Buff.write((line.getLine() + "," + line.getSendTime() + "\n").getBytes());
			Buff.flush();
			Buff.close();
			long end0 = System.currentTimeMillis();
			logger.debug("BufferedOutputStream执行耗时:" + (end0 - begin0) + " 毫秒");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("存储到:" + ConstantsUtil.path + "--失败");
		}
	}
}
