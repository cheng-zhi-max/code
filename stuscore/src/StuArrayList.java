import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StuArrayList {
	/**
	 * ������ ����
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ArrayList<StuInfo> arrstu = new ArrayList<StuInfo>();
		while (true) {
			System.out
					.println("========================================================");
			System.out
					.println("1.���  2.����  3.����  4.�޸�  5.ɾ��  6.��ʾ  7.��ȡ  8.����   0.�˳�");
			System.out
					.println("========================================================");
			System.out.print("���������ѡ��");
			int czflag = sc.nextInt();
			switch (czflag) {
			case 1:
				// 1.���
				minput(arrstu);
				break;
			case 2:
				// 2.����
				System.out.print("������Ҫ����ѧ����ѧ�ţ�");
				String strno = sc.next();
				int ifind = mfind(arrstu, strno);
				if (ifind == -1) {
					System.out.println("��Ҫ���ҵ�ѧ�������ڣ�");
				} else {
					StuInfo stu = (StuInfo) arrstu.get(ifind);
					System.out.println("ѧ��\t\t����\t\t����");
					stu.display();
				}
				break;
			case 3:
				// 3.����
				msort(arrstu);
				break;
			case 4:
				// 4.�޸�
				mupdate(arrstu);
				break;
			case 5:
				// 5.ɾ��
				mdelete(arrstu);
				break;
			case 6:
				// 6.��ʾ
				try {
					mshow(arrstu);
				} catch (Exception e) {
					e.printStackTrace();
				}
				break;
			case 7:
				// 7.��ȡ
				mread(arrstu);
				break;
			case 8:
				// 8.����
				mwrite(arrstu);
				break;
			case 0:
				// 0.�˳�
				sc.close();
				sc = null;
				System.gc();
				System.exit(0);
				break;
			default:
				// ��������ָ��
				System.out.println("����ָ�����");
				break;
			}
		}
	}

	/**
	 * ���ѧ���ɼ���Ϣ����
	 * 
	 * @param arrstu
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
	 */
	public static void minput(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("������ѧ��(����0�˳�)��");
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
				System.out.print("������������");
				String strname = sc.next();
				System.out.print("�����������");
				int iscore = sc.nextInt();
				StuInfo student = new StuInfo(number, strname, iscore);
				arrstu.add(student);
			} else {
				System.out.println("ѧ���ظ�,����������.");
			}
		}
	}

	/**
	 * ��ѧ�Ų���ѧ���ɼ���Ϣ�ĺ���
	 * 
	 * @param arr
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
	 * @param number
	 *            Ҫ���ҵ�ѧ��ѧ��
	 * @return Ҫ���ҵ�ѧ�������ڣ�����-1�����򷵻�����ֵ
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
	 * ��ѧ���ɼ�����ĺ���
	 * 
	 * @param arrstu
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
	 */
	public static void msort(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		int N = arrstu.size();
		System.out.print("1.���ճɼ����򣨴Ӵ�С��  2.����ѧ�����򣨴�С���󣩣������룺");
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
			System.out.println("������ɣ�");
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
			System.out.println("������ɣ�");
		} else {
			System.out.println("����ָ���������ʧ�ܣ�");
		}
	}

	/**
	 * ��ѧ���޸�ѧ���ɼ���Ϣ
	 * 
	 * @param arr
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
	 */
	public static void mupdate(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		System.out.print("������Ҫ�޸�ѧ����ѧ�ţ�");
		String number = sc.next();
		int index = mfind(arrstu, number);
		if (index == -1) {
			System.out.println("��Ҫ�޸ĵ�ѧ�������ڣ�");
		} else {
			StuInfo student = (StuInfo) arrstu.get(index);
			System.out.println("�Ѿ��ҵ���ѧ�ŵ�ѧ����Ϣ��");
			System.out.println("ѧ��\t\t����\t\t����");
			student.display();

			System.out.print("1.�޸�ѧ��  2.�޸�����  3.�޸ķ���  0.�˳��������룺");
			int n = sc.nextInt();
			if (n == 1) {
				System.out.print("�������µ�ѧ�ţ�");
				String strnum = sc.next();
				int iflag = mfind(arrstu, strnum);
				if (iflag == -1) {
					arrstu.get(index).setSno(strnum);
					System.out.println("�޸ĳɹ���");
				} else {
					System.out.print("ѧ���ظ��������޸ġ�");
				}
			} else if (n == 2) {
				System.out.print("�������µ�������");
				String strname = sc.next();
				arrstu.get(index).setSname(strname);
				System.out.println("�޸ĳɹ���");
			} else if (n == 3) {
				System.out.print("�������µĳɼ���");
				int iscore = sc.nextInt();
				arrstu.get(index).setScj(iscore);
				System.out.println("�޸ĳɹ���");
			} else if (n == 0) {
				return;
			} else {
				System.out.println("ָ�����");
				return;
			}
		}
	}

	/**
	 * ��ѧ��ɾ��ѧ���ɼ���Ϣ�ĺ���
	 * 
	 * @param arrstu
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
	 */
	public static void mdelete(ArrayList<StuInfo> arrstu) {
		Scanner sc = new Scanner(System.in);
		System.out.print("������Ҫɾ��ѧ����ѧ�ţ�");
		String number = sc.next();
		int index = mfind(arrstu, number);
		if (index == -1) {
			System.out.println("��Ҫɾ����ѧ�������ڣ�");
		} else {
			StuInfo student = (StuInfo) arrstu.get(index);
			System.out.println("�ҵ�ѧ����Ϣ��");
			System.out.println("ѧ��\t\t����\t\t����");
			student.display();
			System.out.print("ȷ��ɾ��?(y/n):");
			String xz = sc.next();
			if (xz.toLowerCase().equals("y")) {
				arrstu.remove(index);
				System.out.println("ɾ���ɹ���");
			} else {
				System.out.println("���Ѿ�����ɾ��������");
			}
		}

	}

	/**
	 * ���ѧ���ɼ���ĺ���
	 * 
	 * @param arrstu
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
	 */
	public static void mshow(ArrayList<StuInfo> arrstu) throws Exception {
		int n = arrstu.size();
		if (n == 0) {
			System.out.println("ѧ���ɼ���Ϊ�գ�");
			return;
		}
		System.out.println("\t\tѧ���ɼ���");
		System.out.println("=====================================");
		System.out.print("ѧ��\t\t����\t\t����\n");
		for (int i = 0; i < n; i++) {
			arrstu.get(i).display();
		}
	}

	/**
	 * �Ӵ����ļ��ж�ȡ���ݣ��洢����̬����ĺ��� read write
	 * 
	 * @param arrstu
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
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
			System.out.println("�ļ���ȡ�ɹ���");
		} else {
			System.out.println("�ļ������ڣ�");
		}
	}

	/**
	 * �Ѷ�̬�����е����ݣ�д�뵽�����ļ��еĺ���
	 * 
	 * @param arrstu
	 *            �洢ѧ���ɼ���Ϣ�Ķ�̬����
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
			System.out.println("�ļ�����ɹ���");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
