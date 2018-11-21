import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Bloc extends Rectangle {
        Color couleur;

        public Bloc(int haut, int bas, int gauche, int droite, Color couleur) {
                super();
                this.setX(gauche);
                this.setY(haut);
                this.setHeight(bas - haut);
                this.setWidth(droite - gauche);
                this.setFill(couleur);
        }
}
