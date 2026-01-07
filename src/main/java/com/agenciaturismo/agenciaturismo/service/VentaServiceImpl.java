package com.agenciaturismo.agenciaturismo.service;

import com.agenciaturismo.agenciaturismo.dto.VentaDTO;
import com.agenciaturismo.agenciaturismo.dto.VentaEdicionDTO;
import com.agenciaturismo.agenciaturismo.exceptions.ClienteInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.EmpleadoInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.ProductoInexistenteError;
import com.agenciaturismo.agenciaturismo.exceptions.VentaInexistenteError;
import com.agenciaturismo.agenciaturismo.model.Cliente;
import com.agenciaturismo.agenciaturismo.model.Empleado;
import com.agenciaturismo.agenciaturismo.model.ProductoTuristico;
import com.agenciaturismo.agenciaturismo.model.Venta;
import com.agenciaturismo.agenciaturismo.repository.ClienteRepository;
import com.agenciaturismo.agenciaturismo.repository.EmpleadoRepository;
import com.agenciaturismo.agenciaturismo.repository.ProductoRepository;
import com.agenciaturismo.agenciaturismo.repository.VentaRepository;
import org.springframework.stereotype.Service;


@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final EmpleadoRepository empleadoRepository;
    private final ProductoRepository productoRepository;

    public VentaServiceImpl(VentaRepository ventaRepository,
                            ClienteRepository clienteRepository,
                            EmpleadoRepository empleadoRepository,
                            ProductoRepository productoRepository
                            ) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.empleadoRepository = empleadoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Venta guardarVenta(VentaDTO ventaDTO) {
        Cliente cliente = validarCliente(ventaDTO.getId_cliente());
        Empleado empleado = validarEmpleado(ventaDTO.getId_empleado());
        ProductoTuristico producto = validarProducto(ventaDTO.getCodigo_producto());

        Venta venta = Venta.builder()
                .fecha_venta(ventaDTO.getFecha_venta())
                .medio_pago(ventaDTO.getMedio_pago())
                .cliente(cliente)
                .empleado(empleado)
                .producto_turistico(producto)
                .build();
        return this.ventaRepository.save(venta);
    }

    @Override
    public Venta buscarVenta(Long num_venta) {
        return this.ventaRepository.findById(num_venta).orElseThrow(
                () -> new VentaInexistenteError("La venta no existe")
        );
    }

    @Override
    public void eliminarVenta(Long num_venta) {
        buscarVenta(num_venta);
        this.ventaRepository.deleteById(num_venta);
    }

    @Override
    public Venta editarVenta(VentaEdicionDTO ventaEdicionDTO) {
        buscarVenta(ventaEdicionDTO.getNum_venta());
        Cliente cliente = validarCliente(ventaEdicionDTO.getId_cliente());
        Empleado empleado = validarEmpleado(ventaEdicionDTO.getId_empleado());
        ProductoTuristico producto = validarProducto(ventaEdicionDTO.getCodigo_producto());
        Venta editada = Venta.builder()
                .num_venta(ventaEdicionDTO.getNum_venta())
                .fecha_venta(ventaEdicionDTO.getFecha_venta())
                .medio_pago(ventaEdicionDTO.getMedio_pago())
                .cliente(cliente)
                .empleado(empleado)
                .producto_turistico(producto)
                .build();
        return ventaRepository.save(editada);
    }

    private Cliente validarCliente(Long id_cliente) {
        return clienteRepository.findById(id_cliente)
                .orElseThrow(() -> new ClienteInexistenteError("El cliente ingresado no existe"));
    }

    private Empleado validarEmpleado(Long id_empleado) {
        return empleadoRepository.findById(id_empleado)
                .orElseThrow(() -> new EmpleadoInexistenteError("El empleado ingresado no existe"));
    }

    private ProductoTuristico validarProducto(Long codigo_producto) {
        return productoRepository.findById(codigo_producto)
                .orElseThrow(() -> new ProductoInexistenteError("El producto ingresado no existe"));
    }

}
