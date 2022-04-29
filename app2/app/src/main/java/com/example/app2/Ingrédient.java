package com.example.app2;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial") //With this annotation we are going to hide compiler warnings
public class Ingrédient implements Serializable {
    public String Name;
    public String Description;

    public Ingrédient(String name, String description) {
        Name = name;
        Description = description;
    }
}
