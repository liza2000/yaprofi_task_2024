package ru.yaprofi.task.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.yaprofi.task.model.VirtualMachine;

import java.util.List;

@Repository
public interface VirtualMachineRepository extends JpaRepository<VirtualMachine, Long> {

    @Query(value = "select machineId from VirtualMachine group by machineId having sum(memSize) <= (128-?1) order by sum(memSize) desc")
    List<Integer> findHostWithMaxUtilAndEnoughPlace(Integer taskSize);

    @Query(value = "select distinct(machineId) from VirtualMachine")
    List<Integer> getMachineIds();
}
