package io.github.laufi.heisserdraht.util;

import java.io.Serializable;

public class HighscoreEintrag implements Serializable{
	private float score;
	private String name;
	public HighscoreEintrag(String name, int score){
		this.score = score;
		this.name = name;
	}
	public HighscoreEintrag(float score){
		this.score = score;
	}
	public HighscoreEintrag(){

	}
	public float getScore(){
		return this.score;
	}
	public String getName(){
		if (this.name == null){
			return "Anonymous";
		}
		return this.name;
	}
	public void setScore(int score){
		this.score = score;
	}
	public void setName(String name){
		this.name = name;
	}
}