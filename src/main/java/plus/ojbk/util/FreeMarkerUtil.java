package plus.ojbk.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import plus.ojbk.service.impl.FileServiceImpl;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
public class FreeMarkerUtil {

    private static Configuration configuration;

    /**
     * freemarker渲染html
     *
     * @param warnBulletinDTO     要渲染的数据
     * @param filePath 模板所在路径
     * @param fileName 生成doc文件名称
     * @param dir      文件夹名称(随机数)
     * @param response
     */
    public static File freeMarkerRender(Object warnBulletinDTO, String filePath, String fileName, String dir, HttpServletResponse response) {
        if (null == configuration) {
            configuration = new Configuration(Configuration.VERSION_2_3_28);
            configuration.setDefaultEncoding("utf-8");
            configuration.setClassForTemplateLoading(FileServiceImpl.class, "/templates");
        }

        Writer out = null;
        Writer writer = null;
        try {
            // 获取模板,并设置编码方式
            Template template = configuration.getTemplate(filePath);
            template.setOutputEncoding("utf-8");
            //导出word到本地tomcat指定位置
            File outFile = new File(dir + "/" + fileName);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile), "utf-8"), 10240000);
            template.process(warnBulletinDTO, out);
            out.flush();
            out.close();
            return outFile;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 导出文件
     * @param response
     * @param file
     */
    public static void exportFile(HttpServletResponse response, File file){
        try{
            ServletOutputStream out=response.getOutputStream();
            BufferedInputStream in=new BufferedInputStream(new FileInputStream(file));
            response.setContentType("application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
            byte[] buffer=new byte[1024];
            int count=0;
            while((count=in.read(buffer))!=-1){
                out.write(buffer,0,count);
            }
            out.flush();
            out.close();
        }catch(Exception e){
            log.error("export word error",e);
        }finally{
            file.delete();
        }
    }


}