package com.data.loader.model;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class TagHierarchy {

    private String tag;
    private Set<String> subTags = new HashSet<>();

}
