import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StuArrayList {
	/**
	 * 主函数 泛型
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<StuInfo> arrstu = new ArrayList<StuInfo>();
		while (true) {
			System.out
					.println("========================================================");
			System.out
					.println("1.添加  2.查找  3.排序  4.修改  5.删除  6.显示  7.读取  8.保存   0.退出");
			System.out
					.println("========================================================");
			System.out.print("请输入你的选择：");
			int czflag = sc.nextInt();
			switch (czflag) {
			case 1:
				// 1.添加
				minput(arrstu);
				break;
			case 2:
				// 2.查找
				System.out.print("请输入要查找学生的学号：");
				String strno = sc.next();
				int ifind = mfind(arrstu, strno);
				if (ifind == -1) {
					System.out.println("你要查找的学生不存在！");
				} else {
					StuInfo stu = (StuInfo) arrstu.get(ifind);
					System.out.println("学号\t\t姓名\t\t分数");
					stu.display();
				}
				break;
			case 3:
				// 3.排序
				msort(arrstu);
				break;
			case 4:
				// 4.修改
				mupdate(arrstu);
				break;
			case 5:
				// 5.删除
				mdelete(arrstu);
				break;
			case 6:
				// 6.显示
				try {
					mshow(arrstu);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 7:
				// 7.读取
				mread(arrstu);
				break;
			case 8:
				// 8.保存
				mwrite(arrstu);
				break;
			case 0:
				// 0.退出
				sc.close();
				sc = null;
				System.gc();
				System.exit(0);
				break;
			default:
				// 其它错误指令
				System.out.println("输入指令错误。");
				break;
			}
		}
	}

	/**
	 * 添加学生成绩信息函数
	 * 
	 * @param arrstu
	 *            存储学生成绩信息的动态数组
	 */
	public static void minput(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("请输入学号(输入0退出)：");
			String number = sc.next();
			if (number.equals("0")) {
				break;
			}

			boolean findflag = false;
			int n = arrstu.size();
			for (int i = 0; i < n; i++) {
				StuInfo stu = (StuInfo) arrstu.get(i);
				if (number.equals(stu.getSno())) {
					findflag = true;
					break;
				}
			}
			if (!findflag) {
				System.out.print("请输入姓名：");
				String strname = sc.next();
				System.out.print("请输入分数：");
				int iscore = sc.nextInt();
				StuInfo student = new StuInfo(number, strname, iscore);
				arrstu.add(student);
			} else {
				System.out.println("学号重复,请重新输入.");
			}
		}
	}

	/**
	 * 按学号查找学生成绩信息的函数
	 * 
	 * @param arr
	 *            存储学生成绩信息的动态数组
	 * @param number
	 *            要查找的学生学号
	 * @return 要查找的学生不存在，返回-1，否则返回索引值
	 */
	public static int mfind(ArrayList<StuInfo> arrstu, String number) {
		int index = -1;
		int n = arrstu.size();
		for (int i = 0; i < n; i++) {
			StuInfo stu = (StuInfo) arrstu.get(i);
			if (number.equals(stu.getSno())) {
				index = i;
				break;
			}
		}
		return index;
	}

	/**
	 * 按学生成绩排序的函数
	 * 
	 * @param arrstu
	 *            存储学生成绩信息的动态数组
	 */
	public static void msort(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		int N = arrstu.size();
		System.out.print("1.按照成绩排序（从大到小）  2.按照学号排序（从小到大）；请输入：");
		int x = sc.nextInt();
		if (x == 1) {
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (arrstu.get(j).getScj() > arrstu.get(i).getScj()) {
						StuInfo Temp = arrstu.get(j);
						arrstu.set(j, arrstu.get(i));
						arrstu.set(i, Temp);
					}
				}
			}
			System.out.println("排序完成！");
		} else if (x == 2) {
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					if (arrstu.get(j).getSno()
							.compareTo(arrstu.get(i).getSno()) < 0) {
						StuInfo Temp = arrstu.get(j);
						arrstu.set(j, arrstu.get(i));
						arrstu.set(i, Temp);
					}
				}
			}
			System.out.println("排序完成！");
		} else {
			System.out.println("排序指令错误，排序失败！");
		}
	}

	/**
	 * 按学号修改学生成绩信息
	 * 
	 * @param arr
	 *            存储学生成绩信息的动态数组
	 */
	public static void mupdate(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要修改学生的学号：");
		String number = sc.next();
		int index = mfind(arrstu, number);
		if (index == -1) {
			System.out.println("你要修改的学生不存在！");
		} else {
			StuInfo student = (StuInfo) arrstu.get(index);
			System.out.println("已经找到该学号的学生信息：");
			System.out.println("学号\t\t姓名\t\t分数");
			student.display();

			System.out.print("1.修改学号  2.修改姓名  3.修改分数  0.退出；请输入：");
			int n = sc.nextInt();
			if (n == 1) {
				System.out.print("请输入新的学号：");
				String strnum = sc.next();
				int iflag = mfind(arrstu, strnum);
				if (iflag == -1) {
					arrstu.get(index).setSno(strnum);
					System.out.println("修改成功！");
				} else {
					System.out.print("学号重复，不能修改。");
				}
			} else if (n == 2) {
				System.out.print("请输入新的姓名：");
				String strname = sc.next();
				arrstu.get(index).setSname(strname);
				System.out.println("修改成功！");
			} else if (n == 3) {
				System.out.print("请输入新的成绩：");
				int iscore = sc.nextInt();
				arrstu.get(index).setScj(iscore);
				System.out.println("修改成功！");
			} else if (n == 0) {
				return;
			} else {
				System.out.println("指令错误！");
				return;
			}
		}
	}

	/**
	 * 按学号删除学生成绩信息的函数
	 * 
	 * @param arrstu
	 *            存储学生成绩信息的动态数组
	 */
	public static void mdelete(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		System.out.print("请输入要删除学生的学号：");
		String number = sc.next();
		int index = mfind(arrstu, number);
		if (index == -1) {
			System.out.println("你要删除的学生不存在！");
		} else {
			StuInfo student = (StuInfo) arrstu.get(index);
			System.out.println("找到学生信息：");
			System.out.println("学号\t\t姓名\t\t分数");
			student.display();
			System.out.print("确认删除?(y/n):");
			String xz = sc.next();
			if (xz.toLowerCase().equals("y")) {
				arrstu.remove(index);
				System.out.println("删除成功！");
			} else {
				System.out.println("您已经撤销删除操作！");
			}
		}

	}

	/**
	 * 输出学生成绩表的函数
	 * 
	 * @param arrstu
	 *            存储学生成绩信息的动态数组
	 */
	public static void mshow(ArrayList<StuInfo> arrstu) throws Exception {
		int n = arrstu.size();
		if (n == 0) {
			System.out.println("学生成绩表为空！");
			return;
		}
		System.out.println("\t\t学生成绩表");
		System.out.println("=====================================");
		System.out.print("学号\t\t姓名\t\t分数\n");
		for (int i = 0; i < n; i++) {
			arrstu.get(i).display();
		}
	}

	/**
	 * 从磁盘文件中读取数据，存储到动态数组的函数 read write
	 * 
	 * @param arrstu
	 *            存储学生成绩信息的动态数组
	 */
	public static void mread(ArrayList<StuInfo> arrstu) {
		File file = new File("C:\\student.txt");
		if (file.exists()) {
			FileInputStream is = null;
			try {
				is = new FileInputStream(file);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			arrstu.clear();
			Scanner sc = new Scanner(is);
			while (sc.hasNext()) {
				String number = sc.next();
				String name = sc.next();
				int score = sc.nextInt();
				StuInfo student = new StuInfo(number, name, score);
				arrstu.add(student);
			}
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("文件读取成功！");
		} else {
			System.out.println("文件不存在！");
		}
	}

	/**
	 * 把动态数组中的数据，写入到磁盘文件中的函数
	 * 
	 * @param arrstu
	 *            存储学生成绩信息的动态数组
	 */
	public static void mwrite(ArrayList<StuInfo> arrstu) {
		FileWriter wr = null;
		try {
			wr = new FileWriter("C:\\student.txt");
			int n = arrstu.size();
			for (int i = 0; i < n; i++) {
				wr.write(arrstu.get(i).getSno() + "\t"
						+ arrstu.get(i).getSname() + "\t"
						+ arrstu.get(i).getScj() + "\r\n");
				wr.flush();
			}
			wr.close();
			System.out.println("文件保存成功！");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
