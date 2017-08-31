package com.nabenik.demo.util;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class WordChopper {

	public String[] chopString(String base){
		return base.split("\\|");
	}
}
