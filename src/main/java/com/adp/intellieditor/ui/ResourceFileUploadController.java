package com.adp.intellieditor.ui;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by reddyadi on 6/19/2017.
 */

@RestController
@RequestMapping("/upload")
public class ResourceFileUploadController {

    @RequestMapping(method = RequestMethod.POST)
    public String bundleResources(@RequestParam("files") MultipartFile[] files) {
        return "";
    }


}
