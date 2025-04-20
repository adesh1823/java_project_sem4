package com.yourname.summariser;

import com.yourname.summariser.Entity.SummaryRecord;
import com.yourname.summariser.repository.SummaryRecordRepository;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/summarize")
public class SummarizerController {

    private final DocumentSummarizer summarizer;

    // HuggingFace API key
    private static final String API_KEY = "your key";

    @Autowired
    private SummaryRecordRepository summaryRepo;

    public SummarizerController() {
        this.summarizer = new DocumentSummarizer(API_KEY);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> summarize(@RequestParam("file") MultipartFile file,
                                            @RequestParam("maxLength") int maxLength,
                                            @RequestParam("username") String username) throws IOException {
        String extractedText = extractTextFromPdf(file);
        String summary = summarizer.summarize(extractedText, maxLength);

        // Save to DB
        SummaryRecord record = new SummaryRecord(username, summary);
        summaryRepo.save(record);

        return ResponseEntity.ok(summary);
    }

    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("Document Summarizer is up and running!");
    }
  
    @GetMapping("/summary/{id}")
    public ResponseEntity<?> getSummaryById(@PathVariable Long id) {
        SummaryRecord record = summaryRepo.findById(id).orElse(null);
    
        if (record == null) {
            return ResponseEntity.status(404).body("Summary not found for ID: " + id);
        }
        return ResponseEntity.ok(record);
    }
    

    private String extractTextFromPdf(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(document);
        }
    }
}
