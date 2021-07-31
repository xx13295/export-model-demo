package plus.ojbk.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import plus.ojbk.dto.DemoDTO;
import plus.ojbk.service.FileService;
import plus.ojbk.util.FreeMarkerUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Service
@Slf4j
public class FileServiceImpl implements FileService {



    @Value("${temp.path:E:/}")
    private String TEMP_PATH;


    @Override
    public void downloadDemo(DemoDTO demoDTO, HttpServletResponse response) {

        File file = FreeMarkerUtil.freeMarkerRender(demoDTO, "demo模板.ftl", "demo.doc", TEMP_PATH, response);
        FreeMarkerUtil.exportFile(response, file);

    }


}