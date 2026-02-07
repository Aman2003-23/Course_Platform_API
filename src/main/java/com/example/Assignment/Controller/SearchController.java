package com.example.Assignment.Controller;


import com.example.Assignment.DTO.Search.SearchResponseDto;
import com.example.Assignment.Service.SearchService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/search")
@RequiredArgsConstructor
@Tag(name= "Search")
public class SearchController {
    private final SearchService searchService;

    @GetMapping
    public SearchResponseDto search(@RequestParam String q) {

        return searchService.search(q);
    }
}
