package Character;

import org.springframework.stereotype.Service;

import Creature.BattleCreature;

@Service
public class Character extends BattleCreature{

	//固有情報
	private int charaId;	//キャラクターID
	private String job;		//職業

	public Character() {

	}

	public Character(int charaId, String name, String job) {
		this.charaId = charaId;
		this.name = name;
		this.job = job;
	}

	@Override
	public void Parameter(int maxHp, int atk, int def) {
		this.maxHp = maxHp;
		this.hp = maxHp;
		this.atk = atk;
		this.def = def;

		}


	//=============================クリーチャー情報==================================
		//名前
		@Override
		public String getName() {
			return name;
		}
		@Override
		public void setName(String name) {
		this.name = name;
	}

		//体力
		@Override
		public int getHp() {
			return hp;
		}
		@Override
		public void setHp(int hp) {
			this.hp = hp;

		}

		//体力最大値
		@Override
		public int getMaxHp() {
			return maxHp;
		}
		@Override
		public void setMaxHp(int maxHp) {
			this.maxHp = maxHp;
		}


		//攻撃力
		@Override
		public int getAtk() {
			return atk;
		}
		@Override
		public void setAtk(int atk) {
			this.atk = atk;
		}

		//守備力
		@Override
		public int getDef() {
			return def;
		}
		@Override
		public void setDef(int def) {
			this.def = def;
		}

	//=============================クリーチャー情報==================================




	//=============================キャラクター情報==================================
		//キャラクターID
		public int getCharaId() {
			return charaId;
		}

		//職業
		public String getJob() {
			return job;
		}
		public void setJob(String job) {
			this.job = job;
		}

	//=============================キャラクター情報==================================


	//=============================行動==================================
		public String attack(String weapon, String enemyName) {

			String msg = this.name + "は" + weapon + "で" + enemyName + "を攻撃した！";
			return msg;
		}

		public String recover(String recover_tool) {
			String msg = this.name + "は" + recover_tool + "で回復した！";
			return msg;
		}


	}