package libraryapp;

import bean.Literature;
import bean.Student;
import dao.DaoException;
import dao.LiteratureDAO;
import dao.StudentDAO;
import dao.UsedLiteratureDAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class SchoolLibrary {

    private boolean cancel;

    public SchoolLibrary(){
        cancel = false;
    }

    /*
        Analyze user input.
     */
    private void analyzeInput(String input) throws Exception{
        if(input.equals("/addStud")){
            addStudent();
        }
        else if(input.equals("/addLit")){
            addLiterature();
        }
        else if(input.equals("/setLit")){
            setLiterature();
        }
        else if(input.equals("/retLit")){
            returnLiterature();
        }
        else if(input.equals("/showLit")){
            showLiterature();
        }
        else if(input.equals("/showStud1")){
            showReport1();
        }
        else if(input.equals("/showStud2")){
            showReport2();
        }
        else if(input.equals("/q")){
           cancel = true;
        }
        else if(input.equals("/h")){
            Menu.printHelp();
        }
        else{
            System.out.println("Input \'/h\' to see available options.");
        }
    }

    /*
        Add new student.
     */
    private void addStudent() throws Exception{
        System.out.println("Input information about student. (Format: Name/Date of birth");
        Scanner scanner = new Scanner(System.in);
        String[] studInfo = scanner.nextLine().split("/");

        Student newStudent = new Student();
        newStudent.setName(studInfo[0]);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        newStudent.setDateOfBirth(format.parse(studInfo[1]));

        StudentDAO studDAO = new StudentDAO();
        studDAO.saveStudent(newStudent);
    }

    /*
        Add new literature.
    */
    private void addLiterature() throws Exception{
        System.out.println("Input information about literature. (Format: Type/Name/Author/Amount");
        Scanner scanner = new Scanner(System.in);
        String[] litInfo = scanner.nextLine().split("/");

        Literature newLiterature = new Literature();
        newLiterature.setType(litInfo[0]);
        newLiterature.setName(litInfo[1]);
        newLiterature.setAuthor(litInfo[2]);
        newLiterature.setNumOfAvailable(Integer.parseInt(litInfo[3]));

        LiteratureDAO litDAO = new LiteratureDAO();
        litDAO.saveLiterature(newLiterature);
    }

    /*
        View the list of available literature.
     */
    private void showLiterature() throws DaoException{
        LiteratureDAO litDAO = new LiteratureDAO();
        ArrayList<Literature> literature = new ArrayList<>(litDAO.getLiterature(LiteratureDAO.GET_AVAILABLE_QUERY));
        for(Literature item: literature){
            System.out.format("%-20s %-50s %-20s %n", item.getType(), item.getName(), item.getAuthor());
        }
    }

    /*
        Set literature.
     */
    private void setLiterature() throws DaoException{
        Scanner scanner = new Scanner(System.in);
        LiteratureDAO litDAO = new LiteratureDAO();
        StudentDAO studDao = new StudentDAO();
        UsedLiteratureDAO usedDao = new UsedLiteratureDAO();

        System.out.println("Select student:");

        ArrayList<Student> students = new ArrayList<>(studDao.getStudents(studDao.GET_ALL_QUERY));
        for(int i = 0; i < students.size(); i++){
            System.out.format("%d. %s %n", i+1, students.get(i).getName());
        }

        int studNumber = scanner.nextInt()-1;

        ArrayList<Literature> literature = new ArrayList<>(litDAO.getLiterature(
                LiteratureDAO.GET_AVAILABLE_QUERY));
        System.out.println("Select literature:");
        if(!literature.isEmpty()) {
            for (int i = 0; i < literature.size(); i++) {
                System.out.format("%d. %-20s %-50s %-20s %n", i + 1, literature.get(i).getType(),
                        literature.get(i).getName(), literature.get(i).getAuthor());
            }

            int litNumber = scanner.nextInt() - 1;

            Student student = students.get(studNumber);
            Literature litItem = literature.get(litNumber);

            student.incNumberOfBooks();
            litItem.decNumOfAvailable();

            System.out.println("Are you sure?(y/n)");
            if(scanner.next().equalsIgnoreCase("y")) {
                studDao.updateStudent(student.getId(), student);
                litDAO.updateLiterature(litItem.getId(), litItem);
                usedDao.save(student.getId(), litItem.getId());
            }
        }
        else{
            System.out.println("No books.");
        }
    }

    /*
        Return literature.
    */
    private void returnLiterature() throws  DaoException{
        Scanner scanner = new Scanner(System.in);
        LiteratureDAO litDAO = new LiteratureDAO();
        StudentDAO studDao = new StudentDAO();
        UsedLiteratureDAO usedDao = new UsedLiteratureDAO();

        System.out.println("Select student:");

        ArrayList<Student> students = new ArrayList<>(studDao.getStudents(studDao.GET_ALL_QUERY));
        for(int i = 0; i < students.size(); i++){
            System.out.format("%d. %s %n", i+1, students.get(i).getName());
        }

        int studNumber = scanner.nextInt()-1;
        Student student = students.get(studNumber);

        ArrayList<Literature> literature = new ArrayList<>(usedDao.getByUser(student.getId()));
        if(!literature.isEmpty()) {
            System.out.println("Select literature:");
            for (int i = 0; i < literature.size(); i++) {
                System.out.format("%d. %-20s %-50s %-20s %n", i + 1, literature.get(i).getType(),
                        literature.get(i).getName(), literature.get(i).getAuthor());
            }

            int litNumber = scanner.nextInt() - 1;
            Literature litItem = literature.get(litNumber);

            litItem.incNumOfAvailable();
            student.decNumberOfBooks();

            System.out.println("Are you sure?(y/n)");
            if(scanner.next().equalsIgnoreCase("y")) {
                studDao.updateStudent(student.getId(), student);
                litDAO.updateLiterature(litItem.getId(), litItem);
                usedDao.remove(student.getId(), litItem.getId());
            }
        }
        else {
            System.out.println("No books.");
        }
    }

     /*
        View report on the pupils who have read more than 1 book and less or equal than 5.
     */
    private void showReport1() throws DaoException{
        StudentDAO studDAO = new StudentDAO();
        ArrayList<Student> students = new ArrayList<>(studDAO.getStudents(StudentDAO.GET_FIRST_LIST));
        for(Student student: students){
            System.out.format("%-20s: %-3d %n", student.getName(), student.getNumberOfBooks());
        }
    }

    /*
        View report on the pupils who have read less than or equal to 6 books and more or equal than 3.
     */
    private void showReport2() throws DaoException{
        StudentDAO studDAO = new StudentDAO();
        ArrayList<Student> students = new ArrayList<>(studDAO.getStudents(StudentDAO.GET_SECOND_LIST));
        for(Student student: students){
            System.out.format("%-20s, %-15s: %-3d %n", student.getName(), student.getDateOfBirth().toString(),
                    student.getNumberOfBooks());
        }
    }

    public void work(){
        Menu.printHelp();

        while (!cancel){
            try {
                Scanner scan = new Scanner(System.in);
                analyzeInput(scan.nextLine());
            }catch (Exception exc){
                exc.printStackTrace();
            }
        }
    }
}