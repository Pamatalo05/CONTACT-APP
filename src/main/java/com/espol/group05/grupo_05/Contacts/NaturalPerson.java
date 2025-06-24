package com.espol.group05.grupo_05.Contacts;

public class NaturalPerson extends Contact{
    private String firstName;
    private String secondName;
    private String surname;
    @Override
    public int compareTo(Contact c) {
        NaturalPerson o = (NaturalPerson) c;
        int n = this.surname.compareTo(o.getSurname());
        if(n==0){
            return this.firstName.compareTo(o.getFirstName());
        }
        return n;
    }

    public int compareToCountry(Contact c){
        return this.getCountry().compareTo(c.getCountry());
    }

    public NaturalPerson(String[] credentials){
        super(credentials);
    }
    @Override
    public String toString() {
        return "NP"+this.getUser()+","+firstName+","+secondName+","+surname;
    }
    public String getSurname() {
        return surname;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
}
