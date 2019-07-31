package com.ambition.agile.modules.jzmk.web;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.ImageIcon;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class CreateCodeController {
	
	private String code;
	
	@RequestMapping(value="web/code",method={RequestMethod.GET,RequestMethod.POST})
	public void getCodeAndImage(HttpServletRequest request, HttpServletResponse response, Model model){
		ImageIcon pathIcon = new ImageIcon(request.getSession().getServletContext().getRealPath("/static/images/code.jpg"));
		int width=pathIcon.getIconWidth() / 4;
		int height=pathIcon.getIconHeight() / 6-4;
		// 创建画布
		BufferedImage bufferedImage = new BufferedImage(width, height,BufferedImage.TYPE_3BYTE_BGR);
		// 获取画笔
		Graphics g = bufferedImage.getGraphics();
		// 开始画画/
		g.drawImage(pathIcon.getImage(), -10, -10, null);
		//设置验证码字符范围
		String strDX[]={"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
		String strXX[]={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
		String strNum[]={"0","1","2","3","4","5","6","7","8","9"};
		ArrayList<String[]> list=new ArrayList<String[]>();
		list.add(strDX);
		list.add(strXX);
		list.add(strNum);
		//存放产生的验证码
		StringBuffer buffer=new StringBuffer();

		//获取产生随机数的对象
		Random rd=new Random();
		/*for (int i = 0; i < 100; i++) {
			//设置画笔颜色
			g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255),124));
			//获取字符数组
			String[] strTemp = list.get(rd.nextInt(list.size()));
			//获取字符
			String str = strTemp[rd.nextInt(strTemp.length)];
			//画出字符
			g.drawString(str, (1+i)*25+rd.nextInt(10), rd.nextInt(80));
		}*/
		
		//设置画笔颜色
		g.setColor(new Color(0, rd.nextInt(255), rd.nextInt(255),128));
		//画直线
		/*for (int i = 0; i < 10; i++) {
			int x=rd.nextInt(width);
			int y=rd.nextInt( height);
			int x1=rd.nextInt(width);
			int y1=rd.nextInt( height);
			g.drawLine(x, y, x1, y1);
		}*/
		
		//画噪点
		for (int i = 0; i < 100; i++) {
			g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
			int x=rd.nextInt(width);
			int y=rd.nextInt( height);
			g.drawOval(x, y, 1, 1);
		}
		//字体样式数组
		String fontStyle[]={"楷体","宋体","黑体","Highlight LET Plain","John Handy LET Plain","Tiranti Solid LET Plain","方正兰亭超细黑简体","Broadway BT"};
		//画验证码
		for (int i = 0; i < 5; i++) {
			//设置画笔样式
			g.setFont(new Font(fontStyle[rd.nextInt(fontStyle.length)], Font.BOLD, 46));
			//设置画笔颜色
			g.setColor(new Color(rd.nextInt(255), rd.nextInt(255), rd.nextInt(255)));
			//获取字符数组
			String[] strTemp = list.get(rd.nextInt(list.size()));
			//获取字符
			String str = strTemp[rd.nextInt(strTemp.length)];
			//画出字符
			g.drawString(str, (i)*25+rd.nextInt(10)+5, rd.nextInt(10)+46);
			//保存字符
			buffer.append(str);
		}
		//输出
		try {
			ServletOutputStream output = response.getOutputStream();
			ImageIO.write(bufferedImage, "jpg", output);
			code=buffer.toString();
		} catch (IOException e) {
			System.out.println("IO异常");
		}
	}
	
	@ResponseBody
	@RequestMapping(value="web/cCode",method={RequestMethod.GET,RequestMethod.POST})
	public String checkCode(HttpServletRequest request, HttpServletResponse response, String inCode){
		String msg="e";
		if(!code.equals(inCode)){
			msg="r";
		}
		return msg;
	}
	
	@ResponseBody
	@RequestMapping(value="web/gCode",method={RequestMethod.GET,RequestMethod.POST})
	public String getCode(HttpServletRequest request, HttpServletResponse response, Model model){
		return code;
	}

}
