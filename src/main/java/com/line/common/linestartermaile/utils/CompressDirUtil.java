package com.line.common.linestartermaile.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
 
/**
* @ClassName: CompressDirUtil 
* @Description: 压缩文件工具类
* @author zhoujie 
* @date 2018年7月29日 下午9:08:44 
* @version V1.0
 */
public class CompressDirUtil {

	/**
	* @Title: compressAllFileZip 
	* @Description: 传递文件路径压缩文件，传递文件夹路径压缩文件夹，注：空的文件夹不会出现在压缩包内
	* @param @param compresspath 需要压缩的文件夹的目录
	* @return void    返回类型 
	* @throws
	 */
	public static boolean compressFileToZip(String compresspath) {
		boolean bool = false;
		try {
			ZipOutputStream zipOutput = null;
        	File file = new File(compresspath);
        	if(file.isDirectory()){
        		zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compresspath + ".zip")));
        		compressZip(zipOutput, file, ""); //递归压缩文件夹，最后一个参数传""压缩包就不会有当前文件夹；传file.getName(),则有当前文件夹;
        	}else{
        		zipOutput = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(compresspath.substring(0, compresspath.lastIndexOf(".")) + ".zip")));
        		zipOFile(zipOutput, file); //压缩单个文件
        	}
            zipOutput.closeEntry();
			zipOutput.close();
			bool = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bool;
	}
 
    /**
    * @Title: compressZip 
    * @Description: 子文件夹中可能还有文件夹，进行递归
    * @param @param zipOutput
    * @param @param file
    * @param @param suffixpath
    * @param @throws IOException    
    * @return void    返回类型 
    * @throws
     */
    private static void compressZip(ZipOutputStream zipOutput, File file, String suffixpath) {
    	File[] listFiles = file.listFiles();// 列出所有的文件
        for(File fi : listFiles){
            if(fi.isDirectory()){
            	if(suffixpath.equals("")){
            		compressZip(zipOutput, fi, fi.getName());
            	}else{
            		compressZip(zipOutput, fi, suffixpath + File.separator + fi.getName());
            	}
            }else{
                zip(zipOutput, fi, suffixpath);
            }
        }
    }
 
    /**
    * @Title: zip 
    * @Description: 压缩的具体操作
    * @param @param zipOutput 
    * @param @param file  文件
    * @param @param suffixpath  文件夹拼接路径
    * @return void    返回类型 
    * @throws
     */
    public static void zip(ZipOutputStream zipOutput, File file, String suffixpath) {
        try {
        	ZipEntry zEntry = null;
        	if(suffixpath.equals("")){
        		zEntry = new ZipEntry(file.getName());
        	}else{
        		zEntry = new ZipEntry(suffixpath + File.separator + file.getName());
        	}
			zipOutput.putNextEntry(zEntry);
			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
	        byte[] buffer = new byte[1024];
	        int read = 0;
	        while((read = bis.read(buffer)) != -1){
	            zipOutput.write(buffer, 0, read);
	        }
	        bis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * @Title: zip 
     * @Description: 压缩单个文件
     * @param @param zipOutput 
     * @param @param file  文件
     * @return void    返回类型 
     * @throws
      */
     public static void zipOFile(ZipOutputStream zipOutput, File file) {
         try {
         	ZipEntry zEntry = new ZipEntry(file.getName());
 			zipOutput.putNextEntry(zEntry);
 			BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
 	        byte[] buffer = new byte[1024];
 	        int read = 0;
 	        while((read = bis.read(buffer)) != -1){
 	            zipOutput.write(buffer, 0, read);
 	        }
 	        bis.close();
 		} catch (Exception e) {
 			e.printStackTrace();
 		}
     }
	
}