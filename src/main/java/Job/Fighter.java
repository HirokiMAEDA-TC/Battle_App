package Job;

public class Fighter extends Job{
	public String weapon = "拳";
	String recover_tool = "やくそう";

	public String attack(String name) {
		String msg = name + "は" + weapon + "で攻撃した！";
		return msg;
	}

	public String recover(String name) {
		String msg = name + "は" + recover_tool + "で回復した！";
		return msg;
	}
}
