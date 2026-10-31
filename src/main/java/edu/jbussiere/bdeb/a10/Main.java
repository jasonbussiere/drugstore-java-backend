package edu.jbussiere.bdeb.a10;

import edu.jbussiere.bdeb.a10.persistence.factory.DAOFactory;
import edu.jbussiere.bdeb.a10.view.DoctorView;
import javafx.application.Application;

public class Main {

    static void main(String[] args) {

        //choose witch datasource to use and comment the others
        //DAOFactory.useJPA();
        DAOFactory.useJDBC();
        //DAOFactory.useMock();

        Application.launch(DoctorView.class, args);
    }
}
