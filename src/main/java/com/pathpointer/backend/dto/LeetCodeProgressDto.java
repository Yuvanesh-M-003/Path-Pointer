package com.pathpointer.backend.dto;

import java.util.Set;

public class LeetCodeProgressDto {

    private int totalSolved;
    private int easySolved;
    private int mediumSolved;
    private int hardSolved;
    private Set<String> solvedSlugs;

    public LeetCodeProgressDto() {}

    public LeetCodeProgressDto(int totalSolved, int easySolved, int mediumSolved,
                               int hardSolved, Set<String> solvedSlugs) {
        this.totalSolved = totalSolved;
        this.easySolved = easySolved;
        this.mediumSolved = mediumSolved;
        this.hardSolved = hardSolved;
        this.solvedSlugs = solvedSlugs;
    }

    public int getTotalSolved() {
        return totalSolved;
    }

    public void setTotalSolved(int totalSolved) {
        this.totalSolved = totalSolved;
    }

    public int getEasySolved() {
        return easySolved;
    }

    public void setEasySolved(int easySolved) {
        this.easySolved = easySolved;
    }

    public int getMediumSolved() {
        return mediumSolved;
    }

    public void setMediumSolved(int mediumSolved) {
        this.mediumSolved = mediumSolved;
    }

    public int getHardSolved() {
        return hardSolved;
    }

    public void setHardSolved(int hardSolved) {
        this.hardSolved = hardSolved;
    }

    public Set<String> getSolvedSlugs() {
        return solvedSlugs;
    }

    public void setSolvedSlugs(Set<String> solvedSlugs) {
        this.solvedSlugs = solvedSlugs;
    }
}