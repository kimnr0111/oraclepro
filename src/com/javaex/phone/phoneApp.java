package com.javaex.phone;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class phoneApp {

	public static void main(String[] args) {
		
		PhoneDao phoneDao = new PhoneDao();
		boolean menuflag = true;
		int menu;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("*******************************************");
    	System.out.println("*             전화번호 관리 프로그램                      *");
    	System.out.println("*******************************************");
    	
    	while(menuflag) {
    		try {
    			System.out.println("1.리스트 2.등록 3.수정 4.삭제 5.검색 6.종료");
        		System.out.println("----------------------------------");
        		System.out.print(">메뉴번호:");
        		menu = sc.nextInt();
        		switch(menu) {
        		case 1:
        			System.out.println("<1.리스트>");
        			List<PhoneVo> phoneList = phoneDao.getPhoneList("");
        			for(PhoneVo list: phoneList) {
        				System.out.println(list.getPersonId() + "\t" + list.getName() + "\t" + list.getHp() + 
        						"\t" + list.getCompany());
        			}
        			break;
        		case 2:
        			System.out.println("<2.등록>");
               		System.out.print(">이름: ");
               		String inName = sc.next();
               		System.out.print(">휴대전화: ");
               		String inHp = sc.next();
               		System.out.print(">회사전화: ");
               		String inCompany = sc.next();
               		PhoneVo insert = new PhoneVo(inName, inHp, inCompany);
            		phoneDao.phoneInsert(insert);
            		System.out.println("[등록되었습니다.]");
            		break;
        		case 3:
               		System.out.println("<3.수정>");
               		System.out.print(">번호: ");
               		int upId = sc.nextInt();
               		System.out.print(">이름: ");
               		String upName = sc.next();
               		System.out.print(">휴대전화: ");
               		String upHp = sc.next();
               		System.out.print(">회사전화: ");
               		String upCompany = sc.next();
               		PhoneVo update = new PhoneVo(upId, upName, upHp, upCompany);
               		phoneDao.phoneUpdate(update);
        			break;
        		case 4:
               		System.out.println("<4.삭제>");
               		System.out.print(">번호: ");
               		int Id = sc.nextInt();
               		PhoneVo delete = new PhoneVo(Id);
               		phoneDao.phoneDelete(delete);
        			break;
        		case 5:
               		System.out.println("<5.검색>");
               		System.out.print(">검색어: ");
               		String str = sc.next();
               		List<PhoneVo> searchList = phoneDao.getPhoneList(str);
               		for(PhoneVo list: searchList) {
               			if(list.getName().contains(str) || list.getHp().contains(str) || list.getCompany().contains(str)) {
               				System.out.println(list.getPersonId() + "\t" + list.getName() + "\t" + list.getHp() + 
            						"\t" + list.getCompany());
               			}
               		}
        			break;
        		case 6:
        			menuflag = false;
        			System.out.println("*******************************************");
        	    	System.out.println("*                  감사합니다                            *");
        	    	System.out.println("*******************************************");
        			break;
        		default:
        			System.out.println("[다시 입력해 주세요.]");
        			break;
        		}
    		} catch(InputMismatchException e) {
    			System.out.println("[다시 입력해 주세요.]");
    			sc.nextLine();
    		}
    		
    		
    	}
    	sc.close();

	}

}
