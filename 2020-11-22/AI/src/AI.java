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
class MainCanvas extends Canvas implements Runnable
{

	/*
	变量的声明
	语法：数据类型 变量名称（标识符）;
	*/
	Thread thread;
	int heroX,heroY,bossX,bossY;
	int leftFlag,rightFlag,upFlag,downFlag;
	Image heroImg[][]=new Image[4][3];
	Image bossImg;
	//4个方向、每个方向3张图片，0表示left、1表示right、2表示up、3表示down
	/*
	规则二维数组
	*/

	Image currentImg;

	public MainCanvas(){
		try
		{
			for(int i=0;i<heroImg.length;i++){
				for(int j=0;j<heroImg[i].length;j++){
					heroImg[i][j]=Image.createImage("/sayo"+i+j+".png");
				}
			}
			bossImg=Image.createImage("/zuzu000.png");
			currentImg=heroImg[3][1];
			heroX=120;
			heroY=100;

			bossX=0;
			bossY=0;

			leftFlag=1;
			rightFlag=1;
			upFlag=1;
			downFlag=1;
			
			thread=new Thread(this);
			thread.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	/*
	在多线程里写一个死循环，不停的比较boss和hero的坐标
	*/
	public void run(){
		while(true){
			try
			{
				Thread.sleep(200);//FPS：屏幕刷新率
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
			if(bossX<heroX){
				bossX++;
			}
			else{
				bossX--;
			}

			if(bossY<heroY){
				bossY++;
			}else{
				bossY--;
			}
			repaint();
		}
	}

	public void paint(Graphics g){
		g.setColor(220,20,60);
		g.fillRect(0,0,getWidth(),getHeight());
		g.drawImage(currentImg,heroX,heroY,0);//120：X坐标、100：Y坐标
		g.drawImage(bossImg,bossX,bossY,0);
	}
	public void keyPressed(int keyCode){
		int action=getGameAction(keyCode);
		/*
		action的值：UP、DOWN、LEFT、RIGHT
		*/


		if(action==LEFT){
		if (leftFlag==1){
		currentImg=heroImg[0][0];
		leftFlag++;
		}
		else if (leftFlag==2){
		currentImg=heroImg[0][2];
		leftFlag=1;
		}
			heroX=heroX-1;
			
		}


		if(action==RIGHT){
		if (rightFlag==1){
		currentImg=heroImg[1][0];
		rightFlag++;
		}
		else if (rightFlag==2){
		currentImg=heroImg[1][2];
		rightFlag=1;
		}
			heroX=heroX+1;
			
		}


		if(action==UP){
		if (upFlag==1){
		currentImg=heroImg[2][0];
		upFlag++;
		}
		else if (upFlag==2){
		currentImg=heroImg[2][2];
		upFlag=1;
		}
			heroY=heroY+1;
			
		}


		if(action==DOWN){
		if (downFlag==1){
		currentImg=heroImg[3][0];
		downFlag++;
		}
		else if (downFlag==2){
		currentImg=heroImg[3][2];
		downFlag=1;
		}
			heroY=heroY+1;
			
		}
	}
}