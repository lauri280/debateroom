package application;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane root = new GridPane();
		root.setHgap(10);
		root.setVgap(10);
		root.setPadding(new Insets(10, 10, 10, 10));
		
		// --- Vajalikud objektid ---
		OsalejateKogum osalejad = new OsalejateKogum();
		Randomiseerija randomiseerija = new Randomiseerija();
		
		// --- GUI asjad, mis peavad eespool olema ---
		ListView<String> osalejateList = new ListView<>();
		root.add(osalejateList, 2, 1);
		GridPane.setRowSpan(osalejateList, 15);
		
		ListView<String> tiimideList = new ListView<>();
		root.add(tiimideList, 4, 1);
		GridPane.setRowSpan(tiimideList, 10);
		
		ListView<String> kohtunikeList = new ListView<>();
		root.add(kohtunikeList, 5, 1);
		GridPane.setRowSpan(kohtunikeList, 10);
		
		// magic, mille abil saab hiirega valida mitu cell'i
		osalejateList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
		osalejateList.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
			tiimideList.getSelectionModel().clearSelection();
			kohtunikeList.getSelectionModel().clearSelection();
			
			Node node = evt.getPickResult().getIntersectedNode();
			if (node instanceof ListCell) {
				evt.consume();
				ListCell<?> cell = (ListCell<?>)node;
				ListView<?> lv = cell.getListView();
				
				lv.requestFocus();
				
				if (!cell.isEmpty()) {
					int i = cell.getIndex();
					if (cell.isSelected()) {
						lv.getSelectionModel().clearSelection(i);
					} else {
						lv.getSelectionModel().select(i);
					}
				}
			}
		});
		
		// järgmised kaks blokki selleks, et saaks selekteerida vaid ühe ListView cell'e
		tiimideList.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
			osalejateList.getSelectionModel().clearSelection();
			kohtunikeList.getSelectionModel().clearSelection();
		});
		
		kohtunikeList.addEventFilter(MouseEvent.MOUSE_PRESSED, evt -> {
			osalejateList.getSelectionModel().clearSelection();
			tiimideList.getSelectionModel().clearSelection();
		});
		
		// --- Osaleja sisestamine (GUI 1. blokk) ---
		Label labelSisesta = new Label();
		labelSisesta.setText("Sisesta nimi:");
		root.add(labelSisesta, 0, 0);
		
		TextField tfSisesta = new TextField();
		root.add(tfSisesta, 0, 1);
		
		CheckBox cbAlgaja = new CheckBox();
		cbAlgaja.setText("Algaja");
		root.add(cbAlgaja, 0, 2);
		
		Button buttonLisa = new Button("Lisa");
		root.add(buttonLisa, 0, 3);
		buttonLisa.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (!(MuudMeetodid.onTyhiString(tfSisesta.getText()))) { // kui tekstiväli on tühi
					if (osalejad.nimePoleNimekirjas(tfSisesta.getText())) { // kui osaleja pole nimekirjas
						osalejad.lisaOsaleja(new Osaleja(tfSisesta.getText(), cbAlgaja.isSelected()));
						tfSisesta.clear();
						osalejateList.setItems(osalejad.tagastaObservList());
					} else {
						System.out.println("Osaleja on juba nimekirjas!");
					}
				}
			}
		});

		tfSisesta.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.ENTER) {
					if (!(MuudMeetodid.onTyhiString(tfSisesta.getText()))) {
						if (osalejad.nimePoleNimekirjas(tfSisesta.getText())) {
							osalejad.lisaOsaleja(new Osaleja(tfSisesta.getText(), cbAlgaja.isSelected()));
							tfSisesta.clear();
							osalejateList.setItems(osalejad.tagastaObservList());
						} else {
							System.out.println("Osaleja on juba nimekirjas!");
						}
					}
				}
			}
		});
		
		Button buttonTest = new Button("Testnupp"); // nupp testimiseks
		root.add(buttonTest, 0, 5);
		
		buttonTest.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				osalejateList.getSelectionModel().clearSelection();
			}
		});
		
		Button buttonSeaded = new Button("Seaded");
		root.add(buttonSeaded, 0, 4);
		
		Separator eraldaja1 = new Separator(); // eraldab 1. ja 2. GUI bloki
		eraldaja1.setOrientation(Orientation.VERTICAL);
		root.add(eraldaja1, 1, 0);
		GridPane.setRowSpan(eraldaja1, 22);
		
		
		// --- Osalejate list (GUI 2. blokk) ---
		Label labelNimekiri = new Label("Osalejad:");
		root.add(labelNimekiri, 2, 0);
		
		// ListView on eespool!
		
		Button buttonEemalda = new Button("Eemalda");
		root.add(buttonEemalda, 2, 16);
		buttonEemalda.setPrefWidth(130);
		buttonEemalda.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (!(osalejateList.getSelectionModel().getSelectedItem() == null)) {
					osalejad.eemaldaOsaleja(osalejad.tagastaNimegaOsaleja
							(osalejateList.getSelectionModel().getSelectedItem()));
					osalejateList.setItems(osalejad.tagastaObservList());
				}
			}
		});
		
		Button buttonLisaTiim = new Button("Määra tiimi");
		root.add(buttonLisaTiim, 2, 17);
		buttonLisaTiim.setPrefWidth(130);
		buttonLisaTiim.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if ((osalejateList.getSelectionModel().getSelectedItems().size() > 0) && 
						osalejateList.getSelectionModel().getSelectedItems().size() < 3) {
					osalejad.lisaTiim(osalejateList.getSelectionModel().getSelectedItems());
					osalejateList.setItems(osalejad.tagastaObservList());
					tiimideList.setItems(osalejad.tagastaObservTiimideList());
				}
			}
		}); 
		
		Button buttonLisaKohtunik = new Button("Määra kohtunikuks");
		root.add(buttonLisaKohtunik, 2, 18);
		buttonLisaKohtunik.setPrefWidth(130);
		buttonLisaKohtunik.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				// see esimene variant töötab vaid ühe osalejaga korraga, igaks juhuks praegu jätan alles
/*				if (!(osalejateList.getSelectionModel().getSelectedItem() == null)) {
					osalejad.lisaKohtunik(osalejad.tagastaNimegaOsaleja
							(osalejateList.getSelectionModel().getSelectedItem()));
					osalejateList.setItems(osalejad.tagastaObservList());
					kohtunikeList.setItems(osalejad.tagastaObservKohtunikeList());
				}
*/				
				if (osalejateList.getSelectionModel().getSelectedItems().size() > 0) {
					osalejad.lisaKohtunikud(osalejateList.getSelectionModel().getSelectedItems());
					osalejateList.setItems(osalejad.tagastaObservList());
					kohtunikeList.setItems(osalejad.tagastaObservKohtunikeList());
				}
			}
		});
		
		Button buttonPositsioonid = new Button("Määra positsioonid");
		root.add(buttonPositsioonid, 2, 19);
		buttonPositsioonid.setPrefWidth(130);
		
		
		Separator eraldaja2 = new Separator(); // eraldab 2. ja 3./4. GUI bloki
		eraldaja2.setOrientation(Orientation.VERTICAL);
		root.add(eraldaja2, 3, 0);
		GridPane.setRowSpan(eraldaja2, 22);
		
		
		// --- Tiimid/kohtunikud (GUI 3. blokk) ---
		// ListView elemendid eespool
		Label labelTiimid = new Label("Tiimid:");
		root.add(labelTiimid, 4, 0);
		
		Label labelKohtunikud = new Label("Kohtunikud:");
		root.add(labelKohtunikud, 5, 0);
		
		Button buttonEemaldaTK = new Button("Eemalda tiim/kohtunik");
		root.add(buttonEemaldaTK, 4, 11);
		buttonEemaldaTK.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if ((kohtunikeList.getSelectionModel().getSelectedItem() == null) &&
						(tiimideList.getSelectionModel().getSelectedItem() != null)) {
					osalejad.eemaldaTiim(tiimideList.getSelectionModel().getSelectedItem());
					osalejateList.setItems(osalejad.tagastaObservList());
					tiimideList.setItems(osalejad.tagastaObservTiimideList());
					
				} else if ((kohtunikeList.getSelectionModel().getSelectedItem() != null) &&
						(tiimideList.getSelectionModel().getSelectedItem() == null)) {
					osalejad.eemaldaKohtunik(kohtunikeList.getSelectionModel().getSelectedItem());
					osalejateList.setItems(osalejad.tagastaObservList());
					kohtunikeList.setItems(osalejad.tagastaObservKohtunikeList());
				}
			}
		});
		
		Separator eraldaja3 = new Separator();
		root.add(eraldaja3, 4, 12);
		GridPane.setColumnSpan(eraldaja3, 2);
		
		
		// --- Ruum (GUI 4. blokk) ---
		Label labelRuum = new Label("Ruum:");
		root.add(labelRuum, 4, 13);
		
		GridPane gpRuum = new GridPane(); // Ruumi osa, kus on positsioonid kirjas
		root.add(gpRuum, 4, 15);
		GridPane.setColumnSpan(gpRuum, 2);
		
		// Üksikud GridPane'id ruumi osade jaoks
		// OG
		GridPane gpOG = new GridPane();
		gpOG.setPrefWidth(250);
		gpRuum.add(gpOG, 0, 0);
		
		Label lbOGtxt = new Label("OG:");
		Label lbOG1 = new Label("-");
		Label lbOG2 = new Label("-");
		gpOG.add(lbOGtxt, 0, 0);
		gpOG.add(lbOG1, 0, 1);
		gpOG.add(lbOG2, 0, 2);
		
		//OO
		GridPane gpOO = new GridPane();
		gpOO.setPrefWidth(250);
		gpRuum.add(gpOO, 2, 0);
		
		Label lbOOtxt = new Label("OO:");
		Label lbOO1 = new Label("-");
		Label lbOO2 = new Label("-");
		gpOO.add(lbOOtxt, 0, 0);
		gpOO.add(lbOO1, 0, 1);
		gpOO.add(lbOO2, 0, 2);
		
		// CG
		GridPane gpCG = new GridPane();
		gpRuum.add(gpCG, 0, 2);
		
		Label lbCGtxt = new Label("CG:");
		Label lbCG1 = new Label("-");
		Label lbCG2 = new Label("-");
		gpCG.add(lbCGtxt, 0, 0);
		gpCG.add(lbCG1, 0, 1);
		gpCG.add(lbCG2, 0, 2);
		
		// CO
		GridPane gpCO = new GridPane();
		gpRuum.add(gpCO, 2, 2);
		
		Label lbCOtxt = new Label("CO:");
		Label lbCO1 = new Label("-");
		Label lbCO2 = new Label("-");
		gpCO.add(lbCOtxt, 0, 0);
		gpCO.add(lbCO1, 0, 1);
		gpCO.add(lbCO2, 0, 2);
		
		//Kohtunikud
		GridPane gpKoh = new GridPane();
		gpRuum.add(gpKoh, 0, 4);
		GridPane.setColumnSpan(gpKoh, 3);
		
		Label lbKohtxt = new Label("Kohtunikud:");
		Label lbKoh = new Label("-");
		gpKoh.add(lbKohtxt, 0, 0);
		gpKoh.add(lbKoh, 0, 1);
		
		// Eraldajad
		Separator eraldajaA = new Separator();
		gpRuum.add(eraldajaA, 0, 1);
		eraldajaA.setPadding(new Insets(10, 0, 5, 0));
		GridPane.setColumnSpan(eraldajaA, 3);
		
		Separator eraldajaB = new Separator();
		gpRuum.add(eraldajaB, 1, 0);
		eraldajaB.setOrientation(Orientation.VERTICAL);
		GridPane.setRowSpan(eraldajaB, 3);
		
		Separator eraldajaC = new Separator();
		gpRuum.add(eraldajaC, 0, 3);
		eraldajaC.setPadding(new Insets(10, 0, 5, 0));
		GridPane.setColumnSpan(eraldajaC, 3);
		
		// --- Ruumi loomine ---
		buttonPositsioonid.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent me) {
				if (osalejad.tagastaKoguLiikmeteArv() > 3) {
					// Tekita uus ruum randomiseerijaga ja siis selle sisu ekraanile
					Ruum uusRuum = randomiseerija.moodustaRuum(osalejad);
					System.out.println(uusRuum.toString()); // testimiseks
					
					lbOG1.setText(uusRuum.getTiimOG().get(0).getNimi());
					lbOG2.setText(uusRuum.getTiimOG().get(1).getNimi());
					
					lbOO1.setText(uusRuum.getTiimOO().get(0).getNimi());
					lbOO2.setText(uusRuum.getTiimOO().get(1).getNimi());
					
					lbCG1.setText(uusRuum.getTiimCG().get(0).getNimi());
					lbCG2.setText(uusRuum.getTiimCG().get(1).getNimi());
					
					lbCO1.setText(uusRuum.getTiimCO().get(0).getNimi());
					lbCO2.setText(uusRuum.getTiimCO().get(1).getNimi());
					
					lbKoh.setText(uusRuum.getStrKohtunikud());
				}
			}
		});
		
		
		// --- Stseeni tekitamine ---
		Scene scene = new Scene(root, 980, 660);
		primaryStage.setScene(scene);
		primaryStage.setTitle("DebateRoom v1.0");
		primaryStage.getIcons().add(new Image("/icons/DRicon16x16.png"));
		primaryStage.getIcons().add(new Image("/icons/DRicon32x32.png"));
		primaryStage.getIcons().add(new Image("/icons/DRicon48x48.png"));
		primaryStage.getIcons().add(new Image("/icons/DRicon256x256.png"));
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
