package studentProgram;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentMain {
	//�޴� : ���� �Է�, ���� ���� ��ȸ, ��ü ���� ��ȸ, ������ ����, ���� ����, ���� ����, ����
	public static final int INSERT=1, SEARCH=2, PRINT=3, SORT=4, UPDATE=5, DELETE=6, EXIT=7;
	
	//��ĳ�� ����
	public static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		int menu = 0;
		boolean flag = false;
		
		//�޴� ���� : ���� �Է�, ���� ���� ��ȸ, ��ü ���� ��ȸ, ������ ����, ���� ����, ���� ����, ����
		while(!flag) {
			//�޴� ���� �Լ� ȣ��
			menu = selectMenu();
			switch(menu) {
			case INSERT: studentInsert(); break;
			case SEARCH: studentSearch(); break;
			case PRINT: studentPrint(); break;
			case SORT: studentSort(); break;
			case UPDATE: studentUpdate(); break;
			case DELETE: studentDelete(); break;
			case EXIT: System.out.println("���� ���α׷��� �����մϴ�.");	flag = true; break;
			}
		}
	}
	
	//�޴� ����
	private static int selectMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("**************************************************************************");
			System.out.println("   1. �Է� 2. ���� ��ȸ 3. ��ü ���� ��ȸ  4. ���� 5. ���� 6. ����  7. ����");
			System.out.println("**************************************************************************");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			}
			
			if(menu>0 && menu<8) flag = true;
			else System.out.println("1~7 ������ ���ڸ� �Է����ּ���.");
		}
		return menu;
	}
	
	//���� ������ �Է�
	private static void studentInsert() {
		//������� : �й�, �̸�, ����, ����, ����, ����, ����, ���, ���
		String id = null;
		String name = null;
		String gender = null;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int total = 0;
		double avr = 0.0;
		char grade = '\u0000';
		
		//�й� �Է� �Լ� ȣ��
		while(true) {
			System.out.print("ID�� �Է��� �ּ���. : ");
			id = scan.nextLine();

			if(id.length()>=1 && id.length() <= 10) {break;}
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		
		
		//������ ��ü ��ȸ
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSelect();
		
		//id �ߺ� Ȯ��
		if(list.size()>0) {
			for(StudentModel data : list) {
				if(id==data.getId()) {
					System.out.println("�̹� �Էµ� id�Դϴ�.");
					return;
				}
			}
		}
		
		//�̸� �Է�
		while(true) {
			System.out.print("�̸��� �Է��� �ּ���. : ");
			name = scan.nextLine();
			
			Pattern pattern = Pattern.compile("^[��-�R]*$");
			Matcher matcher = pattern.matcher(name);
			
			if(matcher.matches()) break;
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		
		//���� �Է�
		while(true) {
			System.out.print("������ �Է��� �ּ���.(����/����) : ");
			gender = scan.nextLine();
			
			if(gender.equals("����")||gender.equals("����")) break;
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		
		//����, ����, ���� ���� �Է� 
		while(true) {
			System.out.print("���������� �Է��� �ּ���. : ");
			kor = scan.nextInt();
			
			if(kor >= 0 && kor <= 100) break;
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		while(true) {
			System.out.print("���������� �Է��� �ּ���. : ");
			eng = scan.nextInt();
			
			if(kor >= 0 && kor <= 100) break;
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		while(true) {
			System.out.print("���������� �Է��� �ּ���. : ");
			math = scan.nextInt();
			
			if(kor >= 0 && kor <= 100) break;
			else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
		}
		
		//StudentData ��ü ����
		StudentModel sd = new StudentModel(id, name, gender, kor, eng, math);
		
		//����, ���, ��� ��� �Լ� ȣ��(StudentData)
		total = sd.calTotal();
		sd.setTotal(total);
		avr = sd.calAvr();
		sd.setAvr(avr);
		grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//�����ͺ��̽��� ���� 
		int result = DBController.dataInsert(sd);
		
		if(result!=0) System.out.println(name+"���� ���� �Է��� �Ϸ�Ǿ����ϴ�.");
		else System.out.println(name+"���� ���� �Է¿� �����Ͽ����ϴ�.");
	}
	
	
	
	//���� ���� ��ȸ
	private static void studentSearch() {
		String name = null;
		String searchData = null;
		
			while(true) {
				System.out.println("�˻��� �̸��� �Է��� �ּ���.");
				System.out.print(">> ");
				name = scan.nextLine();
				
				Pattern pattern = Pattern.compile("^[��-�R]*$");
				Matcher matcher = pattern.matcher(name);
				
				if(matcher.matches()) break;
				else System.out.println("�߸� �Է��ϼ̽��ϴ�. �ٽ� �Է����ּ���.");
			}
			searchData = name;
			
		
		//�����ͺ��̽� ��ȸ : ���� ������
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSearch(searchData, 1);

		//��ȸ ��� ���
		for(StudentModel data : list) {
			System.out.println(data);
		}
}

	
	//��ü ���� ��ȸ 
	private static void studentPrint() {
		List<StudentModel> list = new ArrayList<StudentModel>();
		
		//�����ͺ��̽� ��ȸ : ��ü ������
		list = DBController.dataSelect();
		
		if(list.size()<=0) {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�.");
			return;
		}
		
		//������ ���
		dataPrint(list);
		
		//��ü �л���, ��ü ����, ��ü ��� ���, ���� ���
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
		System.out.println("��ü �л��� :"+count +"�� \t ��ü ���� : "+sum+"�� \t ��ü ��� : "+ String.format("%.2f", totalAvr)+"��");
		System.out.println("���� ��� : "+String.format("%.2f", korAvr)+"�� \t ���� ��� : "+String.format("%.2f", engAvr) +"�� \t ���� ��� : "+String.format("%.2f", mathAvr)+"��");
		System.out.println("");
	}
	
	//���� ���� : �й���, �̸���, ������
	private static void studentSort() {
		//��ȸ �޴� ����
		int menu = sortMenu();
		
		if(menu==4) {
			System.out.println("����մϴ�.");
			return;
		}

		//�����ͺ��̽� ��ȸ : ������ ����
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSort(menu);
		
		if(list.size()<=0) {
			System.out.println("�����Ͱ� �����ϴ�.");
			return;
		}
		
		//���ĵ� ���� ���
		dataPrint(list);
	}
	
	//���� ���� �޴� ���� : �й�, �̸�, ����
	private static int sortMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("**************************************************");
			System.out.println("   1.�̸��� ����   2. ������ ����   3. ���");
			System.out.println("**************************************************");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 ������ ���ڸ� �Է����ּ���.");
		}
		
		return menu;
	}
	
	//������ ���
	private static void dataPrint(List<StudentModel> list) {
		System.out.println("----------------------------------------------------------------------");
		System.out.println("�й�\t"+"�̸�\t"+"����\t"+" ����\t"+" ����\t"+" ����\t"+" ����\t"+"  ���\t"+" ���");
		System.out.println("----------------------------------------------------------------------");
		for(StudentModel data : list) System.out.println(data);
	}
	
	//���� ���� : ID�� ��ȸ, ����(��,��,��) �����Ͽ� ����
	private static void studentUpdate() {
		final int KOR=1, ENG=2, MATH=3, EXIT=4;
		String name = null;
		int menu = 0;
		int kor = 0;
		int eng = 0;
		int math = 0;
		int result = 0;
		
		//�й� �Է�
		System.out.print("������ �̸��� �Է��ϼ��� : ");
		name = scan.nextLine();
		
		//���� �� ������ Ȯ�� : �й����� ������ ��ȸ
		List<StudentModel> list = new ArrayList<StudentModel>();
		list = DBController.dataSearch(String.valueOf(name), 1);
		
		if(list.size()<=0) {
			System.out.println("�Էµ� �����Ͱ� �����ϴ�.");
			return;
		}
		
		System.out.println("���� ������ ������ �����ϴ�.");
		dataPrint(list);
		
		//StudentData ��ü�� ����
		StudentModel sd = list.get(0);
		
		//������ ���� ����
		menu = updateMenu();
		
		switch(menu) {
		case KOR : 
			System.out.print("������ ���������� �Է��ϼ��� : ");
			kor = scan.nextInt();
			sd.setKor(kor); break;
		case ENG : 
			System.out.print("������ ���������� �Է��ϼ��� : ");
			eng = scan.nextInt();
			sd.setEng(eng);	break;
		case MATH : 
			System.out.print("������ ���������� �Է��ϼ��� : ");
			math = scan.nextInt();
			sd.setMath(math); break;
		case EXIT : 
			System.out.println("������ ����մϴ�.");
			return;
		}
		
		//����, ���, ��� ���
		int total = sd.calTotal();
		sd.setTotal(total);
		double avr = sd.calAvr();
		sd.setAvr(avr);
		char grade = sd.calGrade(avr);
		sd.setGrade(grade);
		
		//�����ͺ��̽� ����
		result = DBController.dataUpdate(sd, menu);
		
		if(result!=0) System.out.println("���� ������ �Ϸ�Ǿ����ϴ�.");
		else System.out.println("���� ������ �����Ͽ����ϴ�.");
	}
	
	//������ ���� ����
	private static int updateMenu() {
		boolean flag = false;
		int menu = 0;
		
		while(!flag) {
			System.out.println("**************************************************");
			System.out.println("   1. ������� 2. ������� 3. ���м���  4. ���");
			System.out.println("**************************************************");
			System.out.print("�޴� ���� >> ");
			try {
				menu = Integer.parseInt(scan.nextLine());
			} catch(InputMismatchException e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			} catch(Exception e) {
				System.out.println("���ڸ� �Է����ּ���.");
				continue;
			}
			
			if(menu>0 && menu<5) flag = true;
			else System.out.println("1~4 ������ ���ڸ� �Է����ּ���.");
		}
		
		return menu;
	}

	//���� ���� : ID�� �˻�
	private static void studentDelete() {
		String id = null;
		int result = 0;
		
		//�й� �Է�
		System.out.print("������ ID�� �Է��ϼ��� : ");
		id = scan.nextLine();
		
		//�����ͺ��̽� ����
		result = DBController.dataDelete(id);

		if(result!=0) System.out.println("���� ������ �Ϸ�Ǿ����ϴ�.");
		else System.out.println("���� ������ �����Ͽ����ϴ�.");
	}
}
