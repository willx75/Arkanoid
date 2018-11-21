import java.util.ArrayList;
import java.util.List;

//LINA
import java.io.FileReader;
import java.io.BufferedReader;
//END-LINA


import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class Jeu {

        private double hauteur;
        private double largeur;
        private Plateau pad;
        private List<Ball> listball;
        private List<Bloc> listbloc;
        private double speed = 5;
        private double xMin, yMin;
        private double xMax, yMax;
        private boolean bool = false;
        private ArkanoidFrame frame;

        
        public Jeu(double height, double width, ArkanoidFrame frame) {
                this.hauteur = height;
                this.largeur = width;
                this.listball = new ArrayList<>();// liste de balles qu'on utilise pour le jeu 
                this.listbloc = new ArrayList<>();// les blocs de jeu a devoir etre detruit
                this.frame = frame;
        }

        
        public void update() {
                this.pad.update();

                for (Ball b : this.listball) {
                        b.update();
                }
        }
        
        
        private void ajoutBloc(List<Node> listElements, int haut, int bas,int gauche, int droite, Color couleur) {

                if (this.yMin > haut) {
                        this.yMin = haut;
                        bool = true;
                }
                if (this.xMin > gauche) {
                        this.xMin = gauche;
                        bool = true;

                }
                if (this.yMax < bas) {
                        this.yMax = bas;
                        bool = true;

                }
                if (this.xMax < droite) {
                        this.xMax = droite;
                        bool = true;

                }

                Bloc b = new Bloc(haut, bas, gauche, droite, couleur);
                listElements.add(b);

               this.listbloc.add(b);

        }
        
        
        public void eventKeyReleased(KeyEvent event) {
            switch (event.getCode()) {
            case RIGHT:
                    this.pad.stopDroite();
                    break;
            case LEFT:
                    this.pad.stopGauche();
                    break;
            default:
                    break;
            }
        }
        
        
        public void eventKeyPressed(KeyEvent event) {
        		if (!this.frame.isGameOver()) {
        			switch (event.getCode()) {
        			case RIGHT:	
        				this.pad.deplacementDroite();
                        break;
        			case LEFT:
                        this.pad.deplacementGauche();
                        break;
        			default:
                        break;
        			}
        		}
        }

        
        //Loading a new level from a file
        public List<Node> nouvellePartie(String filePath) throws Exception {
        	this.listbloc.clear();
        	this.listball.clear();
            List<Node> listElements = new ArrayList<Node>();

            loadFromFile(filePath, listElements);

            this.pad = new Plateau(this.speed, this.hauteur, this.largeur);

            listElements.add(this.pad);

            Ball ball = new Ball(this.pad, this.speed, this.hauteur, this.largeur, frame);
            this.listball.add(ball);
            listElements.add(ball);

            return listElements;
      }
        

        //Reading a file
        private void loadFromFile(String path, List<Node> listElements) throws Exception {
        	//reading file lines into a list
      	    List<String> lines = new ArrayList<>();
            BufferedReader reader = new BufferedReader(new FileReader(path));
            
            String line = reader.readLine();
            
            while (line != null) {
                lines.add(line);
                line = reader.readLine();
            }
            
            reader.close();
            
            //Filling game model
            String firstLine = lines.get(0);
            String[] dimensions = firstLine.split("x");
            
            largeur = Integer.parseInt(dimensions[0].trim());
            hauteur = Integer.parseInt(dimensions[1].trim());
            
            String color;
            String[] parts, pointNW, pointSE;
            
            for (int i = 1; i < lines.size(); i++) {
                line = lines.get(i);
                if (!line.contains("&")) {
                	throw new Exception("Format Error: Brick data should be delimited by '&'");
                }
                parts = line.split("&");
                color = parts[2].trim();
                pointNW = parts[0].trim().split(",");
                pointSE = parts[1].trim().split(",");
                
                int dimNW_X = Integer.parseInt(pointNW[0].trim().split("\\(")[1].trim()),
                    dimNW_Y = Integer.parseInt(pointNW[1].trim().split("\\)")[0].trim()),
                    dimSE_X = Integer.parseInt(pointSE[0].trim().split("\\(")[1].trim()), 
                    dimSE_Y = Integer.parseInt(pointSE[1].trim().split("\\)")[0].trim());
                
                if (dimNW_X >= dimSE_X || dimNW_Y >= dimSE_Y) {
                	throw new Exception("Format Error: Illegal block coordinates. \n (x1, y1) should be < (x2, y2).");
                }
                
                if (dimNW_X < 0 || dimNW_X > largeur ||
                    dimSE_X < 0 || dimSE_X > largeur ||
                    dimNW_Y < 0 || dimNW_Y > hauteur ||
                    dimSE_Y < 0 || dimSE_Y > hauteur) {
                    throw new Exception("Format Error: Block outside terrain borders.");
                }
                ajoutBloc(listElements, dimNW_Y, dimSE_Y, dimNW_X, dimSE_X, Color.valueOf(color));
            }
        }
        
        
        public double getHauteur() {
        	return hauteur;
        }
        
        
        public double getLargeur() {
        	return largeur;
        }
        
        
        public Plateau getPad() {
        	return this.pad;
        }
        
        
        public List<Bloc> getBlocks() {
        	return listbloc;
        }
        
        
        public void setSpeed(double speed) {
        	this.speed = speed;
        	
        	for (Ball ball : listball)
        		ball.setSpeed(speed);
        }
}