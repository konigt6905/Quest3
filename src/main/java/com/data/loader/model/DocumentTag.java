package com.data.loader.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class DocumentTag {

    private String uri;
    private Set<String> tags = new HashSet<>();

}
