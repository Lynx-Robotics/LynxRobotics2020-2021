package org.firstinspires.ftc.teamcode.TEACHING;

public class Student {
    String name;
    Integer studentID1;
    int studentID2;

    public Student(String assignedName, int studentID2){
        name = assignedName;
        this.studentID2 = studentID2; // tihs refers to the var in the class
    }

    public boolean doHomework(String name){
        int homeworkDone = 0;

        if(name == "Diego"){
            homeworkDone++;
        }
        return false;
    }
}
