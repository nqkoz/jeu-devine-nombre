package application;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;

public class Controleur {

	@FXML
	private TextField champBorneMin;

	@FXML
	private TextField champBorneMax;

	@FXML
	private TextField champProposition;

	@FXML
	private Label Resultat;

	@FXML
	private Label Essais;

	@FXML

	private Button Rejouer;
	@FXML
	private Label Min;

	@FXML
	private Label Max;
	@FXML
	private Label Perdu;

	@FXML
	private Label Historique;

	@FXML
	private Label Proposition;

	@FXML
	private CheckBox checkBoxAide;

	@FXML
	private Button boutonDemarrerJeu;

	@FXML
	private TabPane tabPane;

	private jeuNombre Jeu;

	private boolean jeuDemarre = false;

	@FXML
	private void initialize() {
		Jeu = new jeuNombre(1, 100);

	}

// demarrer le jeu avec le parametrage des bornes
	@FXML
	private void handleDemarrerJeu() {

		if (!jeuDemarre) {

			if (!champBorneMin.getText().isEmpty() && !champBorneMax.getText().isEmpty()) {
				try {
					int borneMin = Integer.parseInt(champBorneMin.getText());
					int borneMax = Integer.parseInt(champBorneMax.getText());
					Jeu.reset(borneMin, borneMax);
					Min.setText(String.valueOf(Jeu.getMinCourant()));
					Max.setText(String.valueOf(Jeu.getMaxCourant()));
					System.out.println("Nouveau jeu démarré avec les bornes : " + borneMin + " à " + borneMax);
					champBorneMin.setText("");
					champBorneMax.setText("");
				} catch (NumberFormatException e) {
					System.out.println("Veuillez entrer des bornes valides.");
				}
			} else {
				System.out.println("Nouveau jeu démarré avec les bornes par défaut : 1 à 100");
			}

			Tab jeuTab = tabPane.getTabs().get(1);
			jeuTab.setDisable(false);
			Tab parametre = tabPane.getTabs().get(0);
			parametre.setDisable(true);
			tabPane.getSelectionModel().select(1);

			jeuDemarre = true;
		}
	}

	// envoyer une proposition
	@FXML
	private void handleSoumettre() {
		try {
			Proposition a;
			int proposition = Integer.parseInt(champProposition.getText());
			a = new Proposition(proposition, Jeu.getMinCourant(), Jeu.getMaxCourant());
			String resultat = Jeu.Proposition(proposition, a);
			Resultat.setText(resultat);
			champProposition.setText("");

			if (checkBoxAide.isSelected()) {
				Min.setText(String.valueOf(Jeu.getMinCourant()));
				Max.setText(String.valueOf(Jeu.getMaxCourant()));
				Proposition.setText(Jeu.getHistorique());
			} else {
				Min.setText("");
				Max.setText("");
				Proposition.setText("");
			}

			if (Jeu.GetTrouver()) {
				Tab bilanTab = tabPane.getTabs().get(2);
				if (bilanTab != null) {
					bilanTab.setDisable(false);
					Essais.setText(String.valueOf(Jeu.GetNbPropositions()));
					Historique.setText(Jeu.getHistorique());
					tabPane.getSelectionModel().select(2);
					Tab jeuTab = tabPane.getTabs().get(1);
					jeuTab.setDisable(true);

				} else {
					System.out.println("L'onglet n'est pas trouvé.");
				}
			}
		} catch (NumberFormatException e) {
			Resultat.setText("Veuillez entrer un nombre valide.");
		}
	}

	// bouton rejouer
	@FXML

	private void handleRejouer() {
		try {
			Tab jeuTab = tabPane.getTabs().get(1);
			jeuTab.setDisable(true);
			Tab bilanTab = tabPane.getTabs().get(2);
			bilanTab.setDisable(true);
			jeuDemarre = false;
			tabPane.getSelectionModel().select(0);
			Tab parametre = tabPane.getTabs().get(0);
			parametre.setDisable(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//boutton abandonner

	@FXML
	private void Abandonner() {
		Tab bilanTab = tabPane.getTabs().get(2);
		bilanTab.setDisable(false);
		Tab jeuTab = tabPane.getTabs().get(1);
		jeuTab.setDisable(true);
		Essais.setText(String.valueOf(Jeu.GetNbPropositions()));
		Historique.setText(Jeu.getHistorique());
		tabPane.getSelectionModel().select(2);
		Perdu.setText("Perdu : Partie Abandonnee");
	}
}
