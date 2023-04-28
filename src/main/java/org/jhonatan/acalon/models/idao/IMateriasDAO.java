package org.jhonatan.acalon.models.idao;

import javafx.collections.ObservableList;
import org.jhonatan.acalon.models.domain.Materias;
/**
 *
 * @author Jhonatan Jose Acalón Ajanel <jacalon-2021048@kinal.edu.gt>
 * @date 26/09/2022
 * @time 11:16:06
 * @codigo IN5BM
 * @jornada Matutina
 * @grupo 1
 */
public interface IMateriasDAO{
    public ObservableList<Materias> getAll();
    
    public int add(Materias materias);
    
    public int update(Materias materias);
    
    public int delete(Materias materias);
    
    public Materias get(Materias materias);
}