package aua.projects.engineering.dto;

import java.util.List;
import java.util.Map;

public class SearchFilter {
    private Map<String, String> searchParams;

    public Map<String, String> getSearchParams() {
        return searchParams;
    }

    public void setSearchParams(Map<String, String> searchParams) {
        this.searchParams = searchParams;
    }
}
