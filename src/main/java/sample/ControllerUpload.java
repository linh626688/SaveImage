package sample;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by DangThanhLinh on 08/03/2017.
 */
@RestController
public class ControllerUpload {
    private static String UPLOADED_FOLDER = "D://temp//";

    @GetMapping("/")
    public String index() {
        return "upload";
    }

    @RequestMapping(value = "/upload", headers = "content-type=multipart/form-data", method = RequestMethod.POST)
    public String singleFileUpload(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile.isEmpty()) {
            return "Null";
        }
        try {
            byte[] bytes = multipartFile.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + multipartFile.getOriginalFilename());
            Files.write(path, bytes);
            System.out.println(path);


        } catch (IOException e) {
            e.printStackTrace();
        }
        return "done";
    }
}
