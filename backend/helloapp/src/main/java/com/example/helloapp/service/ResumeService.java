package com.example.helloapp.service;
import com.example.helloapp.service.JobService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ResumeService {

    // Creates uploads folder inside project directory
    private final String UPLOAD_DIR =
        Paths.get(
                "backend",
                "helloapp",
                "uploads"
        ).toAbsolutePath().toString();

    public String uploadResume(MultipartFile file) {

        try {

            // Create uploads folder if it doesn't exist
            File directory = new File(UPLOAD_DIR);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Get original filename
            String fileName = file.getOriginalFilename();

            // Create destination file
            File destination = new File(
                    directory,
                    fileName
            );

            // Print save path in console
            System.out.println(
                    "Saving to: "
                    + destination.getAbsolutePath()
            );

            // Save file
            file.transferTo(destination);

            String extractedText =
        extractText(
                destination.getAbsolutePath()
        );

List<String> skills =
        extractSkills(
                extractedText
        );

System.out.println(
        "Detected Skills:"
);

Map<String,Integer> matches =
        jobService.matchJobs(
                skills
        );

System.out.println(
        "Job Matches:"
);

System.out.println(
        matches
);

System.out.println(
        extractedText
);

return "Resume Uploaded Successfully";

        }

        catch (IOException e) {

            e.printStackTrace();

            return "Upload Failed: "
                    + e.getMessage();
        }
    }
    public String extractText(String filePath){

    try{

        File file = new File(filePath);

        PDDocument document =
                Loader.loadPDF(file);

        PDFTextStripper pdfStripper =
                new PDFTextStripper();

        String text =
                pdfStripper.getText(document);

        document.close();

        return text;

    }

    catch(Exception e){

        e.printStackTrace();

        return "Could not extract text";
    }

}
public List<String> extractSkills(
        String text){

    List<String> allSkills =
            List.of(

            "Java",
            "Spring Boot",
            "React",
            "Node.js",
            "Python",
            "MongoDB",
            "PostgreSQL",
            "HTML",
            "CSS",
            "JavaScript",
            "TypeScript",
            "AWS",
            "Docker",
            "Git",
            "Machine Learning",
            "SQL"

    );

    List<String> foundSkills =
            new ArrayList<>();

    for(String skill : allSkills){

        if(text.toLowerCase()
                .contains(
                        skill.toLowerCase()
                )){

            foundSkills.add(skill);

        }
    }

    return foundSkills;
}
@Autowired
JobService jobService;
}