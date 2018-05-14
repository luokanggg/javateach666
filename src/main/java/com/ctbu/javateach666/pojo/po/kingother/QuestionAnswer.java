package com.ctbu.javateach666.pojo.po.kingother;

public class QuestionAnswer {

    private Integer id;
    private String checked;
    private String title;
    private String answer;
    private Integer score;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public QuestionAnswer() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public Integer getId() {

        return id;
    }

    public String getChecked() {
        return checked;
    }
}
