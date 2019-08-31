package Job;

public abstract class Job {
	String job;
	String weapon;
	String recover_tool;

	public abstract String attack(String name);
	public abstract String recover(String name);
}
