package plus.ojbk.dto;

import lombok.Data;

import java.util.List;

@Data
public class DemoDTO {


    private String time1; //接收时间
    private String time2;//发布时间
    private String time3; //开始时间
    private String time4; //结束时间


    private String sms; //短信内容
    private String pic; //图片base64 不要前面的那个 【data:image/png;base64,】
    private List<RecordDTO> records;  //审批记录
}
