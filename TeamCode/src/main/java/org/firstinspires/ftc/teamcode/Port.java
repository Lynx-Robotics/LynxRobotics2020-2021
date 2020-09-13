package org.firstinspires.ftc.teamcode;

class Port<T>{
    // portName is used as a non-numeric ID
    public String portName;

    // data held by the port
    private T portData;

    // constructor (assigns the name of the port)
    public Port(String name){
        this.portName = name;
    }

    // updates port data
    public void updatePortData(T Data){
        this.portData = Data;
    }

    // returns port data
    public T getPortData(){
        return this.portData;
    }
}
