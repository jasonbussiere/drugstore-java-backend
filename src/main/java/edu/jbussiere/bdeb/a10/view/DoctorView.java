package edu.jbussiere.bdeb.a10.view;

import edu.jbussiere.bdeb.a10.control.DoctorController;
import edu.jbussiere.bdeb.a10.model.*;
import edu.jbussiere.bdeb.a10.persistence.factory.DAOFactory;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class DoctorView extends Application {
    private TextField prescribed_tf;
    private TextField concomitant_tf;
    private TextField patientId_tf;
    private TextField patientId_tf_ad;
    private TextField drugName_tf;
    private GridPane pInfoGrid;
    private GridPane adGrid;
    private GridPane sdGrid;
    private DoctorController doctorController;

    public void init() {
       this.doctorController = new DoctorController();
    }

    public void start(Stage stage) throws Exception {
        //title pane
        StackPane title_header = new StackPane();
        Label title_label = new Label("Doctor View");
        title_label.setStyle("-fx-font-family: 'Segoe UI';-fx-font-size: 28px; -fx-font-weight: bold;");
        title_header.getChildren().add(title_label);

        //main pane

        VBox mainPane = new VBox();
        mainPane.setSpacing(10);
        mainPane.setPadding(new Insets(10));
        mainPane.setAlignment(Pos.TOP_CENTER);


        ColumnConstraints c1 = new ColumnConstraints();
        c1.setPercentWidth(40);
        ColumnConstraints c2 = new ColumnConstraints();
        c2.setPercentWidth(60);

        //left menu elements
        Label patientId_label = new Label("Patient ID:");
        Label drugName_label = new Label("Drug Name:");
        Label patientId_label_ad = new Label("Patient ID:");
        Label concomitant_drugName_label = new Label("Concomitant Drug:");
        Label prescribed_drugName_label = new Label("Prescribed Drug:");
        Label dataSource = new Label("Data source: " + DAOFactory.getCurrentDataSource());


        patientId_tf = new TextField();
        drugName_tf = new TextField();
        patientId_tf_ad = new TextField();
        concomitant_tf = new TextField();
        prescribed_tf = new TextField();

        //center output results
        sdGrid = new GridPane();
        sdGrid.setHgap(10);
        sdGrid.setVgap(10);
        sdGrid.setPadding(new Insets(10, 10, 10, 10));
        sdGrid.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        adGrid = new GridPane();
        adGrid.setVgap(10);
        adGrid.setHgap(10);
        adGrid.setPadding(new Insets(10, 10, 10, 10));
        adGrid.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

        pInfoGrid = new GridPane();
        pInfoGrid.setVgap(10);
        pInfoGrid.setHgap(10);
        pInfoGrid.setPadding(new Insets(10, 10, 10, 10));
        pInfoGrid.setBorder(new Border(new BorderStroke(Color.LIGHTGRAY, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));


        Button retrieve_stdDose_button = new Button("Retrieve Std Dose");
        retrieve_stdDose_button.setOnAction(e -> {

            retrieveDoseAndADR_UI();
        });

        Button calculate_adjusted_std_dose_button = new Button("Calculate Adj Dose");
        calculate_adjusted_std_dose_button.setOnAction(e -> {

            calculateDose_UI();
        });

        Button display_patient_info_button = new Button("Display Patient Info");
        display_patient_info_button.setOnAction(e -> {

            displayPatientData_UI();
        });

        //standard dose menu
        VBox vbox_sd = new VBox();
        vbox_sd.setSpacing(10);
        vbox_sd.setPadding(new Insets(10,10,10,10));
        vbox_sd.getChildren().addAll(
                drugName_label,
                drugName_tf,
                retrieve_stdDose_button
        );
        TitledPane titled_pane_sd = new TitledPane("Retrieve standard dose",vbox_sd);
        titled_pane_sd.collapsibleProperty().setValue(false);

        //adjusted dose menu
        VBox vbox_ad = new VBox();
        vbox_ad.setSpacing(10);
        vbox_ad.setPadding(new Insets(10,10,10,10));
        vbox_ad.getChildren().addAll(
                patientId_label_ad,
                patientId_tf_ad,
                prescribed_drugName_label,
                prescribed_tf,
                concomitant_drugName_label,
                concomitant_tf,
                calculate_adjusted_std_dose_button
        );

        TitledPane titled_pane_ad = new TitledPane("Calculate adjusted dose",vbox_ad);
        titled_pane_ad.collapsibleProperty().setValue(false);

        //patient info menu
        VBox vbox_pInfo = new VBox();
        vbox_pInfo.setSpacing(10);
        vbox_pInfo.setPadding(new Insets(10,10,10,10));
        vbox_pInfo.getChildren().addAll(
                patientId_label,
                patientId_tf,
                display_patient_info_button
        );

        TitledPane titled_pane_pInfo = new TitledPane("Dsiplay Patient Info:",vbox_pInfo);
        titled_pane_pInfo.collapsibleProperty().setValue(false);

        //add nodes to grid
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10,10,10,10));
        grid.getColumnConstraints().addAll(c1,c2);

        grid.add(titled_pane_sd,0,0);
        grid.add(sdGrid,1,0);
        grid.add(titled_pane_ad,0,1);
        grid.add(adGrid,1,1);
        grid.add(titled_pane_pInfo,0,2);
        grid.add(pInfoGrid,1,2);

        mainPane.getChildren().addAll(
                title_header,
                dataSource,
                grid
        );

        Scene scene = new Scene(mainPane, 1000,800);
        stage.setTitle("Projet Capstone DrugStore");
        stage.setScene(scene);
        stage.show();
    }

    private void retrieveDoseAndADR_UI() {
        String drugName = drugName_tf.getText();

        if(drugName.isEmpty()){
            showError("Please enter a drug name");
            return;
        }

        Drug drug = doctorController.getDrugByName(drugName.toLowerCase());
        if(drug == null){
            showError("This drug is not found");
            return;
        }

        //clear ui grid
        sdGrid.getChildren().clear();

        double stdDose = doctorController.getStandardDose(drugName);

        addRow(sdGrid,"Drug Name :",drugName, 0);
        addRow(sdGrid,"Standard Dose :",stdDose+" mg / kg", 1);
    }

    private void calculateDose_UI() {
        String prescribed = prescribed_tf.getText();
        String concomitant = concomitant_tf.getText();
        int patientId = Integer.parseInt(patientId_tf_ad.getText());

        //clear ui grid
        adGrid.getChildren().clear();

        if (prescribed.isBlank() || concomitant.isBlank()) {
            showError("prescribed or concomitant cannot be null");
            return;
        }

        DoseResult result = null;
        result = doctorController.getCalculateDose(patientId, prescribed, concomitant);
        if (result == null) {
            showError("Calculate dose failed");
            return;
        }

        Prescription rx = doctorController.getPrescriptionForDrug(patientId, prescribed);
        if (rx == null) {
            showError("Prescription not found for this drug");
            return;
        }
        List<AdverseDrugReaction> adrs = rx.getEffects();

        addRow(adGrid, "Prescibed drug :", prescribed, 0);
        addRow(adGrid, "Concomitant drug :", concomitant, 1);
        addRow(adGrid, "Interaction factor :", String.valueOf(result.getInteractionFactor()), 2);
        addRow(adGrid, "Adjusted Dose :", result.getAdjustedDose() + " mg", 3);
        int row = 4;
        for (AdverseDrugReaction a : adrs) {
            addRow(adGrid, a.getCategory(), a.getDescription(), row++);
        }
    }

    public void displayPatientData_UI() {
        int patientId = Integer.parseInt(patientId_tf.getText());

        // clear ui grid
        pInfoGrid.getChildren().clear();

        Patient patient = doctorController.displayPatientData(patientId);
        if (patient == null) {
            pInfoGrid.add(new Label("Patient introuvable"), 0, 0);
            return;
        }

        addRow(pInfoGrid,"Name :", patient.getName(), 0);
        addRow(pInfoGrid,"Age :", patient.getAge() +" ans", 1);
        addRow(pInfoGrid,"Weight :", patient.getWeight() + " kg", 2);
        addRow(pInfoGrid,"Tel :", patient.getTel(), 3);
        addRow(pInfoGrid,"Allergies :", patient.getAllergies(), 4);
        int row = 5;
        for (Prescription p : patient.getPrescriptions()) {
            addRow(pInfoGrid, "Prescription ID :", String.valueOf(p.getId()), row++);
            addRow(pInfoGrid, "PDD :", p.getPDD() +"mg/day", row++);
            addRow(pInfoGrid, "Doctor :", p.getDoctor().getName(), row++);

            //drugs
            StringBuilder druglist = new StringBuilder();
            for (Drug d : p.getDrugs()) {
                druglist.append(d.getName()).append(", ");
            }
            if(!p.getDrugs().isEmpty()){
                druglist.setLength(druglist.length() - 2); // supprimer la derniere virgule
            }
            addRow(pInfoGrid, "Drugs :", druglist.toString(), row++);

            //ADRs
            for(AdverseDrugReaction adr : p.getEffects()) {
                addRow(pInfoGrid, "ADR (" +adr.getCategory() + "):",
                        adr.getDescription(),
                        row++);
            }
            //ligne vide entre les prescriptions
            row++;
        }
    }

    private void addRow(GridPane grid, String label, String value, int row) {
        Label lbl = new Label(label);
        lbl.setStyle("-fx-font-weight: bold; -fx-font-fill: #2a2a2a;");

        Label val = new Label(value);
        val.setStyle("-fx-font-fill: #333333;");

        grid.add(lbl, 0, row);
        grid.add(val, 1, row);
    }

    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
