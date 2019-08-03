package ua.dovhopoliuk.springtask.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class FileController {

    @Value("${image.directory.path}")
    private String imageDirPath;

    public String saveFile(MultipartFile multipartFile) throws IOException {
        System.out.println("Start");
        File directory = new File(imageDirPath);

        if (!directory.exists()){
            directory.mkdir();
        }

        System.out.println("Start");
        String uuidFile = UUID.randomUUID().toString();
        String resultFileName = uuidFile + multipartFile.getOriginalFilename();

        System.out.println(resultFileName);

        File destinationFile = new File(imageDirPath + "/" + resultFileName);

        System.out.println(imageDirPath + "/" + resultFileName);

        if (destinationFile.createNewFile()) {
            multipartFile.transferTo(destinationFile);
            return resultFileName;
        }

        return null;
    }

}
