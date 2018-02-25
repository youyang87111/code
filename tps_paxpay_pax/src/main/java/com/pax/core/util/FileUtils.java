package com.pax.core.util;

import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pax.core.exception.BusinessException;

import sun.nio.ch.FileChannelImpl;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhuxl@paxsz.com  on 2017/7/7.
 */
public class FileUtils {
    private static Logger logger = LoggerFactory.getLogger(FileUtils.class);

    /**
     * 压缩文件夹
     *
     * @param sourceDIR   文件夹名称（包含路径）
     * @param zipFilePath 生成zip文件名
     * @author liuxiangwei
     */
    public static void zipDir(String sourceDIR, String zipFilePath) {
        FileOutputStream target = null;
        ZipOutputStream out = null;
        try {
            target = new FileOutputStream(zipFilePath);
            out = new ZipOutputStream(new BufferedOutputStream(target));
            int BUFFER_SIZE = 1024;
            byte buff[] = new byte[BUFFER_SIZE];
            File dir = new File(sourceDIR);
            if (!dir.isDirectory()) {
                throw new IllegalArgumentException(sourceDIR + " is not a directory!");
            }
            File files[] = dir.listFiles();

            for (int i = 0; i < files.length; i++) {
                FileInputStream fi = new FileInputStream(files[i]);
                BufferedInputStream origin = new BufferedInputStream(fi);
                ZipEntry entry = new ZipEntry(files[i].getName());
                out.putNextEntry(entry);
                int count;
                while ((count = origin.read(buff)) != -1) {
                    out.write(buff, 0, count);
                }
                origin.close();
                fi.close();
            }

        } catch (IOException e) {
            logger.error("文件或目录不存在", e);
            throw new BusinessException("文件或目录不存在");
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (target != null) {
                    target.close();
                }
            } catch (Exception e) {
                throw new BusinessException("打包失败");
            }
        }
    }
    
    /**
	 * 上传文件到指定目录
	 */
	public static boolean uploadFile(String desdir, File upload, String fileName) {
		try {
			File appFileDir = new File(desdir);
			if (!appFileDir.exists()) {
				appFileDir.mkdir();
			}
			File destFile = new File(appFileDir, fileName);
			org.apache.commons.io.FileUtils.copyFile(upload, destFile);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * 删除指定目录下所有的文件和子目录
	 * @param filePath
	 * @return
	 */
	public static boolean deleteAllFile(String filePath) {
		try {
			File fileDir = new File(filePath);
			for (File file : fileDir.listFiles()) {
				if (file.isDirectory()) {
					deleteAllDirAndFile(file.getPath());
				} else {
					file.delete();
				}
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 删除指定目录下所有的文件和子目录，包括指定目录
	 * @param filePath
	 * @return
	 */
	public static boolean deleteAllDirAndFile(String filePath) {
		try {
			File fileDir = new File(filePath);
			for (File file : fileDir.listFiles()) {
				if (file.isDirectory()) {
					deleteAllDirAndFile(file.getPath());
				} else {
					file.delete();
				}
			}
			fileDir.delete();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

    public static boolean deleteDir(String dir) {
        //如果dir不以文件分隔符结尾，自动添加文件分隔符
        if(!dir.endsWith(File.separator)){
            dir = dir+File.separator;
        }
        File dirFile = new File(dir);
        //如果dir对应的文件不存在，或者不是一个目录，则退出
        if(!dirFile.exists() || !dirFile.isDirectory()){
            logger.error("删除目录失败"+dir+"目录不存在!");
            return false;
        }
        boolean flag = true;
        //删除文件夹下的所有文件(包括子目录)
        File[] files = dirFile.listFiles();
        for(int i=0;i<files.length;i++){
            //删除子文件
            if(files[i].isFile()){
                flag = deleteFile(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
            //删除子目录
            else{
                flag = deleteDir(files[i].getAbsolutePath());
                if(!flag){
                    break;
                }
            }
        }

        if(!flag){
            logger.error("删除目录失败");
            return false;
        }

        //删除当前目录
        if(dirFile.delete()){
            logger.info("删除目录"+dir+"成功!");
            return true;
        }else{
            logger.error("删除目录"+dir+"失败!");
            return false;
        }
    }

    public static boolean deleteFile(String fileName){
        File file = new File(fileName);
        if(file.isFile() && file.exists()){
            Boolean succeedDelete = file.delete();
            if(succeedDelete){
                logger.info("删除单个文件"+fileName+"成功!");
                return true;
            }
            else{
                logger.error("删除单个文件"+fileName+"失败!");
                return true;
            }
        }else{
            logger.error("删除单个文件"+fileName+"失败!");
            return false;
        }
    }

    /**
     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能
     *
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readBytes(String filename) throws IOException {

        FileChannel fc = null;
        RandomAccessFile raf=null;
        try {
            raf=new RandomAccessFile(filename, "r");
            fc = raf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).load();
            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            Method m = FileChannelImpl.class.getDeclaredMethod("unmap",
                    MappedByteBuffer.class);
            m.setAccessible(true);
            m.invoke(FileChannelImpl.class, byteBuffer);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new BusinessException(e);
        } finally {
            try {
                raf.close();
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException("读取文件失败");
            }
        }
    }
    public static void saveXlsx(SXSSFWorkbook sxssfWorkbook, String filepath){
        FileOutputStream fos = null;
        try{
            fos=new FileOutputStream(new File(filepath));
            sxssfWorkbook.write(fos);
            fos.flush();
        }catch (Exception e){
            e.printStackTrace();
            throw new BusinessException("保存excel文件出错");
        }finally {
            try {
                sxssfWorkbook.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
                throw new BusinessException("关闭excel文件出错");
            }
        }
    }
}