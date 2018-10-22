package Controller;

public class Reg_Controller {
	public boolean id_chk;
	public boolean nick_chk;
	public boolean pw_chk;	
	char[] pw1, pw2;
	
	public Reg_Controller() {
		this.id_chk = false;
		this.pw_chk = false;
		this.nick_chk = false;
		String pw1 = "";
		String pw2 = "";
	}	
	
	public boolean is_OK() {
		return this.id_chk && this.pw_chk && this.nick_chk;
	}
	
	
}