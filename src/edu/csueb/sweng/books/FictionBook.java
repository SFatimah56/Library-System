package edu.csueb.sweng.books;

import java.util.List;

public class FictionBook extends Book{
	private String theme;
	private List<String> characters;
	
	public FictionBook(String theme, List<String> characters) {
		super();
		this.theme = theme;
		this.characters = characters;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public List<String> getCharacters() {
		return characters;
	}

	public void setCharacters(List<String> characters) {
		this.characters = characters;
	}
		
}

