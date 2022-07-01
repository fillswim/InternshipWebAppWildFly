package org.example.models;

import javax.persistence.*;

@Entity
@Table(name = "information")
public class Info {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private String period;

    @Column(name = "copyright")
    private String copyright;

    public Info() {
    }

    public Info(String name, String period, String copyright) {
        this.name = name;
        this.period = period;
        this.copyright = copyright;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }
}
