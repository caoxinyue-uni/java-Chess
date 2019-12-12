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
	public static final Color bgColor=new Color(245,250,160);	//���̵ı���ɫ
	public static final Color focusbg=new Color(242,242,242);	//����ѡ�к�ı���ɫ
	public static final Color focuschar=new Color(96,95,91);	//����ѡ�к���ַ���ɫ
	public static final Color color1=new Color(249,183,173);	//�췽����ɫ
	public static final Color color2=Color.white;				//�׷�����ɫ
	
	int width=60;												//������������֮��ľ���
	QiZi[][] qiZi=new QiZi[9][10];								
	QiPan jpz=new QiPan(qiZi,width,this);						
	
	int colorh=0;												//0 ������壬1�������
	
	public XiangQi()
	{
		this.initialComponent();								//��ʼ���ؼ�
		this.initialQiZi();										//��ʼ������
		this.initialFrame();									//��ʼ������
	}
	public void initialComponent()
	{
		jpz.setLayout(null);									//��������Ϊ�ղ���
		jpz.setBounds(0,0,700,700);								//���ô�С
	}
	
	
	public void initialQiZi()
	{
		qiZi[0][0]=new QiZi(color1,"܇",0,0);
		qiZi[1][0]=new QiZi(color1,"�R",1,0);
		qiZi[2][0]=new QiZi(color1,"��",2,0);
		qiZi[3][0]=new QiZi(color1,"��",3,0);
		qiZi[4][0]=new QiZi(color1,"��",4,0);
		qiZi[5][0]=new QiZi(color1,"��",5,0);
		qiZi[6][0]=new QiZi(color1,"��",6,0);
		qiZi[7][0]=new QiZi(color1,"�R",7,0);
		qiZi[8][0]=new QiZi(color1,"܇",8,0);
		qiZi[1][2]=new QiZi(color1,"�h",1,2);
		qiZi[7][2]=new QiZi(color1,"�h",7,2);
		qiZi[0][3]=new QiZi(color1,"��",0,3);
		qiZi[2][3]=new QiZi(color1,"��",2,3);
		qiZi[4][3]=new QiZi(color1,"��",4,3);
		qiZi[6][3]=new QiZi(color1,"��",6,3);
		qiZi[8][3]=new QiZi(color1,"��",8,3);
		qiZi[0][9]=new QiZi(color2,"܇",0,9);
		qiZi[1][9]=new QiZi(color2,"�R",1,9);
		qiZi[2][9]=new QiZi(color2,"��",2,9);
		qiZi[3][9]=new QiZi(color2,"ʿ",3,9);
		qiZi[4][9]=new QiZi(color2,"��",4,9);
		qiZi[5][9]=new QiZi(color2,"ʿ",5,9);
		qiZi[6][9]=new QiZi(color2,"��",6,9);
		qiZi[7][9]=new QiZi(color2,"�R",7,9);
		qiZi[8][9]=new QiZi(color2,"܇",8,9);
		qiZi[1][7]=new QiZi(color2,"��",1,7);
		qiZi[7][7]=new QiZi(color2,"��",7,7);
		qiZi[0][6]=new QiZi(color2,"��",0,6);
		qiZi[2][6]=new QiZi(color2,"��",2,6);
		qiZi[4][6]=new QiZi(color2,"��",4,6);
		qiZi[6][6]=new QiZi(color2,"��",6,6);
		qiZi[8][6]=new QiZi(color2,"��",8,6);
	}
	public void initialFrame()
	{
		this.setTitle("�й�����");	
		this.add(this.jpz);
		this.setBounds(500,100,730,730);
		this.setVisible(true);
		JOptionPane.showMessageDialog(this,"�췽����","��ӭ�����й����塤����",
                JOptionPane.INFORMATION_MESSAGE);
	}


	public void next(){
		for(int i=0;i<9;i++){								//���������鶼�ÿ�
			for(int j=0;j<10;j++){
				this.qiZi[i][j]=null;
			}
		}
		colorh = 0;
		this.initialQiZi();									//���³�ʼ������ 
		this.repaint();										//�ػ�
	}
	public static void main(String args[])
	{
		new XiangQi();
	}
	
	
}