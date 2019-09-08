package Job;

public class Fighter extends Job{
	public String weapon = "拳";
	public String recover_tool = "やくそう";

	@Override
	public String getWeapon() {
		return weapon;
	}
	@Override
	public String getRecover_tool() {
		return recover_tool;
	}


}
