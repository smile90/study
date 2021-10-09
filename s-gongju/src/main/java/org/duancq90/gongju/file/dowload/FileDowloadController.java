package org.duancq90.gongju.file.dowload;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @author dcq
 * @version V1.0
 * @date 2021/4/8 下午2:50
 */
@Slf4j
@RestController
@RequestMapping("/file/dowload")
public class FileDowloadController {

    class Content {
        public Content(int i) {
            this.i = i;
        }

        private int i = 0;
        public String[] getContent() {
            return new String[] {"内容" + i + "-1", "内容" + i + "-2",
                    "内容" + i + "-3", "内容" + i + "-4", "内容" + i + "-5",
                    "内容" + i + "-6", "内容" + i + "-7", "内容" + i + "-8"};
        }
    }

    @GetMapping("/excel")
    public void dowloadExcel(HttpServletResponse response) throws IOException {
        response.addHeader("Content-Disposition", "attachment; filename=file.xlsx");
        response.setCharacterEncoding("UTF-8");

        OutputStream os = response.getOutputStream();

        ExcelBuilder.CurrentSheet sheet =
                new ExcelBuilder()
                .createSheet("test")
                .writeHeadTitle(
                    os,
                    new String[] {"表头1", "表头2", "表头3", "表头4", "表头5", "表头6", "表头7", "表头8"}
                );
        for (int i = 0; ; i++){
            Content content = new Content(i);
            log.info("row:" + i);
            sheet.writeContent(os, content.getContent());
            os.flush();
        }
    }
}
