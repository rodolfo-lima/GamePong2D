import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Bola {
	private int velX;
	private int velY;
	private int posX;
	private int posY;
	private int raio;
	
	private BufferedImage imageBola;
	
	public Bola() {
		try {
			imageBola = ImageIO.read(getClass().getResource("sprites/bola.png"));
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}

		this.raio = 16;
		this.posX = Principal.LARGURA_TELA/2 - raio;
		this.posY = Principal.ALTURA_TELA/2 - raio;
		
		this.velX = -5;
		this.velY = -5;
		
	}

	public int getVelX() {
		return velX;
	}

	public void setVelX(int velX) {
		this.velX = velX;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getRaio() {
		return raio;
	}

	public void setRaio(int raio) {
		this.raio = raio;
	}

	public BufferedImage getImageBola() {
		return imageBola;
	}

	public void setImageBola(BufferedImage imageBola) {
		this.imageBola = imageBola;
	}	
	

}
