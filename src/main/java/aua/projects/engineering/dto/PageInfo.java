package aua.projects.engineering.dto;

public class PageInfo {
    public static final int DEFAULT_SIZE = 15;

    private int index;
    private int size;

    public PageInfo() {
        this.setIndex(0);
        this.setSize(DEFAULT_SIZE);
    }

    public PageInfo(int index, int size) {
        this.setIndex(index);
        this.setSize(size);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}

