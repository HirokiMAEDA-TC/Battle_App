package Creature;

public abstract class BattleCreature {

	//固有情報
	protected String name;	//名前

	//能力パラメーター
	protected int hp;	//体力
	protected int maxHp;//体力最大値
	protected int atk;	//攻撃力
	protected int def;	//守備力

	public abstract void Parameter(int maxHp, int atk, int def);

	//GETTERとSETTER
	public abstract String getName();
	public abstract void setName(String name);

	public abstract int getHp();
	public abstract void setHp(int hp);

	public abstract int getMaxHp();
	public abstract void setMaxHp(int maxHp);

	public abstract int getAtk();
	public abstract void setAtk(int atk);

	public abstract int getDef();
	public abstract void setDef(int def);


}
