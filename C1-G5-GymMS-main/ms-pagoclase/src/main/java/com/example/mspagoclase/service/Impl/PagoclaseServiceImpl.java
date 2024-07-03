package com.example.mspagoclase.service.Impl;

import com.example.mspagoclase.entity.Pagoclase;
import com.example.mspagoclase.feing.ClaseFeing;
import com.example.mspagoclase.feing.ClientepagoclaseFeing;
import com.example.mspagoclase.repository.PagoclaseRepository;
import com.example.mspagoclase.service.PagoclaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@Service
public class PagoclaseServiceImpl implements PagoclaseService {
    @Autowired
    private PagoclaseRepository pagoclaseRepository;

    @Autowired
    private ClientepagoclaseFeing clientepagoclaseFeing;
    @Autowired
    private ClaseFeing claseFeing;

    @Override
    public List<Pagoclase> listar() {
        return pagoclaseRepository.findAll();
    }

    @Override
    public Pagoclase guardar(Pagoclase pagoclase) {
        BigDecimal monto = BigDecimal.valueOf(pagoclase.getMonto()).setScale(2, RoundingMode.HALF_UP);
        BigDecimal montoConIGV = calcularMontoConIGV(monto).setScale(2, RoundingMode.HALF_UP);
        pagoclase.setMonto(montoConIGV.doubleValue());
        pagoclase.setFechaPago(new Date());
        return pagoclaseRepository.save(pagoclase);
    }

    @Override
    public Pagoclase buscarPorId(Integer id) {
        Pagoclase pagoclase = pagoclaseRepository.findById(id).get();
        pagoclase.setClientepagoclaseDto(clientepagoclaseFeing.buscarPorId(pagoclase.getClientegymId()).getBody());
        pagoclase.setClaseDto(claseFeing.buscarPorId(pagoclase.getClaseId()).getBody());
        return pagoclase;
    }
    @Override
    public Pagoclase editar(Pagoclase pagoclase) {return pagoclaseRepository.save(pagoclase);}

    @Override
    public void eliminar(Integer id) {
        pagoclaseRepository.deleteById(id);
    }

    @Override
    public void procesarPago(Double montoConIGV) {
        Pagoclase pagoclase = new Pagoclase();
        pagoclase.setMonto(montoConIGV);
        pagoclase.setFechaPago(new Date());
        pagoclaseRepository.save(pagoclase);
    }

    private BigDecimal calcularMontoConIGV(BigDecimal monto) {
        BigDecimal porcentajeIGV = new BigDecimal("0.18"); // 18% de IGV
        BigDecimal igv = monto.multiply(porcentajeIGV).setScale(2, RoundingMode.HALF_UP);
        return monto.add(igv);
    }
}

