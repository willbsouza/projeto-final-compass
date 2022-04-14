package com.compass.projetodoacao.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests().antMatchers("/doadores").permitAll().antMatchers("/doadores/*").permitAll()
		.antMatchers("/categorias").permitAll().antMatchers("/categorias/*").permitAll()
		.antMatchers("/doacoes").permitAll().antMatchers("/doacoes/*").permitAll()
		.antMatchers("/donatarios").permitAll().antMatchers("/donatarios/*").permitAll()
		.antMatchers("/enderecos").permitAll().antMatchers("/enderecos/*").permitAll()
		.antMatchers("/itens").permitAll().antMatchers("/itens/*").permitAll()
		.antMatchers("/ongs").permitAll().antMatchers("/ongs/*").permitAll()
		.antMatchers("/solicitacoes").permitAll().antMatchers("/solicitacoes/*")
		.permitAll().antMatchers("/telefones").permitAll().antMatchers("/telefones/*").permitAll()
		.anyRequest().authenticated()
		.and().formLogin();
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {

	}
}
