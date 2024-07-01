package com.data.loader.config;

import com.data.loader.DataFileException;
import com.data.loader.model.DocumentTag;
import com.data.loader.model.TagHierarchy;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class DataLoader {

    @Value("${data.file.path}")
    private String dataFilePath;

    @Bean
    public Map<String, TagHierarchy> tagHierarchy() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dataFilePath)) {
            if (inputStream == null) {
                throw new DataFileException("File not found: " + dataFilePath);
            }
            DataFile dataFile = objectMapper.readValue(inputStream, DataFile.class);
            return dataFile.getTagHierarchies().stream().collect(Collectors.toMap(TagHierarchy::getTag, tag -> tag));
        } catch (IOException e) {
            throw new DataFileException("Failed to load tag hierarchy from file: " + dataFilePath, e);
        }
    }

    @Bean
    public Map<String, DocumentTag> documentTags() {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(dataFilePath)) {
            if (inputStream == null) {
                throw new DataFileException("File not found: " + dataFilePath);
            }
            DataFile dataFile = objectMapper.readValue(inputStream, DataFile.class);
            return dataFile.getDocumentTags().stream().collect(Collectors.toMap(DocumentTag::getUri, doc -> doc));
        } catch (IOException e) {
            throw new DataFileException("Failed to load document tags from file: " + dataFilePath, e);
        }
    }

    @Data
    private static class DataFile {
        private List<TagHierarchy> tagHierarchies;
        private List<DocumentTag> documentTags;
    }
}