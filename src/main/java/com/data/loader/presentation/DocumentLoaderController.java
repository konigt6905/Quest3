package com.data.loader.presentation;

import com.data.loader.service.DocumentLoaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController()
@RequiredArgsConstructor
public class DocumentLoaderController {

    private final DocumentLoaderService documentLoaderService;

    @GetMapping("/taggedContent")
    public Set<String> getTaggedContent(@RequestParam String tag) {
        return documentLoaderService.documentLoaderService(tag);
    }

}
