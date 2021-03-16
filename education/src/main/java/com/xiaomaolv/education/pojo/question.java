package com.xiaomaolv.education.pojo;

import java.util.Date;

public class question {

    private Integer id;
    private Integer importance;
    private String remark;
    private Date timestamp;
    private String title;
    private Integer type;
    private Integer status;
    private String questions_stem;
    private String rightAns;

    public question(){}

    public question(Integer id, Integer importance, String remark, Date timestamp, String title, Integer type, Integer status, String questions_stem, String rightAns) {
        this.id = id;
        this.importance = importance;
        this.remark = remark;
        this.timestamp = timestamp;
        this.title = title;
        this.type = type;
        this.status = status;
        this.questions_stem = questions_stem;
        this.rightAns = rightAns;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getImportance() {
        return importance;
    }

    public void setImportance(Integer importance) {
        this.importance = importance;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getQuestions_stem() {
        return questions_stem;
    }

    public void setQuestions_stem(String questions_stem) {
        this.questions_stem = questions_stem;
    }

    public String getRightAns() {
        return rightAns;
    }

    public void setRightAns(String rightAns) {
        this.rightAns = rightAns;
    }

    @Override
    public String toString() {
        return "question{" +
                "id=" + id +
                ", importance=" + importance +
                ", remark='" + remark + '\'' +
                ", timestamp=" + timestamp +
                ", title='" + title + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", questions_stem='" + questions_stem + '\'' +
                ", rightAns='" + rightAns + '\'' +
                '}';
    }
}
