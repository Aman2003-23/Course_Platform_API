package com.example.Assignment.Service;


import com.example.Assignment.DTO.Search.SearchCourseResultDto;
import com.example.Assignment.DTO.Search.SearchMatchDto;
import com.example.Assignment.DTO.Search.SearchResponseDto;
import com.example.Assignment.Repository.SearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SearchServiceImpl implements SearchService {

    private final SearchRepository searchRepository;

    @Override
    public SearchResponseDto search(String query) {

        List<Object[]> rows = searchRepository.searchRaw(query);

        Map<String, CourseAccumulator> byCourse = new LinkedHashMap<>();

        for (Object[] r : rows) {

            String courseId = (String) r[0];
            String courseTitle = (String) r[1];
            String topicTitle = (String) r[2];
            String subtopicId = (String) r[3];
            String subtopicTitle = (String) r[4];
            String content = (String) r[5];

            String type = content.toLowerCase().contains(query.toLowerCase())
                    ? "content"
                    : "subtopic";

            String snippet = buildSnippet(content, query);

            SearchMatchDto match = new SearchMatchDto(
                    type,
                    topicTitle,
                    subtopicId,
                    subtopicTitle,
                    snippet
            );

            byCourse
                    .computeIfAbsent(courseId,
                            k -> new CourseAccumulator(
                                    courseTitle,
                                    new ArrayList<>()
                            ))
                    .matches()
                    .add(match);

        }

        List<SearchCourseResultDto> results =
                byCourse.entrySet().stream()
                        .map(e -> new SearchCourseResultDto(
                                e.getKey(),
                                e.getValue().courseTitle(),
                                e.getValue().matches()
                        ))
                        .toList();


        return new SearchResponseDto(query, results);
    }

    private String buildSnippet(String content, String query) {

        int idx = content.toLowerCase()
                .indexOf(query.toLowerCase());

        if (idx == -1) {
            return content.substring(0,
                    Math.min(120, content.length())) + "...";
        }

        int start = Math.max(0, idx - 40);
        int end = Math.min(content.length(), idx + 80);

        return "..." + content.substring(start, end) + "...";
    }
    private record CourseAccumulator(
            String courseTitle,
            List<SearchMatchDto> matches
    ) {}

}

