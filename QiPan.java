package chess_last;
import java.awt.*;import java.awt.event.*;
import javax.swing.*;import javax.swing.event.*;
import java.util.*;
public class QiPan extends JPanel implements MouseListener{
	private int width;											//��������֮��ľ���
	boolean focus=false;
	int jiang1_i=4;												//"��"��i����
	int jiang1_j=0;												//"��"��j����
	int jiang2_i=4;												//"��"��i����
	int jiang2_j=9;												//"��"��j����
	int startI=-1;												//���ӵĿ�ʼλ��
	int startJ=-1;
	int endI=-1;												//���ӵ���ֹλ��
	int endJ=-1;
	public QiZi qiZi[][];
	XiangQi xq=null;
	GuiZe guiZe;
	
	public QiPan(QiZi qiZi[][],int width,XiangQi xq){
		this.xq=xq;
		this.qiZi=qiZi;
		this.width=width;
		guiZe=new GuiZe(qiZi);
		this.addMouseListener(this);							//Ϊ�����������¼�������
		this.setBounds(0,0,700,700);							//�������̵Ĵ�С
		this.setLayout(null);									//��Ϊ�ղ���
	}
	
	public void paint(Graphics g1){
		
		Graphics2D g=(Graphics2D)g1;						
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		                   RenderingHints.VALUE_ANTIALIAS_ON);	//�����������
		Color c=g.getColor();									//��û�����ɫ
		g.setColor(XiangQi.bgColor);							//��������Ϊ����ɫ
		g.fill3DRect(60,30,580,630,false);						//����һ����������
		g.setColor(Color.black);								//������ɫ��Ϊ��
		
		for(int i=80;i<=620;i=i+60){							//���������еĺ��ߣ�һ��60
			g.drawLine(110,i,590,i);
		}
		g.drawLine(110,80,110,620);								//���������
		g.drawLine(590,80,590,620);								//�����ұ���
		
		for(int i=170;i<=530;i=i+60){							//�����м������
			g.drawLine(i,80,i,320);
			g.drawLine(i,380,i,620);
		}
		
		g.drawLine(290,80,410,200);								//�������ߵ�б��
		g.drawLine(290,200,410,80);
		g.drawLine(290,500,410,620);
		g.drawLine(290,620,410,500);
		
		this.smallLine(g,1,2);									//��־��
		this.smallLine(g,7,2);							
		this.smallLine(g,0,3);							
		this.smallLine(g,2,3);							
		this.smallLine(g,4,3);							
		this.smallLine(g,6,3);			
		this.smallLine(g,8,3);		
		this.smallLine(g,0,6);		
		this.smallLine(g,2,6);	
		this.smallLine(g,4,6);
		this.smallLine(g,6,6);
		this.smallLine(g,8,6);
		this.smallLine(g,1,7);
		this.smallLine(g,7,7);
		g.setColor(Color.black);
		Font font1=new Font("����",Font.BOLD,50);				//��������
		g.setFont(font1);
		g.drawString("�� ��",170,365);							//���Ƴ��Ӻ���
		g.drawString("�h ��",400,365);
		Font font=new Font("����",Font.BOLD,30);
		g.setFont(font);
		
		for(int i=0;i<9;i++){
			for(int j=0;j<10;j++){								//��������
				if(qiZi[i][j]!=null){
					if(this.qiZi[i][j].getFocus()!=false){		//�Ƿ�ѡ��
						g.setColor(XiangQi.focusbg);			//ѡ�к�ı���ɫ
						g.fillOval(110+i*60-25,80+j*60-25,50,50);//���Ƹ�����
						g.setColor(XiangQi.focuschar);			//�ַ�����ɫ
					}
					else{
						g.fillOval(110+i*60-25,80+j*60-25,50,50);//���Ƹ�����
						g.setColor(qiZi[i][j].getColor());		//���û�����ɫ
					}
				    g.drawString(qiZi[i][j].getName(),110+i*60-15,80+j*60+10);
				    g.setColor(Color.black);					//��Ϊ��ɫ
				}
			}
		}
		g.setColor(c);											//��ԭ������ɫ
	}
	
	public void mouseClicked(MouseEvent e){
			int i=-1,j=-1;
			int[] pos=getPos(e);
			i=pos[0];
			j=pos[1];
			if(i>=0&&i<=8&&j>=0&&j<=9){							//��������̷�Χ��
				if(focus==false){								//�������û��ѡ������
					this.noFocus(i,j);
				}
				else{											//�����ǰѡ�й�����
					if(qiZi[i][j]!=null){						//����ô�������
						if(qiZi[i][j].getColor()==qiZi[startI][startJ].getColor())
						{										//������Լ�������
							qiZi[startI][startJ].setFocus(false);
							qiZi[i][j].setFocus(true);			//����ѡ�ж���
							startI=i;startJ=j;					//�����޸�
						}
						else{									//����ǶԷ�����
							endI=i;								//����õ�
							endJ=j;
							String name=qiZi[startI][startJ].getName();//��ø����ӵ�����

							boolean canMove=guiZe.canMove(startI,startJ,endI,endJ,name);
							if(canMove)
							{
									if (xq.colorh == 1)
										xq.colorh = 0;
									else
										xq.colorh = 1;
									
								    if(qiZi[endI][endJ].getName().equals("��")||
								       qiZi[endI][endJ].getName().equals("��"))
								    {							//����յ㴦�ǶԷ���"��"
								    	this.success();
								    }
								    else{						//����յ㲻�ǶԷ���"��"
								    	this.noJiang();
								    }
								}
								
							}
						}
					
					else{													//���û������
						endI=i;
						endJ=j;												//�����յ�
						String name=qiZi[startI][startJ].getName();			//��ø��������
						boolean canMove=guiZe.canMove(startI,startJ,endI,endJ,name);//�ж��Ƿ����
						if(canMove){										//��������ƶ�
							if (xq.colorh == 1)
								xq.colorh = 0;
							else
								xq.colorh = 1;
							
							this.noQiZi();
						}
					}
				}
			}
			this.xq.repaint();
		}
	
	
	public int[] getPos(MouseEvent e){
		int[] pos=new int[2];
		pos[0]=-1;
		pos[1]=-1;
		Point p=e.getPoint();												//����¼������������
		double x=p.getX();
		double y=p.getY();
		if(Math.abs((x-110)/1%60)<=25){										//��ö�Ӧ������x�±��λ��
			pos[0]=Math.round((float)(x-110))/60;
		}
		else if(Math.abs((x-110)/1%60)>=35){
			pos[0]=Math.round((float)(x-110))/60+1;
		}
		if(Math.abs((y-80)/1%60)<=25){										//��ö�Ӧ������y�±��λ��
			pos[1]=Math.round((float)(y-80))/60;
		}
		else if(Math.abs((y-80)/1%60)>=35){
			pos[1]=Math.round((float)(y-80))/60+1;
		}
		return pos;
	}
	
	public void noFocus(int i,int j){
		if(this.qiZi[i][j]!=null)											//�����λ��������
		{
			if(this.xq.colorh==0)											//����Ǻ췽
			{
				if(this.qiZi[i][j].getColor().equals(XiangQi.color1))		//��������Ǻ�ɫ
				{
					this.qiZi[i][j].setFocus(true);							//����������Ϊѡ��״̬
					focus=true;		
					startI=i;	
					startJ=j;
				}
			}
			else															//����ǰ׷�
			{
				if(this.qiZi[i][j].getColor().equals(XiangQi.color2))		//����������ǰ�ɫ
				{
					this.qiZi[i][j].setFocus(true);							//����������Ϊѡ��״̬
					focus=true;					
					startI=i;			
		            startJ=j;
				}
			}
		}
	}
	
	
	public void success(){
		qiZi[endI][endJ]=qiZi[startI][startJ];
		qiZi[startI][startJ]=null;
		this.xq.repaint();
		JOptionPane.showMessageDialog(this.xq,"��ϲ��������ʤ��","��ʾ",
		                JOptionPane.INFORMATION_MESSAGE);
		this.xq.colorh=0;
		this.xq.next();
	
		startI=-1;
		startJ=-1;
		endI=-1;
		endJ=-1;
		jiang1_i=4;
		jiang1_j=0;
		jiang2_i=4;
		jiang2_j=9;
		focus=false;
	}
	
	public void noJiang(){
		qiZi[endI][endJ]=qiZi[startI][startJ];
		qiZi[startI][startJ]=null;									//����
		qiZi[endI][endJ].setFocus(false);							//��������Ϊ��ѡ��״̬
		this.xq.repaint();
		
		if(qiZi[endI][endJ].getName().equals("��")){					//����ƶ�����"��"
			jiang1_i=endI;											//����"��"��λ������
			jiang1_j=endJ;
		}
		
		else if(qiZi[endI][endJ].getName().equals("��")){
			jiang2_i=endI;				
			jiang2_j=endJ;
		}
		
		if(jiang1_i==jiang2_i){										//���"��"��"��"��һ��������
			int count=0;
			for(int jiang_j=jiang1_j+1;jiang_j<jiang2_j;jiang_j++){	//������������
				if(qiZi[jiang1_i][jiang_j]!=null){
					count++;break;
				}
			}
			
			if(count==0){											//������������ս�
		    	JOptionPane.showMessageDialog(this.xq,"�ս���������ʧ���ˣ�����","��ʾ",
		    	            JOptionPane.INFORMATION_MESSAGE);//����ʧ����Ϣ
				this.xq.colorh=0;
				this.xq.next();
				
				jiang1_i=4;
				jiang1_j=0;
				jiang2_i=4;
				jiang2_j=9;
			}
		}
		startI=-1;
		startJ=-1;
		endI=-1;
		endJ=-1;
		focus=false;
	}
	
	public void noQiZi(){
		
			qiZi[endI][endJ]=qiZi[startI][startJ];
			qiZi[startI][startJ]=null;								//����
			qiZi[endI][endJ].setFocus(false);						//��������Ϊ��ѡ��״̬
			this.xq.repaint();
			
			if(qiZi[endI][endJ].getName().equals("��")){				//����ƶ�����"��"
				jiang1_i=endI;										//����"��"��λ������
				jiang1_j=endJ;
			}
			else if(qiZi[endI][endJ].getName().equals("��")){		//����ƶ�����"��"
				jiang2_i=endI;										//����"��"��λ������
				jiang2_j=endJ;
			}
			if(jiang1_i==jiang2_i)									//���"��"��"��"��һ��������
			{
				int count=0;
				for(int jiang_j=jiang1_j+1;jiang_j<jiang2_j;jiang_j++){//������������
					if(qiZi[jiang1_i][jiang_j]!=null){
						count++;break;
					}
				}
				if(count==0){												//������������ս�
			    	JOptionPane.showMessageDialog(this.xq,"�ս���������ʧ���ˣ�����","��ʾ",
			    	            JOptionPane.INFORMATION_MESSAGE);
			    	
					this.xq.colorh=0;
					this.xq.next();
					
					jiang1_i=4;
					jiang1_j=0;
					jiang2_i=4;
					jiang2_j=9;
				}
			}
			startI=-1;
			startJ=-1;
			endI=-1;
			endJ=-1;
			focus=false;
		
	
	}
	
	public void mousePressed(MouseEvent e){}		//���û�������갴ťʱ����
	public void mouseReleased(MouseEvent e){}		//�û��ɿ���갴ťʱ����
	
	public void mouseEntered(MouseEvent e){
		JOptionPane.showMessageDialog(this,"��ӭ����","��ӭ�����й����塤����",
                JOptionPane.INFORMATION_MESSAGE);
	}		//������뿪��ǰ����������������������ʱ�����¼�
	
	public void mouseExited(MouseEvent e){}			//������뿪���ʱ����
	
	
	public void smallLine(Graphics2D g,int i,int j){
		int x=110+60*i;														//��������
		int y=80+60*j;
		if(i>0){															//�������Ϸ��ı�־
			g.drawLine(x-3,y-3,x-20,y-3);g.drawLine(x-3,y-3,x-3,y-20);
		}
		if(i<8){															//�������Ϸ��ı�־
			g.drawLine(x+3,y-3,x+20,y-3);g.drawLine(x+3,y-3,x+3,y-20);
		}
		if(i>0){															//�������·��ı�־
			g.drawLine(x-3,y+3,x-20,y+3);g.drawLine(x-3,y+3,x-3,y+20);
		}
		if(i<8){															//�������·��ı�־
			g.drawLine(x+3,y+3,x+20,y+3);g.drawLine(x+3,y+3,x+3,y+20);
		}
	}
}
