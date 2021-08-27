package day20_DB;

import java.util.ArrayList;
import java.util.Scanner;

public class MainClass {

	public static void main(String[] args) {
		
		DBClass db = new DBClass();
		Scanner input = new Scanner(System.in);
		
		int choice,result,age;
		String stNum,name;
		
		while (true) {
			System.out.println("1. 등록하기");
			System.out.println("2. 전체보기");
			System.out.println("3. 삭제하기");
			System.out.println("4. 수정하기");
			System.out.println("5. 종료하기");
			System.out.print(">> ");
			choice = input.nextInt();
			
			switch (choice) {
			case 1 : 
				System.out.print("학번 입력 : ");
				stNum = input.next();
				System.out.print("이름 입력 : ");
				name = input.next();
				System.out.print("나이 입력 : ");
				age = input.nextInt();
				
				//result = db.saveData(stNum,name,age);
				result = db.saveData02(stNum,name,age);
				if (result == 1) {
					System.out.println("저장이 완료되었습니다");
				} else {
					System.out.println("동일한 학번이 존재합니다");
				}
				break;
			case 2 :
				ArrayList<StudentDTO> list = db.getUsers();
				if(list.size()==0) {
					System.out.println("저장된 데이터가 없습니다");
				} else {
					for(int i=0;i<list.size();i++) {
						System.out.println("학번 : " + list.get(i).getStNum());
						System.out.println("이름 : " + list.get(i).getName());
						System.out.println("나이 : " + list.get(i).getAge());
						System.out.println("-------------------------------");
					}
				}
				break;
			case 3 :
				System.out.print("학번 입력 : ");
				stNum = input.next();
				result = db.delete(stNum);
				if (result==1) {
					System.out.println("삭제 되었습니다");
				} else {
					System.out.println("해당 학번은 존재하지 않습니다");
				}
				break;
			case 4 :
				System.out.print("학번 입력 : ");
				stNum = input.next();
				System.out.print("수정할 이름 입력 : ");
				name = input.next();
				System.out.print("수정할 나이 입력 : ");
				age = input.nextInt();
				
				if (db.modify(stNum,name,age) == 1) {
					System.out.println("수정이 완료 되었습니다");
				} else {
					System.out.println("해당 학번은 존재하지 않습니다");
				}
				
				break;
			case 5 :
				return;
			}
		}
		
		
	}
}
