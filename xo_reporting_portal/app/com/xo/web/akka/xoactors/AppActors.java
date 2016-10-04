package com.xo.web.akka.xoactors;

import akka.actor.ActorSystem;

import com.typesafe.config.ConfigFactory;

public class AppActors {

	public static final String ACTOR_USER_LOCALSYNCACTOR = "user/localsyncactor";
	public static final ActorSystem XOPORTAL_ACTOR_SYSTEM = ActorSystem.create("xoportalsystem", ConfigFactory.load().getConfig("xoportal"));
	
}
