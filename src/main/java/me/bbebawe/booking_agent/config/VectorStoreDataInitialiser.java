package me.bbebawe.booking_agent.config;

import jakarta.annotation.PostConstruct;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class VectorStoreDataInitialiser {

    @Autowired
    private VectorStore vectorStore;

    @PostConstruct
    public void init() {
        vectorStore.add(new TikaDocumentReader(new ClassPathResource("company-policy.txt")).get());
    }
}
