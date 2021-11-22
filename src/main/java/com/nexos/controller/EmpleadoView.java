/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.nexos.controller;

import com.nexos.entity.Departamentos;
import com.nexos.entity.Empleados;
import com.nexos.facade.DepartamentosFacadeLocal;
import com.nexos.facade.EmpleadosFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

/**
 *
 * @author andre
 */
@Named(value = "empleadoView")
@ViewScoped
public class EmpleadoView implements Serializable {

    @Inject
    private EmpleadosFacadeLocal facadeEmpleado;
    @Inject
    private DepartamentosFacadeLocal facadeDepartamento;

    private Empleados empleado;
    private String opcionModal;
    private List<Departamentos> departamentos;
    private LazyDataModel<Empleados> lazyEmpleados;
    private int idDepartamento;
    private int totalEmpleados;

    @PostConstruct
    public void init() {
        empleado = new Empleados();
        departamentos = facadeDepartamento.findAll();

        lazyEmpleados = new LazyDataModel<Empleados>() {
            @Override
            public List<Empleados> load(int primero, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filterBy) {
                List<Empleados> empleados = facadeEmpleado.empleados(primero, pageSize);
                totalEmpleados = facadeEmpleado.totalEmpleados();
                setRowCount(totalEmpleados);
                return empleados;
            }
        };
    }

//<editor-fold defaultstate="collapsed" desc="getter and setter">
    public LazyDataModel<Empleados> getLazyEmpleados() {
        return lazyEmpleados;
    }

    public void setLazyEmpleados(LazyDataModel<Empleados> lazyEmpleados) {
        this.lazyEmpleados = lazyEmpleados;
    }

    public int getTotalEmpleados() {
        return totalEmpleados;
    }

    public void setTotalEmpleados(int totalEmpleados) {
        this.totalEmpleados = totalEmpleados;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public String getOpcionModal() {
        return opcionModal;
    }

    public void setOpcionModal(String opcionModal) {
        this.opcionModal = opcionModal;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public List<Departamentos> getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(List<Departamentos> departamentos) {
        this.departamentos = departamentos;
    }

//</editor-fold>
    public void addMessage(FacesMessage.Severity severity, String titulo, String mensaje) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, titulo, mensaje));
    }

    public void guardarEmpleado() {
        try {
            empleado.setFechaHoraCrea(new Date());
            empleado.setDepartamentosId(facadeDepartamento.find(idDepartamento));
            facadeEmpleado.create(empleado);
            empleado = new Empleados();
            idDepartamento=0;
            addMessage(FacesMessage.SEVERITY_INFO, "Exito", "Empleado guardado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "");
        }
    }

    public void modificarEmpleado() {
        try {
            empleado.setFechaHoraModifica(new Date());
            empleado.setDepartamentosId(facadeDepartamento.find(idDepartamento));
            facadeEmpleado.edit(empleado);
            addMessage(FacesMessage.SEVERITY_INFO, "Exito", "Empleado modificado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "");
        }
    }

    public void eliminarEmpleado(Empleados emp) {
        try {
            facadeEmpleado.remove(emp);
            addMessage(FacesMessage.SEVERITY_INFO, "Exito", "Empleado eliminado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "");
        }
    }

    /**
     * asigna variables usadas en el modal empleados
     *
     * @param emp
     * @param opcion ver,modificar,nuevo
     */
    public void opcionEmpleado(Empleados emp, String opcion) {
        empleado = emp;
        opcionModal = opcion;
        if (opcion.equals("nuevo")) {
            empleado = new Empleados();
        }
        idDepartamento=(empleado.getDepartamentosId()==null?0:empleado.getDepartamentosId().getId());
    }

}
