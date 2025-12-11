package com.jobfit.util;

import java.util.*;
import java.util.stream.Collectors;

public class SkillMatcher {

    private static final List<String> KNOWN_SKILLS = Arrays.asList(
            "java",
            "spring",
            "spring boot",
            "jdbc",
            "mysql",
            "sql",
            "html",
            "css",
            "javascript",
            "react",
            "angular",
            "node",
            "docker",
            "aws",
            "git",
            "rest",
            "microservices"
    );

    public static List<String> extractSkillsFromResume(String resumeText) {
        if (resumeText == null) return Collections.emptyList();
        String lower = resumeText.toLowerCase();
        return KNOWN_SKILLS.stream()
                .filter(lower::contains)
                .collect(Collectors.toList());
    }

    public static List<String> parseRequiredSkills(String csv) {
        if (csv == null || csv.trim().isEmpty()) {
            return Collections.emptyList();
        }
        return Arrays.stream(csv.split(","))
                .map(s -> s.trim().toLowerCase())
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    public static double calculateMatchPercent(List<String> candidateSkills, List<String> requiredSkills) {
        if (requiredSkills == null || requiredSkills.isEmpty()) return 0.0;
        long matched = requiredSkills.stream()
                .filter(candidateSkills::contains)
                .count();
        return (matched * 100.0) / requiredSkills.size();
    }

    public static List<String> findMissingSkills(List<String> candidateSkills, List<String> requiredSkills) {
        if (requiredSkills == null) return Collections.emptyList();
        return requiredSkills.stream()
                .filter(skill -> !candidateSkills.contains(skill))
                .collect(Collectors.toList());
    }
}
