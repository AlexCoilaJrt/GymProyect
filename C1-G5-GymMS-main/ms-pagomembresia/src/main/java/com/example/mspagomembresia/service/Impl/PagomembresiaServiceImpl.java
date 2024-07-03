package com.example.mspagomembresia.service.Impl;

import com.example.mspagomembresia.dto.ClientegymDto;
import com.example.mspagomembresia.dto.MembresiaDto;
import com.example.mspagomembresia.entity.Pagomembresia;
import com.example.mspagomembresia.feing.ClientegymFeing;
import com.example.mspagomembresia.feing.MembresiaFeing;
import com.example.mspagomembresia.repository.PagomembresiaRepository;
import com.example.mspagomembresia.service.PagoMembresiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class PagomembresiaServiceImpl implements PagoMembresiaService {
    @Autowired
private PagomembresiaRepository pagomembresiaRepository;
    @Autowired
    private ClientegymFeing clientegymFeing;
    @Autowired
    private MembresiaFeing membresiaFeing;
    @Override
    public List<Pagomembresia> listar() {
        return pagomembresiaRepository.findAll();
    }

    @Override
    public Pagomembresia guardar(Pagomembresia pagomembresia) {
        BigDecimal monto = BigDecimal.valueOf(pagomembresia.getMonto()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal montoConIGV = calcularMontoConIGV(monto).setScale(2, RoundingMode.HALF_UP);
        pagomembresia.setMonto(montoConIGV.doubleValue());
        pagomembresia.setFechaPago(new Date());
        return pagomembresiaRepository.save(pagomembresia);
    }
    @Override
    public Pagomembresia buscarPorId(Integer id) {
        Pagomembresia pagomembresia = pagomembresiaRepository.findById(id).get();
        pagomembresia.setClientegymDto(clientegymFeing.buscarPorId(pagomembresia.getClientegymId()).getBody());
        pagomembresia.setMembresiaDto(membresiaFeing.buscarPorId(pagomembresia.getMembresiaId()).getBody());
        return pagomembresia;
    }
    @Override
    public Pagomembresia editar(Pagomembresia pagomembresia) {
        return pagomembresiaRepository.save(pagomembresia);
    }

    @Override
    public void eliminar(Integer id) {
        pagomembresiaRepository.deleteById(id);
    }

    @Override
    public void procesarPago(Double montoConIGV) {
        Pagomembresia pagomembresia = new Pagomembresia();
        pagomembresia.setMonto(montoConIGV);
        pagomembresia.setFechaPago(new Date());
        pagomembresiaRepository.save(pagomembresia);
    }

    private BigDecimal calcularMontoConIGV(BigDecimal monto) {
        BigDecimal porcentajeIGV = new BigDecimal("0.18"); // 18% de IGV
        BigDecimal igv = monto.multiply(porcentajeIGV).setScale(2, RoundingMode.HALF_UP);
        return monto.add(igv);
    }



}
