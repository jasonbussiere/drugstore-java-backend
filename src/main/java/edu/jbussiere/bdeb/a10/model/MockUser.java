package edu.jbussiere.bdeb.a10.model;

public class MockUser {
    public int id;
    String discriminator;
    public String name;
    public String tel;
    int age;
    int weight;
    String allergies;
    String skill;

    public MockUser(int id, String discriminator, String name, String tel, int age, int weight, String allergies, String skill) {
        this.id = id;
        this.discriminator = discriminator;
        this.name = name;
        this.tel = tel;
        this.age = age;
        this.weight = weight;
        this.allergies = allergies;
        this.skill = skill;
    }
}
