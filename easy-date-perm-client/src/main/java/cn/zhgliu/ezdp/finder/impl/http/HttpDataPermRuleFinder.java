package cn.zhgliu.ezdp.finder.impl.http;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.zhgliu.ezdp.consts.ParamNames;
import cn.zhgliu.ezdp.finder.DataPermRuleFinder;
import cn.zhgliu.ezdp.model.DataPermissionItem;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ZhgLiu
 */
public class HttpDataPermRuleFinder implements DataPermRuleFinder {

    Logger log = LoggerFactory.getLogger(HttpDataPermRuleFinder.class);

    private String dataPermServer;

    public HttpDataPermRuleFinder(String dataPermServer) {
        this.dataPermServer = dataPermServer;
    }

    public static final String FIND_RULE_URL = "/api/permissions";

    @Override
    public List<List<DataPermissionItem>> findRoleGroupedRules(String subSystem, String userId, String operationIdentifier) {
        List<List<DataPermissionItem>> ret = new LinkedList<>();
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(1000)
                    .setConnectTimeout(1000)
                    .setSocketTimeout(1000)
                    .build();
            URIBuilder uriBuilder = new URIBuilder(dataPermServer + FIND_RULE_URL);
            uriBuilder.addParameter(ParamNames.SUBSYSTEM, subSystem);
            uriBuilder.addParameter(ParamNames.OPERATION_IDENTIFIER, operationIdentifier);
            uriBuilder.addParameter(ParamNames.USER_ID, userId);
            HttpGet get = new HttpGet(uriBuilder.build());
            get.setConfig(config);
            CloseableHttpResponse response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                String httpResult = EntityUtils.toString(entity, "utf-8");
                log.debug(httpResult);
                List<List<DataPermissionItem>> collect = stringToList(httpResult);
                return collect;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static final List<List<DataPermissionItem>> stringToList(String httpResult) {
        JSONArray permissionData = JSONUtil.parseArray(httpResult);

        List<List<DataPermissionItem>> collect = permissionData.stream().map(innerList -> {
            return ((JSONArray) innerList).stream().map(item -> {
                return JSONUtil.toBean((JSONObject) item, DataPermissionItem.class);
            }).collect(Collectors.toList());
        }).collect(Collectors.toList());
        return collect;
    }


}
