package com.example.task;

public class UseDataBaseThread extends Thread {
    private CommonResource resource;
    private String[] array;
    private int start;
    private int end;

    public UseDataBaseThread(String name, CommonResource resource, String[] array, int start, int end){
        super(name);
        this.resource = resource;
        this.start = start;
        this.end = end;
        this.array = array;
    }

    @Override
    public void run(){
        resource.addNumbersToDateBase(this.getName(), this.array, this.start, this.end);
    }
}
