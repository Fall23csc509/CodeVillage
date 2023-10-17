package org.codevillage.filter;

import java.util.ArrayList;

public class JavaClass {

    private String name;
    private int methods;

    private int attributes;
    private String packageName;
    private ArrayList<String> relations;
    private String category;

    public JavaClass(){
        this.name = null;
        this.methods = 0;
        this.attributes = 0;
        this.packageName = null;
        this.relations = null;
        this.category  = null;
    }
    public JavaClass(String name, int methods, int attributes, String packageName, ArrayList<String> relations, String category ) {
        this.name = name;
        this.methods = methods;
        this.attributes = attributes;
        this.packageName = packageName;
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

    public String getPackageName() {
        return packageName;
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

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setRelations(ArrayList<String> relations) {
        this.relations = relations;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "JavaClass{" +
                "name='" + name + '\'' +
                ", methods=" + methods +
                ", attributes=" + attributes +
                ", district='" + packageName + '\'' +
                ", relations=" + relations +
                ", category='" + category + '\'' +
                '}';
    }
}
