package com.allen.nest.mongodao;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

public enum ClientManager {
	
	manager;
	
	private static final String DEFAULT_HOST = "127.0.0.1";
	private static final String DEFAULT_DATABASE = "admin";
	private static final int DEFAULT_PORT = 27017;
	private static final int DEFAULT_MAX_CLIENT_NUMBER = 10;
	
	private MongoClientOptions clientOptions;
	private BlockingQueue<MongoClient> clientQueue;
	
	private ClientManager() {
		init(DEFAULT_MAX_CLIENT_NUMBER);
	}
	
	private void init(int maxClientsCount) {
		clientQueue = new LinkedBlockingQueue<MongoClient>(maxClientsCount);
		clientOptions = MongoClientOptions.builder().build();
	}
	
	public MongoClient getClient(String host, int port, String userName, String password) {
		ServerAddress serverAddress = new ServerAddress(host, port);
		MongoCredential credential = MongoCredential.createCredential(userName, DEFAULT_DATABASE, password.toCharArray());
		MongoClient client = new MongoClient(serverAddress, Arrays.asList(credential), clientOptions);
		return client;
	}
	
	public MongoClient getClient(Map<String, Integer> hosts, Map<String, String> credentials) {
		return null;
	}
	
	public MongoClient getClient() {
		ServerAddress serverAddress = new ServerAddress(DEFAULT_HOST, DEFAULT_PORT);
		MongoClient client = new MongoClient(serverAddress, clientOptions);
		return client;
	}
}
