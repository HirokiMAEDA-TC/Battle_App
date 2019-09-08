package Job;

public class Warrior extends Job {

	public String weapon = "剣";
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
