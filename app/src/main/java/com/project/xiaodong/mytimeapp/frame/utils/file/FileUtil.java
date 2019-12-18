package com.project.xiaodong.mytimeapp.frame.utils.file;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

import com.project.xiaodong.mytimeapp.frame.constants.BaseConstants;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by xiaodong.jin on 2017/10/21.
 */

    @SuppressLint({"SdCardPath"})
    public class FileUtil {
        public static String SDCard = Environment.getExternalStorageDirectory().getAbsolutePath();

        public FileUtil() {
        }

        public static InputStream getInputStream(Context mContext, String rootPath, String path) throws Exception {
            File file = new File(getDataPath() + rootPath + path);
            return (InputStream)(file.exists()?new FileInputStream(file):mContext.getAssets().open(path));
        }

        public static String getDataPath() {
            return MemorySpaceCheckUtil.checkSDCard()?SDCard + "/Android/data/" + BaseConstants.PACKAGE_NAME + "/":"/data/data/" + BaseConstants.PACKAGE_NAME + "/";
        }

        public static File getDirectory(String path) {
            File appDir = new File(path);
            if(!appDir.exists()) {
                appDir.mkdirs();
            }

            return appDir;
        }

        public static boolean deleteDirectory(String sPath) {
            boolean flag = false;
            if(!sPath.endsWith(File.separator)) {
                sPath = sPath + File.separator;
            }

            File dirFile = new File(sPath);
            if(dirFile.exists() && dirFile.isDirectory()) {
                flag = true;
                File[] files = dirFile.listFiles();

                for(int i = 0; i < files.length; ++i) {
                    if(files[i].isFile()) {
                        flag = deleteFile(files[i].getAbsolutePath());
                        if(!flag) {
                            break;
                        }
                    } else {
                        flag = deleteDirectory(files[i].getAbsolutePath());
                        if(!flag) {
                            break;
                        }
                    }
                }

                return !flag?false:dirFile.delete();
            } else {
                return false;
            }
        }

        public static int createFloder(String filePath) {
            File file = new File(filePath);
            return file.exists()?0:(file.mkdirs()?1:-1);
        }

        public static boolean deleteFile(String sPath) {
            boolean flag = false;
            File file = new File(sPath);
            if(file.isFile() && file.exists()) {
                file.delete();
                flag = true;
            }

            return flag;
        }

        public static String readerFile(String filePath) {
            StringBuffer buffer = new StringBuffer();

            try {
                FileInputStream e = new FileInputStream(filePath);
                InputStreamReader isr = new InputStreamReader(e, "utf-8");
                BufferedReader in = new BufferedReader(isr);

                int ch;
                while((ch = in.read()) > -1) {
                    buffer.append((char)ch);
                }

                in.close();
                return buffer.toString();
            } catch (Exception var6) {
                return null;
            }
        }

        public static int writeFile(String path, String content) {
            try {
                File e = new File(path);
                if(e.exists()) {
                    e.delete();
                }

                if(e.createNewFile()) {
                    FileOutputStream utput = new FileOutputStream(e);
                    utput.write(content.getBytes());
                    utput.close();
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception var4) {
                return 0;
            }
        }

        public static int writeFile(String path, InputStream in) {
            try {
                if(in == null) {
                    return 0;
                } else {
                    File e = new File(path);
                    if(e.exists()) {
                        e.delete();
                    }

                    if(!e.createNewFile()) {
                        return 0;
                    } else {
                        FileOutputStream utput = new FileOutputStream(e);
                        byte[] buffer = new byte[1024];
                        boolean count = true;

                        int count1;
                        while((count1 = in.read(buffer)) != -1) {
                            utput.write(buffer, 0, count1);
                            utput.flush();
                        }

                        utput.close();
                        in.close();
                        return 1;
                    }
                }
            } catch (Exception var6) {
                return 0;
            }
        }

        public static int copyStream(InputStream is, OutputStream os) {
            byte bytes;
            try {
                boolean e = true;
                byte[] bytes1 = new byte[1024];

                while(true) {
                    int e1 = is.read(bytes1, 0, 1024);
                    if(e1 == -1) {
                        byte e2 = 1;
                        return e2;
                    }

                    os.write(bytes1, 0, e1);
                }
            } catch (IOException var14) {
                bytes = 0;
            } finally {
                try {
                    is.close();
                    os.close();
                } catch (IOException var13) {
                    ;
                }

            }

            return bytes;
        }

        public static Object readerObject(String filePath) {
            try {
                FileInputStream e = new FileInputStream(filePath);
                ObjectInputStream objectIn = new ObjectInputStream(e);
                Object oo = objectIn.readObject();
                objectIn.close();
                e.close();
                return oo;
            } catch (Exception var4) {
                return null;
            }
        }

        public static int writeObject(String path, Object object) {
            try {
                File e = new File(path);
                if(e.exists()) {
                    e.delete();
                }

                if(e.createNewFile()) {
                    FileOutputStream utput = new FileOutputStream(e);
                    ObjectOutputStream objOut = new ObjectOutputStream(utput);
                    objOut.writeObject(object);
                    objOut.close();
                    utput.close();
                    return 1;
                } else {
                    return 0;
                }
            } catch (Exception var5) {
                return 0;
            }
        }

        public static void unzip(String rootPath, InputStream fileIn) {
            try {
                File e = new File(rootPath);
                e.mkdir();
                e = new File(rootPath + "resource/");
                e.mkdir();
                ZipInputStream in = new ZipInputStream(new BufferedInputStream(fileIn, 2048));
                ZipEntry entry = null;

                while((entry = in.getNextEntry()) != null) {
                    decompression(entry, rootPath, in);
                }

                in.close();
            } catch (Exception var5) {
                var5.printStackTrace();
            }

        }

        private static void decompression(ZipEntry entry, String rootPath, ZipInputStream in) throws Exception {
            File file;
            if(!entry.isDirectory() && -1 != entry.getName().lastIndexOf(".")) {
                file = new File(rootPath + entry.getName());
                if(!file.exists()) {
                    file.createNewFile();
                }

                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file), 2048);

                int b;
                while((b = in.read()) != -1) {
                    bos.write(b);
                }

                bos.close();
            } else {
                file = new File(rootPath + entry.getName().substring(0, entry.getName().length() - 1));
                file.mkdir();
            }

        }

        public static String getExtensionName(String filename) {
            if(filename != null && filename.length() > 0) {
                int dot = filename.lastIndexOf(46);
                if(dot > -1 && dot < filename.length() - 1) {
                    return filename.substring(dot + 1);
                }
            }

            return filename;
        }
    }

