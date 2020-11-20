import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import java.io.*;

public class AI extends MIDlet
{
	Display display;
	MainCanvas mc;
	public AI(){
		display=Display.getDisplay(this);
		mc=new MainCanvas();
		display.setCurrent(mc);
	}
	public void startApp(){
	}
	public void destroyApp(boolean unc){
	}
	public void pauseApp(){
	}
}
class MainCanvas extends Canvas
{

	/*
	变量的声明
	语法：数据类型 变量名称（标识符）;
	*/
	int x,y;
	int leftFlag,rightFlag,upFlag,downFlag;
	Image img1,img2,img3,img4,currentImg;
	public MainCanvas(){
		try
		{   //左
			img1=Image.createImage("/sayo10.png");
			img2=Image.createImage("/sayo12.png");
			img3=Image.createImage("/sayo06.png");
			img4=Image.createImage("/sayo04.png");
			currentImg=img1;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void paint(Graphics g){
		g.setColor(220,20,60);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,110,130,0);//120：X坐标、100：Y坐标
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		/*
		action的值：UP、DOWN、LEFT、RIGHT
		*/
		if(action==LEFT){

			currentImg=img2;

			repaint();
		}

		if(action==RIGHT){

			currentImg=img3;

			repaint();
		}

		if(action==UP){

			currentImg=img4;

			repaint();
		}

		if(action==DOWN){

			currentImg=img1;

			repaint();
		}
	}
}