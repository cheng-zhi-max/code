public class StuInfo {
	String sno;      //����ѧ��
	String sname;    //��������
	int scj;        //�ɼ�

	public StuInfo() {
	} //�޲κ���

	public StuInfo(String sno, String sname, int scj) {
		this.sno = sno;
		this.sname = sname;
		this.scj = scj;
	}//�вκ���

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public int getScj() {
		return scj;
	}

	public void setScj(int scj) {
		this.scj = scj;
	}

	public void display() {
		System.out.println(sno + "\t\t" + sname + "\t\t" + scj);
	}

}
