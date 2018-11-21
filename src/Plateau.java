import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Plateau extends Rectangle {

		private double width, height;
        private boolean droite = false, gauche = false;
        private int profondeurPad = 100;
        private double vitesse ;

       
        public Plateau(double speed, double height, double width) {
                this.setX((width / 2) - (this.profondeurPad / 2));
                this.setHeight(10);
                this.vitesse = 2 * speed;
                this.height = height;
                this.width = width;
                this.setY(height * 0.99);
                this.setWidth(this.profondeurPad);
                this.setFill(Color.BLACK);
        }

        
        public void deplacementDroite() {
                this.droite = true;
        }

        
        public void stopDroite() {
                this.droite = false;
        }

        
        public void deplacementGauche() {
                this.gauche = true;
        }

        
        public void stopGauche() {
                this.gauche = false;
        }

        
        public void update() {

                if (this.droite && ((getX() + this.profondeurPad) < this.width)) {
                        this.setX(getX() + this.vitesse);
                        if ((this.getX() + this.profondeurPad) > this.width) {
                                this.setX(this.width - this.profondeurPad);
                        }
                }
                	
                if (this.gauche && (getX() > 0)) {
                        this.setX(getX() - this.vitesse);
                        if (this.getX() < 0) {
                                this.setX(0);
                        }
                }
        }
}
