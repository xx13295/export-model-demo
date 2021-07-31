package plus.ojbk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import plus.ojbk.dto.DemoDTO;
import plus.ojbk.dto.RecordDTO;
import plus.ojbk.service.FileService;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@RestController
public class IndexController {

    @Autowired
    private FileService fileService;
    @RequestMapping("/index")
    public String index(){
        return "HelloWorld!";
    }

    /**
     * 这里 应该是post  为了方便测试
     * 改成 get
     * @param
     * @param response
     */
    /*@PostMapping("/download")
    public void downloadProduct(@RequestBody DemoDTO demoDTO, HttpServletResponse response) {
        fileService.downloadDemo(demoDTO, response);
    }*/
    @GetMapping("/download")
    public void downloadProduct( HttpServletResponse response) throws Exception{
        DemoDTO demoDTO = new DemoDTO();
        demoDTO.setTime1("2021-07-31 10:10:10");
        demoDTO.setTime2("2021-07-31 10:11:10");
        demoDTO.setTime3("2021-07-31 10:12:10");
        demoDTO.setTime4("2021-07-31 10:13:10");
        //fuck  字符串直接定义太长了不然搞 因此demo麻烦点。 正常post的json参数是没问题的 或者后端用file去转base64 总之方法很多
        File file =  ResourceUtils.getFile(ResourceUtils.CLASSPATH_URL_PREFIX + "static/pic.txt");
        demoDTO.setPic(txt2String(file).replaceAll("(\\\r\\\n|\\\r|\\\n|\\\n\\\r)", ""));

        demoDTO.setSms("2021年05月30日20时至2021年05月31日20时因连续性降雨，结合地质环境条件和地质灾害发育特点，波密县、工布江达县、墨脱县地质灾害气象风险预警等级为三级，发生地质灾害风险较高,巴宜区、米林县、朗县地质灾害气象风险预警等级为四级，有一定的风险发生。请当地居民注意防范强降水引发的地质灾害。");
        List<RecordDTO> record = new ArrayList<>();
        RecordDTO recordDTO1 = new RecordDTO();
        recordDTO1.setName("小红");
        recordDTO1.setTime("2021-07-31 10:15:10");
        recordDTO1.setResult("已发布");

        record.add(recordDTO1);

        RecordDTO recordDTO2 = new RecordDTO();
        recordDTO2.setName("小强");
        recordDTO2.setTime("2021-07-31 10:20:10");
        recordDTO2.setResult("已通过");

        record.add(recordDTO2);

        demoDTO.setRecords(record);
        fileService.downloadDemo(demoDTO, response);
    }


   //
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                result.append(System.lineSeparator()+s);
            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
