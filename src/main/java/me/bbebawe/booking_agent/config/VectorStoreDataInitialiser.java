package me.bbebawe.booking_agent.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.chroma.vectorstore.ChromaVectorStore;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VectorStoreDataInitialiser {

    @Autowired
    private VectorStore vectorStore;

    @PostConstruct
    public void init() {
        TikaDocumentReader tikaDocumentReader = new TikaDocumentReader(new ClassPathResource("company-policy.txt"));

        List<Document> documents = tikaDocumentReader.get();
        vectorStore.add(documents);
    }
}
