package com.cathetine.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @description: 操作文件工具类
 */
public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);
    private static final int BUFFER_SIZE = 2 * 1024;
    
    public static final String ENCODE_UTF_8 = "UTF-8";
    public static final String ENCODE_GBK = "GBK";

    /**
     *  写入txt文件
     * @param list     明细
     * @param head     表头
     * @param fileName 文件名
     * @param encode   编码格式
     * @param filePath 文件路径
     * @param filePath 文件路径
     * @return isDefault 是否默认文件内容前加入字符 "88888888"
     */
    public static Boolean writeDataTxt(List<String> list, String head, String fileName, String encode, String filePath,boolean isDefault) {
        long start = System.currentTimeMillis();
        boolean flag = false;
        BufferedWriter out = null;
        try {
            if (list != null && StringUtils.isNotEmpty(fileName) && StringUtils.isNotEmpty(filePath)) {
                //fileName += "_" + System.currentTimeMillis() + ".txt";
                fileName += ".txt";
                File pathFile = new File(filePath);
                if (!pathFile.exists()) {
                    pathFile.mkdirs();
                }
                String relFilePath = filePath + File.separator + fileName;
                File file = new File(relFilePath);
                if (!file.exists()) {
                    file.createNewFile();
                }
                //编码格式
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), encode));
                
                //是否默认文件内容前加入字符 "88888888"
                if(isDefault) {
                	 out.write("88888888");
                }
                //标题头
                out.write(head);
                out.newLine();
                //内容明细
                for (int i = 0; i < list.size(); i++) {
                    out.write(list.get(i));
                    out.newLine();
                }

                flag = true;
                logger.info("写入文件耗时：******" + (System.currentTimeMillis() - start) + "毫秒");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return flag;
    }

    /**
     * 删除文件或文件夹
     * @param directory 临时生成的文件夹
     */
    public static void delAllFile(File directory) {
        if (!directory.isDirectory()) {
            directory.delete();
        } else {
            File[] files = directory.listFiles();
            // 空文件夹
            if (files.length == 0) {
                directory.delete();
                return;
            }
            // 删除子文件夹和子文件
            for (File file : files) {
                if (file.isDirectory()) {
                    delAllFile(file);
                } else {
                    file.delete();
                }
            }
            // 删除文件夹本身
            directory.delete();
        }
    }

    /**
     * 压缩成ZIP
     *
     * @param srcDir           压缩文件夹路径
     * @param out              压缩文件输出流
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws RuntimeException 压缩失败会抛出运行时异常
     */
    public static void toZip(String srcDir, OutputStream out, boolean KeepDirStructure) throws RuntimeException {
        long start = System.currentTimeMillis();
        ZipOutputStream zos = null;
        try {
            zos = new ZipOutputStream(out);
            File sourceFile = new File(srcDir);
            compress(sourceFile, zos, sourceFile.getName(), KeepDirStructure);
            long end = System.currentTimeMillis();
            logger.debug("压缩完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            e.printStackTrace();
            //logger.error("系统异常：", e);
        } finally {
            if (zos != null) {
                try {
                    zos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    //logger.error("系统异常：",e);
                }
            }
        }

    }

    /**
     * 递归压缩方法
     * @param sourceFile       源文件
     * @param zos              zip输出流
     * @param name             压缩后的名称
     * @param KeepDirStructure 是否保留原来的目录结构,true:保留目录结构;
     *                         false:所有文件跑到压缩包根目录下(注意：不保留目录结构可能会出现同名文件,会压缩失败)
     * @throws Exception
     */
    private static void compress(File sourceFile, ZipOutputStream zos, String name, boolean KeepDirStructure) throws Exception {
        byte[] buf = new byte[BUFFER_SIZE];
        if (sourceFile.isFile()) {
            // 向zip输出流中添加一个zip实体，构造器中name为zip实体的文件的名字
            zos.putNextEntry(new ZipEntry(name));
            // copy文件到zip输出流中
            int len;
            FileInputStream in = new FileInputStream(sourceFile);
            while ((len = in.read(buf)) != -1) {
                zos.write(buf, 0, len);
            }
            // Complete the entry
            zos.closeEntry();
            in.close();
        } else {
            File[] listFiles = sourceFile.listFiles();
            if (listFiles == null || listFiles.length == 0) {
                // 需要保留原来的文件结构时,需要对空文件夹进行处理
                if (KeepDirStructure) {
                    // 空文件夹的处理
                    zos.putNextEntry(new ZipEntry(name + "/"));
                    // 没有文件，不需要文件的copy
                    zos.closeEntry();
                }

            } else {
                for (File file : listFiles) {
                    // 判断是否需要保留原来的文件结构
                    if (KeepDirStructure) {
                        // 注意：file.getName()前面需要带上父文件夹的名字加一斜杠,
                        // 不然最后压缩包中就不能保留原来的文件结构,即：所有文件都跑到压缩包根目录下了
                        compress(file, zos, name + "/" + file.getName(), KeepDirStructure);
                    } else {
                        compress(file, zos, file.getName(), KeepDirStructure);
                    }

                }
            }
        }
    }

    /**
     * 读取文本文件
     * @param filePath 文本路径
     * @param encoding  编码格式
     * @return
     * @throws Exception 
     */
    public static List<String> readTxtFile(String filePath, String encoding) throws Exception {
        List<String> list = new ArrayList<>();
        try {
            File file = new File(filePath);
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String s = null;
                while ((s = bufferedReader.readLine()) != null) {//使用readLine方法，一次读一行
                    list.add(s);
                }
                bufferedReader.close();
                read.close();

        } catch (Exception e) {
        	logger.error("读取文本文件异常：",e);
        	throw e;
        }
        return list;
    }

    
    /**
     * 生成文件夹
     * @param filePath
     */
    public static void createDir(String filePath) {
		
    	File pathFile = new File(filePath);
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }
	}
    
    
}
