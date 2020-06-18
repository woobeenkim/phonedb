package com.javaex.phone;


import java.util.List;
import java.util.Scanner;


public class PhoneApp {
	public static void main(String[] args){
		PhoneDao phonedao = new PhoneDao();
		
		//시작
		phonedao.Start();
		
		Scanner sc = new Scanner(System.in);

		while(true){
			System.out.println("1.리스트  2.등록  3.수정  4.삭제  5.검색  6.종료");
			System.out.println("------------------------------------------");
			System.out.print(">메뉴번호: ");
			System.out.print("");
			int a = sc.nextInt();
			switch(a) {
			case 1:{
			//리스트	
			
				System.out.println("<1.리스트>");
				List<PersonVo> PL = phonedao.Select();
				for(PersonVo pv : PL)
				{
					System.out.println(pv.getPersonID()+".  "+pv.getName()+"    "+pv.getHp()+"    "+pv.getCompany());
				}
				break;
			}
		
			case 2:{
			//삽입
				System.out.println("<2.등록>");
				PersonVo vo01 = new PersonVo();
				System.out.print("이름 : ");
				vo01.setName(sc.next());
				System.out.print("휴대전화 : ");
				vo01.setHp(sc.next());
				System.out.print("회사전화 : ");
				vo01.setCompany(sc.next());
				System.out.print("[등록되었습니다.]");
				System.out.println("");
				System.out.println(vo01);
				phonedao.Insert(vo01);
				System.out.println("");
				break;
			}
			case 3:{
				//수정
				System.out.println("<3.수정>");
				PersonVo vo01 = new PersonVo();
				System.out.print("번호 : ");
				vo01.setPersonID(sc.nextInt());
				System.out.print("이름 : ");
				vo01.setName(sc.next());
				System.out.print("휴대전화 : ");
				vo01.setHp(sc.next());
				System.out.print("회사전화 : ");
				vo01.setCompany(sc.next());
				phonedao.Update(vo01);
				System.out.println("");
				break;
			}
			case 4:{
			//삭제
				System.out.println("<4.삭제>");

				System.out.print("삭제할 대상의 고유번호를 입력해 주세요 ");
				phonedao.Delete(sc.nextInt());
				System.out.println("");
				break;
			}
			case 5:{
			//검색
				System.out.println("<5.검색>");
				System.out.println("검색어 : ");
				List<PersonVo> PL = phonedao.Find(sc.next());
				for(PersonVo pv : PL)
				{
					System.out.println(pv.getPersonID()+".  "+pv.getName()+"    "+pv.getHp()+"    "+pv.getCompany());
				}
				break;
			}
			
			case 6:{
			//끝
				phonedao.End();
				break;
				
			}
			
			default:{
			
					System.out.println("잘못 입력 하셨습니다.");	
					System.out.println("다시 입력해 주세요.");
					
				}
			
			}
			if(a==6)
			break;
		}
	}
}
