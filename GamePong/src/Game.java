import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javafx.scene.shape.Rectangle;

public class Game extends JPanel{
	private Bola bola;
	private Barra barraEsquerda;
	private Barra barraDireita;
	
	//private Rectangle ball;
	private Rectangle p1;
	private Rectangle p2;
	
	private BufferedImage bolaImg;
	private BufferedImage esquerdaImg;
	private BufferedImage direitaImg;
	
	private boolean k_cima = false;
	private boolean k_baixo = false;
	private boolean k_w = false;
	private boolean k_s = false;
	
	private int scoreEsquerda = 0;
	private int scoreDireita = 0;
	
	public Game() {
		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: k_cima = false; break;
				case KeyEvent.VK_DOWN: k_baixo = false; break;
				case KeyEvent.VK_W: k_w = false; break;
				case KeyEvent.VK_S: k_s = false; break;
				
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: k_cima = true; break;
				case KeyEvent.VK_DOWN: k_baixo = true; break;
				case KeyEvent.VK_W: k_w = true; break;
				case KeyEvent.VK_S: k_s = true; break;
				
				}
			}
		});
		
		bola = new Bola();
		barraEsquerda = new Barra();
		
		barraDireita = new Barra();
		barraDireita.setPosY(Principal.ALTURA_TELA/2 - 64);
		barraDireita.setPosX(Principal.LARGURA_TELA - 32);
		
		setFocusable(true);
		setLayout(null);
		
		new Thread(new Runnable() { // instancia da Thread + classe interna anï¿½nima
			@Override
			public void run() {
				gameloop(); // inicia o gameloop
				
			}
			
		}).start(); // dispara a Thread
		
	}
	
	// GAMELOOP -------------------------------
		public void gameloop() {
			while(true) { // repetiÃ§Ã£o intermitente do gameloop
				
				handlerEvents();
				update();
				render();
				
				try {
					Thread.sleep(17);
				}catch (Exception e) {
					e.printStackTrace();
					e.getMessage();
					
				}
			}
		}
	// ---------------------------------------
		public void handlerEvents() {
			barraEsquerda.setVelY(0);
			barraDireita.setVelY(0);
			
			bolaImg = bola.getImageBola();
			esquerdaImg = barraEsquerda.getImageEsquerda();
			direitaImg = barraDireita.getImageDireita();
			
			if(k_cima == true) {
				barraDireita.setVelY(-5);
				
			}else if(k_baixo == true) {
				barraDireita.setVelY(5);
				
			}
			
			
			if(k_w == true) {
				barraEsquerda.setVelY(-5);
					
			}else if(k_s == true) {
				barraEsquerda.setVelY(5);
				
			}
		}
		
		public void update() {
			bola.setPosX(bola.getPosX() + bola.getVelX());
			bola.setPosY(bola.getPosY() + bola.getVelY());
			barraEsquerda.setPosY(barraEsquerda.getPosY() + barraEsquerda.getVelY());
			barraDireita.setPosY(barraDireita.getPosY() + barraDireita.getVelY());
			
			testeColisoes();
		}
		
		public void render() {
			repaint();
			
		}
		
		// OUTROS METODOS -------------------------
		public void testeColisoes() {
			// Movimentos da bola
			
			/** Essa seção do código cria retangulos com as mesmas dimensões e seguindo as posições dos sprites,
			 * o metodo intersects faz todos os calculos necessarios e detecta se houve colisão entre 
			 * esses retangulos
			 * */
			
			//ball = new Rectangle(bola.posX, bola.posY, bola.raio * 2, bola.raio * 2);
			p1 = new Rectangle(barraEsquerda.getPosX(), barraEsquerda.getPosY(), 
					barraEsquerda.getLarguraBarra(), barraEsquerda.getAlturaBarra());
			p2 = new Rectangle(barraDireita.getPosX(), barraDireita.getPosY(), 
					barraDireita.getLarguraBarra(), barraDireita.getAlturaBarra());
						
						if (p1.intersects(bola.getPosX(), bola.getPosY(), bola.getRaio() * 2, bola.getRaio() * 2) || 
								p2.intersects(bola.getPosX(), bola.getPosY(), bola.getRaio() * 2, bola.getRaio() * 2)) {
							bola.setVelX(bola.getVelX() * -1);
							
						}
						
						if (bola.getPosY() + (bola.getRaio() * 2) >= Principal.ALTURA_TELA || bola.getPosY() <=0) {
							bola.setVelY(bola.getVelY() * -1);
							
						}
			
			// Movimentos das barras
			
			if (barraEsquerda.getPosY() + 128 >= Principal.ALTURA_TELA || barraEsquerda.getPosY() <= 0) {
				barraEsquerda.setPosY(barraEsquerda.getPosY() - barraEsquerda.getVelY());
				
			}
			
			if (barraDireita.getPosY() + 128 >= Principal.ALTURA_TELA || barraDireita.getPosY() <= 0) {
				barraDireita.setPosY(barraDireita.getPosY() - barraDireita.getVelY());
				
			}
			
			// Score do jogo
			
			if (bola.getPosX() + (bola.getRaio() * 2) > Principal.LARGURA_TELA) { // Ponto para a esquerda
				scoreEsquerda ++;
				restartGame();
				
			}
			
			if (bola.getPosX() < 0) { // Ponto para a direita
				scoreDireita ++;
				restartGame();
				
			}			
			
		}
		
		public void restartGame() {
			bola.setPosX(Principal.LARGURA_TELA/2 - bola.getRaio());
			bola.setPosY(Principal.ALTURA_TELA/2 - bola.getRaio());
			
		}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// setBackground(Color.LIGHT_GRAY);
		Image background = new ImageIcon(getClass().getResource("sprites/fundo.png")).getImage();
		g.drawImage(background, 0, 0, this);
		
		//Placar
		Font fonte = new Font("Dialog", Font.BOLD, 24);
		g.setFont(fonte);
		g.drawString("Player 1: " + scoreEsquerda, Principal.LARGURA_TELA * 1/4 - g.getFontMetrics()
				.stringWidth("Player 1: " + scoreEsquerda) /2 , g.getFontMetrics(fonte).getHeight());
		g.drawString("Player 2: " + scoreDireita, Principal.LARGURA_TELA * 3/4 - g.getFontMetrics()
				.stringWidth("Player 2: " + scoreDireita) /2 , g.getFontMetrics(fonte).getHeight());
		
		g.drawImage(bolaImg, bola.getPosX(), bola.getPosY(), null);
		g.drawImage(esquerdaImg, barraEsquerda.getPosX(), barraEsquerda.getPosY(), null);
		g.drawImage(direitaImg, barraDireita.getPosX(), barraDireita.getPosY(), null);
		
	}
	

}
