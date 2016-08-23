package com_mec_bm;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.table.TableColumn;

/**
 * Class <code>{baoming }</code>{微易码学员管理报名系统}
 * 
 * @author 		晁倩
 * @version 	2015/08/17
 * @see    		java.lang.Class
 * @since  		JDK{jdk1.7.0_71}
 */
public class baoming 
{  
	private JFrame jfrmMain;
	private Container cont;
	
	private JLabel jlblTopic;
	
	private DefaultListModel<myData> dlmCourseList;
	private JList<myData> jlstCourseList;
	private JScrollPane jscCourseList;
	
		
	private JComboBox jcbShow;
	private static final String msg[]={"显示在读学生信息","显示全部学生信息"};

	private JPanel jplCheck;
	private JTextField jtxtCheckWay;
	private JButton jbtCheck;
	private JButton jbtReturn;
	
	private JLabel jlblStudentTable;
	private JTable jtbStudentTable;
	private JScrollPane jscpStudentTable;

	private JLabel jlblStudentCount;
	
	private JLabel jlblStudentNumber;
	private JLabel jlblNumber;
	
	private JLabel jlblName;
	private JTextField jtxtName;
	
	private JLabel jlblId;
	private JTextField jtxtId;
	
	private JLabel jlblSex;
	
	private JRadioButton jrdbSex[];
	private ButtonGroup bgSex;
	
	private JLabel jlblNation;
	private JComboBox jcmbNation;
	
	private JLabel jlblNative;
	private JComboBox jcmbNative;
	
	private JLabel jlblPhone;
	private JTextField jtxtTelePhone;
	
	private JLabel jlblStudayCourse;
	private JComboBox jcmbStudayCourse;
	
	private JLabel jlblUniversity;
	private JComboBox jcmbUniversity;
	
	private JLabel jlblDepartment;
	private JComboBox jcmbDepartment;
	
	private JLabel jlblMajor;
	private JComboBox jcmbMajor;
	
	private JLabel jlblAgent;
	private JComboBox jcmbAgent;
	
	private JLabel jlblStudentState;
	
	private JRadioButton jrdbStudentState[];
	private ButtonGroup bgStudentState;
	
	private JButton jbtApply;
	private JButton jbtModify;
	private JButton jbtDelete;
	private JButton jbtExit;
	
	private JLabel jlblPhotoTopic;
	private JLabel jlblPhoto;
	
	private static final int SCAN=0;//浏览状态
	private static final int EDIT=1;

	private static int LASTACTION;
	
	private String oldName;
	private String oldeId;
	private String oldTelePhone;
	private String oldStudentId;
	private String oldNation;
	private String oldNative;
	private String oldCourse;
	private String oldUniversity;
	private String oldDepartment;
	private String oldMajor;
	private String oldAgent;
	
	private int oldSex;
	private int oldState;
	
	private static boolean flag = true;
	
	 /**
	  * 初始化窗口
	  */
	 private void initFrame()
	 {
		Font normalFont=new Font("宋体",Font.PLAIN,16);
		Font normalFontjbt=new Font("宋体",Font.PLAIN,15);
		
		this.jfrmMain=new JFrame("西安微易码科技—报名信息管理");
		this.jfrmMain.setSize(12165/15, 9575/15);
		this.jfrmMain.setLocation(300, 40);
		cont=this.jfrmMain.getContentPane();
		cont.setLayout(null);
	    //标题
		this.jlblTopic=new JLabel("报名信息管理");
		this.jlblTopic.setBounds(4770/15, 0, 3420/15, 555/15);
		this.jlblTopic.setFont(new Font("隶书",Font.PLAIN,26));
		this.jlblTopic.setForeground(Color.BLUE);
		cont.add(jlblTopic);
		//显示下拉列表
		this.jcbShow=new JComboBox(msg);
		this.jcbShow.setBounds(480/15, 600/15, 3975/15, 360/15);
		this.jcbShow.setFont(normalFont);
		cont.add(jcbShow);
		
		
		this.dlmCourseList=new DefaultListModel<myData> ();
		this.jlstCourseList=new JList<myData> (dlmCourseList);
		this.jscCourseList=new JScrollPane (jlstCourseList);
		this.jscCourseList.setBounds(480/15,1080/15, 3975/15, 1980/15);
		cont.add(jscCourseList);
		
		
		this.jtxtCheckWay=new JTextField();
		this.jtxtCheckWay.setBounds(44/15, 360/15, 2895/15, 375/15);
		this.jtxtCheckWay.setFont(normalFont);
	
		
		//查询方式
		jplCheck = new JPanel();
		jplCheck.setBounds(480/15,3120/15, 3015/15, 855/15);
		jplCheck.setBorder(new TitledBorder(null,"按姓名或学号查询"));
		jplCheck.setLayout(null);
		jplCheck.add(this.jtxtCheckWay);//TODO
		cont.add(jplCheck);

	
		this.jbtCheck=new JButton("查询");
		this.jbtCheck.setBounds(3550/15, 3240/15, 900/15, 400/15);
		this.jbtCheck.setFont(new Font("宋体",Font.PLAIN,12));
		cont.add(jbtCheck);
		
		this.jbtReturn=new JButton("恢复");
		this.jbtReturn.setBounds(3550/15, 3720/15,  900/15, 400/15);
		this.jbtReturn.setFont(new Font("宋体",Font.PLAIN,12));
		cont.add(jbtReturn);
		
	
		jlblStudentTable=new JLabel("学员信息");
		jlblStudentTable.setBounds(480/15,4050/15,1335/15, 255/15);
		jlblStudentTable.setFont(normalFont);
		cont.add(jlblStudentTable);

		
		this.jlblStudentCount=new JLabel("共有xx名学生");
		this.jlblStudentCount.setBounds(480/15, 8400/15, 2160/15, 240/15);
		this.jlblStudentCount.setFont(normalFont);
		cont.add(jlblStudentCount);
		
		jlblPhoto = new JLabel("");
		ImageIcon img = new ImageIcon(
				"E:\\程序\\eclipse\\程序练习\\8.jpg");
		img.setImage(img.getImage().getScaledInstance(2175/15, 2775/15, Image.SCALE_DEFAULT));
		jlblPhoto.setIcon(img);
		jlblPhoto.setBounds(9240/15, 960/15, 2175/15, 2775/15);
		cont.add(jlblPhoto);

		
		
		this.jlblPhotoTopic=new JLabel("照片");
		this.jlblPhotoTopic.setBounds(10080/15, 3840/15, 480/15, 240/15);
		this.jlblPhotoTopic.setFont(normalFont);
		cont.add(jlblPhotoTopic);
		
		this.jlblStudentNumber=new JLabel("学员编号：");
		this.jlblStudentNumber.setBounds(4680/15, 720/15, 1250/15, 240/15);
		this.jlblStudentNumber.setFont(normalFont);
		cont.add(jlblStudentNumber);
		
		this.jlblNumber=new JLabel("20150101001");
		this.jlblNumber.setBounds(5880/15, 720/15, 2760/15, 240/15);
		this.jlblNumber.setFont(normalFont);
		cont.add(jlblNumber);
		
		this.jlblName=new JLabel("姓    名：");
		this.jlblName.setBounds(4680/15, 1334/15, 1250/15, 240/15);
		this.jlblName.setFont(normalFont);
		cont.add(jlblName);
		
		this.jtxtName=new JTextField("");
		this.jtxtName.setBounds(5880/15, 1266/15, 2892/15, 396/15);
		this.jtxtName.setFont(normalFont);
		cont.add(jtxtName);
		
		this.jlblId=new JLabel("身份证号：");
		this.jlblId.setBounds(4680/15, 1968/15, 1250/15, 240/15);
		this.jlblId.setFont(normalFont);
		cont.add(jlblId);
		
		this.jtxtId=new JTextField("");
		this.jtxtId.setBounds(5880/15, 1902/15, 2892/15, 396/15);
		this.jtxtId.setFont(normalFont);
		cont.add(jtxtId);
				
		this.jlblSex=new JLabel("性    别：");
		this.jlblSex.setBounds(4680/15, 2592/15, 1250/15, 240/15);
		this.jlblSex.setFont(normalFont);
		cont.add(jlblSex);
		
		 jrdbSex = new JRadioButton[2];
		 jrdbSex[0]=new JRadioButton("男");
		 jrdbSex[0].setFont(normalFont);
		 jrdbSex[0].setBounds(6240/15,2532/15, 732/15, 360/15);
		 cont.add(jrdbSex[0]);
		   
		 jrdbSex[1]=new JRadioButton("女");
		 jrdbSex[1].setFont(normalFont);
		 jrdbSex[1].setBounds(7560/15, 2526/15, 732/15, 372/15);
		 cont.add(jrdbSex[1]);
		 
		 bgSex = new ButtonGroup();
		 bgSex.add(jrdbSex[0]);
		 bgSex.add(jrdbSex[1]);
		   
		 this.jlblNation=new JLabel("民    族：");
		 this.jlblNation.setBounds(4680/15, 3216/15, 1280/15, 240/15);
		 this.jlblNation.setFont(normalFont);
		 cont.add(jlblNation);
		 
		 this.jcmbNation=new JComboBox();
		 this.jcmbNation.setBounds(5880/15, 3156/15, 2892/15,360/15);
		 this.jcmbNation.setFont(normalFont);
		 cont.add(jcmbNation);
		 
		   
		 this.jlblNative=new JLabel("籍    贯：");
		 this.jlblNative.setBounds(4680/15, 3840/15, 1280/15, 240/15);
		 this.jlblNative.setFont(normalFont);
		 cont.add(jlblNative);
				 
		 this.jcmbNative=new JComboBox();
		 this.jcmbNative.setBounds(5880/15, 3780/15, 2892/15,360/15);
		 this.jcmbNative.setFont(normalFont);
		 cont.add(jcmbNative); 
		 
		 
		 this.jlblPhone=new JLabel("联系电话：");
		 this.jlblPhone.setBounds(6000/15, 4560/15, 1280/15, 240/15);
		 this.jlblPhone.setFont(normalFont);
		 cont.add(jlblPhone);
		 
		 this.jtxtTelePhone=new JTextField("");
		 this.jtxtTelePhone.setBounds(7320/15, 4494/15, 4095/15, 396/15);
		 this.jtxtTelePhone.setFont(normalFont);
		 cont.add(jtxtTelePhone);
		 
		   
		 this.jlblStudayCourse=new JLabel("学习课程：");
		 this.jlblStudayCourse.setBounds(6000/15, 5160/15, 1280/15, 240/15);
		 this.jlblStudayCourse.setFont(normalFont);
		 cont.add(jlblStudayCourse);
						 
		 this.jcmbStudayCourse=new JComboBox();
		 this.jcmbStudayCourse.setBounds(7320/15, 5052/15, 4095/15,360/15);
		 this.jcmbStudayCourse.setFont(normalFont);
		 cont.add(jcmbStudayCourse); 
		 
		 this.jlblUniversity=new JLabel("院    校：");
		 this.jlblUniversity.setBounds(6000/15, 5640/15, 1280/15, 240/15);
		 this.jlblUniversity.setFont(normalFont);
		 cont.add(jlblUniversity);
						 
		 this.jcmbUniversity=new JComboBox();
		 this.jcmbUniversity.setBounds(7320/15, 5592/15, 4095/15,360/15);
		 this.jcmbUniversity.setFont(normalFont);
		 cont.add(jcmbUniversity); 
		 
		 this.jlblDepartment=new JLabel("院    系：");
		 this.jlblDepartment.setBounds(6000/15, 6180/15, 1280/15, 240/15);
		 this.jlblDepartment.setFont(normalFont);
		 cont.add(jlblDepartment);
						 
		 this.jcmbDepartment=new JComboBox();
		 this.jcmbDepartment.setBounds(7320/15, 6132/15, 4095/15,360/15);
		 this.jcmbDepartment.setFont(normalFont);
		 cont.add(jcmbDepartment); 
		 
		 this.jlblMajor=new JLabel("专    业：");
		 this.jlblMajor.setBounds(6000/15, 6720/15, 1280/15, 240/15);
		 this.jlblMajor.setFont(normalFont);
		 cont.add(jlblMajor);
						 
		 this.jcmbMajor=new JComboBox();
		 this.jcmbMajor.setBounds(7320/15,6672/15, 4095/15,360/15);
		 this.jcmbMajor.setFont(normalFont);
		 cont.add(jcmbMajor); 
		 
		 this.jlblAgent=new JLabel("推 荐 人：");
		 this.jlblAgent.setBounds(6000/15, 7260/15, 1280/15, 240/15);
		 this.jlblAgent.setFont(normalFont);
		 cont.add(jlblAgent);
						 
		 this.jcmbAgent=new JComboBox();
		 this.jcmbAgent.setBounds(7320/15,7212/15, 4095/15,360/15);
		 this.jcmbAgent.setFont(normalFont);
		 cont.add(jcmbAgent); 
		 		 
		 this.jlblStudentState=new JLabel("状    态：");
		 this.jlblStudentState.setBounds(6000/15, 7800/15, 1280/15, 240/15);
		 this.jlblStudentState.setFont(normalFont);
		 cont.add(jlblStudentState);
						 
		 jrdbStudentState= new JRadioButton[2];
		 jrdbStudentState[0]=new JRadioButton("在读");
		 jrdbStudentState[0].setFont(normalFont);
		 jrdbStudentState[0].setBounds(8280/15,7734/15, 900/15, 372/15);
		 cont.add(jrdbStudentState[0]);
		   
		 jrdbStudentState[1]=new JRadioButton("毕业");
		 jrdbStudentState[1].setFont(normalFont);
		 jrdbStudentState[1].setBounds(9960/15, 7734/15, 900/15, 372/15);
		 cont.add(jrdbStudentState[1]);
		 
		 bgStudentState = new ButtonGroup();
		 bgStudentState.add(jrdbStudentState[0]);
		 bgStudentState.add(jrdbStudentState[1]);
		   
		 this.jbtApply=new JButton("报名");
		 this.jbtApply.setBounds(6000/15, 8280/15, 1095/15, 495/15);
		 this.jbtApply.setFont(normalFontjbt);
		 cont.add(jbtApply);

		 this.jbtModify=new JButton("修改");
		 this.jbtModify.setBounds(7080/15, 8280/15, 1095/15, 495/15);
		 this.jbtModify.setFont(normalFontjbt);
		 cont.add(jbtModify);

		 this.jbtDelete=new JButton("删除");
		 this.jbtDelete.setBounds(8160/15, 8280/15, 1095/15, 495/15);
		 this.jbtDelete.setFont(normalFontjbt);
		 cont.add(jbtDelete);
		 
		 this.jbtExit=new JButton("退出");
		 this.jbtExit.setBounds(10200/15, 8280/15, 1095/15, 495/15);
		 this.jbtExit.setFont(normalFontjbt);
		 cont.add(jbtExit);

		String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId AND StudentState=0";
		initStudentTable(SQLString1);
		
		
		this.jfrmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.jfrmMain.setVisible(true);
	}
	 
	 /**
	  * showMsg工具
	  * @param jfrm
	  * @param mes
	  */
		public static final void showMsg(JFrame jfrm, String mes)
		{
			JOptionPane.showMessageDialog(jfrm, mes, "系统温馨提示",JOptionPane.OK_OPTION);
		}
	 
	 /**
	  * 对学员信息的表格进行初始化
	  * @param condition
	  */
		private void initStudentTable(String condition)
		{
			 ResultSet rs=null;
			String SQLString=condition;
		
			 MECData dat=new MECData("SYS_XYGL_BM");
			 
			 try {
				 	dat.connectionDatabase();
				 	rs=dat.select(SQLString);
				 	int count=0;
				 	while(rs.next())
				 	count++;
				 	
				 	Object[][] data = new Object[count][3];//TODO 判断显示下拉列表
					
				 	rs = dat.select(SQLString);
					count = 0;
					while(rs.next())
					{
						data[count][0] = rs.getString("StudentId");
						data[count][1] = rs.getString("StudentName");
						data[count][2] = rs.getString("CourseName");
						for(int i=0; i < dlmCourseList.getSize(); i++)
							if(data[count][2].equals(dlmCourseList.getElementAt(i).getId()))
								data[count][2] = dlmCourseList.getElementAt(i).getName();
						count++;

					}
					String[] title = {"学员编号","姓名","课程"};
					
					this.jtbStudentTable = new JTable(data,title);		
					
					this.jscpStudentTable = new JScrollPane(jtbStudentTable);
					jscpStudentTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
					jscpStudentTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
					jscpStudentTable.setBounds(480/15,4300/15, 5250/15, 4095/15);	
					jtbStudentTable.setShowGrid(false);
					
					jtbStudentTable.setAutoResizeMode(jtbStudentTable.AUTO_RESIZE_OFF);
					TableColumn column= jtbStudentTable.getColumnModel().getColumn(0);//对第一列的宽度进行设置
					column.setPreferredWidth(110);
					
					TableColumn column1= jtbStudentTable.getColumnModel().getColumn(1);//对第二列的宽度进行设置
					column1.setPreferredWidth(80);
					

					TableColumn column2= jtbStudentTable.getColumnModel().getColumn(2);//对第三列的宽度进行设置
					column2.setPreferredWidth(175);
					jtbStudentTable.setAutoResizeMode(jtbStudentTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
					if(jtbStudentTable.getRowCount()>0)
						jtbStudentTable.setRowSelectionInterval(0, 0);//默认选择第一行

					cont.add(jscpStudentTable);
					StudentCount();
				 	dat.disconnection();
				 	
				 	tableAction();
				
			 	} catch (Exception e) 
			 	{

			 		e.printStackTrace();
			 	}		 
		}

	/**
	 * 对学员信息的表格进行鼠标监听
	 */
	 private void tableAction()
	 {		
			this.jtbStudentTable.addMouseListener
			(
					new MouseListener()
					{
						public void mouseClicked(MouseEvent e) 
						{								
							initStudentMes();
						}
						public void mousePressed(MouseEvent e) {}
						public void mouseReleased(MouseEvent e) {}
						public void mouseEntered(MouseEvent e) {}
						public void mouseExited(MouseEvent e) {}						
					}
			);	 
	 }
 
	 /**
	  * 显示学员人数
	  */
	 private void StudentCount()
	 {
		 String count = String.valueOf(this.jtbStudentTable.getRowCount());
		 this.jlblStudentCount.setText("共有"+count+"个学员");		
	 }
	
	 /**
	  * 初始化学员信息区
	  */
	 private void initStudentMes()
	 {
		 if(this.jtbStudentTable.getSelectedRow() != -1)
		 {
			 Object ID=jtbStudentTable.getValueAt(jtbStudentTable.getSelectedRow(), 0);
			   int state;
			   int sex;
			   ResultSet rs=null;
			   String SQLString="SELECT StudentId, StudentName, Id, Sex, PhoneNumber, Nation ,Native ,"
			   +"Course, University, Department, Major, AgentId, StudentState "+
			   "FROM SYS_XYGL_BM WHERE StudentId='"+ID+"'";
			 
			   MECData dat=new MECData("SYS_XYGL_BM");
			   
			   try {
				   	dat.connectionDatabase();
				   	rs=dat.select(SQLString);
				   	while(rs.next())
				   	{
				   		state=rs.getInt("StudentState");
				   		sex=rs.getInt("Sex");
				   		this.jlblNumber.setText(rs.getString("StudentId"));//TODO
				   		this.jtxtName.setText(rs.getString("StudentName"));
				   		this.jtxtId.setText(rs.getString("Id"));
				   		this.jrdbSex[sex].setSelected(true);
				   		this.jcmbNation.setSelectedIndex(Integer.valueOf(rs.getString("Nation"))-1);
				   		
				   	    String n=rs.getString("Native");//选中籍贯
				   		for(int i=0;i<Integer.valueOf(this.jcmbNative.getItemCount());i++)
				   		{
				   			if(n.equals(this.jcmbNative.getItemAt(i).toString().substring(0, 0+2)))
				   				jcmbNative.setSelectedIndex(i);
				   		}
				   		
				   		String c=rs.getString("Course");//选中学习课程			   	
				   		for(int i=0;i<Integer.valueOf(this.jcmbStudayCourse.getItemCount());i++)
				   		{
				   			if((c.equals(this.jcmbStudayCourse.getItemAt(i).toString().substring(0, 0+10))))
				   				jcmbStudayCourse.setSelectedIndex(i);
				   		}
				   		
				   		
				   	    String u=rs.getString("University");//选中院校
				   		for(int i=0;i<Integer.valueOf(this.jcmbUniversity.getItemCount());i++)
				   		{
		 		   			if((u.substring(0, 0+2)).equals(this.jcmbUniversity.getItemAt(i).toString().substring(0, 0+2)))
				   				jcmbUniversity.setSelectedIndex(i);
				   		}
				   					   				   		
				   		String d=rs.getString("Department");//选中院系
				   		for(int i=0;i<Integer.valueOf(this.jcmbDepartment.getItemCount());i++)
				   		{
		 		   			if((d.substring(2, 2+2)).equals(this.jcmbDepartment.getItemAt(i).toString().substring(2, 2+2)))
		 		   			jcmbDepartment.setSelectedIndex(i);	 		   			
				   		}
				   	
				   		String m=rs.getString("Major");//选中专业
				   		for(int i=0;i<Integer.valueOf(this.jcmbMajor.getItemCount());i++)
				   		{
		 		   			if((m.substring(4, 4+2)).equals(this.jcmbMajor.getItemAt(i).toString().substring(4, 4+2)))
		 		   			jcmbMajor.setSelectedIndex(i);	 		   			
				   		}
				   		this.jcmbAgent.setSelectedItem(rs.getString("AgentId"));			   		
				   		this.jtxtTelePhone.setText(rs.getString("PhoneNumber"));
				   		this.jrdbStudentState[state].setSelected(true);
				   		setStudentPhoto(jtxtId.getText());
				   	}				   	
				   	dat.disconnection();
			   		} catch (Exception e)
			   		{			
			   			e.printStackTrace();
			   		}
		 }
		   

	 }
	 
	 /**
	  * 学员照片
	  * @param Id
	  */
	 private void setStudentPhoto(String Id)
		{
			String photoPath = "E:\\程序\\照片\\"+Id+".jpg";
			File fp = new File(photoPath);
			if(!fp.exists())
				photoPath = "E:\\程序\\eclipse\\程序练习\\8.jpg";

			jlblPhoto.setText(null);
			ImageIcon image = new ImageIcon(photoPath);
			image.setImage(image.getImage().getScaledInstance(2175/15, 2775/15, Image.SCALE_DEFAULT));
			jlblPhoto.setIcon(image);
			jlblPhoto.setBounds(9100/15, 960/15, 2175/15, 2775/15);
			cont.add(this.jlblPhoto);
		}
		
	 
	 /**
	  * 初始化课程列表	
	  */
	 private void initCourseList()
	 {
		
		    CheckListCellRenderer jckChek = new CheckListCellRenderer();
			this.jlstCourseList.setCellRenderer(jckChek);
			
			dlmCourseList.removeAllElements();		
			
			 myData value=new myData("", "全部课程");
			 value.invertSelected();					
			 jlstCourseList.repaint();
			 dlmCourseList.addElement(value);
		
			ResultSet rs = null;
			String subjectIdList=null;
			String subjectNameList=null;
		
			String SQLString ="SELECT CourseId,CourseName FROM SYS_INF_COURSE ";
			MECData dat = new MECData("SYS_XYGL_BM");
				try 
				{
					dat.connectionDatabase();
					rs = dat.select(SQLString);				
					while(rs.next())
					{
						subjectIdList = rs.getString("CourseId");
						subjectNameList = rs.getString("CourseName");						
						dlmCourseList.addElement(new myData(subjectIdList, subjectNameList));
					}			
					dat.disconnection();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}	
			
	 }

	 /**
	  * 初始化民族下拉列表
	  */
	 private void initNation()
	 {
		String nationcode;
		String nation;
		ResultSet rs=null;
		
		String SQLString="SELECT NationCode,Nation FROM SYS_INF_NATION ";
		
		MECData dat=new MECData("SYS_XYGL_BM");
		try {
			   dat.connectionDatabase();
			   rs=dat.select(SQLString);
			   while(rs.next())
			   {
				   nationcode=rs.getString("NationCode");
				   nation=rs.getString("Nation");
				   this.jcmbNation.addItem(nationcode+" "+nation);
			   }
			  
			   dat.disconnection();
			   if(this.jcmbNation.getItemCount()>0)
				   this.jcmbNation.setSelectedItem(0);
			
		    } catch (Exception e) {
			
			 e.printStackTrace();
		    }
	}
  
	 /**
	  * 初始化籍贯下拉列表
	  */
	 private void initNative()
	{
		String nativecode;
		String nativename;
		ResultSet rs=null;
		
		String SQLString="SELECT NativeCode,Native FROM SYS_INF_NATIVE";
		MECData dat=new MECData("SYS_XYGL_BM");
		try {
			 dat.connectionDatabase();
			 rs=dat.select(SQLString);
			 while(rs.next())
			 {
				 nativecode=rs.getString("NativeCode");
				 nativename=rs.getString("Native");
				 
				 this.jcmbNative.addItem(nativecode+" "+nativename);				 
			 }
			 dat.disconnection();
			 if(this.jcmbNative.getItemCount()>0)
				 this.jcmbNative.setSelectedItem(0);
			
		    } catch (Exception e) 
		    {
		    	e.printStackTrace();
		    }	
	}
	
	
	/**
	 * 初始化课程下拉列表
	 */
	 private void initCourse() 
	 {
         String courseid;
         String coursename;
         ResultSet rs=null;
         
         String SQLString="SELECT CourseId,CourseName FROM SYS_INF_COURSE WHERE CourseStatus='0'";
         MECData dat=new MECData("SYS_XYGL_BM");
 		 try {
 			 dat.connectionDatabase();
 			 rs=dat.select(SQLString);
 			 while(rs.next())
 			 {
 				 courseid=rs.getString("CourseId");
 				coursename=rs.getString("CourseName");
 				 
 				 this.jcmbStudayCourse.addItem(courseid+" "+coursename);				 
 			 }
 			 dat.disconnection();
 			 if(this.jcmbStudayCourse.getItemCount()>0)
 				 this.jcmbStudayCourse.setSelectedItem(0);
 			
 		    } catch (Exception e) 
 		    {
 		    	e.printStackTrace();
 		    }	
			
	 }
	

	/**
	 * 初始化院校下拉列表
	 */
	 private void initUniversity() 
	 {
			String id;
			String university;
			ResultSet rs=null;
			String SQLString="SELECT U_D_Mid,U_D_Mname FROM SYS_XTGL_YXXZYXX WHERE U_D_Mstate=0 AND RIGHT(U_D_Mid,4)='0000'";
			MECData dat=new MECData("SYS_XYGL_BM");
			try {
				  dat.connectionDatabase();
				  rs=dat.select(SQLString);
				  while(rs.next())
				  {
					  id=rs.getString("U_D_Mid");
					  university=rs.getString("U_D_Mname");
					  if(id!=null)
					  {
					  this.jcmbUniversity.addItem(id+" "+university);
					  if(this.jcmbUniversity.getItemCount()>0)
					       this.jcmbUniversity.setSelectedItem(0);	
					    
					  }
					  else
						  this.jcmbUniversity.removeAllItems();
				  }
				  String condition=jcmbUniversity.getSelectedItem().toString().substring(0, 0+2);
				  initDepartment(condition);
				    				  
				  dat.disconnection();
		    	} catch (Exception e) 
				{
		    		e.printStackTrace();
				}
	}
	

	/**
	 * 初始化院系下拉列表
	 * @param condition
	 */
	 private void initDepartment(String condition)
	 {
		  this.jcmbDepartment.removeAllItems();
		//  System.out.println(condition);
		  if(condition!=null)
		   {
		    String id=null;
			String department=null;
			ResultSet rs=null;
			String SQLString="SELECT U_D_Mid,U_D_Mname FROM SYS_XTGL_YXXZYXX WHERE U_D_Mstate=0 AND MID(U_D_Mid,3,2)<>'00' AND RIGHT(U_D_Mid,2)='00' AND LEFT(U_D_Mid,2)='"+condition+"'";
			
			MECData dat=new MECData("SYS_XYGL_BM");
			try {
				  dat.connectionDatabase();
				  rs=dat.select(SQLString);
				  while(rs.next())
				  {
					  id=rs.getString("U_D_Mid");
					  department=rs.getString("U_D_Mname");
					  if(id!=null)
					  { 	
						    jcmbDepartment.addItem(id+" "+department);
					  	    if(this.jcmbDepartment.getItemCount()>0)
				  		    this.jcmbDepartment.setSelectedIndex(0);
					  }
					  else
						  jcmbDepartment.removeAllItems();					  
				  }
				  		
				 } catch (Exception e) 
				 {
		    		e.printStackTrace();
				 }
				//此处判断院系下拉表是否为空，如果为空，则删除专业下拉表；反之，则以编号前四位作为条件调用专业下拉表
				if(jcmbDepartment.getItemCount()>0)
				{
					String condition1=jcmbDepartment.getSelectedItem().toString().substring(0, 0+4);
					initMajor(condition1);
				}
				else
				{
					jcmbMajor.removeAllItems();
				}
	  		}


 }
	
	
	 /**
	  * 初始化专业下拉列表
	  * @param condition
	  */
	 private void initMajor(String condition)
	 {
		 this.jcmbMajor.removeAllItems();
		 if(condition!=null)  
		 { 
			String id=null;
			String major=null;
			ResultSet rs=null;
			String SQLString="SELECT U_D_Mid,U_D_Mname FROM SYS_XTGL_YXXZYXX WHERE U_D_Mstate=0 AND RIGHT(U_D_Mid,2)<>'00' AND LEFT(U_D_Mid,4)='"+condition+"'";
		
			MECData dat=new MECData("SYS_XYGL_BM");
			try {
				  dat.connectionDatabase();
				  rs=dat.select(SQLString);
				  while(rs.next())
				  {
					  id=rs.getString("U_D_Mid");
					  major=rs.getString("U_D_Mname");
					  if(id!=null)
					  {
						  this.jcmbMajor.addItem(id+" "+major);
						  if(this.jcmbMajor.getItemCount()>0)
						  this.jcmbMajor.setSelectedIndex(0);
					  }
					  else
					  {  
						  jcmbMajor.removeAllItems();
					  }
				  }
				
				
		    	 } catch (Exception e) 
				 {
		    		e.printStackTrace();
			  	}
		 }
	 }
	 
     /**
      * 初始化负责人下拉列表
      */
	 private void initAgent()
		{
			    String id;
				
				ResultSet rs=null;
				String SQLString="SELECT AgentNo FROM SYS_MEC_AGENT ";
				MECData dat=new MECData("SYS_XYGL_BM");
				try {
					  dat.connectionDatabase();
					  rs=dat.select(SQLString);
					  while(rs.next())
					  {
						  id=rs.getString("AgentNo");						
						  this.jcmbAgent.addItem(id);						 
					  }
					  if(this.jcmbAgent.getItemCount()>0)
						  this.jcmbAgent.setSelectedIndex(0);
			    	 } catch (Exception e) 
					 {
			    		e.printStackTrace();
				  	}
			
		}
	 

	 /**
	  * 通过学号或姓名查找学员
	  */
	 private void search()
	 {
		 String hanzi= "^[\\u4e00-\\u9fa5]+";
		 String shuzi= "^[0-9]+$";
		 String searchStudent=this.jtxtCheckWay.getText();
		 String SQLString ="SELECT StudentId,StudentName,Id,Sex,Nation,Native,Phonenumber,Course, University, Department, Major, AgentId, StudentState"+
				 		" FROM SYS_XYGL_BM ";
		 if(this.jtxtCheckWay.getText().matches(shuzi))
		 {
			 SQLString +="WHERE StudentId= '"+searchStudent+"'";
		 }
		 else if(this.jtxtCheckWay.getText().matches(hanzi))
		 {
			 SQLString +="WHERE StudentName= '"+searchStudent+"'";
		 }
		 
		 creatTable(SQLString);
	 }
	 
	 
	 private void creatTable(String  SQLString )
	 {
		 ResultSet rs=null;

		 MECData dat=new MECData("SYS_XYGL_BM");

		 try {
			 	dat.connectionDatabase();
			 	rs=dat.select(SQLString);
			 	int count=0;
			 	while(rs.next())
			 	count++;
			 
			 	Object[][] data = new Object[count][3];
			 	rs = dat.select(SQLString);
				count = 0;
				while(rs.next())
				{	
					data[count][0] = rs.getString("StudentId");
					data[count][1] = rs.getString("StudentName");
					data[count][2] = rs.getString("Course");
					for(int i=0; i < dlmCourseList.getSize(); i++)
						if(data[count][2].equals(dlmCourseList.getElementAt(i).getId()))
							data[count][2] = dlmCourseList.getElementAt(i).getName();
					count++;
				
				}
				String[] title = {"学员编号","姓名","课程"};
				
				this.jtbStudentTable = new JTable(data,title);		
				
				this.jscpStudentTable = new JScrollPane(jtbStudentTable);
				jscpStudentTable.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				jscpStudentTable.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
				jscpStudentTable.setBounds(480/15,4300/15, 5250/15, 4095/15);	
				jtbStudentTable.setShowGrid(false);
				
				jtbStudentTable.setAutoResizeMode(jtbStudentTable.AUTO_RESIZE_OFF);
				TableColumn column= jtbStudentTable.getColumnModel().getColumn(0);//对第一列的宽度进行设置
				column.setPreferredWidth(110);
				
				TableColumn column1= jtbStudentTable.getColumnModel().getColumn(1);//对第二列的宽度进行设置
				column1.setPreferredWidth(80);
				

				TableColumn column2= jtbStudentTable.getColumnModel().getColumn(2);//对第三列的宽度进行设置
				column2.setPreferredWidth(175);
				jtbStudentTable.setAutoResizeMode(jtbStudentTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
				if(jtbStudentTable.getRowCount()>0)
					jtbStudentTable.setRowSelectionInterval(0, 0);//默认选择第一行

				cont.add(jscpStudentTable);
				StudentCount();
			 	dat.disconnection();
			 
			  	tableAction();
			 
		 	} catch (Exception e) 
		 	{
		 		e.printStackTrace();
		 	}		 
	 }
	 
	 /**
	  * 再次初始化表格
	  */
	 private void reinitTable()
	 {
		 String SQLString="SELECT Studentid,StudentName,Course FROM SYS_XYGL_BM WHERE true";
		 if(this.jcbShow.getSelectedIndex()==0)
			 SQLString += " AND StudentState = 0 ";
		 int count=0;
		 if(!this.dlmCourseList.firstElement().isSelected())
			 for(int i=0; i < dlmCourseList.getSize(); i++)
				{
					if(count == 0 && dlmCourseList.elementAt(i).isSelected())
					{
						SQLString += " AND Course = '" + dlmCourseList.elementAt(i).getId() + "'";
						count = 1;
					}
					else if(dlmCourseList.elementAt(i).isSelected())
					{
						SQLString += " OR Course = '" + dlmCourseList.elementAt(i).getId() + "'";
					}
				}
			SQLString += " ORDER BY StudentId";			
			creatTable(SQLString);
	 }


	 
	/**
	 * 向数据库中添加学员信息
	 */
	 private void Apply()
	 {		 
		 String studentid;
		 String studentname;
		 String id;
		 int sex;
		 String nation;
		 String Native;
		 String phonenumber;
		 String course;
		 String university;
		 String department;
		 String major;
		 String agent;
		 int studentstate;
		 int IsHavePhoto=0;
		 int IsHaveCard=0;
	 
		 Object studentID=jtbStudentTable.getValueAt(jtbStudentTable.getSelectedRow(), 0);
	
		 studentname=this.jtxtName.getText();
		 id=this.jtxtId.getText();
		 sex=this.jrdbSex[0].isSelected()?0:1;
		 nation=this.jcmbNation.getSelectedItem().toString().substring(0, 0+2);
		 Native=this.jcmbNative.getSelectedItem().toString().substring(0, 0+2);
		 phonenumber=this.jtxtTelePhone.getText();
		 course=this.jcmbStudayCourse.getSelectedItem().toString().substring(0, 0+10);
		 university=this.jcmbUniversity.getSelectedItem().toString().substring(0, 0+6);
		 department=this.jcmbDepartment.getSelectedItem().toString().substring(0, 0+6);
		 major=this.jcmbMajor.getSelectedItem().toString().substring(0, 0+6);
		 agent=this.jcmbAgent.getSelectedItem().toString().substring(0, 0+9);
		 studentstate=this.jrdbStudentState[0].isSelected()?0:1;
		 studentid=getnewstudentid();
	
		 if(LASTACTION==0)
		 {
			 String SQLString="INSERT INTO SYS_XYGL_BM"+
			 			" VALUES('"+studentid+"','"+studentname+"','"+ id+"',"+sex+",'"+nation+"','"+Native+"','"+phonenumber+"','"+
			 			course+"','"+university+"','"+department+"','"+major+"','"+agent+"',"+studentstate+","+0+","+0+")";
		 
			 MECData dat=new MECData("SYS_XYGL_BM");
		
				
			 try {
				 	dat.connectionDatabase();
				 	dat.update(SQLString);
				 	dat.disconnection();
				
				 	if(jcbShow.getSelectedIndex()==0)							
					  {
						cont.remove(jscpStudentTable);
						String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId AND StudentState=0";
						initStudentTable(SQLString1);
					  }
					else if(jcbShow.getSelectedIndex()==1)
					{
						cont.remove(jscpStudentTable);
						 String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId";
						 initStudentTable(SQLString1);
					}
				 	
			 	 }  catch (Exception e) 
			 	 {
			
			 		 e.printStackTrace();
			 	 }
		 }
		 else if(LASTACTION==1)
		 {
			String SQLString="UPDATE SYS_XYGL_BM SET "+
							"StudentName='"+studentname+"',Id='"+id+"',Sex="+sex+
							",Nation='"+nation+"',Native='"+Native+"',PhoneNumber='"+phonenumber+
							"',Course='"+course+"',University='"+university+"',Department='"+department+
							"',Major='"+major+"',AgentId='"+agent+"',StudentState="+studentstate+
							",IsHavePhoto=0,IsHaveCard=0 WHERE StudentId='"+studentID+"'";
		
			 MECData dat=new MECData("SYS_XYGL_BM");
			 
			 try {
				 	dat.connectionDatabase();
				 	dat.update(SQLString);
				 	dat.disconnection();
				    

					if(jcbShow.getSelectedIndex()==0)							
					  {
						cont.remove(jscpStudentTable);
						String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId AND StudentState=0";
						initStudentTable(SQLString1);
					  }
					else if(jcbShow.getSelectedIndex()==1)
					{
						cont.remove(jscpStudentTable);
						 String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId";
						 initStudentTable(SQLString1);
					}
				 	
			 	 }  catch (Exception e) 
			 	 {
			
			 		 e.printStackTrace();
			 	 }
			 	
		}	 
		
	 }
	 
	 /**
	  * 得到新的学员编号StudentId
	  * @return
	  */
	 private  String getnewstudentid() 
	 {
		 	String stuId = null;
			ResultSet rs = null;
			String courseId =this.jcmbStudayCourse.getSelectedItem().toString().substring(0, 0+10);
			int count = 0;
			MECData dat = new MECData("SYS_XYGL_BM");
			String SQLString = "SELECT Delete_Id FROM SYS_XYGL_DELETEID WHERE LEFT(Delete_Id, 10) = '" + courseId + "' ORDER BY Delete_Id ASC";
			try 
			{
				dat.connectionDatabase();			
				rs = dat.select(SQLString);
				while(rs.next())
				{
					stuId = rs.getString("Delete_Id");	
					break;
				}
				dat.disconnection();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}
			
			if(stuId != null)
			{
				SQLString = "DELETE FROM SYS_XYGL_DELETEID WHERE Delete_Id = '" + stuId + "'";
				try 
				{
					dat.connectionDatabase();			
					dat.update(SQLString);
					dat.disconnection();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			else
			{
				SQLString = "SELECT COUNT(*) AS CNT FROM SYS_XYGL_BM WHERE LEFT(StudentId, 10) = '" + courseId + "'";
				try 
				{
					dat.connectionDatabase();			
					rs = dat.select(SQLString);
					while(rs.next())
					{
						count = rs.getInt("CNT");
						System.out.println(count);
						stuId = courseId + (1000 + count + 1 + "").substring(1, 1+3);
						break;
					}
					dat.disconnection();
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			
			return stuId;			
	 }

	/**
	 * 验证身份证号码的有效性
	 * @param id
	 * @throws Exception
	 */
	private void checkStudentPeopleId(String id) throws Exception
	{
			final String nativeCode = "11, 12, 13, 14, 15, 21, 22, 23, 31, 32, 33, 34, 35, 36, 37, " +
				"41, 42, 43, 44, 45, 46, 50, 51, 52, 53, 54, 61, 62, 63, 64, 65, 71, 81, 82"; 
			final String[] errMsg = 
			{
				"身份证号码长度必须为18位！",
				"身份证号码包含非法字符！",
				"身份证号码的省份码有错误！",
				"身份证号码的生日码有错误！",
				"身份证号码的验证码有错误！",
				"身份证号码验证位若为X，则必须是大写X！"
			};
			final int ERR_ID_LEN						= 0;
			final int ERR_ILLEGAL_CHAR					= 1;
			final int ERR_NATIVE						= 2;
			final int ERR_BIRTHDAY						= 3;
			final int ERR_CHECK_CODE					= 4;
			final int ERR_CHECK_CODE_MUST_BE_UPPER_CASE	= 5;
			
			String errStr = "studentPeopleId:";
			
			if(id.length() !=0 && id.length() != 18)
			{
	        	jtxtId.requestFocus();
	        	jtxtId.setSelectionStart(0);
	        	jtxtId.setSelectionEnd(jtxtId.getText().length());
	        	baoming.showMsg(jfrmMain, errStr + errMsg[ERR_ID_LEN]);
			}
			else if(id.length() !=0 && id.length() == 18)
			{
				if(id.substring(17).equalsIgnoreCase("X") && id.substring(17).equals("x"))
				{
					flag = false;
					jtxtId.requestFocus();
		        	jtxtId.setSelectionStart(0);
		        	jtxtId.setSelectionEnd(jtxtId.getText().length());
		        	baoming.showMsg(jfrmMain, errStr + errMsg[ERR_CHECK_CODE_MUST_BE_UPPER_CASE]);
				}
				if(nativeCode.indexOf(id.substring(0, 2) + ", ") == -1)
				{
					flag = false;
					jtxtId.requestFocus();
		        	jtxtId.setSelectionStart(0);
		        	jtxtId.setSelectionEnd(jtxtId.getText().length());
		        	baoming.showMsg(jfrmMain, errStr + errMsg[ERR_NATIVE]);
				}
				if(checkIdIllegal(id))
				{
					flag = false;
					jtxtId.requestFocus();
		        	jtxtId.setSelectionStart(0);
		        	jtxtId.setSelectionEnd(jtxtId.getText().length());
		        	baoming.showMsg(jfrmMain, errStr + errMsg[ERR_ILLEGAL_CHAR]);
				}
				if(!checkIdBirthday(id.substring(6, 6+8)))
				{
					flag = false;
					jtxtId.requestFocus();
		        	jtxtId.setSelectionStart(0);
		        	jtxtId.setSelectionEnd(jtxtId.getText().length());
		        	baoming.showMsg(jfrmMain, errStr + errMsg[ERR_BIRTHDAY]);
				}
				if(!getIdCheckString(id).equals(id.substring(17)))
				{
					flag = false;
					jtxtId.requestFocus();
		        	jtxtId.setSelectionStart(0);
		        	jtxtId.setSelectionEnd(jtxtId.getText().length());
		        	baoming.showMsg(jfrmMain, errStr + errMsg[ERR_CHECK_CODE]);
				}
			}
			
			if(jtxtId.getText().length() == 18)
			{
				jrdbSex[Integer.parseInt(jtxtId.getText().substring(16, 16+1))%2 == 1 ? 0 : 1].setSelected(true);
				
				for(int i=0; i < Integer.valueOf(jcmbNative.getItemCount());i++)
					if(jtxtId.getText().substring(0,0+2).equals(((String) jcmbNative.getItemAt(i)).substring(0, 0+2)))
						jcmbNative.setSelectedIndex(i);	
			}
	}
		
	/**
	* 验证身份证号码的验证码 
	*/
	private String getIdCheckString(String id)
	{
			String checkTable[] = new String[] {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
			int[] power = new int[]{7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1};
			int sum=0;
	        
			for(int i=0; i<17; i++)
			{
				String ch = id.substring(i, i+1);
				sum = sum + Integer.parseInt(ch)*power[i];
			}
	        
	        return checkTable[sum % 11];
	}
		
	/**
	 * 验证身份证号码是否包含非法字符
	 */
	private boolean checkIdIllegal(String id)
	{
			Pattern p = null;
			Matcher m = null;
			
			p = Pattern.compile("^(\\d){17}(\\d|X)$");
			m = p.matcher(id);
			
			return !m.find();
	}
		
	/**
	 * 验证身份证号码的生日码
	 */
	private boolean checkIdBirthday(String birthStr)
	{
			String str = birthStr.substring(0, 4) + "-" + 
				birthStr.substring(4, 4+2) + "-" + 
				birthStr.substring(6);
			Calendar d = Calendar.getInstance();
			d.set(Integer.parseInt(birthStr.substring(0, 4)), 
					Integer.parseInt(birthStr.substring(4, 4+2))-1,
					Integer.parseInt(birthStr.substring(6)));
			StringBuffer s = new StringBuffer();
			Formatter lastDate = new Formatter(s);
			lastDate.format("%tF", d);
			lastDate.close();
			
			return !s.equals(str);
	}
		
	/**
	 * 判断学员是否选过该课程
	 * @return
	 */
	private boolean isHave()
	{
			boolean flag = false;
			
			String id = jtxtId.getText();
			String course = this.jcmbStudayCourse.getSelectedItem().toString().substring(0, 0+10);
			
			String SQLString = "SELECT Id, Course" +
								" FROM SYS_XYGL_BM" +
								" WHERE Id = '" + id + "'AND Course ='" + course +"'"; 
			MECData dat = new MECData("SYS_XYGL_BM");
			ResultSet rs = null;
			try 
			{
				dat.connectionDatabase();		
				rs = dat.select(SQLString);	
				
				while(rs.next())
				{
					flag = true;
					break;
				}
				
				dat.disconnection();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}	
			
			return flag;
	}
		
		
	 
	/**
	 * 删除学员信息
	 */
	 private void deletStudent()
	 {
		 Object ID=jtbStudentTable.getValueAt(jtbStudentTable.getSelectedRow(), 0);		 
		 String SQLString ="DELETE FROM SYS_XYGL_BM WHERE StudentId='"+ID+"'";
		 MECData dat=new MECData("SYS_XYGL_BM");
		 saveDeleteId(ID);
		 try 
		 {
			dat.connectionDatabase();
			dat.update(SQLString);
		 	dat.disconnection();
		 	
			if(jcbShow.getSelectedIndex()==0)							
			  {
				cont.remove(jscpStudentTable);
				String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId AND StudentState=0";
				initStudentTable(SQLString1);
			  }
			else if(jcbShow.getSelectedIndex()==1)
			{
				cont.remove(jscpStudentTable);
				 String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId";
				 initStudentTable(SQLString1);
			}
		 	
		 } catch (Exception e) 
		 {
			e.printStackTrace();
		 }		 
	 }

	/**
	 * 保存删除的学员的编号
	 * @param stuId
	 */
	 private void saveDeleteId(Object stuId) 
	{
			String SQLString = "INSERT INTO SYS_XYGL_DELETEID VALUES('" + stuId +"')";
			
			MECData dat = new MECData("SYS_XYGL_BM");
			try 
			{
				dat.connectionDatabase();	
				dat.update(SQLString);		
				dat.disconnection();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}	
	}
	 
	/**
	 * 对于表格中的内容进行判断，如果有内容，则修改和删除按钮可点击，否则，不可点击
	 */
	 private void jbtState()
	 {
          
	    	if(this.jtbStudentTable.getRowCount()>0)
	    	{
	    		this.jbtModify.setEnabled(true);
	    		this.jbtDelete.setEnabled(true);   		
	    	}
	    	else
	    	{
	    		this.jbtCheck.setEnabled(false);
	    		this.jbtReturn.setEnabled(false);
	    		this.jbtModify.setEnabled(false);
	    		this.jbtDelete.setEnabled(false);   
	    	}
	 }
	 
	 
	 /**
	  * 浏览状态
	  * @param type
	  */
     private void setstate(int type)
     {
    	boolean state=type==SCAN;
    	
    	this.jcbShow.setEnabled(state);
    	this.jtbStudentTable.setEnabled(state);
    	this.jtxtCheckWay.setEnabled(state);
    	this.jlstCourseList.setEnabled(state);
    	this.jbtCheck.setEnabled(state);
    	this.jbtReturn.setEnabled(state);
   	
    	this.jtxtName.setEnabled(!state);
    	this.jtxtId.setEnabled(!state);
    	this.jrdbSex[0].setEnabled(!state);
    	this.jrdbSex[1].setEnabled(!state);
    	this.jcmbNation.setEnabled(!state);
    	this.jcmbNative.setEnabled(!state);
    	this.jtxtTelePhone.setEnabled(!state);
    	this.jcmbStudayCourse.setEnabled(!state);
    	this.jcmbUniversity.setEnabled(!state);
    	this.jcmbDepartment.setEnabled(!state);
    	this.jcmbMajor.setEnabled(!state);
    	this.jcmbAgent.setEnabled(!state);
    	this.jrdbStudentState[0].setEnabled(!state);
    	this.jrdbStudentState[1].setEnabled(!state);
 
     }
     
     
    /**
     * 在报名时候的编辑区的状态
     */
    private void ApplyOrModifyState()
    {
    	this.jbtCheck.setEnabled(false);
    	this.jbtReturn.setEnabled(false);
    	this.jrdbSex[0].setEnabled(false);
    	this.jrdbSex[1].setEnabled(false);
    	this.jcmbNative.setEnabled(false);
    	
    	jtxtName.setText("");
		jtxtId.setText("");
		jtxtTelePhone.setText("");
		jrdbSex[0].setSelected(true);
		jcmbNation.setSelectedIndex(0);
		jcmbNative.setSelectedIndex(0);
		jcmbStudayCourse.setSelectedIndex(0);
		jcmbUniversity.setSelectedIndex(0);
		jcmbDepartment.setSelectedIndex(0);
		jcmbMajor.setSelectedIndex(0);
		jcmbAgent.setSelectedIndex(0);
		jrdbStudentState[0].setSelected(true);
		
    }
    
    /**
     * 在点击报名和修改前的信息编辑区的信息
     */
    private void getOldMes()
    {
    	oldName=jtxtName.getText();
		oldeId=jtxtId.getText();
		oldTelePhone=jtxtTelePhone.getText();
		oldStudentId=jlblNumber.getText();
		oldNation=(String) jcmbNation.getSelectedItem();
		oldNative=(String) jcmbNative.getSelectedItem();
		oldCourse=(String) jcmbStudayCourse.getSelectedItem();
		oldUniversity=(String) jcmbUniversity.getSelectedItem();
		oldDepartment=(String) jcmbDepartment.getSelectedItem();
		oldMajor=(String) jcmbMajor.getSelectedItem();
		oldAgent=(String) jcmbAgent.getSelectedItem();
		oldSex=jrdbSex[0].isSelected()?0:1;
		oldState=jrdbStudentState[0].isSelected()?0:1;
	
    }
    
    /**
     * 在取消报名或修改时，编辑区信息恢复到原来
     */
    private void setOldMes()
    {
    	jtxtName.setText(oldName);
		jtxtId.setText(oldeId);
		jtxtTelePhone.setText(oldTelePhone);
		jcmbNation.setSelectedItem(oldNation);
		jcmbNative.setSelectedItem(oldNative);
		jcmbStudayCourse.setSelectedItem(oldCourse);
		jcmbUniversity.setSelectedItem(oldUniversity);
		jcmbDepartment.setSelectedItem(oldDepartment);
		jcmbMajor.setSelectedItem(oldMajor);
		jcmbAgent.setSelectedItem(oldAgent);
		jrdbSex[oldState].setSelected(true);
		jrdbStudentState[oldState].setSelected(true);
    }

     
	/**
	 * 监听事件
	 */
	 private void dealAction() 
	{
		/**
		 * 此处是对退出按钮的监听
		 */
		this.jbtExit.addActionListener
		(
				new ActionListener()
				{

					public void actionPerformed(ActionEvent e)
					{
						int a = JOptionPane.showConfirmDialog(jfrmMain, "你确定要退出吗？","系统温馨提示",JOptionPane.OK_OPTION);
						if(a==0)
						{
							jfrmMain.dispose();
						}
					}
					
				}
		);


		/**
		 * 此处是对身份证文本框的监听
		 */
		this.jtxtId.addCaretListener
		(
				new CaretListener()
				{
					public void caretUpdate(CaretEvent e) 
					{
						if(flag && !jbtDelete.isVisible() && jtxtId.getText().length() > 17)
							jtxtTelePhone.requestFocus();
					}
					
				}
		);
		
		/**
		 * 此处是对身份证文本框的监听
		 */
		this.jtxtId.addFocusListener
		(
				new FocusListener()
				{
					public void focusGained(FocusEvent e) 
					{
						jtxtId.setText(null);
					}

					public void focusLost(FocusEvent e) 
					{
						if(jtxtId.getText().length() <= 17)
							;
						else
						{
							try 
							{
								checkStudentPeopleId(jtxtId.getText());
							} catch (Exception e1) 
							{
								e1.printStackTrace();
							}	
						}
						
					}
					
				}
		);
		
		/**
		 * 此处是对报名按钮的监听
		 */
		this.jbtApply.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(jbtApply.getText().equals("报名"))
						{							
							getOldMes();
							
							setstate(EDIT);
							jbtApply.setText("确定");
							jbtModify.setText("取消");
							jbtDelete.setVisible(false);
							
							jlblNumber.setText(null);
							jtxtName.requestFocus(true);
							LASTACTION=0;	
							
							ApplyOrModifyState();
							
						}
						else
						{						
							jbtApply.setText("报名");
							jbtModify.setText("修改");
							jbtDelete.setVisible(true);
							jlblNumber.setText(getnewstudentid());
							setstate(SCAN);
								if(LASTACTION==0)
								{									
									if(LASTACTION==0 && isHave() )
									{
										showMsg(jfrmMain, "该学员已报过该课程");
										//JOptionPane.showConfirmDialog(jfrmMain, "该学员已报过该课程","系统温馨提示",JOptionPane.OK_OPTION);
									}
									else
									{
										Apply();
									}
								}
								else if(LASTACTION==1) 
								{
									
									Apply();
								}	
						}						
					}					
				}
		);
	
		/**
		 * 此处是对修改按钮进行监听
		 */
		this.jbtModify.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if(jbtModify.getText().equals("修改"))
						{
							getOldMes();
							setstate(EDIT);
	
							jrdbSex[0].setEnabled(false);
						    jrdbSex[1].setEnabled(false);
						    jcmbNative.setEnabled(false);
							jbtApply.setText("确定");
							jbtModify.setText("取消");							
							jbtDelete.setVisible(false);
							initStudentMes();
							
							LASTACTION=1;	
						}
						else 
						{									
							setOldMes();	
							
							jbtApply.setText("报名");
							jbtModify.setText("修改");
							jbtDelete.setVisible(true);
							
							setstate(SCAN);
						}						
					}					
				}
		);
		
		/**
		 * 此处是对删除按钮进行监听
		 */
		this.jbtDelete.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						if(jbtDelete.getText().equals("删除"))
						{

							Object Name=jtbStudentTable.getValueAt(jtbStudentTable.getSelectedRow(), 1);	
							
							int a = JOptionPane.showConfirmDialog(jfrmMain, "你确定要删除'"+Name+"'的信息吗？","系统温馨提示",JOptionPane.OK_OPTION);
							if(a==0)
							{
								deletStudent();
							}
						}						
					}
					
				}
				
		);
	
		/**
		 * 此处是对查询按钮的监听
		 */
		this.jbtCheck.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						if(jtxtCheckWay.getText().length()>0)
						{
							cont.remove(jscpStudentTable);
							search();
							initStudentMes();
							if(jtbStudentTable.getRowCount()==0)
							{
								showMsg(jfrmMain, "查无此人，请确认后重新输入");
								JOptionPane.showConfirmDialog(jfrmMain, "查无此人，请确认后重新输入","系统温馨提示",JOptionPane.OK_OPTION);
								jtxtCheckWay.setText(null);
								cont.remove(jscpStudentTable);
								initStudentMes();
							}	
						}
						else 
						{
								showMsg(jfrmMain, "请输入学号或姓名");
							//JOptionPane.showConfirmDialog(jfrmMain, "请输入学号或姓名","系统温馨提示",JOptionPane.OK_OPTION);
						}
						setstate(SCAN);
					}
					
				}
		);
		
        /**
         * 此处是对恢复按钮的监听
         */
		this.jbtReturn.addActionListener
		(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						jtxtCheckWay.setText("");
						cont.remove(jscpStudentTable);
						if(jcbShow.getSelectedIndex()==0)							
						  {
							cont.remove(jscpStudentTable);
							String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId AND StudentState=0";
							initStudentTable(SQLString1);
						  }
						else if(jcbShow.getSelectedIndex()==1)
						{
							cont.remove(jscpStudentTable);
							 String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId";
							 initStudentTable(SQLString1);
						}
					}
					
				}
		);

		/**
		 * 此处是对显示下拉列表的监听
		 */
		this.jcbShow.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e) 
					{
						if(e.getStateChange()==ItemEvent.SELECTED)
						{
							if(jcbShow.getSelectedIndex()==0)							
							  {
								cont.remove(jscpStudentTable);
								String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId AND StudentState=0";
								initStudentTable(SQLString1);
							  }
							else if(jcbShow.getSelectedIndex()==1)
							{
								cont.remove(jscpStudentTable);
								 String SQLString1 ="SELECT StudentId,StudentName,CourseName FROM SYS_INF_COURSE, SYS_XYGL_BM WHERE Course = CourseId";
								 initStudentTable(SQLString1);
							}
						}						
					}					
				}
		);
		
		
		/**
		 * 此处是对院校下拉列表的监听
		 */
		this.jcmbUniversity.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange()==ItemEvent.SELECTED)
						{		
							String condition=jcmbUniversity.getSelectedItem().toString().substring(0, 0+2);
					     	initDepartment(condition);				
						}
					}
					
				}
		);
		
		/**
		 * 此处是对院系下拉列表的监听
		 */
		this.jcmbDepartment.addItemListener
		(
				new ItemListener()
				{
					public void itemStateChanged(ItemEvent e)
					{
						if(e.getStateChange()==ItemEvent.SELECTED)
						{
							if(jcmbDepartment.getItemCount()>0)
							{
								String condition=jcmbDepartment.getSelectedItem().toString().substring(0, 0+4);
								initMajor(condition);
							}
							else
							{
								jcmbMajor.removeAllItems();
							}
						}
					}
					
				}
		);
		
		/**
		 * 此处是对课程列表进行监听
		 */
		this.jlstCourseList.addMouseListener
		(
				new MouseListener()
				{

					public void mouseClicked(MouseEvent e)
					{  
						
						 if(dlmCourseList.firstElement().isSelected())
						 {
							 myData value=jlstCourseList.getSelectedValue();
							 value.invertSelected();					
							 jlstCourseList.repaint();
							 dlmCourseList.firstElement().setSelected(false);							 
						 }
						 else
						{
							 myData value=jlstCourseList.getSelectedValue();
							 if(value==dlmCourseList.firstElement())
							 {
								 for(int i=1;i<dlmCourseList.getSize();i++)
									 dlmCourseList.getElementAt(i).setSelected(false);
							 }
							 value.invertSelected();					
							 jlstCourseList.repaint();							 
						}
						 cont.remove(jscpStudentTable);
						 reinitTable();
					}
					public void mousePressed(MouseEvent e) {}
					public void mouseReleased(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}					
				}
		);		
    }
	 
	 /**
	  * 再次初始化窗口
	  */
	 private void reinitFrame() 
	{   
		initCourseList();
		initNation();
		initNative();
		initCourse();
		initUniversity();
		initAgent();
		initStudentMes();
		setstate(SCAN);
		jbtState();		
    }

	 public baoming()
	   {
		   initFrame();		   
		   reinitFrame();		   
		   dealAction();			
	   }
	 public static void main(String[] args) 
	 {	
			new baoming();
	 }
}
