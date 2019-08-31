package Job;

public class Warrior extends Job {

	public String weapon = "剣";
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
