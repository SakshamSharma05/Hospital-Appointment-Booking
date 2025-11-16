package com.hospital.service;

import com.hospital.entity.Department;
import com.hospital.exception.BadRequestException;
import com.hospital.exception.ResourceNotFoundException;
import com.hospital.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
    }

    public Department createDepartment(Department department) {
        if (departmentRepository.findByName(department.getName()).isPresent()) {
            throw new BadRequestException("Department with this name already exists");
        }
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, Department departmentDetails) {
        Department department = getDepartmentById(id);
        
        if (!department.getName().equals(departmentDetails.getName()) &&
            departmentRepository.findByName(departmentDetails.getName()).isPresent()) {
            throw new BadRequestException("Department with this name already exists");
        }
        
        department.setName(departmentDetails.getName());
        department.setDescription(departmentDetails.getDescription());
        department.setHeadDoctor(departmentDetails.getHeadDoctor());
        return departmentRepository.save(department);
    }

    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}

