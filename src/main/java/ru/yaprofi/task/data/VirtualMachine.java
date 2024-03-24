package ru.yaprofi.task.data;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VirtualMachine {

    Long id;
    String task;
    Integer size;

}
