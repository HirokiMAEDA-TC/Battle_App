package com.example.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Action.Battle;
import Character.CharaRegister;
import Character.Character;
import Enemy.Encount;
import Enemy.Goblin;
import Job.Fighter;
import Job.Warrior;
import Job.Wizard;


@Controller
public class BattleController {

	@Autowired
	HttpSession session;


	//キャラクター人数
	int charaId = 0;

	//キャラクター情報
	List<Character> charaList = new ArrayList<Character>();

	Character chara = new Character();
	CharaRegister charaRegister = new CharaRegister();

	//キャラクターIdとコマンドの紐づける、クラス間受け渡し用ハッシュマップ
	HashMap<Integer, String> CommandListMap = new HashMap<Integer, String>();

	//表示用のハッシュマップ
	HashMap<String, String> NameAndCommandMap =  new HashMap<String,String>();


	//エネミー遭遇
	Encount encountEnemy = new Encount();
	List<Goblin> enemyList = new ArrayList<Goblin>();


	//バトル
	Battle battle = new Battle();


	//メッセージ
	List<String> msgList = new ArrayList<String>();





	//=============================キャラクター作成画面==================================
	@RequestMapping(value="/MakeChara", method=RequestMethod.GET)
	public ModelAndView MakeChara(ModelAndView mav) {

		mav.setViewName("MakeChara");

		return mav;

	}
	//=============================キャラクター作成画面==================================




	//=============================キャラクター登録画面==================================
	@RequestMapping(value="/RegisterChara", method=RequestMethod.POST)
	public ModelAndView RegisterChara(
			@RequestParam(value="name") String name,
			@RequestParam(value="job") String job,
			ModelAndView mav) {

		if(name.equals("") && charaList.isEmpty()) {
			mav.setViewName("MakeChara");
			return mav;
		}

		if(!(name.equals(""))) {

			//4人までキャラクターを作成する
			if(charaId < 4) {
				charaList = charaRegister.getCharaList(charaId, name, job);
				charaId ++;

				//職業に応じて、各職業のクラスをインスタンス化して、セッションに保存
				switch (job) {
					case "戦士":
						Warrior warrior = new Warrior();
						session.setAttribute("Warrior", warrior);
						break;

					case "魔法使い":
						Wizard wizard = new Wizard();
						session.setAttribute("Wizard", wizard);
						break;

					case "武闘家":
						Fighter fighter = new Fighter();
						session.setAttribute("Fighter", fighter);
						break;
				}
			}

			session.setAttribute("CharaList", charaList);
			}

		mav.addObject("charaList", charaList);
		mav.setViewName("RegisterChara");

		return mav;
	}
	//=============================キャラクター登録画面==================================




	//=============================行動画面==================================
	@RequestMapping(value="Action", method=RequestMethod.POST)
	public ModelAndView Action(ModelAndView mav) {

		@SuppressWarnings("unchecked")
		List<Character> charaList = (List<Character>) session.getAttribute("CharaList");

		mav.addObject("charaList", charaList);
		return mav;
	}
	//=============================行動画面==================================




	//=============================「たんさく」時、敵遭遇==================================
	@RequestMapping(value="/EncountEnemy", method=RequestMethod.POST)
	public ModelAndView EncountEnemy(ModelAndView mav) {

		enemyList = encountEnemy.EncountEnemy(0, "ゴブリン", 'A');
		enemyList = encountEnemy.EncountEnemy(1, "ゴブリン", 'B');

		mav.addObject("enemyList", enemyList);
		session.setAttribute("enemyList", enemyList);
		return mav;
	}
	//=============================「たんさく」時、敵遭遇==================================




	//=============================コマンド登録画面==================================
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/SetCommand", method= {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView SetCommand(ModelAndView mav) {

		List<Character> charaList = (List<Character>) session.getAttribute("CharaList");
		List<Goblin> enemyList = (List<Goblin>) session.getAttribute("enemyList");

		msgList.clear();

		mav.addObject("charaList", charaList);
		mav.addObject("enemyList", enemyList);

		mav.setViewName("SetCommand");



		return mav;
	}
	//=============================コマンド登録画面==================================




	//=============================キャラクター別コマンド表示==================================
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Command", method=RequestMethod.POST)
	public ModelAndView Command(
			@RequestParam(value="charaId") int charaId,
			@RequestParam(value="command") String command,
			ModelAndView mav) {

		//キャラクター情報取得
		List<Character> charaList = (List<Character>) session.getAttribute("CharaList");


		//コマンド入力したキャラクターの職業を取得
		chara = charaList.get(charaId);

		CommandListMap.put(chara.getCharaId(), command);
		session.setAttribute("commandList", CommandListMap);

		NameAndCommandMap.put(chara.getName(), command);


		//敵情報取得
		List<Goblin> enemyList = (List<Goblin>) session.getAttribute("enemyList");


		mav.addObject("charaList", charaList);
		mav.addObject("enemyList", enemyList);

		mav.addObject("commandList", NameAndCommandMap);

		mav.setViewName("Command");
		return mav;
	}
	//=============================キャラクター別コマンド表示==================================



	//=============================コマンドの結果を表示=============================
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/Result", method=RequestMethod.POST)
	public ModelAndView Result(ModelAndView mav) {

		HashMap<Integer, String> CommandListMap = (HashMap<Integer, String>) session.getAttribute("commandList");
		List<Goblin> enemyList = (List<Goblin>) session.getAttribute("enemyList");


		for(Integer charaId : CommandListMap.keySet()) {

			//キャラクターの情報(名前・職業)取得
			chara = charaList.get(charaId);

			String job = chara.getJob();
			String weapon ="";
			String recover_tool ="";


			//敵情報を取得
			int enemyNum = enemyList.size();
			int enemyId = new java.util.Random().nextInt(enemyNum);
			Goblin enemy = enemyList.get(enemyId);


			//キャラクターのコマンドを取得
			String command = CommandListMap.get(charaId);


			//セッションに保存したインスタンスを使用する。
			String msg = "";

			//msg = battle.BattleProcess(chara, enemy, command);

			switch (job) {
				case "戦士":
					Warrior warrior = (Warrior) session.getAttribute("Warrior");

					weapon = warrior.getWeapon();
					recover_tool = warrior.getRecover_tool();

					break;

				case "魔法使い":
					Wizard wizard = (Wizard) session.getAttribute("Wizard");

					weapon = wizard.getWeapon();
					recover_tool = wizard.getRecover_tool();

					break;

				case "武闘家":
					Fighter fighter = (Fighter) session.getAttribute("Fighter");

					weapon = fighter.getWeapon();
					recover_tool = fighter.getRecover_tool();

					break;
			}

			//コマンド別の行動処理
			switch(command) {
				case "たたかう":
					msg = chara.attack(weapon, enemy.getName()+enemy.getSuffix());
					enemy.setHp(chara.getAtk() - enemy.getDef());

					break;

				case "かいふく":
					msg = chara.recover(recover_tool);
					break;

			}

			msgList.add(msg);

			if(enemy.getHp() <= 0) {
				enemyList.remove(enemyId);
				msg = enemy.getName() + enemy.getSuffix() + "をたおした！";
				msgList.add(msg);
			}

		}



		List<Character> charaList = (List<Character>) session.getAttribute("CharaList");

		mav.addObject("charaList", charaList);
		mav.addObject("enemyList", enemyList);


		mav.addObject("msgList", msgList);


		mav.setViewName("Result");
		return mav;
	}


	//=============================コマンドの結果を表示=============================







	//=============================戦闘後の処理を実施=============================
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/BattleCalculation", method=RequestMethod.POST)
		public String BattleCalculation(Model model) {

			//メッセージ一覧を削除
			msgList.clear();


			List<Goblin> enemyList = (List<Goblin>) session.getAttribute("enemyList");


			if(!(enemyList.isEmpty())) {
				return "redirect:SetCommand";
			} else {
				return "redirect:MakeChara";
			}

		}



	//=============================戦闘後の処理を実施=============================

}