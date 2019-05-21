package br.com.randomthings.utils;

import org.springframework.beans.factory.annotation.Value;

public class SystemVariable {

	@Value("${SYSTEMPATH}")
	public static String systemPath;
}
