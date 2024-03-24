package ru.yaprofi.task.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "virtual_machine")
public class VirtualMachine {
    @Id
    Long id;
    String task;
    @NotNull
    @Column(name = "mem_size")
    Integer memSize;
    @NotNull
    @Column(name = "machine_id")
    Integer machineId;
}
