/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.nexos.controller;

import com.nexos.entity.Departamentos;
import com.nexos.facade.DepartamentosFacadeLocal;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.primefaces.PrimeFaces;

/**
 *
 * @author andre
 */
@Named(value = "departamentoView")
@ViewScoped
public class DepartamentoView implements Serializable {

    @Inject
    private DepartamentosFacadeLocal facadeDepartamento;

    private Departamentos departamento;
    private String opcionModal;

    @PostConstruct
    public void init() {
        departamento = new Departamentos();
    }

    //<editor-fold defaultstate="collapsed" desc="getter and setter">
    public Departamentos getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamentos departamento) {
        this.departamento = departamento;
    }

    public String getOpcionModal() {
        return opcionModal;
    }

    public void setOpcionModal(String opcionModal) {
        this.opcionModal = opcionModal;
    }

    //</editor-fold>
    public void addMessage(FacesMessage.Severity severity, String titulo, String mensaje) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, titulo, mensaje));
    }

    public List<Departamentos> getDepartametos() {
        return facadeDepartamento.departamentos();
    }

    public void guardarDepartamento() {
        try {
            departamento.setFechaHoraCrea(new Date());
            facadeDepartamento.create(departamento);
            departamento = new Departamentos();
            addMessage(FacesMessage.SEVERITY_INFO, "Exito", "Departamento guardado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "");
        }
    }

    public void modificarDepartamento() {
        try {
            departamento.setFechaHoraModifica(new Date());
            facadeDepartamento.edit(departamento);
            addMessage(FacesMessage.SEVERITY_INFO, "Exito", "Departamento modificado.");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "");
        }
    }

    /**
     * para asignar variables usadas en el modal
     *
     * @param d
     * @param opcion ver,modificar,nuevo
     */
    public void opcionDepartamento(Departamentos d, String opcion) {
        departamento = d;
        opcionModal = opcion;
        if (opcion.equals("nuevo")) {
            departamento = new Departamentos();
        }
    }

    public void eliminarDepartamento(Departamentos d) {
        try {
            if (d.getEmpleadosList().isEmpty()) {
                facadeDepartamento.remove(d);
                addMessage(FacesMessage.SEVERITY_INFO, "Exito", "Departamento eliminado.");
            } else {
                addMessage(FacesMessage.SEVERITY_WARN, "Error", "El departamento cuenta con empleados asignados.");
            }
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "");
        }
    }

}
