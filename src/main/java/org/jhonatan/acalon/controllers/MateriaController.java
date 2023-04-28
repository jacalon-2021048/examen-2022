package org.jhonatan.acalon.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import com.jfoenix.controls.JFXTimePicker;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.util.converter.LocalTimeStringConverter;
import org.jhonatan.acalon.models.dao.MateriasDaoImpl;
import org.jhonatan.acalon.models.domain.Materias;

/**
 * FXML Controller class
 * @author Jhonatan Jose Acalón Ajanel <jacalon-2021048@kinal.edu.gt>
 * @date 26/09/2022
 * @time 11:37:06
 * @codigo IN5BM
 * @jornada Matutina
 * @grupo 1
 */
public class MateriaController implements Initializable {
    private enum Operacion{
        NINGUNO,GUARDAR,ACTUALIZAR
    }
    private Operacion operacion=Operacion.NINGUNO;
    
    private final String PAQUETE_IMAGES="org/jhonatan/acalon/resources/images/";
    
    @FXML
    private Button btnAgregar;
    
    @FXML
    private ImageView imgAgregar;
    
    @FXML
    private Button btnCambiar;
    
    @FXML
    private ImageView imgCambiar;
    
    @FXML
    private Button btnEliminar;
    
    @FXML
    private ImageView imgEliminar;
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtMaestro;
    
    @FXML
    private TextField txtId;
    
    @FXML
    private Spinner<Integer> spnCiclo;
    
    private SpinnerValueFactory<Integer> valueFactoryCiclo;
    
    @FXML
    private TextField txtSalon;
    
    @FXML
    private Spinner<Integer> spnOcupacionMax;
    
    private SpinnerValueFactory<Integer> valueFactoryMax;
    
    @FXML
    private Spinner<Integer> spnOcupacionMin;
    
    private SpinnerValueFactory<Integer> valueFactoryMin;
    
    @FXML
    private Spinner<Integer> spnNota;
    
    private SpinnerValueFactory<Integer> valueFactoryNota;
    
    @FXML
    private TableView<?> tblMaterias;
    
    @FXML
    private TableColumn<?, ?> colId;
    
    @FXML
    private TableColumn<?, ?> colNombre;
    
    @FXML
    private TableColumn<?, ?> colCiclo;
    
    @FXML
    private TableColumn<?, ?> colHoraIni;
    
    @FXML
    private TableColumn<?, ?> colHoraFin;
    
    @FXML
    private TableColumn<?, ?> colMaestro;
    
    @FXML
    private TableColumn<?, ?> colSalon;
    
    @FXML
    private TableColumn<?, ?> colMaximoPer;
    
    @FXML
    private TableColumn<?, ?> colMinimoPer;
    
    @FXML
    private TableColumn<?, ?> colNota;
    
    @FXML
    private Label lblCantidad;
    
    @FXML
    private Label lblNombre;
    
    @FXML
    private Label lblMaestro;
    
    @FXML
    private Label lblSalon;
    
    @FXML
    private Label lblNota;
    
    @FXML
    private JFXTimePicker tmpInicio;
    
    @FXML
    private JFXTimePicker tmpFinal;
    
    private int i=0;
    
    private MateriasDaoImpl mdi=new MateriasDaoImpl();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        iniciarSpinner();
        iniciarTimePicker();
        deshabilitarCampos();
        cargarDatos();
    }
    
    @FXML
    private void clicAgregar(ActionEvent event) {
        switch(operacion){
            case NINGUNO:
                habilitarCampos();
                tblMaterias.setDisable(true);
                limpiarCampos();
                btnAgregar.setText("Guardar");
                imgAgregar.setImage(new Image(PAQUETE_IMAGES+"guardar.png"));
                btnCambiar.setText("Cancelar");
                imgCambiar.setImage(new Image(PAQUETE_IMAGES+"cancelar.png"));
                btnEliminar.setDisable(true);
                operacion=Operacion.GUARDAR;
                break;
            case GUARDAR:
                if(evaluacionCampos()){
                    if (evaluacionCaracteres()) {
                        if(agregarMateria()){
                            limpiarCampos();
                            limpiarLabel();
                            deshabilitarCampos();
                            tblMaterias.setDisable(false);
                            btnAgregar.setText("Agregar");
                            imgAgregar.setImage(new Image(PAQUETE_IMAGES+"agregar.png"));
                            btnCambiar.setText("Cambiar");
                            imgCambiar.setImage(new Image(PAQUETE_IMAGES+"modificar.png"));
                            btnEliminar.setDisable(false);
                            operacion=Operacion.NINGUNO;
                        }
                    }else{
                        evaluacionDigitos();
                    }
                }else{
                    camposObligatorios();
                }
                break;
        }
    }

    @FXML
    private void clicCambiar(ActionEvent event) {
        switch(operacion){
            case NINGUNO:
                if (existeElementoSeleccionado()) {
                    habilitarCampos();
                    btnCambiar.setText("Guardar");
                    imgCambiar.setImage(new Image(PAQUETE_IMAGES+"guardar.png"));
                    btnEliminar.setText("Cancelar");
                    imgEliminar.setImage(new Image(PAQUETE_IMAGES+"cancelar.png"));
                    btnAgregar.setDisable(true);
                    operacion=Operacion.ACTUALIZAR;
                }else{
                    alertasWarning("Seleccione un registro");
                }
                break;
                
            case GUARDAR:
                btnAgregar.setText("Agregar");
                imgAgregar.setImage(new Image(PAQUETE_IMAGES+"agregar.png"));
                btnCambiar.setText("Cambiar");
                imgCambiar.setImage(new Image(PAQUETE_IMAGES+"modificar.png"));
                btnEliminar.setDisable(false);
                tblMaterias.setDisable(false);
                limpiarLabel();
                limpiarCampos();
                deshabilitarCampos();
                operacion=Operacion.NINGUNO;
                break;
                
            case ACTUALIZAR:
                if(evaluacionCampos()){
                        if(evaluacionCaracteres()){
                            if(actualizarMateria()){
                                limpiarCampos();
                                cargarDatos();
                                deshabilitarCampos();
                                tblMaterias.setDisable(false);
                                tblMaterias.getSelectionModel().clearSelection();
                                btnCambiar.setText("Cambiar");
                                imgCambiar.setImage(new Image(PAQUETE_IMAGES+"modificar.png"));
                                btnEliminar.setText("Eliminar");
                                imgEliminar.setImage(new Image(PAQUETE_IMAGES+"eliminar.png"));
                                btnAgregar.setDisable(false);
                                operacion=Operacion.NINGUNO;
                            }
                        }else{
                            evaluacionDigitos();
                        }
                    }else{
                        camposObligatorios();
                    }
                break;
        }
    }

    @FXML
    private void clicEliminar(ActionEvent event) {
        switch(operacion){
            case NINGUNO:
                if(existeElementoSeleccionado()){
                    Alert alerta=new Alert(Alert.AlertType.CONFIRMATION);
                    alerta.setTitle("Control Académico - Confirmación");
                    alerta.setHeaderText(null);
                    alerta.setContentText("¿Desea eliminar este registro?");
                    Stage stage=(Stage) alerta.getDialogPane().getScene().getWindow();
                    Image icon=new Image(PAQUETE_IMAGES+"icono.png");
                    stage.getIcons().add(icon);
                    Optional<ButtonType> result=alerta.showAndWait();
                    if(result.get().equals(ButtonType.OK)){
                        if(eliminarMateria()){
                            limpiarCampos();
                            cargarDatos();
                            Alert info=new Alert(Alert.AlertType.INFORMATION);
                            info.setTitle("Control Académico - AVISO!!!");
                            info.setHeaderText(null);
                            info.setContentText("El registro se ha eliminado correctamente");
                            info.show();
                            Stage stag=(Stage) info.getDialogPane().getScene().getWindow();
                            Image ico=new Image(PAQUETE_IMAGES+"icono.png");
                            stag.getIcons().add(ico);
                        }
                    }else if(result.get().equals(ButtonType.CANCEL)){
                        alerta.close();
                        tblMaterias.getSelectionModel().clearSelection();
                        limpiarCampos();
                    }
                }else{
                    alertasWarning("Antes de continuar selecciona un registro");
                }
                break;
                
            case ACTUALIZAR:
                btnCambiar.setText("Cambiar");
                imgCambiar.setImage(new Image(PAQUETE_IMAGES+"modificar.png"));
                btnEliminar.setText("Eliminar");
                imgEliminar.setImage(new Image(PAQUETE_IMAGES+"eliminar.png"));
                btnAgregar.setDisable(false);
                limpiarCampos();
                limpiarLabel();
                deshabilitarCampos();
                tblMaterias.getSelectionModel().clearSelection();
                operacion=Operacion.NINGUNO;
                break;
        }
    }

    @FXML
    private void seleccionarElemento() {
        if(existeElementoSeleccionado()){
            txtId.setText(String.valueOf(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getIdMateria()));
            txtNombre.setText(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getNombreMateria());
            spnCiclo.getValueFactory().setValue(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getCicloEscolar());
            Time inicio=((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getHorarioInicio();
            Time terminar=((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getHorarioFinal();
            if (inicio==null) {
                tmpInicio.setValue(null);
            }else{
                tmpInicio.setValue(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getHorarioInicio().toLocalTime());
            }
            if(terminar==null){
                tmpFinal.setValue(null);
            }else{
                tmpFinal.setValue(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getHorarioFinal().toLocalTime());
            }
            txtMaestro.setText(String.valueOf(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getCatedratico()));
            txtSalon.setText(String.valueOf(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getSalon()));
            spnOcupacionMax.getValueFactory().setValue(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getCupoMaximo());
            spnOcupacionMin.getValueFactory().setValue(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getCupoMinimo());
            spnNota.getValueFactory().setValue(((Materias)tblMaterias.getSelectionModel().getSelectedItem()).getNota());
        }
    }
    
    @FXML
    private void clicSalir(ActionEvent event) {
        System.exit(0);
    }
    
    private boolean agregarMateria(){
        Materias materia=new Materias(txtNombre.getText(),spnCiclo.getValue()
                , evaluacionInicio(), evaluacionFinal()
                , txtMaestro.getText(), txtSalon.getText(), spnOcupacionMax.getValue()
                , spnOcupacionMin.getValue(), spnNota.getValue());
        System.out.println(materia.toString());
        int a=mdi.add(materia);
        if (a==1) {
            i++;
            contador();
            cargarDatos();
            return true;
        }
        return false;
    }
    
    private boolean actualizarMateria(){
        Materias materia=new Materias(Integer.parseInt(txtId.getText()),txtNombre.getText(),spnCiclo.getValue()
                , evaluacionInicio(), evaluacionFinal()
                , txtMaestro.getText(), txtSalon.getText(), spnOcupacionMax.getValue()
                , spnOcupacionMin.getValue(), spnNota.getValue());
        int a = mdi.update(materia);
        if (a==1) {
            cargarDatos();
            return true;
        }
        return false;
    }
    
    private boolean eliminarMateria(){
        Materias materia=new Materias(Integer.parseInt(txtId.getText()));
        int a=mdi.delete(materia);
        if (a==1) {
            i--;
            contador();
            return true;
        }
        return false;
    }
    
    private boolean existeElementoSeleccionado(){
        return (tblMaterias.getSelectionModel().getSelectedItem()!=null);
    }
    
    private Time evaluacionInicio(){
        if(tmpInicio.getValue()==null){
            return Time.valueOf(LocalTime.MIN);
        }
        return Time.valueOf(tmpInicio.getValue());
    }
    
    private Time evaluacionFinal(){
        if(tmpFinal.getValue()==null){
            return Time.valueOf(LocalTime.MIN);
        }
        return Time.valueOf(tmpFinal.getValue());
    }
    
    private void limpiarLabel(){
        lblNombre.setText("");
        lblMaestro.setText("");
        lblSalon.setText("");
        lblNota.setText("");
    }
    
    private boolean evaluacionCampos(){
        return (!(txtNombre.getText().isEmpty())) && (!(txtMaestro.getText().isEmpty()))
                && (!(txtSalon.getText().isEmpty())) && (!(spnNota.getValueFactory().getValue().equals(0)));
    }
    
    private boolean evaluacionCaracteres(){
        return (txtNombre.getText().length()<=45) && (txtMaestro.getText().length()<=45) && (txtSalon.getText().length()<=45);
    }
    
    private void evaluacionDigitos(){
        if(txtNombre.getText().length()>45){
            lblNombre.setText("No más de 45 digitos");
        }else{
            if(txtMaestro.getText().length()>45){
                lblMaestro.setText("No más de 45 digitos");
            }else{
                if(txtSalon.getText().length()>45){
                    lblSalon.setText("No más de 45 digitos");
                }
            }
        }
    }
    
    private void camposObligatorios(){
        if (txtNombre.getText().isEmpty()) {
            lblNombre.setText("Campo obligatorio");
        } else if (txtMaestro.getText().isEmpty()){
            lblMaestro.setText("Campo obligatorio");
        } else if(txtSalon.getText().isEmpty()){
            lblSalon.setText("Campo obligatorio");
        }else if(spnNota.getValueFactory().getValue().equals(0)){
            lblNota.setText("Campo obligatorio");
        }
    }
    
    private void contador(){
        lblCantidad.setText(String.valueOf(i));
    }
    
    private void habilitarCampos(){
        txtId.setEditable(false);
        txtNombre.setEditable(true);
        spnCiclo.setEditable(false);
        tmpInicio.setEditable(false);
        tmpFinal.setEditable(false);
        txtMaestro.setEditable(true);
        txtSalon.setEditable(true);
        spnOcupacionMax.setEditable(false);
        spnOcupacionMin.setEditable(false);
        spnNota.setEditable(false);
        
        txtId.setDisable(true);
        txtNombre.setDisable(false);
        spnCiclo.setDisable(false);
        tmpInicio.setDisable(false);
        tmpFinal.setDisable(false);
        txtMaestro.setDisable(false);
        txtSalon.setDisable(false);
        spnOcupacionMax.setDisable(false);
        spnOcupacionMin.setDisable(false);
        spnNota.setDisable(false);
    }
    
    private void deshabilitarCampos(){
        txtId.setEditable(false);
        txtNombre.setEditable(false);
        spnCiclo.setEditable(false);
        tmpInicio.setEditable(false);
        tmpFinal.setEditable(false);
        txtMaestro.setEditable(false);
        txtSalon.setEditable(false);
        spnOcupacionMax.setEditable(false);
        spnOcupacionMin.setEditable(false);
        spnNota.setEditable(false);
        
        txtId.setDisable(true);
        txtNombre.setDisable(true);
        spnCiclo.setDisable(true);
        tmpInicio.setDisable(true);
        tmpFinal.setDisable(true);
        txtMaestro.setDisable(true);
        txtSalon.setDisable(true);
        spnOcupacionMax.setDisable(true);
        spnOcupacionMin.setDisable(true);
        spnNota.setDisable(true);
    }
    
    private ObservableList getMateria(){
        ObservableList<Materias> lista;
        lista=FXCollections.observableArrayList(mdi.getAll());
        i=lista.size();
        contador();
        return lista;
    }

    private void cargarDatos(){
        tblMaterias.setItems(getMateria());
        colId.setCellValueFactory(new PropertyValueFactory<>("idMateria"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreMateria"));
        colCiclo.setCellValueFactory(new PropertyValueFactory<>("cicloEscolar"));
        colHoraIni.setCellValueFactory(new PropertyValueFactory<>("horarioInicio"));
        colHoraFin.setCellValueFactory(new PropertyValueFactory<>("horarioFinal"));
        colMaestro.setCellValueFactory(new PropertyValueFactory<>("catedratico"));
        colSalon.setCellValueFactory(new PropertyValueFactory<>("salon"));
        colMaximoPer.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        colMinimoPer.setCellValueFactory(new PropertyValueFactory<>("cupoMinimo"));
        colNota.setCellValueFactory(new PropertyValueFactory<>("nota"));
    }
    
    private void alertasWarning(String mensaje){
        Alert alerta=new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Materias - AVISO!!!");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.show();
        Stage stage=(Stage) alerta.getDialogPane().getScene().getWindow();
        Image ico=new Image(PAQUETE_IMAGES+"icono.png");
        stage.getIcons().add(ico);
    }
    
    private void limpiarCampos(){
        txtId.clear();
        txtNombre.clear();
        spnCiclo.getValueFactory().setValue(2022);
        tmpInicio.getEditor().clear();
        tmpFinal.getEditor().clear();
        txtMaestro.clear();
        txtSalon.clear();
        spnOcupacionMax.getValueFactory().setValue(50);
        spnOcupacionMin.getValueFactory().setValue(0);
        spnNota.getValueFactory().setValue(0);
    }
    
    private void iniciarSpinner(){
        valueFactoryCiclo=new SpinnerValueFactory.IntegerSpinnerValueFactory(2022, 2100, 2022, 1);
        spnCiclo.setValueFactory(valueFactoryCiclo);
        
        valueFactoryMax=new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 100, 50, 1);
        spnOcupacionMax.setValueFactory(valueFactoryMax);
        
        valueFactoryMin=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 50, 1, 1);
        spnOcupacionMin.setValueFactory(valueFactoryMin);
        
        valueFactoryNota=new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 70, 0, 1);
        spnNota.setValueFactory(valueFactoryNota);
    }
    
    private void iniciarTimePicker(){
        tmpInicio.set24HourView(false);
        tmpFinal.set24HourView(false);
        
        StringConverter<LocalTime> defaultConverter
                =new LocalTimeStringConverter(FormatStyle. SHORT,Locale.UK);
        tmpFinal.setConverter(defaultConverter);
        
        StringConverter<LocalTime> defaultConverter2
                =new LocalTimeStringConverter(FormatStyle.SHORT,Locale.UK);
        tmpInicio.setConverter(defaultConverter2);
    }
}
