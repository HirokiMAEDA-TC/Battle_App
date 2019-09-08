package Enemy;

import java.util.ArrayList;
import java.util.List;

public class Encount {

	List<Goblin> enemyList = new ArrayList<Goblin>();

	public List<Goblin> EncountEnemy(int enemyId, String name, char suffix) {

		switch(name) {
		case "ゴブリン":
			Goblin goblin = new Goblin(enemyId, name, suffix);
			enemyList.add(goblin);
			break;
		}


		return enemyList;

	}

}
