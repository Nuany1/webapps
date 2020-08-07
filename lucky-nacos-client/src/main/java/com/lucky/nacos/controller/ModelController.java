package com.lucky.nacos.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfCopy;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lucky.nacos.entity.ModelPage;
import com.lucky.nacos.service.ModelPageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RefreshScope
@RequestMapping("/config")
@Api(value = "/config", description = "User 相关操作")
public class ModelController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ModelPageService modelPageService;

    @GetMapping("/configinfo")
    @ApiOperation(value = "/configinfo", notes = "测试")
    public String query() {
        return "fjkdla";
    }

    @PostMapping("/model")
    @ApiOperation(value = "/model", notes = "Model")
    public Map ModelPage() {
        Map map = new HashMap();
        List<ModelPage> all = modelPageService.findAll();
        map.put("page", all);
        return map;
    }

    @PostMapping("/query")
    @ApiOperation(value = "/model", notes = "Model")
    public String ModelQuery(@RequestBody JSONObject params) {
        String applyId = params.getString("applyId");
        JSONObject postData = new JSONObject();
        postData.put("applyId", applyId);
        String url = "http://172.16.11.123:9011/page/initpdf";
        String body = restTemplate.postForEntity(url, postData, String.class).getBody();
        JSONObject object = JSONObject.parseObject(body);
        System.out.println(object);
        return body;
    }

    @PostMapping("/input")
    public String input() {
        try {
            Str();
            String[] files =new String[] {"D:\\pdf\\2236\\20200804\\openAccount1.pdf","D:\\pdf\\2236\\20200804\\openAccount2.pdf"};
            mergePdfFiles(files,"D:\\pdf\\2236\\20200804\\openAccount.pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "成功";
    }

    public static void Str() throws IOException, DocumentException {

        InputStream input = new FileInputStream(new File("F:\\project\\开户系统\\民银\\open-account\\open-account-service\\src\\main\\resources\\template\\1_opend.pdf"));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream("D:\\pdf\\2236\\20200804\\openAccount1.pdf"));
        input.close();
        stamper.close();
        reader.close();
        File pathPdf1_file = new File("D:\\pdf\\2236\\20200804\\openAccount1.pdf");
        System.out.println(pathPdf1_file.getName());
    }

    public static void mergePdfFiles(String[] files, String newfile) throws IOException, DocumentException {
        Document document = null;
        FileOutputStream stream = null;
        PdfReader pageSize = null;
        try {
            pageSize = new PdfReader(files[0]);
            System.out.println(pageSize.getPageSize(1)+"311111111");
            document = new Document(pageSize.getPageSize(1));
            stream =  new FileOutputStream(newfile);
            PdfCopy copy = new PdfCopy(document, stream);
            document.open();
            for (int i = 0; i < files.length; i++) {
                System.out.println(files[i]);
                PdfReader reader = new PdfReader(files[i]);
                int n = reader.getNumberOfPages();
                for (int j = 1; j <= n; j++) {
                    document.newPage();
                    PdfImportedPage page = copy.getImportedPage(reader, j);
                    copy.addPage(page);
                }
                if (reader!=null){
                    reader.close();
                }
            }
        } finally {
            if (document != null) {
                document.close();
            }
            if (pageSize !=null){
                pageSize.close();
            }
            if (stream!=null){
                stream.close();
            }
        }
    }
}
