package com.espol.group05.grupo_05.Contacts;

public class Company extends Contact{
    public String name;
    public TypeCompany typeCompany;
    @Override
    public int compareTo(Contact c) {
        Company C = (Company) c;
        int n = this.name.compareTo(C.getName());
        if(n==0){
            return this.name.compareTo(C.getName());
        }
        return n;
    }
    public Company(String[] credentials){
        super(credentials);
    }
    @Override
    public String toString() {
        return "C"+name+","+typeCompany.toString();
    }

    public String getName() {
        return name;
    }

    public int compareToCountry(Contact c){
        return this.getCountry().compareTo(c.getCountry());
    }
}
