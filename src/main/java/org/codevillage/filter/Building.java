package org.codevillage.filter;

import java.util.ArrayList;

public class Building {

    private String name;
    private int methods;

    private int attributes;
    private String district;
    private ArrayList<String> relations;
    private String category;

    public Building(){
        this.name = null;
        this.methods = 0;
        this.attributes = 0;
        this.district = null;
        this.relations = null;
        this.category  = null;
    }
    public Building(String name, int methods, int attributes, String district, ArrayList<String> relations, String category ) {
        this.name = name;
        this.methods = methods;
        this.attributes = attributes;
        this.district = district;
        this.relations = relations;
        this.category  = category;
    }

    public String getName() {
        return name;
    }

    public int getMethods() {
        return methods;
    }

    public int getAttributes() {
        return attributes;
    }

    public String getDistrict() {
        return district;
    }

    public ArrayList<String> getRelations() {
        return relations;
    }

    public String getCategory() {
        return category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMethods(int methods) {
        this.methods = methods;
    }

    public void setAttributes(int attributes) {
        this.attributes = attributes;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setRelations(ArrayList<String> relations) {
        this.relations = relations;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Building{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                ", attributes=" + attributes +
                ", district='" + district + '\'' +
                ", relations=" + relations +
                ", category='" + category + '\'' +
                '}';
    }
}
