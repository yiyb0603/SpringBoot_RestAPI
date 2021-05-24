package com.example.demo.service.upload;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.example.demo.lib.Constants;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadServiceImpl implements UploadService {
  @Override
  public HashMap<String, List<String>> handleUploadFiles(List<MultipartFile> files) throws IOException {
    HashMap<String, List<String>> fileMap = new HashMap<String, List<String>>();
    List<String> uploadedFiles = new ArrayList<String>();

    for (MultipartFile file : files) {
      if (!file.isEmpty()) {
        String fileName = file.getOriginalFilename();
        String fileExtension = StringUtils.getFilenameExtension(fileName);

        String basePath = "/" + UUID.randomUUID() + "." + fileExtension;
        String filePath = Constants.STATIC_PATH + basePath;

        File destination = new File(Constants.STATIC_DIRECTORY + basePath);

        if (!destination.exists()) {
          destination.mkdirs();
        }

        file.transferTo(destination);
        uploadedFiles.add(filePath);
      }
    }

    fileMap.put("files", uploadedFiles);
    return fileMap;
  }
}
