package com.sscf.burypoint.constant;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
@Component
public class ConstantsUtil {
	public static String path;
	public static String pathFolder;
	
	public  String getPathFolder() {
		return pathFolder;
	}
	
	@Value("${constants.pathFolder}")
	public void setPathFolder(String pathFolder) {
		ConstantsUtil.pathFolder = pathFolder;
	}

	public String getPath() {
		return path;
	}
	
	@Value("${constants.path}")
	public void setPath(String path) {
		this.path = path;
	}
	
	
}
