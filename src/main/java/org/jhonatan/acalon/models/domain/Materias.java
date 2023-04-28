package org.jhonatan.acalon.models.domain;

import java.sql.Time;
/**
 *
 * @author Jhonatan Jose Acalón Ajanel <jacalon-2021048@kinal.edu.gt>
 * @date 26/09/2022
 * @time 11:16:06
 * @codigo IN5BM
 * @jornada Matutina
 * @grupo 1
 */
public class Materias{
    private int idMateria;
    private String nombreMateria;
    private int cicloEscolar;
    private Time horarioInicio;
    private Time horarioFinal;
    private String catedratico;
    private String salon;
    private int cupoMaximo;
    private int cupoMinimo;
    private int nota;

    public Materias() {
    }

    public Materias(int idMateria, String nombreMateria, int cicloEscolar, Time horarioInicio, Time horarioFinal, String catedratico, String salon, int cupoMaximo, int cupoMinimo, int nota) {
        this.idMateria = idMateria;
        this.nombreMateria = nombreMateria;
        this.cicloEscolar = cicloEscolar;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
        this.nota = nota;
    }

    public Materias(String nombreMateria, int cicloEscolar, Time horarioInicio, Time horarioFinal, String catedratico, String salon, int cupoMaximo, int cupoMinimo, int nota) {
        this.nombreMateria = nombreMateria;
        this.cicloEscolar = cicloEscolar;
        this.horarioInicio = horarioInicio;
        this.horarioFinal = horarioFinal;
        this.catedratico = catedratico;
        this.salon = salon;
        this.cupoMaximo = cupoMaximo;
        this.cupoMinimo = cupoMinimo;
        this.nota = nota;
    }

    public Materias(int idMateria) {
        this.idMateria = idMateria;
    }

    public int getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(int idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }

    public int getCicloEscolar() {
        return cicloEscolar;
    }

    public void setCicloEscolar(int cicloEscolar) {
        this.cicloEscolar = cicloEscolar;
    }

    public Time getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(Time horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public Time getHorarioFinal() {
        return horarioFinal;
    }

    public void setHorarioFinal(Time horarioFinal) {
        this.horarioFinal = horarioFinal;
    }

    public String getCatedratico() {
        return catedratico;
    }

    public void setCatedratico(String catedratico) {
        this.catedratico = catedratico;
    }

    public String getSalon() {
        return salon;
    }

    public void setSalon(String salon) {
        this.salon = salon;
    }

    public int getCupoMaximo() {
        return cupoMaximo;
    }

    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }

    public int getCupoMinimo() {
        return cupoMinimo;
    }

    public void setCupoMinimo(int cupoMinimo) {
        this.cupoMinimo = cupoMinimo;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Materias{" 
                + "idMateria=" + idMateria 
                + ", nombreMateria=" + nombreMateria 
                + ", cicloEscolar=" + cicloEscolar 
                + ", horarioInicio=" + horarioInicio 
                + ", horarioFinal=" + horarioFinal 
                + ", catedratico=" + catedratico 
                + ", salon=" + salon 
                + ", cupoMaximo=" + cupoMaximo 
                + ", cupoMinimo=" + cupoMinimo 
                + ", nota=" + nota + '}';
    }
}