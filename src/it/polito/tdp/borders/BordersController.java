/**
 * Sample Skeleton for 'Borders.fxml' Controller Class
 */

package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Country;
import it.polito.tdp.borders.model.Model;
import it.polito.tdp.borders.model.RisultatoCalcolaConfini;
import it.polito.tdp.borders.model.RisultatoSimulazione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class BordersController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="boxNazione"
    private ComboBox<Country> boxNazione; // Value injected by FXMLLoader

    @FXML // fx:id="btnSimula"
    private Button btnSimula; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void activateSimula(ActionEvent event) {
    	this.btnSimula.setDisable(false);
    }

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	try {
    		Integer.parseInt(this.txtAnno.getText());
    	}
    	catch(NumberFormatException e) {
    		this.txtResult.appendText("Inserire un anno!\n");
    		return;
    	}
    	try {
    		int year = Integer.parseInt(this.txtAnno.getText());
    		if(year<model.minYear()) {
    			this.txtResult.appendText("Anno troppo vecchio!\n");
    			return;
    		}
    		if(year>model.maxYear()) {
    			this.txtResult.appendText("Anno troppo avanti!\n");
    			return;
    		}
    		this.txtResult.appendText("La situazione dei confini nell'anno selezionato è:\n");
    		for(RisultatoCalcolaConfini c : model.calcolaConfini(year)) {
    			this.txtResult.appendText(c+"\n");
    		}
    		this.boxNazione.getItems().setAll(model.getCountries());
    		this.boxNazione.setDisable(false);
    	}
    	catch(RuntimeException e) {
    		this.txtResult.appendText("Errore DB!\n");
    	}
    }

    @FXML
    void doSimula(ActionEvent event) {
    	try {
    		Country c = this.boxNazione.getValue();
    		this.txtResult.appendText("\nAvvio la simulazione...\n");
    		for(RisultatoSimulazione r : model.simula(c))
    			this.txtResult.appendText(r+"\n");
    		this.txtResult.appendText(String.format("La simulazione si è conclusa in %d passi!\n", model.getStepSimulazione()));
    	}
    	catch(RuntimeException e) {
    		this.txtResult.appendText("Errore DB!\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Borders.fxml'.";
        assert boxNazione != null : "fx:id=\"boxNazione\" was not injected: check your FXML file 'Borders.fxml'.";
        assert btnSimula != null : "fx:id=\"btnSimula\" was not injected: check your FXML file 'Borders.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Borders.fxml'.";

    }

	public void setModel(Model model) {
		this.model=model;
	}
}

