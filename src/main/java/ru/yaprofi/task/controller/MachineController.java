package ru.yaprofi.task.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.http.HttpStatus;
import ru.yaprofi.task.data.ResponseData;
import ru.yaprofi.task.data.VirtualMachine;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yaprofi.task.repository.VirtualMachineRepository;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@AllArgsConstructor
@RequestMapping("/machines")
public class MachineController {


    VirtualMachineRepository virtualMachineRepository;

    private final Long N = 100000L;

    @PostMapping
    public ResponseEntity<ResponseData> createVM(@RequestBody VirtualMachine data) {
        ru.yaprofi.task.model.VirtualMachine vm = new ru.yaprofi.task.model.VirtualMachine(data.getId(),data.getTask(),data.getSize(), 0);

        if (data.getSize()>128|| data.getSize()<=0||!is2Pow(data.getSize()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData("NOT_OK", null));

        List<Integer> workingHosts =  virtualMachineRepository.findHostWithMaxUtilAndEnoughPlace(data.getSize());
        if (workingHosts!=null && !workingHosts.isEmpty())
            vm.setMachineId(workingHosts.get(0));
        else {
            Integer freeMachine =  virtualMachineRepository.getMachineIds().size()+1;
            if (freeMachine>N){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData("NOT_OK", null));
            }
            vm.setMachineId(freeMachine);
        }


        vm = virtualMachineRepository.save(vm);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseData("OK",vm.getMachineId()));
    }

    private boolean is2Pow(int a){
        while (a%2==0 && a>1){
            a/=2;
        }
        return a==1;
    }
}
