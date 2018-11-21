public class Rectangle {

	private double hauteur, largeur;
    private boolean droite,gauche; 
	private double largeurPad= 150;
    private double vitessePad;

    
    public Rectangle(double hauteur, double largeur, double vitesse) {
		super();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.vitessePad = vitesse ;
        this.setX((largeur / 2) - (this.largeurPad / 2));
        this.setY(hauteur * 0.90);
        this.setLargeur(this.largeurPad);

    }

    
	private void setY(double d) {
		
	}

	
	public double getHauteur() {
		return hauteur;
	}


	public void setHauteur(double hauteur) {
		this.hauteur = hauteur;
	}

	
	public double getLargeur() {
		return largeur;
	}

	
	public void setLargeur(double largeur) {
		this.largeur = largeur;
	}

	
	public void setDroite(boolean droite) {
		this.droite = true;
	}


	public void setGauche(boolean gauche) {
		this.gauche = true;
	}


	public double getLargeurPad() {
		return largeurPad;
	}


	public void setLargeurPad(double largeurPad) {
		this.largeurPad = largeurPad;
	}


	public double getVitessePad() {
		return vitessePad;
	}


	public void setVitessePad(double vitessePad) {
		this.vitessePad = vitessePad;
	}
    
	
	 public void update() {

         if (droite && ((getX() + largeurPad) < largeur)) {
                 this.setX(getX() + this.vitessePad);
                 if ((this.getX() + this.largeurPad) > this.largeur) {
                         this.setX(this.largeur - this.largeurPad);
                 }
         }

         if (this.gauche && (getX() > 0)) {
                 this.setX(getX() - this.vitessePad);
                 if (this.getX() < 0) {
                         this.setX(0);
                 }
         }
	 }

	 
	private int getX() {
		return 0;
	}

	
	private void setX(double d) {
		
	}
}


