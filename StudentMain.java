package studentProgram;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentMain {
	//¸Ş´º : ¼ºÀû ÀÔ·Â, °³º° ¼ºÀû Á¶È¸, ÀüÃ¼ ¼ºÀû Á¶È¸, ¼ºÀû¼ø Á¤·Ä, ¼ºÀû ¼öÁ¤, ¼ºÀû »èÁ¦, Á¾·á
	public static final int INSERT=1, SEARCH=2, PRINT=3, SORT=4, UPDATE=5, DELETE=6, EXIT=7;
	
	//½ºÄ³³Ê »ı¼º
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu = 0;
		boolean flag = false;
		
		//¸Ş´º ¼±ÅÃ : ¼ºÀû ÀÔ·Â, °³º° ¼ºÀû Á¶È¸, ÀüÃ¼ ¼ºÀû Á¶È¸, ¼ºÀû¼ø Á¤·Ä, ¼ºÀû ¼öÁ¤, ¼ºÀû »èÁ¦, Á¾·á
		while(!flag) {
			//¸Ş´º ¼±ÅÃ ÇÔ¼ö È£Ãâ
			menu = selectMenu();
			switch(menu) {
			case INSERT: studentInsert(); break;
			case SEARCH: studentSearch(); break;
			case PRINT: studentPrint(); break;
			case SORT: studentSort(); break;
			case UPDATE: studentUpdate(); break;
			case DELETE: studentDelete(); break;
			case EXIT: System.out.println("¼ºÀû ÇÁ·Î±×·¥À» Á¾·áÇÕ´Ï´Ù.");	flag = true; break;
			}
		}
	}
	
	//¸Ş´º ¼±ÅÃ
	private static int selectMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("**************************************************************************");
			System.out.println("   1. ÀÔ·Â 2. ¼ºÀû Á¶È¸ 3. ÀüÃ¼ ¼ºÀû Á¶È¸  4. Á¤·Ä 5. ¼öÁ¤ 6. »èÁ¦  7. Á¾·á");
			System.out.println("**************************************************************************");
			System.out.print("¸Ş´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			
			if(menu>0 && menu<8) flag = true;
			else System.out.println("1~7 »çÀÌÀÇ ¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		return menu;
	}
	
	//¼ºÀû µ¥ÀÌÅÍ ÀÔ·Â
	private static void studentInsert() {
		//¸â¹öº¯¼ö : ÇĞ¹ø, ÀÌ¸§, ¼ºº°, ±¹¾î, ¿µ¾î, ¼öÇĞ, ÃÑÁ¡, Æò±Õ, µî±Ş
		String id = null;
		String name = null;
		String gender = null;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int total = 0;
		double avr = 0.0;
		char grade = '\u0000';
		
		//ÇĞ¹ø ÀÔ·Â ÇÔ¼ö È£Ãâ
		while(true) {
			System.out.print("ID¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä. : ");
			id = scan.nextLine();

			if(id.length()>=1 && id.length() <= 10) {break;}
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		
		//µ¥ÀÌÅÍ ÀüÃ¼ Á¶È¸
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSelect();
		
		//id Áßº¹ È®ÀÎ
		if(list.size()>0) {
			for(StudentModel data : list) {
				if(id==data.getId()) {
					System.out.println("ÀÌ¹Ì ÀÔ·ÂµÈ idÀÔ´Ï´Ù.");
					return;
				}
			}
		}
		
		//ÀÌ¸§ ÀÔ·Â
		while(true) {
			System.out.print("ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä. : ");
			name = scan.nextLine();
			
			Pattern pattern = Pattern.compile("^[°¡-ÆR]*$");
			Matcher matcher = pattern.matcher(name);
			
			if(matcher.matches()) break;
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		//¼ºº° ÀÔ·Â
		while(true) {
			System.out.print("¼ºº°À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.(³²¼º/¿©¼º) : ");
			gender = scan.nextLine();
			
			if(gender.equals("³²¼º")||gender.equals("¿©¼º")) break;
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		//±¹¾î, ¿µ¾î, ¼öÇĞ ¼ºÀû ÀÔ·Â 
		while(true) {
			System.out.print("±¹¾îÁ¡¼ö¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä. : ");
			kor = scan.nextInt();
			
			if(kor >= 0 && kor <= 100) break;
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		while(true) {
			System.out.print("¿µ¾îÁ¡¼ö¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä. : ");
			eng = scan.nextInt();
			
			if(kor >= 0 && kor <= 100) break;
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		while(true) {
			System.out.print("¼öÇĞÁ¡¼ö¸¦ ÀÔ·ÂÇØ ÁÖ¼¼¿ä. : ");
			math = scan.nextInt();
			
			if(kor >= 0 && kor <= 100) break;
			else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		//StudentData °´Ã¼ »ı¼º
		StudentModel sd = new StudentModel(id, name, gender, kor, eng, math);
		
		//ÃÑÇÕ, Æò±Õ, µî±Ş °è»ê ÇÔ¼ö È£Ãâ(StudentData)
		total = sd.calTotal();
		sd.setTotal(total);
		avr = sd.calAvr();
		sd.setAvr(avr);
		grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//µ¥ÀÌÅÍº£ÀÌ½º¿¡ ÀúÀå 
		int result = DBController.dataInsert(sd);
		
		if(result!=0) System.out.println(name+"´ÔÀÇ ¼ºÀû ÀÔ·ÂÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		else System.out.println(name+"´ÔÀÇ ¼ºÀû ÀÔ·Â¿¡ ½ÇÆĞÇÏ¿´½À´Ï´Ù.");
	}
	
	
	
	//°³º° ¼ºÀû Á¶È¸
	private static void studentSearch() {
		String name = null;
		String searchData = null;
		
			while(true) {
				System.out.println("°Ë»öÇÒ ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ¼¼¿ä.");
				System.out.print(">> ");
				name = scan.nextLine();
				
				Pattern pattern = Pattern.compile("^[°¡-ÆR]*$");
				Matcher matcher = pattern.matcher(name);
				
				if(matcher.matches()) break;
				else System.out.println("Àß¸ø ÀÔ·ÂÇÏ¼Ì½À´Ï´Ù. ´Ù½Ã ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			}
			searchData = name;
			
		
		//µ¥ÀÌÅÍº£ÀÌ½º Á¶È¸ : °³º° µ¥ÀÌÅÍ
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSearch(searchData, 1);

		//Á¶È¸ °á°ú Ãâ·Â
		for(StudentModel data : list) {
			System.out.println(data);
		}
}

	
	//ÀüÃ¼ ¼ºÀû Á¶È¸ 
	private static void studentPrint() {
		List<StudentModel> list = new ArrayList<StudentModel>();
		
		//µ¥ÀÌÅÍº£ÀÌ½º Á¶È¸ : ÀüÃ¼ µ¥ÀÌÅÍ
		list = DBController.dataSelect();
		
		if(list.size()<=0) {
			System.out.println("ÀÔ·ÂµÈ µ¥ÀÌÅÍ°¡ ¾ø½À´Ï´Ù.");
			return;
		}
		
		//µ¥ÀÌÅÍ Ãâ·Â
		dataPrint(list);
		
		//ÀüÃ¼ ÇĞ»ı¼ö, ÀüÃ¼ ÃÑÁ¡, ÀüÃ¼ Æò±Õ °è»ê, °ú¸ñº° Æò±Õ
		int count = list.size();
		int sum = 0;
		double totalAvr = 0.0;
		double korAvr = 0.0;
		double engAvr = 0.0;
		double mathAvr = 0.0;
		
		for(StudentModel data : list) {
			sum += data.getTotal();
			totalAvr += data.getAvr();
			korAvr += data.getKor();
			engAvr += data.getEng();
			mathAvr += data.getMath();
		}
		
		totalAvr /= (double)count;
		korAvr /= (double)count;
		engAvr /= (double)count;
		mathAvr /= (double)count;
		
		System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
		System.out.println("ÀüÃ¼ ÇĞ»ı¼ö :"+count +"¸í \t ÀüÃ¼ ÃÑÁ¡ : "+sum+"Á¡ \t ÀüÃ¼ Æò±Õ : "+ String.format("%.2f", totalAvr)+"Á¡");
		System.out.println("±¹¾î Æò±Õ : "+String.format("%.2f", korAvr)+"Á¡ \t ¿µ¾î Æò±Õ : "+String.format("%.2f", engAvr) +"Á¡ \t ¼öÇĞ Æò±Õ : "+String.format("%.2f", mathAvr)+"Á¡");
		System.out.println("");
	}
	
	//¼ºÀû Á¤·Ä : ÇĞ¹ø¼ø, ÀÌ¸§¼ø, ¼ºÀû¼ø
	private static void studentSort() {
		//Á¶È¸ ¸Ş´º ¼±ÅÃ
		int menu = sortMenu();
		
		if(menu==4) {
			System.out.println("Ãë¼ÒÇÕ´Ï´Ù.");
			return;
		}

		//µ¥ÀÌÅÍº£ÀÌ½º Á¶È¸ : µ¥ÀÌÅÍ Á¤·Ä
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSort(menu);
		
		if(list.size()<=0) {
			System.out.println("µ¥ÀÌÅÍ°¡ ¾ø½À´Ï´Ù.");
			return;
		}
		
		//Á¤·ÄµÈ ¼ºÀû Ãâ·Â
		dataPrint(list);
	}
	
	//¼ºÀû Á¤·Ä ¸Ş´º ¼±ÅÃ : ÇĞ¹ø, ÀÌ¸§, ÃÑÁ¡
	private static int sortMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("**************************************************");
			System.out.println("   1.ÀÌ¸§¼ø Á¤·Ä   2. ¼ºÀû¼ø Á¤·Ä   3. Ãë¼Ò");
			System.out.println("**************************************************");
			System.out.print("¸Ş´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 »çÀÌÀÇ ¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		return menu;
	}
	
	//µ¥ÀÌÅÍ Ãâ·Â
	private static void dataPrint(List<StudentModel> list) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("ÇĞ¹ø\t"+"ÀÌ¸§\t"+"¼ºº°\t"+" ±¹¾î\t"+" ¿µ¾î\t"+" ¼öÇĞ\t"+" ÃÑÁ¡\t"+"  Æò±Õ\t"+" µî±Ş");
		System.out.println("----------------------------------------------------------------------");
		for(StudentModel data : list) System.out.println(data);
	}
	
	//¼ºÀû ¼öÁ¤ : ID·Î Á¶È¸, °ú¸ñ(±¹,¿µ,¼ö) ¼±ÅÃÇÏ¿© ¼öÁ¤
	private static void studentUpdate() {
		final int KOR=1, ENG=2, MATH=3, EXIT=4;
		String name = null;
		int menu = 0;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int result = 0;
		
		//ÇĞ¹ø ÀÔ·Â
		System.out.print("¼öÁ¤ÇÒ ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä : ");
		name = scan.nextLine();
		
		//¼öÁ¤ Àü µ¥ÀÌÅÍ È®ÀÎ : ÇĞ¹øÀ¸·Î µ¥ÀÌÅÍ Á¶È¸
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSearch(String.valueOf(name), 1);
		
		if(list.size()<=0) {
			System.out.println("ÀÔ·ÂµÈ µ¥ÀÌÅÍ°¡ ¾ø½À´Ï´Ù.");
			return;
		}
		
		System.out.println("ÇöÀç ¼ºÀûÀº ´ÙÀ½°ú °°½À´Ï´Ù.");
		dataPrint(list);
		
		//StudentData °´Ã¼·Î ÀúÀå
		StudentModel sd = list.get(0);
		
		//¼öÁ¤ÇÒ ¼ºÀû ¼±ÅÃ
		menu = updateMenu();
		
		switch(menu) {
		case KOR : 
			System.out.print("¼öÁ¤ÇÒ ±¹¾îÁ¡¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
			kor = scan.nextInt();
			sd.setKor(kor); break;
		case ENG : 
			System.out.print("¼öÁ¤ÇÒ ¿µ¾îÁ¡¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
			eng = scan.nextInt();
			sd.setEng(eng);	break;
		case MATH : 
			System.out.print("¼öÁ¤ÇÒ ¼öÇĞÁ¡¼ö¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
			math = scan.nextInt();
			sd.setMath(math); break;
		case EXIT : 
			System.out.println("¼öÁ¤À» Ãë¼ÒÇÕ´Ï´Ù.");
			return;
		}
		
		//ÃÑÁ¡, Æò±Õ, µî±Ş °è»ê
		int total = sd.calTotal();
		sd.setTotal(total);
		double avr = sd.calAvr();
		sd.setAvr(avr);
		char grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//µ¥ÀÌÅÍº£ÀÌ½º ¼öÁ¤
		result = DBController.dataUpdate(sd, menu);
		
		if(result!=0) System.out.println("¼ºÀû ¼öÁ¤ÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		else System.out.println("¼ºÀû ¼öÁ¤¿¡ ½ÇÆĞÇÏ¿´½À´Ï´Ù.");
	}
	
	//¼öÁ¤ÇÒ °ú¸ñ ¼±ÅÃ
	private static int updateMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("**************************************************");
			System.out.println("   1. ±¹¾î¼öÁ¤ 2. ¿µ¾î¼öÁ¤ 3. ¼öÇĞ¼öÁ¤  4. Ãë¼Ò");
			System.out.println("**************************************************");
			System.out.print("¸Ş´º ¼±ÅÃ >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			} catch(Exception e) {
				System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 »çÀÌÀÇ ¼ıÀÚ¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
		
		return menu;
	}

	//¼ºÀû »èÁ¦ : ID·Î °Ë»ö
	private static void studentDelete() {
		String id = null;
		int result = 0;
		
		//ÇĞ¹ø ÀÔ·Â
		System.out.print("»èÁ¦ÇÒ ID¸¦ ÀÔ·ÂÇÏ¼¼¿ä : ");
		id = scan.nextLine();
		
		//µ¥ÀÌÅÍº£ÀÌ½º »èÁ¦
		result = DBController.dataDelete(id);

		if(result!=0) System.out.println("¼ºÀû »èÁ¦°¡ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		else System.out.println("¼ºÀû »èÁ¦¿¡ ½ÇÆĞÇÏ¿´½À´Ï´Ù.");
	}
}
