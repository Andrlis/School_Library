package bean;

public class Literature {

    private int id;
    private String type;
    private String name;
    private String author;
    private int numOfAvailable;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getNumOfAvailable() {
        return numOfAvailable;
    }

    public void setNumOfAvailable(int numOfAvailable) {
        this.numOfAvailable = numOfAvailable;
    }

    public boolean isAvailable(){
        return numOfAvailable > 0;
    }
}