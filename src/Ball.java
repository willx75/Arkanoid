import com.sun.glass.ui.Application;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball extends Circle {
       
		private double vitesse;
        private double hauteur, largeur;
        private Plateau pad;
        private double angle = Math.PI / 4;
        private double dx, dy;
        
        private boolean ToucheGauche;
        private boolean ToucheDroit;
        private boolean TouchePlafond;
        private boolean ToucheBas;
        private boolean hitPad;
        
        //Variables to check if there is a collision with a block
        private boolean hitBlockTop;
        private boolean hitBlockRight;
        private boolean hitBlockBottom;
        private boolean hitBlockLeft;
        private ArkanoidFrame frame;
        
        //The block that is hit by the ball and should be removed
        private Bloc blocToHide;
      
   
        
        public Ball(Plateau pad, double speed, double height, double width, ArkanoidFrame frame) {
                this.dx = 0;
                this.dy = -1;
                this.pad = pad;
                this.vitesse = speed;
                this.hauteur = height;
                this.largeur = width;
                this.setRadius(7);
                this.setCenterX(width / 2);
                this.setCenterY(this.pad.getY() - getRadius());
                this.setFill(Color.BLACK);
                this.frame = frame;
        }

        
        public void update() {
                murGauche();
                murDroite();
                plafond();
                hitBlock();
                bas();
                hitPad();
                
                if (this.ToucheGauche) {
                        this.dx = Math.abs(this.dx);
                        this.ToucheGauche = false;
                } 
                else if (this.ToucheDroit) {
                        this.dx = -Math.abs(this.dx);
                        this.ToucheDroit = false;

                } 
                else if (this.TouchePlafond) {
                        this.dy = Math.abs(this.dy);
                        this.TouchePlafond = false;

                }
                else if (this.hitBlockRight) {
                    this.dx = Math.abs(this.dx);
                    this.hitBlockRight = false;
                    checkBlocks();
                } 
                else if (this.hitBlockLeft) {
                    this.dx = -Math.abs(this.dx);
                    this.hitBlockLeft = false;
                    checkBlocks();
                } 
                else if (this.hitBlockBottom) {
                    this.dy = Math.abs(this.dy);
                    this.hitBlockBottom = false;
                    checkBlocks();
                }
                else if (this.hitBlockTop) {
                	this.dy = -Math.abs(this.dy);
                	this.hitBlockTop = false;
                	checkBlocks();
                }
                else if (this.hitPad) {

                        double res = ((this.getCenterX()) - this.pad.getX()) / (this.pad.getWidth());
                        
                        this.dx = Math.cos((Math.PI - this.angle) - (res * 2 * ((Math.PI / 2) - this.angle)));
                        this.dy = -1* Math.sin((Math.PI - this.angle) - (res * 2 * ((Math.PI / 2) - this.angle)));

                        this.hitPad = false;

                }
                else if (this.ToucheBas) {
                	frame.ballHitGround();

                }

                this.setCenterX(this.getCenterX() + (this.dx * this.vitesse));
                this.setCenterY(this.getCenterY() + (this.dy * this.vitesse));

        }

        
        private void murGauche() {
                if ((this.getCenterX() - this.getRadius()) < 0) {
                        this.ToucheGauche = true;
                }
        }

        
        private void murDroite() {
                if ((this.largeur - this.getRadius()) < this.getCenterX()) {
                        this.ToucheDroit = true;
                }
        }

        
        private void plafond() {
                if ((this.getCenterY() - this.getRadius()) < 0) {
                        this.TouchePlafond = true;
                }
        }

        
        private void bas() {
        		if (this.getCenterY() > (this.hauteur + this.getRadius())) {
                        this.ToucheBas = true;
                }
        }

        
        private void hitPad() {
    if ((this.getCenterX() < (this.pad.getX() + this.pad.getWidth() + getRadius())) && (this.getCenterX() > (this.pad.getX() - getRadius())) && (((this.getCenterY() + this.getRadius()) > this.pad.getY()) && ((this.getCenterY() - this.getRadius()) < (this.pad.getY() + this.pad.getHeight())))) {
            this.hitPad = true;
                }
        }
        

        //Checking if the ball hit the right edge of a block
        private boolean hitBlockRight(Bloc bloc) {
            if (((this.getCenterX() - this.getRadius()) < (bloc.getX() + bloc.getWidth())) &&
                ((this.getCenterX() + this.getRadius()) > (bloc.getX() + bloc.getWidth())) &&
                ((this.getCenterY() + this.getRadius()) > bloc.getY() && (this.getCenterY() - this.getRadius()) < (bloc.getY() + bloc.getHeight())) &&
                this.dx < 0) {
                    return true;
            }
            return false;
        }

        
      //Checking if the ball hit the left edge of a block
        private boolean hitBlockLeft(Bloc bloc) {
            if (((this.getCenterX() + this.getRadius()) > bloc.getX()) &&
            	((this.getCenterX() - this.getRadius()) < bloc.getX()) &&
            	((this.getCenterY() + this.getRadius()) > bloc.getY() && (this.getCenterY() - this.getRadius()) < (bloc.getY() + bloc.getHeight())) &&
            	this.dx > 0) {
                    return true;
            }
            return false;
        }

        
      //Checking if the ball hit the bottom edge of a block
        private boolean hitBlockBottom(Bloc bloc) {
            if (((this.getCenterY() - this.getRadius()) < (bloc.getY() + bloc.getHeight())) &&
            	((this.getCenterY() + this.getRadius()) > (bloc.getY())) &&	
            	((this.getCenterX() + this.getRadius()) > bloc.getX() && (this.getCenterX() - this.getRadius()) < (bloc.getX() + bloc.getWidth())) &&
            	this.dy < 0) {
                    return true;
            }
            return false;
        }

        
      //Checking if the ball hit the top edge of a block
        private boolean hitBlocTop(Bloc bloc) {
            if (((this.getCenterY() + this.getRadius()) > bloc.getY()) &&
            	((this.getCenterY() - this.getRadius()) < bloc.getY() + bloc.getHeight()) &&
            	(this.getCenterX() + this.getRadius() > bloc.getX() && this.getCenterX() - this.getRadius() < bloc.getX() + bloc.getWidth()) &&
            	this.dy > 0) {
            	return true;
            }
            return false;
        }
        
        
        //Checking collisions with blocks
        private void hitBlock() {
        	for (Bloc bloc : frame.getBlocks()) {
        		if (hitBlockBottom(bloc)) {
        			this.hitBlockBottom = true;
        			this.blocToHide = bloc;
        			return;
        		}
        		if (hitBlockLeft(bloc)) {
        			this.hitBlockLeft = true;
        			this.blocToHide = bloc;
        			return;
        		}
        		if (hitBlockRight(bloc)) {
        			this.hitBlockRight = true;
        			this.blocToHide = bloc;
        			return;
        		}
        		if (hitBlocTop(bloc)) {
        			this.hitBlockTop = true;
        			this.blocToHide = bloc;
        			return;
        		}
        	}
        }
        
        
        //Remove the hit block and check if no more blocks remain and the game is won
        private void checkBlocks() {
        	this.frame.getNodes().remove(this.blocToHide);
        	this.blocToHide = null;
            if (this.frame.noBlocks()) {
            	this.frame.gameWon();
            }
        }
        
        
        public void setSpeed(double speed) {
        	this.vitesse = speed;
        }
}
