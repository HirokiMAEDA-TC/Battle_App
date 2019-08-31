package com.example.demo;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import Job.Fighter;
import Job.Warrior;
import Job.Wizard;


@Controller
public class BattleController {

	@Autowired
		HttpSession session;

	@RequestMapping(value="/MakeChara", method=RequestMethod.GET)
	public ModelAndView MakeChara(ModelAndView mav) {


		mav.setViewName("MakeChara");
		return mav;

	}


	@RequestMapping(value="/Command", method=RequestMethod.POST)
	public ModelAndView Command(
			@RequestParam(value="name") String name,
			@RequestParam(value="job") String job,
			ModelAndView mav) {

		//String name = setName(name);

		mav.addObject("name", name);
		mav.addObject("job", job);

		//職業に応じて、各職業のクラスをインスタンス化して、セッションに保存
		switch (job) {
			case "戦士":
				Warrior warrior = new Warrior();
				session.setAttribute("Job", warrior);
				break;

			case "魔法使い":
				Wizard wizard = new Wizard();
				session.setAttribute("Job", wizard);
				break;

			case "武闘家":
				Fighter fighter = new Fighter();
				session.setAttribute("Job", fighter);
				break;
		}

		mav.setViewName("Command");
		return mav;
	}


	@RequestMapping(value="/Result", method=RequestMethod.POST)
	public ModelAndView Result(
			@RequestParam(value="job") String job,
			@RequestParam(value="name") String name,
			@RequestParam(value="command") String command,
			ModelAndView mav) {

		/*
		//職業に応じて、攻撃方法を指定する
		String weapon = "";
		switch(job) {
			case "戦士":
				weapon = "剣";
				break;

			case "魔法使い":
				weapon ="魔法";
					break;
		}

		mav.addObject("weapon", weapon);
		mav.addObject("name", name);
		*/

		//セッションに保存したインスタンスを使用する。
		String msg = "";

		switch (job) {
			case "戦士":
				Warrior warrior = (Warrior) session.getAttribute("Job");

				if(command.equals("たたかう")) {
					msg = warrior.attack(name);
				} else if(command.equals("かいふく")) {
					msg = warrior.recover(name);
				}

				break;

			case "魔法使い":
				Wizard wizard = (Wizard) session.getAttribute("Job");

				if(command.equals("たたかう")) {
					msg = wizard.attack(name);
				} else if(command.equals("かいふく")) {
					msg = wizard.recover(name);
				}

				break;

			case "武闘家":
				Fighter fighter = (Fighter) session.getAttribute("Job");

				if(command.equals("たたかう")) {
					msg = fighter.attack(name);
				} else if(command.equals("かいふく")) {
					msg = fighter.recover(name);
				}

				break;
		}



		mav.addObject("msg", msg);

		mav.setViewName("Result");
		return mav;
	}


}
