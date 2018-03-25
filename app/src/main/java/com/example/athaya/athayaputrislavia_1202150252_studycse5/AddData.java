package com.example.athaya.athayaputrislavia_1202150252_studycse5;

/**
 * Created by Athaya on 3/24/2018.
 */

public class AddData {
    //mendeklarasika variabel
    String todo, desc, prior;

    //menginisiasi objek
    public AddData(String todo, String desc, String prior){
        this.todo=todo;
        this.desc=desc;
        this.prior=prior;
    }

    //method setter dan getter
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
