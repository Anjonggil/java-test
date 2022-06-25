package com.example.javatest;


public class Study {

    private StudyStatus studyStatus;
    private int limit;

    private String name;

    public StudyStatus getStudyStatus() {
        return studyStatus;
    }

    public Study() {
        this.studyStatus = StudyStatus.DRAFT;
    }

    public Study(int limit, String name) {
        this.limit = limit;
        this.name = name;
    }

    public Study(int limit) {
        if (limit < 0) throw new IllegalArgumentException("limit은 0 보다 커야합니다.");
        this.limit = limit;
    }

    public Study(StudyStatus studyStatus, int limit) {
        this.studyStatus = studyStatus;
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Study{" +
                "studyStatus=" + studyStatus +
                ", limit=" + limit +
                ", name='" + name + '\'' +
                '}';
    }
}
