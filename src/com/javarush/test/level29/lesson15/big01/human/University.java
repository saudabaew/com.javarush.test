package com.javarush.test.level29.lesson15.big01.human;

import java.util.List;

public class University
{

    private String name;
    private int age;
    private List<Student> students;

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }

    public List<Student> getStudents()
    {

        return students;
    }

    public void setStudents(List<Student> students)
    {
        this.students = students;
    }

    public University(String name, int age)
    {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double averageGrade)
    {
        Student student = null;
        //TODO:
        for (Student entry : students
                )
        {
            if (entry.getAverageGrade() == averageGrade)
            {
                student = entry;
                break;
            }
        }
        return student;
    }

    public Student getStudentWithMaxAverageGrade()
    {
        double maxGrade = -1.0;
        Student bestStudent = null;
        for (Student student : students)
            if (student.getAverageGrade() > maxGrade){
                bestStudent = student;
                maxGrade = bestStudent.getAverageGrade();
            }
        return bestStudent;
    }

    public Student getStudentWithMinAverageGrade()
    {
        if (students.isEmpty()) return null;
        Student worstStudent = null;
        double minGrade = Double.MAX_VALUE;
        for (Student student : students)
            if (student.getAverageGrade() < minGrade)
            {
                worstStudent = student;
                minGrade = worstStudent.getAverageGrade();
            }
        return worstStudent;
    }

    public void expel(Student student)
    {
        //TODO:
        students.remove(student);
    }
}
