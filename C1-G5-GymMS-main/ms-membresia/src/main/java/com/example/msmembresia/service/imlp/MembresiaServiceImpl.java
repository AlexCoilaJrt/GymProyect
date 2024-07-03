package com.example.msmembresia.service.imlp;

import com.example.msmembresia.dto.ClientegymDto;
import com.example.msmembresia.entity.Membresia;
import com.example.msmembresia.entity.MembresiaDetalle;
import com.example.msmembresia.feing.ClientegymFeign;
import com.example.msmembresia.repository.MembresiaRepository;
import com.example.msmembresia.service.MembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MembresiaServiceImpl implements MembresiaService {
    @Autowired
    private MembresiaRepository membresiaRepository;

    @Autowired
    private ClientegymFeign clientegymFeign;

    @Override
    public List<Membresia> listar() {
        return membresiaRepository.findAll();
    }

    @Override
    public Membresia guardar(Membresia membresia) {
        // Si el ID del cliente está presente, busca la información del cliente y la asigna al DTO
            if (membresia.getClientegymId() != null) {
            ClientegymDto clientegymDto = clientegymFeign.buscarPorId(membresia.getClientegymId()).getBody();
            if (clientegymDto != null) {
                membresia.setClientegymDto(clientegymDto);
            } else {
                throw new RuntimeException("No se encontró el cliente con el ID: " + membresia.getClientegymId());
            }
        }

        return membresiaRepository.save(membresia);
    }

    @Override
    public Membresia buscarPorId(Integer id) {
        Membresia membresia = membresiaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Membresia no encontrada"));

        if (membresia.getClientegymId() != null) {
            ClientegymDto clientegymDto = clientegymFeign.buscarPorId(membresia.getClientegymId()).getBody();
            membresia.setClientegymDto(clientegymDto);
        }

        return membresia;
    }

    @Override
    public Membresia editar(Membresia membresia) {return membresiaRepository.save(membresia);
    }

    @Override
    public void eliminar(Integer id) {
        membresiaRepository.deleteById(id);
    }

} 
