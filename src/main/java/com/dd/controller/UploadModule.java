package com.dd.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.nutz.dao.Cnd;
import org.nutz.ioc.impl.PropertiesProxy;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.lang.util.NutMap;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.filter.CrossOriginFilter;
import org.nutz.mvc.upload.TempFile;
import org.nutz.mvc.upload.UploadAdaptor;

/**
 * 文件上传
 */
@IocBean
@At("/upload")
@Ok("json")
public class UploadModule {
	Log log = Logs.get();

	@Inject
	private PropertiesProxy conf;

	/**
	 * 发送图片,上传图片接口
	 * @param file
	 * @param context
	 * @return
	 */
	@At
	@POST
	@Filters({@By(type=CrossOriginFilter.class)})
	@AdaptBy(type = UploadAdaptor.class, args = { "/usr/local/tomcat/webapps/upload" })
	public Object image(@Param("file") TempFile file,ServletContext context){
//		System.out.println(file.getName());
//		System.out.println(file.getSubmittedFileName());
		String relpath = "/usr/local/tomcat/webapps/upload/images/"+file.getSubmittedFileName();
//		String relpath = "/usr/local/tomcat/webapps/upload/images/";
		log.debug("relpath:"+relpath);
		Files.copy(file.getFile(),new File(relpath));
		String url ="http://192.168.1.11:9999/upload/images/"+file.getSubmittedFileName();
		//构建json数据
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code", "0");
		data.put("msg", "");
		Map<String,String> sourceUrl = new HashMap<String,String>();
		sourceUrl.put("src", url);
		data.put("data", sourceUrl);
		return data;
	}

	/**
	 * 发送图片,上传图片接口
	 * @param file
	 * @param context
	 * @return
	 */
	@At
	@POST
	@Filters({@By(type=CrossOriginFilter.class)})
	@AdaptBy(type = UploadAdaptor.class, args = { "/usr/local/tomcat/webapps/upload" })
	public Object files(@Param("file") TempFile file,ServletContext context){
		System.out.println(file.getName());
		System.out.println(file.getSubmittedFileName());
		String relpath = "/usr/local/tomcat/webapps/upload/files/"+file.getSubmittedFileName();
		Files.copy(file.getFile(),new File(relpath));
		String url ="http://192.168.1.11:9999/upload/files/"+file.getSubmittedFileName();
		// 构建json数据
		Map<String,Object> data = new HashMap<String,Object>();
		data.put("code", "0");
		data.put("msg", "");
		Map<String,String> sourceUrl = new HashMap<String,String>();
		sourceUrl.put("src", url);
		data.put("data", sourceUrl);
		return data;
	}
	
	
	@At
	public Object test(){
		NutMap nm = new NutMap();
		String contextPath = Mvcs.getServletContext().getContextPath();
		String realPath = Mvcs.getServletContext().getRealPath("/");
		String parent = new File(realPath).getParent();
		nm.setv("contextPath",contextPath);
		nm.setv("realPath",realPath);
		nm.setv("parent",parent);
		return nm;
	}


	public String getDir(){
		String realPath = Mvcs.getServletContext().getRealPath("/");
		String parent = new File(realPath).getParent();
		log.debug("uploadDir:"+parent);
		return parent;
	}


    /**
     * 发送图片,上传图片接口
     * @param file
     * @param context
     * @return
     */
    @At
    @POST
    @Filters({@By(type=CrossOriginFilter.class)})
    @AdaptBy(type = UploadAdaptor.class, args = { "/usr/local/tomcat/webapps/upload" })
    public Object image2(@Param("file") TempFile file,ServletContext context){
        String url = "";
        //构建json数据
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("code", "0");
        data.put("msg", "");
        Map<String,String> sourceUrl = new HashMap<String,String>();
        sourceUrl.put("src", url);
        data.put("data", sourceUrl);
        return data;
    }
	
}
