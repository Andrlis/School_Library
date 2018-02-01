package libraryapp;

public class Menu {

    public static void printHelp(){
        System.out.println("/addStud - add new student.\n" +
        "/addLit - add new literature.\n" +
        "/setLit - give out literature for student.\n" +
        "/retLit - return literature to the library.\n" +
        "/showLit - view the list of available for reading books, articles, journals and newspapers.\n" +
        "/showStud1 - view report on the pupils who have read more than 1 book.\n" +
        "/showStud2 - view report on the pupils who have read less than or equal to 2 books.\n" +
        "/q - exit.");
    }

    public static void analizeInput(String input){
        if(input.equals("/addStud")){
            //ToDo
        }
        else if(input.equals("/addLit")){
            //ToDo
        }
        else if(input.equals("/setLit")){
            //ToDo
        }
        else if(input.equals("/retLit")){
            //ToDo
        }
        else if(input.equals("/showLit")){
            //ToDo
        }
        else if(input.equals("/showStud1")){
            //ToDo
        }
        else if(input.equals("/showStud2")){
            //ToDo
        }
        else if(input.equals("/q")){
            //ToDo
        }
        else if(input.equals("/h")){
            Menu.printHelp();
        }
        else{
            System.out.println("Input \'/h\' to see available options.");
        }
    }
}
