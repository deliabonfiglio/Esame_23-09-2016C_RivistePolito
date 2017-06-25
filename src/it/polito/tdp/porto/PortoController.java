package it.polito.tdp.porto;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import it.polito.tdp.porto.model.Model;
import it.polito.tdp.porto.model.Paper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PortoController {

	private Model model;
	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private TextField txtMatricolaStudente;

	@FXML
	private TextArea txtResult;

	@FXML
	void doFrequenzaRiviste(ActionEvent event) {
		txtResult.appendText("Lista riviste con frequenze: \n");
		//model.creaGrafo();
		Map<String, Integer> mappa = model.getFrequenzeRiviste();
		for(String s: mappa.keySet())
		txtResult.appendText(s.toString()+" "+mappa.get(s)+"\n");
		
	}

	@FXML
	void doVisualizzaRiviste(ActionEvent event) {
		List<String> riviste = model.getRivisteMin();
		for(String s: riviste)
			txtResult.appendText(s+"\n");
	}

	@FXML
	void initialize() {
		assert txtMatricolaStudente != null : "fx:id=\"txtMatricolaStudente\" was not injected: check your FXML file 'DidatticaGestionale.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'DidatticaGestionale.fxml'.";

	}

	public void setModel(Model model) {
		this.model=model;
		
	}

}
