package com.example.mspedidoservice.service.Impl;

import com.example.mspedidoservice.entity.Pedido;
import com.example.mspedidoservice.entity.PedidoDetalle;
import com.example.mspedidoservice.feing.ClienteFeing;
import com.example.mspedidoservice.feing.ProductoFeing;
import com.example.mspedidoservice.repository.PedidoRepository;
import com.example.mspedidoservice.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
@Service
public class PedidoServiceImpl implements PedidoService {

    private static final double IGV_PERCENTAGE = 0.18; // Porcentaje de IGV
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteFeing clienteFeing;

    @Autowired
    private ProductoFeing productoFeing;

    @Override
    public List<Pedido> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public Pedido guardar(Pedido pedido) {
        // Calcular el precio con IGV para cada detalle del pedido
        pedido.getDetalles().forEach(PedidoDetalle::calcularPrecioConIgv);

        BigDecimal subtotal = calcularSubtotal(pedido);
        BigDecimal montoConIgv = calcularMontoConIgv(subtotal).setScale(2, RoundingMode.HALF_UP);
        // Aquí también puedes asignar el monto total al pedido si es necesario
        // pedido.setMonto(montoConIgv.doubleValue());  // Descomentar si es necesario asignar el monto total
        return pedidoRepository.save(pedido);
    }

    private BigDecimal calcularSubtotal(Pedido pedido) {
        return pedido.getDetalles().stream()
                .map(detalle -> BigDecimal.valueOf(detalle.getPrecio()).multiply(BigDecimal.valueOf(detalle.getCantidad())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal calcularMontoConIgv(BigDecimal monto) {
        BigDecimal porcentajeIgv = new BigDecimal(IGV_PERCENTAGE);
        BigDecimal igv = monto.multiply(porcentajeIgv).setScale(2, RoundingMode.HALF_UP);
        return monto.add(igv);
    }

    @Override
    public Pedido buscarPorId(Integer id) {
        Pedido pedido = pedidoRepository.findById(id).get();
        pedido.setClienteDto(clienteFeing.findById(pedido.getClientegymId()).getBody());

        List<PedidoDetalle> pedidoDetalles = pedido.getDetalles().stream().map(pedidoDetalle -> {
            pedidoDetalle.setProducto(productoFeing.buscarPorId(pedidoDetalle.getProductoId()).getBody());
            return pedidoDetalle;
        }).toList();
        pedido.setDetalles(pedidoDetalles);
        return pedido;
    }

    @Override
    public Pedido editar(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    @Override
    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id);
    }
    @Override
    public double calcularPrecioTotalConIgv(double subtotal) {
        double igv = subtotal * IGV_PERCENTAGE;
        return subtotal + igv;
    }
}
