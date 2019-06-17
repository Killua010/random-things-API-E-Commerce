package br.com.randomthings.security;

public class AccessControl {
	
	public static final String[] PUBLIC_MATCHERS = {
			"/h2-console/**"
	};
	
	public static final String[] PUBLIC_MATCHERS_GET = {
			"/categories/**",
			"/products/**",
			"/promotionalCoupons/**",
			"/residenceTypes/**",
			"/states/**",
			"/flags/**",
			"/genders/**",
			"/images/**",
			"/telephonesTypes/**"
	};
	
	public static final String[] PUBLIC_MATCHERS_POST = {
			"/clients/**"
	};
}
