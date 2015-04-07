package com.xiaoy.base.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.xiaoy.base.web.form.BaseForm;

public class UploadImageHelper {
	
	//保存图片的文件夹
	private static String DEVICE_IMAGE_URL = "deviceUploadImages";
	
	public static String  PICURL;
	
	/**
	 * 上传图片
	 * @param baseForm
	 */
	public static void uploadImage(BaseForm baseForm)
	{
		//获取图上的后缀名
    	int index = baseForm.getImageFileName().indexOf(".");
		String px = baseForm.getImageFileName().substring(index);
		//生成图片名字
		String newFileName = DateHelper.dateTimeConverString(new Date(),"yyyyMMddhhmmss");
		//图片的新名字
		baseForm.setNewFileName(newFileName + px);
		
		String realpath = ServletActionContext.getServletContext().getRealPath("/" + DEVICE_IMAGE_URL);
        File savefile = new File(new File(realpath), baseForm.getNewFileName());
        if (!savefile.getParentFile().exists())
            savefile.getParentFile().mkdirs();
        try {
			FileUtils.copyFile(baseForm.getImage(), savefile);
			PICURL = "/"+ DEVICE_IMAGE_URL +"/" + baseForm.getNewFileName();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
