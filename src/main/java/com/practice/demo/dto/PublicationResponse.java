package com.practice.demo.dto;

import java.util.List;

public class PublicationResponse {
    private List<PublicationDTO> content;
    private int numbPage;
    private int sizePage;
    private long totalElements;
    private int totalPages;
    private boolean isLast;

    public PublicationResponse() {
        super();
    }

    public List<PublicationDTO> getContent() {
        return content;
    }

    public void setContent(List<PublicationDTO> content) {
        this.content = content;
    }

    public int getNumbPage() {
        return numbPage;
    }

    public void setNumbPage(int numbPage) {
        this.numbPage = numbPage;
    }

    public int getSizePage() {
        return sizePage;
    }

    public void setSizePage(int sizePage) {
        this.sizePage = sizePage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }
}
