package dev.tienvv.demo.BE_Vinpearl.service.serviceImpl;

import dev.tienvv.demo.BE_Vinpearl.model.Role;
import dev.tienvv.demo.BE_Vinpearl.repository.RoleRepository;
import dev.tienvv.demo.BE_Vinpearl.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Iterable findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Optional findById(Long id) {
        return roleRepository.findById(id);
    }

    @Override
    public Role save(Role role) {

        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Long id) {
        try{
            roleRepository.deleteById(id);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
