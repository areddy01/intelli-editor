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

    private static String UPLOADED_FOLDER = "./tmp";

    @RequestMapping(method = RequestMethod.POST)
    public String bundleResources(@RequestParam("files") MultipartFile[] files) throws IOException {
        String message = writeDocumentToServer(files);
        return message;
    }

    private String writeDocumentToServer(MultipartFile[] files) {

        if (files.length == 0)
            return "Mandatory information missing";

        String message = "";
        for (int i = 0; i < files.length; i++) {
            MultipartFile file = files[i];
            String fileName =  files[i].getOriginalFilename();
            try {

                byte[] bytes = file.getBytes();
                File uploadDirectory = new File(UPLOADED_FOLDER + File.separator);

                if (!uploadDirectory.exists())
                    uploadDirectory.mkdirs();

                File serverFile = new File(uploadDirectory.getAbsolutePath()
                        + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                message = message + "You successfully uploaded file=" + fileName + "";
            } catch (IOException e){
                return "You failed to upload " + fileName + " => " + e.getMessage();
            }
        }
        return message;
    }


}
