package Enemy;

import org.springframework.stereotype.Service;

import Creature.BattleCreature;

@Service
public class Enemy extends BattleCreature{

	//固有情報
	private int enemyId;
	private char suffix;

	public Enemy(int enemyId, String name, char suffix) {
		this.enemyId = enemyId;
		this.name = name;
		this.suffix = suffix;

	}

	@Override
	public void Parameter(int maxHp, int atk, int def) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;

	}


	//=============================クリーチャー情報==================================
	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getHp() {
		return hp;
	}
	@Override
	public void setHp(int hp) {
		this.hp -= hp;

	}

	@Override
	public int getMaxHp() {
		return maxHp;
	}
	@Override
	public void setMaxHp(int maxHp) {
		this.maxHp = maxHp;
	}

	@Override
	public int getAtk() {
		return atk;
	}
	@Override
	public void setAtk(int atk) {
		this.atk = atk;
	}

	@Override
	public int getDef() {
		return def;
	}
	@Override
	public void setDef(int def) {
		this.def = def;
	}



	//=============================エネミー情報==================================
	public int getEnemyId() {
		return enemyId;
	}

	public char getSuffix() {
		return suffix;
	}









}
