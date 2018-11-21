import javafx.event.ActionEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.sun.media.jfxmediaimpl.platform.Platform;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ArkanoidFrame extends Application {

	private Stage pageAccueil;
	private Scene scene;
	private Jeu game;
	private BorderPane bpane;
	private Pane jeuPane;
	private MenuBar menu;
	private AnimationTimer animation;
	private boolean running = false;
	private Pane niveau;
	private Pane logMessage;
	
	//Current level loaded
	private int currentLevel = 1;
	private boolean firstLoad = true;
	private boolean gameOver = false;
	
	//Text area to view application messages to user
    private TextArea message = new TextArea();
    private boolean autoLoad = false;
    
    //Buttons and menu items of the 7 levels
    private Button[] buttons = new Button[8];
    private MenuItem[] menuitems = new MenuItem[8];
    private boolean alreadyLoaded = true;



	@Override
	public void start(Stage pageAccueil) throws Exception {

		this.pageAccueil = pageAccueil;
		this.bpane = new BorderPane();
		this.jeuPane = new Pane();
		this.niveau = new VBox();
		
		//Creating buttons that load levels
		Button niv1 = new Button("Level 1");
		this.niveau.getChildren().add(niv1);
		buttons[1] = niv1;
		Button niv2 = new Button("Level 2");
		this.niveau.getChildren().add(niv2);
		buttons[2] = niv2;
		Button niv3 = new Button("Level 3");
		this.niveau.getChildren().add(niv3);
		buttons[3] = niv3;
		Button niv4 = new Button("Level 4");
		this.niveau.getChildren().add(niv4);
		buttons[4] = niv4;
		Button niv5 = new Button ("Level 5"); 
		this.niveau.getChildren().add(niv5);
		buttons[5] = niv5;
		Button niv6 = new Button ("Level 6"); 
		this.niveau.getChildren().add(niv6);
		buttons[6] = niv6;
		Button niv7 = new Button ("Level 7"); 
		this.niveau.getChildren().add(niv7);
		buttons[7] = niv7;
		
		//Assigning action listeners to buttons that load game levels
		niv1.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(1);
			}
		});
		niv2.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(2);
			}
		});
		niv3.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(3);
			}
		});
		niv4.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(4);
			}
		});
		niv5.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(5);
			}
		});
		niv6.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(6);
			}
		});
		niv7.setOnAction(new EventHandler<ActionEvent>() {
			@Override public void handle(ActionEvent event) {
				nouvellePartie(7);
			}
		});
		
		this.menu = new MenuBar();	
		Menu menu = new Menu("Game");
		Menu nouvellePartie = new Menu("Nouvelle Partie");

		//Creating menu items for loading levels
		menu.getItems().add(nouvellePartie);
		this.menu.getMenus().add(menu);
		
		MenuItem level1 = new MenuItem("Level 1");
		nouvellePartie.getItems().add(level1);
		menuitems[1] = level1;
		level1.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(1);
			}
		});
		MenuItem level2 = new MenuItem("Level 2");
		nouvellePartie.getItems().add(level2);
		menuitems[2] = level2;
		level2.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(2);
			}
		});
		MenuItem level3 = new MenuItem("Level 3");
		nouvellePartie.getItems().add(level3);
		menuitems[3] = level3;
		level3.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(3);
			}
		});
		MenuItem level4 = new MenuItem("Level 4");
		nouvellePartie.getItems().add(level4);
		menuitems[4] = level4;
		level4.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(4);
			}
		});
		MenuItem level5 = new MenuItem("Level 5");
		nouvellePartie.getItems().add(level5);
		menuitems[5] = level5;
		level5.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(5);
			}
		});
		MenuItem level6 = new MenuItem("Level 6");
		nouvellePartie.getItems().add(level6);
		menuitems[6] = level6;
		level6.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(6);
			}
		});
		MenuItem level7 = new MenuItem("Level 7");
		nouvellePartie.getItems().add(level7);
		menuitems[7] = level7;
		level7.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				nouvellePartie(7);
			}
		});
		
		//Game menu items
		MenuItem start = new MenuItem("Start (or S/s)");
		menu.getItems().add(start);
		
		start.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!ArkanoidFrame.this.running && !ArkanoidFrame.this.gameOver && ArkanoidFrame.this.alreadyLoaded) {
					ArkanoidFrame.this.animation.start();
					ArkanoidFrame.this.running = true;
					ArkanoidFrame.this.alreadyLoaded = false;
					ArkanoidFrame.this.message.setText("Game started.");
				}
			}
		});
		
		
		MenuItem pause = new MenuItem("Pause (or P/p)");
		menu.getItems().add(pause);
		
		pause.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (ArkanoidFrame.this.running) {
					ArkanoidFrame.this.animation.stop();
					ArkanoidFrame.this.running = false;
					ArkanoidFrame.this.message.setText("Game Paused.");
				}
			}
		});
		
		MenuItem resume = new MenuItem("Resume (or R/r)");
		menu.getItems().add(resume);
		
		resume.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!ArkanoidFrame.this.running && !gameOver) {
					ArkanoidFrame.this.animation.start();
					ArkanoidFrame.this.running = true;
					ArkanoidFrame.this.message.setText("Game Resumed.");
				}
			}
		});
		
		MenuItem exit = new MenuItem("Exit (or ESC)");
		menu.getItems().add(exit);
		
		exit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		
		
		//Check box for auto load
		CheckBox autoLoadCheck = new CheckBox("Auto-load");
		autoLoadCheck.setSelected(false);
		this.niveau.getChildren().add(autoLoadCheck);
		autoLoadCheck.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (!ArkanoidFrame.this.autoLoad) {
					ArkanoidFrame.this.autoLoad = true;
					for (int i = 1; i <= 7; i++) {
						if (i != ArkanoidFrame.this.currentLevel) {
							ArkanoidFrame.this.buttons[i].setDisable(true);
							ArkanoidFrame.this.menuitems[i].setDisable(true);
						}
					}
				}
				else {
					ArkanoidFrame.this.autoLoad = false;
					for (int i = 1; i <= 7; i++) {
						ArkanoidFrame.this.buttons[i].setDisable(false);
						ArkanoidFrame.this.menuitems[i].setDisable(false);
					}
				}
			}
		});
		
		
		//Game speed radio buttons
		final ToggleGroup speedOptions = new ToggleGroup();
		RadioButton easy = new RadioButton("Easy");
		easy.setToggleGroup(speedOptions);
		easy.setUserData(5);
		easy.setSelected(true);
		RadioButton inter = new RadioButton("Intermediate");
		inter.setToggleGroup(speedOptions);
		inter.setUserData(10);
		RadioButton hard = new RadioButton("Hard");
		hard.setToggleGroup(speedOptions);
		hard.setUserData(15);
		this.niveau.getChildren().add(easy);
		this.niveau.getChildren().add(inter);
		this.niveau.getChildren().add(hard);
		speedOptions.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov,
			        Toggle old_toggle, Toggle new_toggle) {
				if (speedOptions.getSelectedToggle() != null) {
					ArkanoidFrame.this.game.setSpeed(Double.parseDouble(speedOptions.getSelectedToggle().getUserData().toString()));
				}
			}
		});
		

		this.logMessage = new Pane();
		this.logMessage.setMaxHeight(100);
		this.logMessage.getChildren().add(message);
		this.scene = new Scene(this.bpane, 500, 650);

		this.jeuPane.setBorder(new Border(
				new BorderStroke(Color.ORANGE, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

		this.scene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				switch (event.getCode()) {
					case P:
						if (ArkanoidFrame.this.running) {
							ArkanoidFrame.this.animation.stop();
							ArkanoidFrame.this.running = false;
							ArkanoidFrame.this.message.setText("Game Paused.");
						} else if (!gameOver && !alreadyLoaded) {
							ArkanoidFrame.this.animation.start();
							ArkanoidFrame.this.running = true;
							ArkanoidFrame.this.message.setText("Game Resumed.");
						}
						break;
					case R:
						nouvellePartie(currentLevel);
						break;
					case ESCAPE:
						System.exit(0);
						break;
					case S:
						if (!ArkanoidFrame.this.running && !ArkanoidFrame.this.gameOver && ArkanoidFrame.this.alreadyLoaded) {
							ArkanoidFrame.this.animation.start();
							ArkanoidFrame.this.running = true;
							ArkanoidFrame.this.alreadyLoaded = false;
							ArkanoidFrame.this.message.setText("Game started.");
						}
						break;
					default:
						ArkanoidFrame.this.game.eventKeyPressed(event);
				}
			}
		});

		this.scene.setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				ArkanoidFrame.this.game.eventKeyReleased(event);
			}
		});

		this.bpane.setTop(this.menu);
		this.bpane.setCenter(this.jeuPane);

		this.animation = new AnimationTimer() {
			@Override
			public void handle(long now) {
				ArkanoidFrame.this.game.update();
			}
		};

		this.bpane.setLeft(this.niveau);
		this.bpane.setBottom(this.logMessage);
		this.pageAccueil.setScene(this.scene);
		this.pageAccueil.setTitle("Arkanoid");
		this.pageAccueil.setResizable(false);
		this.pageAccueil.show();

		this.game = new Jeu(this.jeuPane.getHeight(), this.jeuPane.getWidth(), this);
		nouvellePartie(currentLevel);
	}

	
	//lancer une nouvelle partie 
	public void nouvellePartie(int levelNo) {
		this.animation.stop();
		this.running = false;
		try {
			recommencer();
			this.jeuPane.getChildren().addAll(this.game.nouvellePartie("Niveaux/niveau" + levelNo));
			pageAccueil.setWidth(this.game.getLargeur() + 120);
			pageAccueil.setHeight(this.game.getHauteur() + 170);
			if (levelNo != currentLevel)
				this.message.setText("Level " + levelNo + " loaded.");
			else if (this.firstLoad) {
				this.firstLoad = false;
				this.message.setText("Level " + levelNo + " loaded.");
			} else
				this.message.setText("Level " + levelNo + " restarted.");
		}
		catch (Exception e) {
			this.message.setText(e.getMessage());
		}
		finally {
			currentLevel = levelNo;
			this.buttons[currentLevel].requestFocus();
		}
	}
	
	
	//methode qui remet une partie a zero
	private void recommencer() {
		Iterator<Node> iterator = this.jeuPane.getChildren().iterator();

		while (iterator.hasNext()) {
			Node element = iterator.next();
			if ((element instanceof Ball)  || (element instanceof Bloc)|| (element instanceof Plateau)) {
				iterator.remove();
			}
		}
		
		gameOver = false;
		alreadyLoaded = true;
	}
	
	//Called when game is lost
	public void ballHitGround() {
		gameOver = true;
		this.message.setText("Game Over!!");
	}
	
	public boolean isGameOver() {
		return gameOver;
	}

	public List<Bloc> getBlocks() {
		List<Bloc> result = new ArrayList<Bloc>();
		
		for (Node node : this.jeuPane.getChildren()) {
			if (node instanceof Bloc)
				result.add((Bloc)node);
		}
		
		return result;
	}
	
	
	public List<Node> getNodes() {
		return this.jeuPane.getChildren();
	}
	
	public boolean noBlocks() {
		for (Node node : this.jeuPane.getChildren()) {
			if (node instanceof Bloc)
				return false;
		}
		return true;
	}
	
	//Called when game is won
	public void gameWon() {
		this.message.setText("Congratulations. You won!!!");
		this.animation.stop();
		this.gameOver = true;
		
		//Loading next level automatically
		if (this.autoLoad) {
			javafx.application.Platform.runLater(new Runnable() {
				
				@Override
				public void run() {
					ArkanoidFrame.this.buttons[ArkanoidFrame.this.currentLevel].setDisable(true);
					ArkanoidFrame.this.menuitems[ArkanoidFrame.this.currentLevel].setDisable(true);
					ArkanoidFrame.this.buttons[ArkanoidFrame.this.currentLevel % 7 + 1].setDisable(false);
					ArkanoidFrame.this.menuitems[ArkanoidFrame.this.currentLevel % 7 + 1].setDisable(false);
					ArkanoidFrame.this.message.setText("Congratulations. You won!!\nLoading next level.");
					try {
						Thread.sleep(2000);
					}
					catch (InterruptedException e) {
						
					}
					nouvellePartie(ArkanoidFrame.this.currentLevel % 7 + 1);
				}
			});
		}
	}
}
