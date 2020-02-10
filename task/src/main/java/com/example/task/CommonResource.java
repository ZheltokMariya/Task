package com.example.task;

import com.example.task.model.Number;
import com.example.task.repo.NumberRepo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;

public class CommonResource {
    private final NumberRepo numberRepo;

    @Autowired
    CommonResource(NumberRepo numberRepo){
        this.numberRepo = numberRepo;
    }

    public  void addNumbersToDateBase(String info, String[] array, int start, int end){
        for (int i = start; i < end+1; i++){
                this.numberRepo.save(new Number(Integer.valueOf(array[i])));
        }
    }
}
