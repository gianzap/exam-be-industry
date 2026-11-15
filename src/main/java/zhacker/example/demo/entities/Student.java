package zhacker.example.demo.entities;


public abstract class Student {
    protected String name;

    public Student(String name){
        this.name = getName();
    }

    public String getName(){
        return name;
    }

    @Override
    public String ToString(){
        return "Student{" +
                "name='"+ name + "\'"+
                '}';
    }
}
