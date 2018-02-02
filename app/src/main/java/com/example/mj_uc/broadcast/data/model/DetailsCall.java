package com.example.mj_uc.broadcast.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

//http://www.jsonschema2pojo.org/

//Source Type: JSON
//Annotation style: GSON
/*
{
  "telefono": "foo",
  "fecha": "bar",
  "hora": "foo",
  "estado": "bar"
}
*/

//Fill Package and Class name

public class DetailsCall {

    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("fecha")
    @Expose
    private String fecha;
    @SerializedName("hora")
    @Expose
    private String hora;
    @SerializedName("estado")
    @Expose
    private String estado;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "DetailsCall{" +
                "telefono='" + telefono + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public DetailsCall() {}

    public DetailsCall(String telefono, String fecha, String hora, String estado) {
        this.telefono = telefono;
        this.fecha = fecha;
        this.hora = hora;
        this.estado = estado;
    }
}