package chess_last;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.net.*;
public class XiangQi extends JFrame
{
	public static final Color bgColor=new Color(245,250,160);	//棋盘的背景色
	public static final Color focusbg=new Color(242,242,242);	//棋子选中后的背景色
	public static final Color focuschar=new Color(96,95,91);	//棋子选中后的字符颜色
	public static final Color color1=new Color(249,183,173);	//红方的颜色
	public static final Color color2=Color.white;				//白方的颜色
	
	int width=60;												//设置棋盘两线之间的距离
	QiZi[][] qiZi=new QiZi[9][10];								
	QiPan jpz=new QiPan(qiZi,width,this);						
	
	int colorh=0;												//0 代表红棋，1代表白棋
	
	public XiangQi()
	{
		this.initialComponent();								//初始化控件
		this.initialQiZi();										//初始化棋子
		this.initialFrame();									//初始化窗体
	}
	public void initialComponent()
	{
		jpz.setLayout(null);									//将棋盘设为空布局
		jpz.setBounds(0,0,700,700);								//设置大小
	}
	
	
	public void initialQiZi()
	{
		qiZi[0][0]=new QiZi(color1,"車",0,0);
		qiZi[1][0]=new QiZi(color1,"馬",1,0);
		qiZi[2][0]=new QiZi(color1,"相",2,0);
		qiZi[3][0]=new QiZi(color1,"仕",3,0);
		qiZi[4][0]=new QiZi(color1,"帥",4,0);
		qiZi[5][0]=new QiZi(color1,"仕",5,0);
		qiZi[6][0]=new QiZi(color1,"相",6,0);
		qiZi[7][0]=new QiZi(color1,"馬",7,0);
		qiZi[8][0]=new QiZi(color1,"車",8,0);
		qiZi[1][2]=new QiZi(color1,"砲",1,2);
		qiZi[7][2]=new QiZi(color1,"砲",7,2);
		qiZi[0][3]=new QiZi(color1,"兵",0,3);
		qiZi[2][3]=new QiZi(color1,"兵",2,3);
		qiZi[4][3]=new QiZi(color1,"兵",4,3);
		qiZi[6][3]=new QiZi(color1,"兵",6,3);
		qiZi[8][3]=new QiZi(color1,"兵",8,3);
		qiZi[0][9]=new QiZi(color2,"車",0,9);
		qiZi[1][9]=new QiZi(color2,"馬",1,9);
		qiZi[2][9]=new QiZi(color2,"象",2,9);
		qiZi[3][9]=new QiZi(color2,"士",3,9);
		qiZi[4][9]=new QiZi(color2,"將",4,9);
		qiZi[5][9]=new QiZi(color2,"士",5,9);
		qiZi[6][9]=new QiZi(color2,"象",6,9);
		qiZi[7][9]=new QiZi(color2,"馬",7,9);
		qiZi[8][9]=new QiZi(color2,"車",8,9);
		qiZi[1][7]=new QiZi(color2,"炮",1,7);
		qiZi[7][7]=new QiZi(color2,"炮",7,7);
		qiZi[0][6]=new QiZi(color2,"卒",0,6);
		qiZi[2][6]=new QiZi(color2,"卒",2,6);
		qiZi[4][6]=new QiZi(color2,"卒",4,6);
		qiZi[6][6]=new QiZi(color2,"卒",6,6);
		qiZi[8][6]=new QiZi(color2,"卒",8,6);
	}
	public void initialFrame()
	{
		this.setTitle("中国象棋");	
		this.add(this.jpz);
		this.setBounds(500,100,730,730);
		this.setVisible(true);
		JOptionPane.showMessageDialog(this,"红方先行","欢迎来到中国象棋···",
                JOptionPane.INFORMATION_MESSAGE);
	}


	public void next(){
		for(int i=0;i<9;i++){								//将棋子数组都置空
			for(int j=0;j<10;j++){
				this.qiZi[i][j]=null;
			}
		}
		colorh = 0;
		this.initialQiZi();									//重新初始化棋子 
		this.repaint();										//重绘
	}
	public static void main(String args[])
	{
		new XiangQi();
	}
	
	
}