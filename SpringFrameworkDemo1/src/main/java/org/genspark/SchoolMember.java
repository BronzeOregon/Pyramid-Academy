package org.genspark;

import org.springframework.beans.factory.annotation.Autowired;

public class SchoolMember {
    int id = 0;
    String name = null;
    Phone ph = null;
    Address add = null;

    @Autowired(required = true)
    public SchoolMember() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + this.id +
                ", name='" + this.name + '\'' +
                ", ph=" + this.ph +
                ", add=" + this.add +
                '}';
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

    public Phone getPh() {
        return ph;
    }

    public void setPh(Phone ph) {
        this.ph = ph;
    }

    public Address getAdd() {
        return add;
    }

    public void setAdd(Address add) {
        this.add = add;
    }
}
