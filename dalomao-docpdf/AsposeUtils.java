package com.weitu.framework.component.document.common.utils;

import com.aspose.words.Document;
import com.aspose.words.DocumentBuilder;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;
import com.weitu.framework.core.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/4/17.
 */
public class AsposeUtils {
    public static boolean getLicense() {
        boolean result = false;

        try {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("license/license.xml");
            License license = new License();
            license.setLicense(is);
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //源文件路径 目标文件路径 关键词的map 使用saveformat.XXX传参
    public static void replace(String srcPath, String destPath, Map<String, String> dataMap, Map<String, String> bookmarkMap, int type) {
        try {
            System.out.println(bookmarkMap);

            Document document = new Document(srcPath);
            //关键字替换
            for (Map.Entry entry : dataMap.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();
                value = value.replaceAll("\r\n", "" + (char)11);//word换行符
                value = value.replaceAll("\n", "" + (char)11);//word换行符
                try {
                    document.getRange().replace(key, value, true, false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            //书签替换
            DocumentBuilder builder = new DocumentBuilder(document);
            for (Map.Entry entry : bookmarkMap.entrySet()) {
                try {
                    if (!StringUtils.isEmpty((String) entry.getValue())) {//ImageUtils类中处理发生异常可能返回""
                        builder.moveToBookmark((String) entry.getKey());
                        String value = (String) entry.getValue();
                        if(!StringUtils.isEmpty(value)){
                            int index = value.lastIndexOf("_");
                            if(index > -1){
                                String imgUrl = value.substring(0, index);
                                String imgSize = value.substring(index + 1);
                                if(!StringUtils.isEmpty(imgUrl) && !StringUtils.isEmpty(imgSize)){
                                    String[] size = imgSize.split("×");
                                    if (size != null && size.length == 2) {
                                        if ("0".equals(size[0]) || "0".equals(size[1])) {
                                            builder.insertImage(imgUrl);
                                        } else {
                                            builder.insertImage(imgUrl, Double.parseDouble(size[0]), Double.parseDouble(size[1]));
                                        }
                                    }
                                }
                            }
                        }

                        /*String[] array = value.split("_");
                        if (array.length == 2) {
                            String[] size = array[1].split("×");
                            if (size.length == 2) {
                                if ("0".equals(size[0]) || "0".equals(size[1])) {
                                    builder.insertImage(array[0]);
                                } else {
                                    builder.insertImage(array[0], Double.parseDouble(size[0]), Double.parseDouble(size[1]));
                                }
                            }
                        }*/
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            document.save(destPath, type);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //多个行,单元格宽度不能自适应(每行列数最好一致)
    public static void createTable(DocumentBuilder builder, List<String[]> table) {
        getLicense();
        try {
//            AutoFitBehavior.AUTO_FIT_TO_WINDOW;
            builder.startTable();
            for (String[] row : table) {
                for (String cell : row) {
                    builder.insertCell();
                    if (cell != null || cell.equals("null")) {
                        builder.write(cell);
                    } else {
                        builder.write("");
                    }
                }
                builder.endRow();
            }
            builder.endTable();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //多行数据(列数可以不一致)
    public static void createRows(DocumentBuilder builder, List<String[]> rows) {
        getLicense();
        try {
            for (String[] row : rows) {
                builder.startTable();
                for (String cell : row) {
                    builder.insertCell();
                    if (cell != null || cell.equals("null")) {
                        builder.write(cell);
                    } else {
                        builder.write("");
                    }
                }
                builder.endRow();
                builder.endTable();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //单行数据,宽度自动调整
    public static void createRow(DocumentBuilder builder, String[] row) {
        getLicense();
        try {
            builder.startTable();
            for (String cell : row) {
                builder.insertCell();
                if (cell != null || cell.equals("null")) {
                    builder.write(cell);
                } else {
                    builder.write("");
                }
            }
            builder.endRow();
            builder.endTable();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //doc 转 pdf
    public static void doc2pdf(String inPath, String outPath){
        getLicense();
        try {
            File file = new File(outPath);
            FileOutputStream os = new FileOutputStream(file);
            Document doc = new Document(inPath);
            doc.save(os, SaveFormat.PDF);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void main(String[] args) {
//        doc2pdf("D:/data/songda2.0/weitu-site/upload/demo.docx","D:/data/songda2.0/weitu-site/upload/demo111.pdf");
//    }
}
