package com.xiaoy.base.util;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.xiaoy.base.web.form.BaseForm;

public class UploadImageHelper {
	
	public static String  PICURL;
	
	/**
	 * 上传图片
	 * @param baseForm
	 */
	public static void uploadImage(BaseForm baseForm,String DEVICE_IMAGE_URL)
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
	
	/**
	 * 更换图片时，根据图片路径删除图上。
	 * @param devicePicUrl	图上路径<br/>
	 * 格式：/deviceStateUploadImages/20150410104208.jpg
	 */
	public static void deleteImage(HttpServletRequest request , String devicePicUrl)
	{
		int index = devicePicUrl.lastIndexOf("/");
		String name = devicePicUrl.substring(index + 1);
		System.out.println(name);
		File file = new File(request.getSession().getServletContext().getRealPath("/") + devicePicUrl);
		file.delete();
	}
}
