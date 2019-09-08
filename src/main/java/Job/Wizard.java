package Job;

public class Wizard extends Job {

	public String weapon = "まほう";
	public String recover_tool = "まほう";

	@Override
	public String getWeapon() {
		return weapon;
	}
	@Override
	public String getRecover_tool() {
		return recover_tool;
	}


}
