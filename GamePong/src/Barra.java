import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Barra {
	private int posY;
	private int posX;
	private int alturaBarra;
	private int larguraBarra;
	private int velY;
	
	private BufferedImage imageEsquerda;
	private BufferedImage imageDireita;
	
	public Barra() {
		try {
			this.imageEsquerda = ImageIO.read(getClass().getResource("sprites/barraEsquerda.png"));
			this.imageDireita = ImageIO.read(getClass().getResource("sprites/barraDireita.png"));
			
		}catch (Exception e) {
			e.printStackTrace();
			
		}
		
		this.posY = Principal.ALTURA_TELA/2 - 64;
		this.posX = 0;
		this.alturaBarra = 128;
		this.larguraBarra = 32;
		this.velY = 0;
		
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getAlturaBarra() {
		return alturaBarra;
	}

	public void setAlturaBarra(int alturaBarra) {
		this.alturaBarra = alturaBarra;
	}

	public int getLarguraBarra() {
		return larguraBarra;
	}

	public void setLarguraBarra(int larguraBarra) {
		this.larguraBarra = larguraBarra;
	}

	public int getVelY() {
		return velY;
	}

	public void setVelY(int velY) {
		this.velY = velY;
	}

	public BufferedImage getImageEsquerda() {
		return imageEsquerda;
	}

	public void setImageEsquerda(BufferedImage imageEsquerda) {
		this.imageEsquerda = imageEsquerda;
	}

	public BufferedImage getImageDireita() {
		return imageDireita;
	}

	public void setImageDireita(BufferedImage imageDireita) {
		this.imageDireita = imageDireita;
	}	
	

}
