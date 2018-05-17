package com.estone.erp.common.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

/***
 * mongo抽象配置
 * 
 * @author Kevin
 *
 */
public abstract class AbstractMongoConfig {
    private String host;

    private int port;

    private String database;

    private String username;

    private String password;

    public MongoClient mongoClientFactory() {
        List<MongoCredential> credentials = new ArrayList<MongoCredential>(1);
        credentials.add(MongoCredential.createCredential(username, database, password.toCharArray()));
        MongoClient client = new MongoClient(new ServerAddress(host, port), credentials);

        return client;
    }

    public MongoDbFactory mongoDbFactory() throws Exception {
        return new SimpleMongoDbFactory(mongoClientFactory(), database);
    }

    /**
     * mongo转换器，不生成_class字段
     * 
     * @param factory factory
     * @param appContext appContext
     * @return mongo转换器
     */
    public MappingMongoConverter getConverter(MongoDbFactory factory, ApplicationContext appContext) {
        MongoMappingContext mongoMappingContext = new MongoMappingContext();
        mongoMappingContext.setApplicationContext(appContext);
        MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(factory),
                mongoMappingContext);
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return converter;
    }

    abstract public MongoTemplate getMongoTemplate() throws Exception;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
