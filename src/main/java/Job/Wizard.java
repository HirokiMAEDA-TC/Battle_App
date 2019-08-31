package Job;

public class Wizard extends Job {

	public String weapon = "まほう";
	String recover_tool = "まほう";

	public String attack(String name) {
		String msg = name + "は" + weapon + "で攻撃した！";
		return msg;
	}

	public String recover(String name) {
		String msg = name + "は" + recover_tool + "で回復した！";
		return msg;
	}

}
