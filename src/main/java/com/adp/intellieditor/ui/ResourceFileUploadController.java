package com.adp.intellieditor.ui;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

/**
 * Created by reddyadi on 6/19/2017.
 */

@RestController
@RequestMapping("/upload")
public class ResourceFileUploadController {

    private static String UPLOADED_FOLDER = "./tmp/";

    @RequestMapping(method = RequestMethod.POST)
    public String bundleResources(@RequestParam("files") MultipartFile[] files) throws IOException {
        writeDocumentToServer(files);
        return "success";
    }

    private String writeDocumentToServer(MultipartFile[] files) throws IOException, FileNotFoundException {
        String msg = "";
        for (int i = 0; i < files.length; i++) {
            String fileName = files[i].getOriginalFilename();

            byte[] bytes = files[i].getBytes();
            File newUploadFile = new File(UPLOADED_FOLDER + fileName);
            BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(newUploadFile));
            buffStream.write(bytes);
            buffStream.close();

           /* if (StringUtils.isNotEmpty(category)) {
                FileUtils.deleteQuietly(newUploadFile);
            }*/

            File directory = new File(UPLOADED_FOLDER);
            if (!directory.exists()) {
                directory.mkdir();
            }

            BufferedOutputStream buffStreamCate = new BufferedOutputStream(
                    new FileOutputStream(new File(UPLOADED_FOLDER + "/" + fileName)));
            buffStreamCate.write(bytes);

            buffStreamCate.close();
        }
        msg = "You have successfully uploaded.";
        return msg;
    }


}
