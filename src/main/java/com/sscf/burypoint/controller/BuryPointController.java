package com.sscf.burypoint.controller;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.sscf.burypoint.constant.ConstantInter;
import com.sscf.burypoint.constant.ConstantsUtil;
import com.sscf.burypoint.dto.BuryPointDto;
import com.sscf.burypoint.rabbit.BuryPointSend;
import com.sscf.education.common.entity.PageResult;
import com.sscf.education.common.entity.Result;
import com.sscf.education.common.util.ResultUtil;

@RestController
@RequestMapping("/burypoint")
@CrossOrigin
public class BuryPointController {
	private static final Logger logger = LoggerFactory.getLogger(BuryPointController.class);
	
	@Autowired
	BuryPointSend buryPointSend;

	@RequestMapping(value = "/postBuryPointInfo")
	@ResponseBody
	@CrossOrigin //跨域
	public Result postBuryPointInfo(@RequestBody BuryPointDto dto) {
		//TimeUtil.for
		if(dto!=null&&StringUtils.isNotEmpty(dto.getLine())&&StringUtils.isNotEmpty(dto.getSendTime())&&dto.getSource()!=null&&dto.getLogType()!=null) {
			//logger.debug("接受到的参数dto:"+dto.getLine());
			buryPointSend.sendMsg(dto,ConstantInter.QUEUE_BURY_POINT);
			return ResultUtil.success();
		}else {
			return ResultUtil.error(-1, "参数异常", "参数为空");
		}
	}
	
	
	
}
