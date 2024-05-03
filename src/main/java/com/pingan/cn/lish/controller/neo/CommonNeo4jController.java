package com.pingan.cn.lish.controller.neo;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.pingan.cn.common.utils.ResponseUtil;
import com.pingan.cn.lish.controller.vo.CommonNeo4jVo;
import io.swagger.annotations.Api;
import org.neo4j.driver.v1.*;
import org.springframework.web.bind.annotation.*;

@Api(tags = "common-neo4j")
@RestController
@RequestMapping(value = "/api/flask")
@CrossOrigin
public class CommonNeo4jController {

    private static String URI = "bolt://localhost:7687";
    private static String USERNAME = "neo4j";
    private static String PASSWORD = "123";

    private static Config noSSL = Config.build().withEncryptionLevel(Config.EncryptionLevel.NONE).toConfig();

    @PostMapping(value = "/excuteCql")
    public ResponseUtil excuteCql(@RequestBody CommonNeo4jVo neo4jVo) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) ,noSSL);
        Session session = driver.session();
        System.out.println( "执行cql ： " +  neo4jVo.getCql());
        StatementResult result = session.run( neo4jVo.getCql() );

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
            jsonObject.put("s",record.get("s").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonObject.put("p",record.get("p").asMap());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }
    @PostMapping(value = "/excuteCqlWithSingleNodeRes")
    public ResponseUtil excuteCypherWithSingleNodeRes(@RequestBody CommonNeo4jVo neo4jVo) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ),noSSL );
        Session session = driver.session();
        System.out.println( "执行cql ： " +  neo4jVo.getCql());
        StatementResult result = session.run( neo4jVo.getCql() );

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
//           match (n) where ${singleNode_cql} return n, labels(n) as label limit ${maxSize}
            jsonObject.put("n",record.get("n").asMap());
            jsonObject.put("label",!record.get("label").equals(null)?record.get("label").asList():record.get("label"));
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }

    @PostMapping(value = "/excuteCqlWithEdgeRes")
    public ResponseUtil excuteCqlWithRes(@RequestBody CommonNeo4jVo neo4jVo) {
        Driver driver = GraphDatabase.driver( URI, AuthTokens.basic( USERNAME, PASSWORD ) ,noSSL);
        Session session = driver.session();
        System.out.println( "执行cql ： " +  neo4jVo.getCql());
        StatementResult result = session.run( neo4jVo.getCql() );

        JSONArray jsonArray = new JSONArray();
        while ( result.hasNext() )
        {
            JSONObject jsonObject = new JSONObject();
            Record record = result.next();
//            match (f:${type}) -[r]->(t) where ${strf} 1=1 return f, r, t , type(r) as type ,labels(f) as labelF, labels(t) as labelT
            jsonObject.put("f",record.get("f").asMap());
            jsonObject.put("r",record.get("r").asMap());
            jsonObject.put("t",record.get("t").asMap());
            jsonObject.put("type",record.get("type").asString());
            jsonObject.put("labelF",record.get("labelF").asList());
            jsonObject.put("labelT",record.get("labelT").asList());
            jsonArray.add(jsonObject);
        }
        session.close();
        driver.close();
        return ResponseUtil.success(jsonArray);
    }
}
