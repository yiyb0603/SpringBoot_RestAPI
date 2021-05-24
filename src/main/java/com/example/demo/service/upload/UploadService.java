package com.example.demo.service.upload;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public interface UploadService {
  public HashMap<String, List<String>> handleUploadFiles(List<MultipartFile> files) throws IOException;
}
