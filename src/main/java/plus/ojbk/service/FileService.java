package plus.ojbk.service;


import plus.ojbk.dto.DemoDTO;

import javax.servlet.http.HttpServletResponse;


public interface FileService {


    /***
     * 下载
     * @param demoDTO
     * @param response
     */
    void downloadDemo(DemoDTO demoDTO, HttpServletResponse response);


}