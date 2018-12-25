package com.rich007.quizzo;

public class TrueFalse {

    private int qId;
    private String ans;

    TrueFalse(int qId,String ans)
    {
        this.qId = qId;
        this.ans = ans;
    }

    public int getqId() {
        return qId;
    }

    public void setqId(int qId) {
        this.qId = qId;
    }

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }
}
