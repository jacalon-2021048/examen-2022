package org.jhonatan.acalon.models.dao;

import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.PreparedStatement;
import org.jhonatan.acalon.models.domain.Materias;
import org.jhonatan.acalon.models.idao.IMateriasDAO;
import org.jhonatan.acalon.db.Conexion;
import java.sql.SQLException;
import javafx.collections.FXCollections;

/**
 *
 * @author Jhonatan Jose Acalón Ajanel <jacalon-2021048@kinal.edu.gt>
 * @date 26/09/2022
 * @time 11:37:06
 * @codigo IN5BM
 * @jornada Matutina
 * @grupo 1
 */
public class MateriasDaoImpl implements IMateriasDAO {

    private static final String SQL_SELECT = "SELECT id_materia,nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota FROM materias";
    private static final String SQL_DELETE = "DELETE FROM materias WHERE id_materia=?";
    private static final String SQL_INSERT = "INSERT INTO materias(nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota) VALUES (?,?,?,?,?,?,?,?,?);";
    private static final String SQL_UPDATE = "UPDATE materias SET nombre_materia=?,ciclo_escolar=?,horario_inicio=?,horario_final=?,catedratico=?,salon=?,cupo_maximo=?,cupo_minimo=?,nota=? WHERE id_materia=?";
    private static final String SQL_SELECT_BY_ID = "id_materia,nombre_materia,ciclo_escolar,horario_inicio,horario_final,catedratico,salon,cupo_maximo,cupo_minimo,nota FROM materias WHERE id_materia = ?";
    private Materias materias;
    private ObservableList<Materias> listaMaterias;

    @Override
    public ObservableList<Materias> getAll() {
        ArrayList<Materias> lista=new ArrayList<>();
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            pstmt = Conexion.getInstance().getConexion().prepareStatement(SQL_SELECT);
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                materias = new Materias(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getTime(4), rs.getTime(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                System.out.println(materias);
                lista.add(materias);
            }
            listaMaterias=FXCollections.observableArrayList(lista);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listaMaterias;
    }

    @Override
    public int add(Materias materias) {
        int rows = 0;
        Connection con;
        PreparedStatement pstmt;
        try {
            con = Conexion.getInstance().getConexion();
            pstmt = con.prepareStatement(SQL_INSERT);
            pstmt.setString(1, materias.getNombreMateria());
            pstmt.setInt(2, materias.getCicloEscolar());
            pstmt.setString(3, materias.getHorarioInicio().toString());
            pstmt.setString(4, materias.getHorarioFinal().toString());
            pstmt.setString(5, materias.getCatedratico());
            pstmt.setString(6, materias.getSalon());
            pstmt.setInt(7, materias.getCupoMaximo());
            pstmt.setInt(8, materias.getCupoMinimo());
            pstmt.setInt(9, materias.getNota());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar insertar el siguiente registro: " + materias.toString());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return rows;
    }

    @Override
    public int update(Materias materias) {
        int rows = 0;
        Connection con;
        PreparedStatement pstmt;
        try {
            con = Conexion.getInstance().getConexion();
            pstmt = con.prepareStatement(SQL_UPDATE);
            pstmt.setString(1, materias.getNombreMateria());
            pstmt.setInt(2, materias.getCicloEscolar());
            pstmt.setString(3, materias.getHorarioInicio().toString());
            pstmt.setString(4, materias.getHorarioFinal().toString());
            pstmt.setString(5, materias.getCatedratico());
            pstmt.setString(6, materias.getSalon());
            pstmt.setInt(7, materias.getCupoMaximo());
            pstmt.setInt(8, materias.getCupoMinimo());
            pstmt.setInt(9, materias.getNota());
            pstmt.setInt(10, materias.getIdMateria());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar modificar el siguiente registro: " + materias.toString());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return rows;
    }

    @Override
    public int delete(Materias materias) {
        int rows = 0;
        Connection con;
        PreparedStatement pstmt;
        try {
            con = Conexion.getInstance().getConexion();
            pstmt = con.prepareStatement(SQL_DELETE);
            pstmt.setInt(1, materias.getIdMateria());
            System.out.println(pstmt.toString());
            rows = pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Se produjo un error al intentar eliminar el registro con el id: " + materias.getIdMateria());
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return rows;
    }

    @Override
    public Materias get(Materias materias) {
        Connection con;
        PreparedStatement pstmt;
        ResultSet rs;
        try {
            con = Conexion.getInstance().getConexion();
            pstmt = con.prepareStatement(SQL_SELECT_BY_ID);
            pstmt.setInt(1, materias.getIdMateria());
            System.out.println(pstmt.toString());
            rs = pstmt.executeQuery();
            while (rs.next()) {
                materias = new Materias(rs.getInt(1), rs.getString(2),
                        rs.getInt(3), rs.getTime(4), rs.getTime(5),
                        rs.getString(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                listaMaterias.add(materias);
            }
            System.out.println("Materia: " + materias);
        } catch (SQLException e) {
            System.out.println("\nSQLException\n");
            e.printStackTrace(System.out);
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return materias;
    }
}
