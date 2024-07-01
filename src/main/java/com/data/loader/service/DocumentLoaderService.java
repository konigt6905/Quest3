package com.data.loader.service;


import com.data.loader.model.DocumentTag;
import com.data.loader.model.TagHierarchy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DocumentLoaderService {

    private final Map<String, TagHierarchy> tagHierarchy;
    private final Map<String, DocumentTag> documentTags;

    public Set<String> documentLoaderService(String tag) {
        Set<String> tagsToSearch = new HashSet<>();
        collectAllSubTags(tag, tagsToSearch);

        Set<String> documents = new HashSet<>();
        for (DocumentTag documentTag : documentTags.values()) {
            for (String t : tagsToSearch) {
                if (documentTag.getTags().contains(t)) {
                    documents.add(documentTag.getUri());
                    break;
                }
            }
        }
        return documents;
    }

    private void collectAllSubTags(String tag, Set<String> tags) {
        tags.add(tag);
        TagHierarchy subTags = tagHierarchy.get(tag);
        if (subTags != null) {
            for (String subTag : subTags.getSubTags()) {
                collectAllSubTags(subTag, tags);
            }
        }
    }
}
