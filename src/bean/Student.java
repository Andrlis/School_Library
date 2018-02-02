package bean;

import java.util.Date;

public class Student {

    private int id;
    private String name;
    private Date dateOfBirth;
    private int numberOfBooks;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getNumberOfBooks() {
        return numberOfBooks;
    }

    public void setNumberOfBooks(int numberOfBooks) {
        this.numberOfBooks = numberOfBooks;
    }

    public void incNumberOfBooks(){
        numberOfBooks++;
    }

    public void decNumberOfBooks(){
        if(numberOfBooks > 0)
            numberOfBooks--;
    }
}