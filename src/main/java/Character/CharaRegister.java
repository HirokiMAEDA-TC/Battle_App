package Character;

import java.util.ArrayList;
import java.util.List;


public class CharaRegister {

	List<Character> charaList = new ArrayList<Character>();

	public List<Character> getCharaList(int charaId, String name, String job) {


		//受け取ったキャラクター情報を入れる
		Character chara = new Character(charaId, name, job);

		//hpの値を20-30で設定
		int maxHp = 20 + 5 - (new java.util.Random().nextInt(11));
		chara.Parameter(maxHp, 15, 10);	//maxHp, atk, defを決定

		//リストに登録
		charaList.add(chara);

			return charaList;
	}

}
